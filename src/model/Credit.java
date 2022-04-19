package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import jdbc.DatabaseConnector;

public class Credit {
	
	private User user; 
	private int userID;
	private double creditAmount; 
	private Timestamp expirationDate;
	private DatabaseConnector dbc;
	
	
	/** 
	 * @param ticket that was canceled
	 * @param user that will receive credit  
	 */
	public Credit(Ticket ticket, User user) {
		this.user = user;
		setCreditAmount(ticket);
		setExpirationDateADD();
		// TODO write to DB
		dbc = new DatabaseConnector();
		dbc.getCreditDBC().addCredit(this);
	}

	/** Constructor used for reading in from DB
	 * @param userId user of user receiving credit 
	 * @param amount value of credit 
	 * @param expirationDate date when credit expires 
	 */
	public Credit(int userId, double amount, Timestamp expirationDate) {
		this.setUserID(userId);
		creditAmount = amount;
		setExpirationDate(expirationDate);
	}
	
	/** Set the credit amount 
	 * @param ticket that is being canceled 
	 */
	private void setCreditAmount(Ticket ticket) {
		if(user.isRU()) { // registered users are refunded in full
			creditAmount = ticket.getPrice();
		}
		else {
			creditAmount = ticket.getPrice() * 0.85;
		}
	}

	public void setExpirationDate(Timestamp date) {
		expirationDate = date;
	}
	
	/**
	 * Method used by database to set the expiration date
	 */
	public void setExpirationDateADD() {
		
		java.util.Date date = new java.util.Date(); // Get the current time and date.
		Timestamp timestamp = new Timestamp(date.getTime()); // Convert current time and date to Timestamp.
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		cal.add(Calendar.DATE, 365); // Add 1 year to the current time and date.
		
		setExpirationDate(new Timestamp(cal.getTime().getTime())); // Set expiration date to 1 year after current time and date.
	}
	
	/** toString
	 * @return information regarding credit 
	 */
	public String issueCredit() {
		String message = "The credited amout of $"+ String.format("%.2f", creditAmount)
						+" will expire on "+expirationDate;
		return message;
	}
	
	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

 
	/** Method used by database connector 
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}
	
	/** Method used by database connector 
	 * @param userID of User issued credit
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
