package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jdbc.DatabaseConnector;
import model.User;
import view.*;


public class MainMenuController implements ActionListener{
	private UserProfileGUI mainMenu;
	private OrderForm buyTickets;
	private RegisteredUserFormGUI manageMembership;
	private User user;
	private DatabaseConnector dbc;

	/** Constructor creates GUI's to be used for: buying tickets and another for managing membership 
	 * @param user, User logged into application
	 * @param dbc, database connection 
	 */
	public MainMenuController(User user, DatabaseConnector dbc) {
		this.dbc = dbc;
		this.user = user;
		mainMenu = new UserProfileGUI();
		
		buyTickets = new OrderForm();
		buyTickets.setVisible(false);

		manageMembership = new RegisteredUserFormGUI("need to know user");		
		manageMembership.setVisible(false);
		
		mainMenu.buyTicketsActionListener(this);
		mainMenu.viewTicketsActionListener(this);
		mainMenu.membershipActionListener(this);
	}

	/**
	 * Method listens for the three buttons on the main menu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// User wants to buy tickets
		if (e.getSource() == mainMenu.getBuyTicketsButton()) {
			BuyTicketsController buyTickets = new BuyTicketsController(user, mainMenu, dbc);
			mainMenu.setVisible(false);
		}
		// User wants to cancel/view their tickets 
		else if (e.getSource() == mainMenu.getViewTicketsButton()) {
			CancelTicketsController cancelTickets = new CancelTicketsController(user, mainMenu, dbc); 
			mainMenu.setVisible(false);
		}
		// User wants to renew/become a new registered user
		else if (e.getSource() == mainMenu.getMembershipButton()) {
			MembershipController membershipForm = new MembershipController(user, mainMenu, dbc);
			mainMenu.setVisible(false);
		}		
	}

}
