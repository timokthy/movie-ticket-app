package jdbc;

import java.sql.*;
import java.util.ArrayList;

import model.ShowTime;

/**
 * The ShowTime database connector
 * @author Timothy Mok
 *
 */
public class ShowTimeDBC {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement prepStmt;
	
	/**
	 * Constructor for the ShowTime database connector
	 * @param conn
	 */
	public ShowTimeDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Gets all the showtimes in the database
	 * @return a list of all the showtimes
	 */
	public ArrayList<ShowTime> getShowTimes() {	
		try {
			ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
			String query = "SELECT * FROM SHOWTIME";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int scheduleId = rs.getInt("ScheduleId");
				String title = rs.getString("Title");
				String dayShowing = rs.getString("DayShowing");
				String timeslot = rs.getString("Timeslot");
				String auditorium = rs.getString("Auditorium");
				String seats = rs.getString("Seats");
				ShowTime theShowTime = new ShowTime(scheduleId, title, dayShowing, timeslot, auditorium, seats);
				showTimes.add(theShowTime);
			}
			return showTimes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Adds a showtime to the database
	 * @param theShowTime
	 */
	public void addShowTime(ShowTime theShowTime) {
		try {
			String query = "INSERT INTO SHOWTIME (ScheduleId, Title, DayShowing, Timeslot, Auditorium, Seats) values (?,?,?,?,?,?)";
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, theShowTime.getScheduleId());
			prepStmt.setString(2, theShowTime.getTitle());
			prepStmt.setString(3, theShowTime.getDayShowing());
			prepStmt.setString(4, theShowTime.getTimeslot());
			prepStmt.setString(5, theShowTime.getAuditorium());
			prepStmt.setString(6, theShowTime.getSeats());
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a showtime if new seats get taken
	 * @param theShowTime being updated
	 */
	public void updateShowTime(ShowTime theShowTime) {
		try {
			String query = "UPDATE SHOWTIME AS S SET ScheduleId=?, Title=?, DayShowing=?, Timeslot=?, Auditorium=?, Seats=?"
					+ " WHERE S.ScheduleId=?";
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, theShowTime.getScheduleId());
			prepStmt.setString(2, theShowTime.getTitle());
			prepStmt.setString(3, theShowTime.getDayShowing());
			prepStmt.setString(4, theShowTime.getTimeslot());
			prepStmt.setString(5, theShowTime.getAuditorium());
			prepStmt.setString(6, theShowTime.getSeats());
			prepStmt.setInt(7, theShowTime.getScheduleId());
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
