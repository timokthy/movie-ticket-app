package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jdbc.DatabaseConnector;
import jdbc.UserDBC;
import model.RegisteredUser;
import model.User;
import view.RegisteredUserFormGUI;
import view.UserProfileGUI;

public class MembershipController implements ActionListener{

	private UserProfileGUI mainMenu;
	RegisteredUserFormGUI registrationForm;
	private DatabaseConnector dbc;
	
	/**
	 * @param user to become registered user
	 * @param menu main menu GUI
	 * @param dbc database connector 
	 */
	public MembershipController(User user, UserProfileGUI menu, DatabaseConnector dbc){
		this.dbc = dbc;
	
		// If the user is a Registered User, display the expiration date of their membership.
		if(user.isRU()) {
			RegisteredUser regUser = (RegisteredUser) user;
			Timestamp expDate = regUser.getRegDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(expDate);
			cal.add(Calendar.DATE, 365); // Add 1 year users register date to get the expiration date.
			Timestamp newDate = new Timestamp(cal.getTime().getTime());
			registrationForm = new RegisteredUserFormGUI("Your membership expires on:\n"+ newDate.toString());
		}else {
			registrationForm = new RegisteredUserFormGUI("WELCOME!");
		}

		mainMenu = menu;
		registrationForm.submitActionListener(this);
		registrationForm.mainMenuActionListener(this);	
	}
	
	/**
	 * Method added new Registered User to Registered User table in database, POST request
	 */
	public void addRegisteredUser() {
		
		// Gets text from GUI input fields to use for user registration
		String userEmail = registrationForm.getEmailInput().getText();
		String cardDetails = registrationForm.getCardInput().getText();
		String username = registrationForm.getFullName().getText();
		String password = registrationForm.getPassWordInput().getText();
		String address= registrationForm.getAddressInput().getText();
		
		RegisteredUser newUser = new RegisteredUser(username, password, userEmail, address, cardDetails); // Creates new registered user
		dbc.getUserDBC().addRegUser(newUser); // Calls JDBC method to add user to the database
	}


	public void setDbc(DatabaseConnector dbc) {
		this.dbc = dbc;
	}

	/**
	 * Method listens for submit and back to home button 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registrationForm.getSumbitButton()) {
			this.addRegisteredUser();
			registrationForm.setVisible(false);
			mainMenu.setVisible(true);
		}
		else if(e.getSource() == registrationForm.getMainMenuButton()) {
			registrationForm.setVisible(false);
			mainMenu.setVisible(true);
		}
		
	}
	

}
