package jdbc;

import java.sql.*;
import java.util.ArrayList;

import model.ShowTime;
import model.Ticket;

/**
 * The datebase connector for the Ticket objects
 * @author Timothy Mok, Tobi Odufeso
 *
 */
public class TicketDBC {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement prepStmt;
	
	/**
	 * Constructs the ticket database connector
	 * @param conn
	 */
	public TicketDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Gets all the tickets in the database
	 * @return a list of all the tickets
	 */
	public ArrayList<Ticket> getAllTickets() {	
		try {
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			String query = "SELECT * FROM TICKET";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int ticketId = rs.getInt("TicketNum");
				String movieName = rs.getString("MovieName");
				int ticketHolder = rs.getInt("TicketHolder");
				int seatNum = rs.getInt("SeatNum");
				String movieDate = rs.getString("MovieDate"); // look out for this
				String ticketStatus = rs.getString("TicketStatus");
				double price = rs.getDouble("Price");
				Ticket theShowTime = new Ticket(ticketId, movieName ,ticketHolder, seatNum, movieDate, ticketStatus, price);
				tickets.add(theShowTime);
			}
			return tickets;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets a single users tickets
	 * @param userID 
	 * @return a list of the users tickets in the database
	 */
	public ArrayList<Ticket> getUsersTickets(int userID) {	
		try {
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			String query = "SELECT * FROM TICKET WHERE TicketHolder="+userID;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int ticketId = rs.getInt("TicketNum");
				String movieName = rs.getString("MovieName");
				int ticketHolder = rs.getInt("TicketHolder");
				int seatNum = rs.getInt("SeatNum");
				String movieDate = rs.getString("MovieDate"); 
				String ticketStatus = rs.getString("TicketStatus");
				double price = rs.getDouble("Price");// look out for this
				Ticket theShowTime = new Ticket(ticketId, movieName ,ticketHolder, seatNum, movieDate, ticketStatus, price);
				tickets.add(theShowTime);
			}
			return tickets;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Updates a ticket to available if one is cancelled
	 * @param userID
	 */
	public void setTicketToAvailable(int userID) {
		try {
			String query = "UPDATE TICKET SET TicketStatus=? WHERE TicketHolder=?";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setString(1, "Available");
			pStat.setInt(2, userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * Add a ticket to the database
	 * @param ticket
	 */
	public void addTicket(Ticket ticket) {
		try {
			String query = "INSERT INTO TICKET (TicketNum, MovieName, TicketHolder, SeatNum, MovieDate, TicketStatus, Price) values (?,?,?,?,?,?,?)";
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, ticket.getTicketNumber());
			prepStmt.setString(2, ticket.getMovieName());
			prepStmt.setInt(3,  ticket.getTicketHolder());
			prepStmt.setInt(4, ticket.getSeatNumber());
			prepStmt.setString(5, ticket.getDate());
			prepStmt.setString(6, ticket.getStatus());
			prepStmt.setDouble(7, ticket.getPrice());
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the max ticket ID so each ticket has a unique ID
	 * @return the max ticket ID currently in the database
	 */
	public int getMaxTicketId() {	
		try {
			String query = "SELECT MAX(T.TicketNum) FROM TICKET AS T ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int latestID = 0;
			while(rs.next()) {
				latestID = rs.getInt("MAX(T.TicketNum)");
			}
			return latestID;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
