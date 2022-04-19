package controller;

import java.util.ArrayList;

import jdbc.DatabaseConnector;
import model.Credit;
import model.Ticket;
import model.User;
import view.MyTicketsGUI;
import view.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 

public class CancelTicketsController implements ActionListener{
	
	private ArrayList<Ticket> tickets;	
	private MyTicketsGUI registrationForm;
	private UserProfileGUI mainMenu;
	private User user; 
	private Ticket ticketBeingCanceled; // ticket to be canceled 
	private DatabaseConnector dbc; 
	
	/**
	 * @param user, user logged into system
	 * @param menu, main menu GUI
	 * @param dbc, database connection
	 */
	public CancelTicketsController(User user, UserProfileGUI menu, DatabaseConnector dbc){
		this.dbc = dbc;
		this.user = user; 
		mainMenu = menu;
		mainMenu.setVisible(false);
		
		tickets = new ArrayList<Ticket>();
		registrationForm = new MyTicketsGUI(); 
		setTickets(); 
	
		registrationForm.addSubmitActionListener(this);
		registrationForm.addBackToMenuActionListener(this);
	}
	
	
	/**
	 *  Method to read tickets from database into tickets ArrayList
	 */
	public void setTickets() {
		tickets = dbc.getTicketDBC().getUsersTickets(user.getUserID());
		System.out.println(tickets.size());
		updateUserView();	
	}
	
	/**
	 * Method writes all of the users bought tickets to text area
	 */
	private void updateUserView() {
		String allTickets = "";
		for (Ticket ticket: tickets) {
			allTickets += "Ticket #: " + ticket.getTicketNumber() 
			+ " Movie: " + ticket.getMovieName()
			+" Date: "+ ticket.getDate()
			+ " Seat: " + ticket.getSeatNumber()
			+ "\n"; 
		}
		registrationForm.getAllTickets().setText(allTickets);	
	}
	
	/**
	 * @param ticket, ticket belonging to User
	 * Ticket is added to array list of all tickets that belong to user
	 */
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	/**
	 * @param ticketToCheck, ticket to be validated 
	 * @return true if ticket belongs to user, otherwise false
	 */
	public boolean validTicket(int ticketToCheck) {
		for (Ticket ticket: tickets) {
			if(ticket.getTicketNumber() ==ticketToCheck)
				return true;
		}
		return false;
	}
	
	
	/**
	 * @param ticket, ticket that cancellation request is for
	 * Users ticket is canceled, Registered Users are issued a credit at 100% ticket cost
	 * regular Users pay a 15% administration fee. Users are alerted via GUI of successful cancellation. 
	 */
	public void cancelTicket(Ticket ticket) {
		
		int idx = 0;
		for (Ticket t: tickets) {
			if(t.getTicketNumber() == ticket.getTicketNumber()) {
				
				if (user.isRU()) { // Ticket holder is a RU
					Credit credit = new Credit(t,user);
					registrationForm.displayMessage(credit.issueCredit());
					tickets.remove(idx);
					t.ticketCanceled(user.getUserID());	
					break;
				}else { // Ticket holder is not a RU
					if(issueCredit(t)) { // Ticket is canceled before 72hrs of show time - valid credit 
						Credit credit = new Credit(t,user);
						registrationForm.displayMessage(credit.issueCredit());
						registrationForm.displayMessage("Credit Issued");
						tickets.remove(idx);
						t.ticketCanceled(user.getUserID());	
						break;
					}
					else { // Ticket is canceled within 72hrs of show time - not valid for credit 
						registrationForm.displayMessage("Credit NOT issued, cancellation with 72hrs of showing");
						tickets.remove(idx);
						t.ticketCanceled(user.getUserID());	
						break;
					}	
				}
			}
			idx++;			
		}
	}
	
	/**
	 * @param ticket, ticket to be cancelled 
	 * Setter method 
	 */
	private void setTicketBeingCanceled(int ticket ) {
		for (Ticket tkt: tickets) 
			if(tkt.getTicketNumber() == ticket)
				ticketBeingCanceled = tkt;	
	}
	
	/**
	 * @param ticket, ticket to be canceled 
	 * @return true if cancellation request is 72hrs ahead of showing, false otherwise  
	 */
	public boolean issueCredit(Ticket ticket) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime showDate = LocalDateTime.parse(ticket.getDate(), formatter);
		LocalDateTime now = LocalDateTime.now();  
			
		 // request date is before date of the movie showing  
		   if(now.isBefore(showDate)) { 
			   // movie showing is 72hrs ahead of current time, credit issued 
			   if(showDate.minusHours(72).isBefore(now)) { 
					   return false;
			   }else { // no refund 
				   return true;
			   }
		   }			
		return false;
	}	

	/**
	 * Method to listen to input from GUI
	 * Read user input and initialize cancellation process.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registrationForm.getSubmitButton()) { // Submit button is clicked 
			try {
			int tempTicketID = Integer.parseInt(registrationForm.getTicket().getText()); // Try to read integer, display error otherwise 

				if(validTicket(tempTicketID)) { 
					setTicketBeingCanceled(tempTicketID); 
					cancelTicket(ticketBeingCanceled);
					updateUserView();
				}else {
					registrationForm.displayMessage("Ticket "+tempTicketID+" does not belong to you");
				}
			}catch(NumberFormatException err) {
				String input = registrationForm.getTicket().getText();
				registrationForm.displayMessage(input+" is not an integer");
			}
			
		}
		// Back to main menu button pressed 
		else if (e.getSource() == registrationForm.getBackToMenuButton()) {
			registrationForm.setVisible(false); 
			mainMenu.setVisible(true);
		}
	}
	
}
