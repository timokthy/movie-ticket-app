package app;

import controller.LoginController;
import jdbc.DatabaseConnector;

public class App {

	public static void main(String[] args) {
		DatabaseConnector dbc = new DatabaseConnector();
		LoginController login = new LoginController(dbc);
		
		login.setUsers(dbc.getUserDBC().getRegUsers());

	}

}
