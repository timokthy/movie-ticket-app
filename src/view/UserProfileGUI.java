package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class UserProfileGUI extends JFrame{

	private JButton buyTickets = new JButton("Buy Tickets");
	private JButton viewTickets = new JButton("View my Tickets");
	private JButton manageMembership = new JButton("Manage Membership");

	public UserProfileGUI() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setSize(300, 175);
		setLayout(new GridBagLayout());
		setTitle("Main Menu"); 
		this.setLayout(layout);
		c.insets = new Insets(10,0,0,10);
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 50; 
		this.add(buyTickets, c);
		c.insets = new Insets(10,0,0,10);
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 50; 
		this.add(viewTickets, c);
		c.insets = new Insets(10,0,0,10);
		c.gridx = 1;
		c.gridy = 3;
		this.add(manageMembership, c);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void buyTicketsActionListener(ActionListener LoginButton) {
		this.buyTickets.addActionListener(LoginButton);
	}
	
	public void viewTicketsActionListener(ActionListener LoginButton) {
		this.viewTickets.addActionListener(LoginButton);
	}
	
	public void membershipActionListener(ActionListener LoginButton) {
		this.manageMembership.addActionListener(LoginButton);
	}
	
	public JButton getBuyTicketsButton() {
		return buyTickets;
	}
	
	public JButton getViewTicketsButton() {
		return viewTickets;
	}
	
	public JButton getMembershipButton() {
		return manageMembership;
	}

	public static void main(String[] args) {
		UserProfileGUI login = new UserProfileGUI(); 
		// if have time go back to fix button size
	}
	
}
