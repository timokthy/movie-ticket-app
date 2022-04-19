package jdbc;

import java.sql.*;
import java.util.ArrayList;

import model.Payment;
import model.ShowTime;

/**
 * The database connector for the Payment class
 * @author Timothy Mok
 *
 */
public class PaymentDBC {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement prepStmt;
	
	/**
	 * Constructor for the payment database connector
	 * @param conn
	 */
	public PaymentDBC(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Adds a payment object into the database
	 * @param payment new payment object
	 */
	public void addPayment(Payment payment) {
		try {
			String query = "INSERT INTO PAYMENT (UserId, Total, PaymentDate, CardInfo) values (?,?,?,?)";
			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, payment.getUserId());
			prepStmt.setDouble(2, payment.getTotal());
			prepStmt.setString(3,  payment.getPaymentDate());
			prepStmt.setString(4, payment.getCardInfo());
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
