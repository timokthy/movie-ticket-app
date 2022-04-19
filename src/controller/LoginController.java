package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jdbc.DatabaseConnector;
import model.RegisteredUser;
import model.User;
import view.LoginGUI;


public class LoginController implements ActionListener{
	
	ArrayList<RegisteredUser> Users;	// List of RegisteredsSers pulled from database using UserDBC.getRegUsers() App.java
	LoginGUI loginGUI;
	RegisteredUser user;
	DatabaseConnector dbc;
	
	public LoginController(DatabaseConnector dbc){
		this.dbc = dbc;
		loginGUI = new LoginGUI();	
		loginGUI.loginActionListener(this);
		loginGUI.guestActionListener(this);
	}
	
	
	
	/**
	 * @param username Account username
	 * @param password Account password
	 * @return true if username and password match credentials from database
	 */
	public boolean validateUser(String username, String password) {
	
		for(int i = 0; i < Users.size(); i++) {
			user = Users.get(i);
			if(username.equals(user.getUserEmail()))
				if(password.equals(user.getPassword()))
					return true;
		}
		return false;
	}
	

	public void setUsers(ArrayList<RegisteredUser> users) {
		Users = users;
	}

	/**
	 * Method listens for two buttons: login or login as guest.
	 * Includes required error handling.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// If the user clicks 'login', open login window
		if(e.getSource() == loginGUI.getLoginButton()) {
			boolean loggedIn = false;
			
			String username = loginGUI.getUserNameInput().getText();
			String password = loginGUI.getPasswordInput().getText();
			
			// If the entered user credentials are valid, move to the main menu as a registered user.
			if(validateUser(username, password)) {
				loggedIn = true;
				loginGUI.setVisible(false);
				MainMenuController registeredUserMenu = new MainMenuController(user, dbc); 
			}
			// If the entered user credentials are invalid, display a message.
			else {
				JOptionPane.showMessageDialog(loginGUI, "Incorrect username or password. Please try again.");
			}
			
		}
		
		// If the user clicks 'continue as guest', move to the main menu as a guest.
		if(e.getSource() == loginGUI.getGuestLoginButton()) {
			int id = dbc.getUserDBC().getId() + 1; // Create a new ID for the guest
			User newUser = new User();
			newUser.setUserID(id);
			
			loginGUI.setVisible(false);
			MainMenuController registeredUserMenu = new MainMenuController(newUser, dbc); 
		}
	}
	
}
