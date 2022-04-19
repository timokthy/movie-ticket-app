package model;

import java.util.ArrayList;

public class User {
	
	private String userEmail;
	private ArrayList<Ticket> userTickets;
	private boolean isRU;
	private int userID;
	
	public User(){
		setRU(false);
	}
	
	User(String userEmail){
		this.setUserEmail(userEmail);
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public boolean isRU() {
		return isRU;
	}

	public void setRU(boolean isRU) {
		this.isRU = isRU;
	}

}
