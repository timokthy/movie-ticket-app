package model;

import jdbc.DatabaseConnector;

/**
 * Ticket class that is stored in the database
 * @author Group 10
 *
 */
public class Ticket {
	
	private int ticketNumber;
	private String movieName;
	private int ticketHolder; 
	private int seatNumber;
	private String date;
	private ShowTime showtime;
	private String status;
	private double price; 
	private DatabaseConnector dbc;

	/**
	 * Constructor for a ticket object
	 * @param ticketId
	 * @param movieName
	 * @param ticketHolder
	 * @param seatNum
	 * @param date is the date of the showtime
	 * @param ticketStatus whether it is active or cancelled
	 * @param price
	 */
	public Ticket(int ticketId, String movieName, int ticketHolder, int seatNum, String date, String ticketStatus, double price) {
		this.ticketNumber = ticketId;
		this.movieName = movieName;
		this.ticketHolder = ticketHolder;
		this.seatNumber = seatNum;
		this.date = date;
		this.status = ticketStatus;
		this.price = price;
		dbc = new DatabaseConnector();
	}

	/**
	 * Sets a ticket to available
	 * @param userID the user submitting the request
	 */
	public void ticketCanceled(int userID) {
		dbc.getTicketDBC().setTicketToAvailable(userID);
		setTicketHolder(0);
		setStatus("Available");
	}

	/**
	 * Gets the ticket number
	 * @return the ticket nummber
	 */
	public int getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * Sets the ticket number
	 * @param ticketNumber
	 */
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * Gets the movie name
	 * @return the movie name
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * Sets the movie name
	 * @param movieName
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * Gets the ticket holder
	 * @return the ticket holder id
	 */
	public int getTicketHolder() {
		return ticketHolder;
	}

	/**
	 * Sets the ticket holder
	 * @param ticketHolder
	 */
	public void setTicketHolder(int ticketHolder) {
		this.ticketHolder = ticketHolder;
	}

	/**
	 * Gets the associated seat number
	 * @return the seat number
	 */
	public int getSeatNumber() {
		return seatNumber;
	}

	/**
	 * Sets the seat number
	 * @param seatNumber
	 */
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * Gets the price of the ticket
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the ticket
	 * @param price of the ticket
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the date of the showtime
	 * @return the date of the showtime
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the showtime
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the status of the ticket
	 * @return status of ticket
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * Sets the status of the ticket
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the associated showtime
	 * @return the showtime
	 */
	public ShowTime getShowtime() {
		return showtime;
	}

	/**
	 * Sets the showtime
	 * @param showtime
	 */
	public void setShowtime(ShowTime showtime) {
		this.showtime = showtime;
	}

}
