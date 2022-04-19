package jdbc;

import java.sql.*;
import java.util.ArrayList;

import model.RegisteredUser;
import model.User;

public class UserDBC {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement prepStmt;
	
	public UserDBC(Connection conn) {
		this.conn = conn;
	}
	
	/** Uses SELECT query in MySQL to pull RegisteredUsers from the database
	 * @return ArrayList of RegisteredUsers
	 */
	public ArrayList<RegisteredUser> getRegUsers() {
		try {
			ArrayList<RegisteredUser> RegUsers = new ArrayList<RegisteredUser>();
			String query = "SELECT * from REGISTERED";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				// Pull attributes of RegisteredUser from database
				int id = rs.getInt("UserID");
				String name = rs.getString("Username");
				String password = rs.getString("UserPassword");
				String email = rs.getString("Email");
				String address = rs.getString("Address");
				String cardNum = rs.getString("CardNum");
				
				RegisteredUser RegUser = new RegisteredUser(id, name, password, email, address, cardNum); // Create RegisteredUser object using information from database
				RegUsers.add(RegUser); // Add RegisteredUser object to ArrayList
			}
			return RegUsers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Uses INSERT query in MySQL to add a RegisteredUser to the database
	 * @param regUser RegisteredUser to be added to the database
	 */
	public void addRegUser(RegisteredUser regUser) {
		try {
			String query = "INSERT INTO REGISTERED (UserId, Username, UserPassword, Email, Address, CardNum, DateOfReg) values (?,?,?,?,?,?,?)";
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, regUser.getUserID());
			prepStmt.setString(2, regUser.getUsername());
			prepStmt.setString(3, regUser.getPassword());
			prepStmt.setString(4, regUser.getUserEmail());
			prepStmt.setString(5, regUser.getAddress());
			prepStmt.setString(6, regUser.getCardNum());
			prepStmt.setTimestamp(7, regUser.getRegDate());
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Uses INSERT query in MySQL to add a guest User to the database
	 * @param user User to be added to the database
	 */
	public void addGuestUser(User user) {
		try {
			String query = "INSERT INTO GUEST (UserId, Email) values (?,?)";
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, user.getUserID());
			prepStmt.setString(2, user.getUserEmail());
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * @return integer, returns the next available user ID
	 */
	public int getId() {	
		try {
			String query = "SELECT MAX(G.UserId) FROM GUEST AS G ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int latestID = 0;
			while(rs.next()) {
				latestID = rs.getInt("MAX(G.UserId)");
			}
			return latestID;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
