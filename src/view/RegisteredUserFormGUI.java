package view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class RegisteredUserFormGUI extends JFrame{
	
	private JLabel registerMessage;
	private JLabel userEmail = new JLabel("Email Address:");
	
	private JLabel fullName = new JLabel("Full Name:");
	private JLabel userAddress = new JLabel("Address:");
	private JLabel userPassword = new JLabel("Password:");
	
	private JLabel card = new JLabel("debit/credit card:");
	private JButton submit = new JButton("Submit");
	private JButton mainMenu = new JButton("Main Menu");
	private JTextField emailAdress = new JTextField(10);
	
	private JTextField inputedName = new JTextField(10);
	private JTextField address = new JTextField(10);
	private JTextField username = new JTextField(10);
	private JTextField password = new JTextField(10);
	
	private JTextField cardDetails = new JTextField(10);
	
	public RegisteredUserFormGUI(String message) {
		registerMessage = new JLabel(message);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setSize(450, 300);
		setLayout(new GridBagLayout());
		setTitle("Registered User Form"); 
		this.setLayout(layout);
		
		c.insets = new Insets(15,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 50; 
		this.add(registerMessage, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.insets = new Insets(25,0,0,10);
		c.gridx = 0;
		c.gridy = 1;
		this.add(userEmail, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(emailAdress, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(fullName, c);
		c.gridx = 1;
		c.gridy = 2;
		this.add(inputedName, c);
		
		c.insets = new Insets(5,0,0,10);
		c.gridx = 0;
		c.gridy = 3;
		this.add(userAddress, c);
		c.gridx = 1;
		c.gridy = 3;
		this.add(address, c);

		c.gridx = 0;
		c.gridy = 4;
		this.add(userPassword, c);
		c.gridx = 1;
		c.gridy = 4;
		this.add(password, c);
		
		
		c.gridx = 0;
		c.gridy = 5;
		this.add(card, c);
		c.gridx = 1;
		c.gridy = 5;
		this.add(cardDetails, c);

		c.gridx = 1;
		c.gridy = 6;
		c.ipadx = 50; 
		this.add(submit, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.ipadx = 50; 
		this.add(mainMenu, c);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void submitActionListener(ActionListener LoginButton) {
		this.submit.addActionListener(LoginButton);
	}
	
	public void mainMenuActionListener(ActionListener mainMenuButton) {
		this.mainMenu.addActionListener(mainMenuButton);
	}
	
	public JTextField getEmailInput() {
		return emailAdress;
	}
	public JTextField getCardInput() {
		return cardDetails;
	}
	public JTextField getAddressInput() {
		return address;
	}
	public JTextField getPassWordInput() {
		return password;
	}
	public JButton getSumbitButton() {
		return submit;
	}
	public JButton getMainMenuButton() {
		return mainMenu;
	}
	
//	public JTextField getUsername() {
//		return username;
//	}
	
	public JTextField getFullName() {
		return inputedName;
	}
	
	public static void main(String[] args) {
		RegisteredUserFormGUI login = new RegisteredUserFormGUI("Membership Expires in XX days"); 
}

}
