package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector implements DatabaseCredentials{
	
	private Connection conn;
	private UserDBC userDBC;
	private ShowTimeDBC showTimeDBC;
	private TicketDBC ticketDBC;
	private PaymentDBC paymentDBC;
	private CreditDBC creditDBC;
	
	public DatabaseConnector() {
		initializeConnection();
		userDBC = new UserDBC(conn);
		showTimeDBC = new ShowTimeDBC(conn);
		ticketDBC = new TicketDBC(conn);
		paymentDBC = new PaymentDBC(conn);
		creditDBC = new CreditDBC(conn);

	}
	

	/**
	 * Initializes connection with MySQL database using credentials from DatabaseCredentials
	 */
	public void initializeConnection() {
		try {
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UserDBC getUserDBC() {
		return userDBC;
	}
	
	public ShowTimeDBC getShowTimeDBC() {
		return showTimeDBC;
	}

	public TicketDBC getTicketDBC() {
		return ticketDBC;
	}
	
	public PaymentDBC getPaymentDBC() {
		return paymentDBC;
	}

	public CreditDBC getCreditDBC() {
		return creditDBC;
	}
}
