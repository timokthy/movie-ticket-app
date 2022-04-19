package model;

import java.time.LocalDateTime;

/**
 * The Payment Object that is stored in the database. Also calculates tax and credits if applicable
 * 
 * @author Timothy Mok
 *
 */
public class Payment {
	
	private int userId;
	private double total;
	private double amountPaid;
	private String paymentDate;
	private String cardInfo;
	private double creditUsed;
	
	/**
	 * Constructor of the payment object 
	 * @param userId the ID of the user 
	 * @param total total amount required to pay
	 * @param cardInfo credit card info of the user
	 */
	public Payment(int userId, double total, String cardInfo) {
		this.setUserId(userId);
		calculateTax(total);
		this.amountPaid = 0;
		setPaymentDate();
		this.setCardInfo(cardInfo);
		this.creditUsed = 0;
	}
	
	/**
	 * Adds tax to the ticket price
	 * @param initialAmount of the ticket
	 */
	public void calculateTax(double initialAmount) {
		setTotal(initialAmount * 1.05);
	}
	
	/**
	 * Applies any credits the user has
	 * @param credit the amount of credit the user has
	 */
	public void applyCredit(double credit) {
		if(credit > total) {
			creditUsed = credit - total;
		}
		else {
			creditUsed = credit;
		}
	}
	
	/**
	 * Makes the payment
	 */
	public void makePayment() {
		this.setAmountPaid(total - creditUsed);
	}
	
	/**
	 * Sends a receipt that is displayed to the screen
	 * @return the receipt
	 */
	public String getReceipt() {
		String receipt = "Thank you for your payment!";
		receipt += ("\n\nTotal: $" + String.format("%.2f", total));
		receipt += ("\nCredit used: $" + String.format("%.2f", creditUsed));
		receipt += ("\nAmount paid: $" + String.format("%.2f", amountPaid));
		receipt += ("\nPaid by card: " + cardInfo);
		return receipt;		
	}
	
	/**
	 * Confirms if the payment was accepted
	 * @return true if payment accepted, false if rejected
	 */
	public boolean confirmPayment() {
		if((creditUsed + amountPaid) == total) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Gets the total
	 * @return the total amount required to pay
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Sets the total price
	 * @param total 
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Gets the payment date
	 * @return the date the payment was made
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * Sets the date the payment was made
	 */
	public void setPaymentDate() {
		LocalDateTime theDate = LocalDateTime.now();
		paymentDate = theDate.toString();
	}

	/**
	 * Gets the credit card info
	 * @return credit card info
	 */
	public String getCardInfo() {
		return cardInfo;
	}

	/**
	 * Sets the credit card info
	 * @param cardInfo
	 */
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	/**
	 * Gets the user id
	 * @return user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/** 
	 * Gets the amount paid
	 * @return the amount paid
	 */
	public double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Sets the amount paid
	 * @param amountPaid
	 */
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Gets the amount of credit used
	 * @return the amound of credit used
	 */
	public double getCreditUsed() {
		return creditUsed;
	}

	/**
	 * Sets the amount of credit used
	 * @param creditUsed
	 */
	public void setCreditUsed(double creditUsed) {
		this.creditUsed = creditUsed;
	}
	

}
