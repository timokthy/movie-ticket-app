package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.*;
import jdbc.*;
import model.Payment;
import model.ShowTime;
import model.Ticket;
import model.User;

/**
 * The BuyTicketsController handles the process for buying a ticket.
 * It adds new Ticket and Payment objects to the database.
 * 
 * @author Timothy Mok
 *
 */
public class BuyTicketsController  implements ActionListener{
	
	private DatabaseConnector dbc;
	private OrderForm orderForm;
	private SeatingChart seatingChart;
	private PaymentForm paymentForm;
	private UserProfileGUI mainMenu;
	private User theUser;
	private ArrayList<ShowTime> requestedShowTimes;
	int counter;
	int requestedMovieNumber, requestedSeatNumber;

	/**
	 * Constructor for the BuyTicketsController
	 * 
	 * @param user the user accessing the program
	 * @param menu the main menu view
	 * @param dbc the database connector
	 */
	public BuyTicketsController(User user, UserProfileGUI menu, DatabaseConnector dbc) {
		this.dbc = dbc;
		this.theUser = user;
		orderForm = new OrderForm();
		seatingChart = new SeatingChart();
		paymentForm = new PaymentForm();
		mainMenu = menu;
		
		orderForm.addBackToMenuActionListener(this);
		orderForm.addSearchListener(this);
		orderForm.addSeatListener(this);
		orderForm.addPayListener(this);
		
		seatingChart.addBackActionListener(this);
		
		paymentForm.addBackListener(this);
		paymentForm.addSubmitListener(this);
		
		mainMenu.setVisible(false);
		orderForm.setVisible(true); 
	}
	
	/**
	 * Listens to the different buttons on the controller and opens different view based on what is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Guides the user back to the main menu from the order form
		if(e.getSource() == orderForm.getBackToMenuButton()) {
			orderForm.setVisible(false);
			mainMenu.setVisible(true);
		}
		
		// Displays movie information into the movies display field
		else if(e.getSource() == orderForm.getSearchButton()) {
			String requestedDate = orderForm.getDate();
			requestedShowTimes = new ArrayList<ShowTime>();
			
			// get showtimes from database and fill array
			ArrayList<ShowTime> showtimes = new ArrayList<ShowTime>();
			
			showtimes = dbc.getShowTimeDBC().getShowTimes();
			
			counter = 1;
			String date = "";
			String title = "";
			String time = "";
			String requestedShowTimesString = "";
			// loop through the showtimes found and see which ones are on the requested date
			for(int i = 0; i < showtimes.size(); i++) {
				date = showtimes.get(i).getTimeslot().substring(0, 10);
				if(date.equals(requestedDate)) {
					title = showtimes.get(i).getTitle();
					time = showtimes.get(i).getTimeslot().substring(11);
					requestedShowTimesString += (counter + ": " + title + " at " + time + "\n");
					counter += 1;
					requestedShowTimes.add(showtimes.get(i));
				}
			}
			// display a message if no showtimes found on the requested date
			if(counter == 1) {
				requestedShowTimesString = "No available showtimes available on this day.";
				orderForm.getAllShowtimes().setText(requestedShowTimesString);
				orderForm.setVisible(false);
				orderForm.setVisible(true);
			}
			// set the display with showtimes found
			else {
				orderForm.getAllShowtimes().setText(requestedShowTimesString);
				orderForm.setVisible(false);
				orderForm.setVisible(true);
			}
		}
		
		// opens up the seating chart with X marking taken seats
		else if (e.getSource() == orderForm.getSeatsButton()) {
			int movieNumber;
			try {
				movieNumber = getMovieNumber();	
				if(movieNumber >= counter || movieNumber <= 0) {
					throw(new NumberFormatException());
				}
				else {
					String takenSeats = requestedShowTimes.get(movieNumber - 1).getSeats();
					seatingChart.setTakenSeats(takenSeats);
					orderForm.setVisible(false);
					seatingChart.setVisible(true);
				}
			}catch(NumberFormatException ex){
				orderForm.displayErrorMessage("Please enter a valid movie number to proceed.");
			}
			
		}	
		
		// opens up the payment form for the user to enter in their email and password
		else if (e.getSource() == orderForm.getPayButton()) {
			// ensure the movie and seat selection are valid
			try {
				requestedMovieNumber = getMovieNumber();	
				if(requestedMovieNumber >= counter || requestedMovieNumber <= 0) {
					throw(new NumberFormatException());
				}
			}catch(NumberFormatException ex){
				orderForm.displayErrorMessage("Please enter a valid movie number to proceed.");
			}
			try {
				requestedSeatNumber = getSeatNumber();	
				if(requestedSeatNumber > 25 || requestedSeatNumber <= 0) {
					throw(new NumberFormatException());
				}
				if(checkSeat(requestedMovieNumber, requestedSeatNumber)) {
					throw(new NumberFormatException());
				}
				orderForm.setVisible(false);
				paymentForm.setVisible(true);
			}catch(NumberFormatException ex){
				orderForm.displayErrorMessage("Please enter a valid seat number to proceed.");
			}
		}	
		
		// goes back to the order form from the payment form
		else if (e.getSource() == paymentForm.getBackButton()) {
			paymentForm.setVisible(false);
			orderForm.setVisible(true);
		}	
		
		// submits the user entered payment information and saves the ticket and payment to the database
		else if (e.getSource() == paymentForm.getSubmitButton()) {
			Payment newPayment = new Payment(theUser.getUserID(), 18.25, paymentForm.getCardNumber().getText());
			
			newPayment.makePayment();
			
			String receipt = "";
			if(newPayment.confirmPayment()) {
				int ticketId = dbc.getTicketDBC().getMaxTicketId() + 1;
				String movieName = requestedShowTimes.get(requestedMovieNumber - 1).getTitle();
				int ticketHolder = theUser.getUserID();
				String movieDate = requestedShowTimes.get(requestedMovieNumber - 1).getTimeslot();
				Ticket newTicket = new Ticket(ticketId, movieName, ticketHolder, requestedSeatNumber, movieDate, "Active", 18.25);
				
				StringBuffer updatedSeats = new StringBuffer(requestedShowTimes.get(requestedMovieNumber - 1).getSeats());
				updatedSeats.setCharAt(requestedSeatNumber - 1, '1');
				requestedShowTimes.get(requestedMovieNumber - 1).setSeats(updatedSeats.toString());
				
				dbc.getTicketDBC().addTicket(newTicket);
				dbc.getPaymentDBC().addPayment(newPayment);
				dbc.getShowTimeDBC().updateShowTime(requestedShowTimes.get(requestedMovieNumber - 1));
				
				receipt = newPayment.getReceipt();
			}
			paymentForm.displayMessage(receipt);
			
			paymentForm.setVisible(false);
			mainMenu.setVisible(true);
		}	
		
		// goes from the seating chart back to the order form
		else if (e.getSource() == seatingChart.getBackButton()) {
			seatingChart.setVisible(false);
			orderForm.setVisible(true);
		}	
	}
	
	/**
	 * Gets the user input movie number
	 * @return int of the movie number entered
	 */
	public int getMovieNumber() {
		int movieNumber = 0;
		try {
			movieNumber = Integer.parseInt(orderForm.getMovieSelection().getText());
		}catch(NumberFormatException ex){}
		return movieNumber;
	}
	
	/**
	 * Get the user input seat number
	 * @return int of the seat number entered
	 */
	public int getSeatNumber() {
		int seatNumber = 0;
		try {
			seatNumber = Integer.parseInt(orderForm.getSeatNumber().getText());
		}catch(NumberFormatException ex){}
		return seatNumber;
	}
	
	/**
	 * Checks if the entered seat number is taken 
	 * @param movieNumber the requested showtime
	 * @param seatNumber the requested seat
	 * @return true of the seat is taken, false if the seat is available
	 */
	public boolean checkSeat(int movieNumber, int seatNumber) {
		if(requestedShowTimes.get(movieNumber - 1).getSeats().charAt(seatNumber - 1) == '1') {
			return true;
		}
		else {
			return false;
		}
	}
	
}
