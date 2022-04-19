package model;

import java.sql.Timestamp;

public class RegisteredUser extends User{
	
	private String username;
	private String password;
	private String address;
	private String cardNum;
	private Timestamp regDate;
	
	public RegisteredUser(String username, String password, String email, String address, String cardNum) {
		this.setUsername(username);
		this.setPassword(password);
		this.setUserEmail(email);
		this.setAddress(address);
		this.setCardNum(cardNum);
		this.setRU(true);
		this.setRegDate(new Timestamp(System.currentTimeMillis()));
	}
	
	public RegisteredUser(int userID, String username, String password, String email, String address, String cardNum) {
		this.setUserID(userID);
		this.setUsername(username);
		this.setPassword(password);
		this.setUserEmail(email);
		this.setAddress(address);
		this.setCardNum(cardNum);
		this.setRU(true);
		this.setRegDate(new Timestamp(System.currentTimeMillis()));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}
