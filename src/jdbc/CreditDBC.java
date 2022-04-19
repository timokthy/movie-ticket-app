package jdbc;

import java.sql.*;
import java.util.ArrayList;

import model.Credit;
import model.ShowTime;
import model.Ticket;

public class CreditDBC {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement prepStmt;
	
	public CreditDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @return all credits stored in database 
	 */
	public ArrayList<Credit> getCredits() {	
		try {
			ArrayList<Credit> credits = new ArrayList<Credit>();
			String query = "SELECT * FROM CREDIT";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userId = rs.getInt("UserId");
				double amount = rs.getDouble("CreditAmount");
				Timestamp expirationDate = rs.getTimestamp("ExpirationDate");
				
				Credit credit = new Credit(userId, amount, expirationDate);
				credits.add(credit);
			}
			return credits;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param userID, find all credits associated to userID
	 * @return ArrayList of all credits belonging to specified user
	 */
	public ArrayList<Credit> getUsersCredits(int userID) {	
		try {
			ArrayList<Credit> credits = new ArrayList<Credit>();
			String query = "SELECT * FROM CREDIT WHERE UserId="+userID;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int userId = rs.getInt("UserId");
				double amount = rs.getDouble("CreditAmount");
				Timestamp expirationDate = rs.getTimestamp("ExpirationDate");
				
				Credit credit = new Credit(userId, amount, expirationDate);
				credits.add(credit);
			}
			return credits;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * @param credit to be added to the database 
	 */
	public void addCredit(Credit credit) {
		try {
			String query = "INSERT INTO CREDIT (UserId, CreditAmount, ExpirationDate) values (?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, credit.getUserID());
			pStat.setDouble(2, credit.getCreditAmount());
			pStat.setTimestamp(3, credit.getExpirationDate());
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
