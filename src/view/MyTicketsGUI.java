package view;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;
//import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class MyTicketsGUI extends JFrame{
	private JLabel cancelTicket = new JLabel("Cancel Ticket:");
	private JButton submit = new JButton("Submit");
	private JButton backToMenu = new JButton("Back to Menu");
	private JTextField ticketID = new JTextField(10);
	private JTextArea allMyTickets = new JTextArea();
	private JScrollPane ticktes = new JScrollPane(allMyTickets);

	public MyTicketsGUI() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setSize(350, 235);
		
		setLayout(new GridBagLayout());
		setTitle("My Tickets"); 
		ticketID.setText("124"); // place holder for input field 
		this.setLayout(layout);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(8,0,0,10);
		c.gridx = 0;
		c.gridy = 1;
		this.add(cancelTicket, c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(ticketID, c);
		
		c.insets = new Insets(5,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		this.add(submit, c);
		
		c.insets = new Insets(5,0,0,0);
		c.gridx = 1;
		c.gridy = 3;
		this.add(backToMenu, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,10);
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0;
		c.ipady = 30;
		this.add(ticktes, c);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	public void addSubmitActionListener(ActionListener actionListener) {
		this.submit.addActionListener(actionListener);
	}
	
	public void addBackToMenuActionListener(ActionListener actionListener) {
		this.backToMenu.addActionListener(actionListener);
	}
		
	public JTextField getTicket() {
		return ticketID;
	}
	
	public JTextArea getAllTickets() {
		return allMyTickets;
	}
	
	public JButton getSubmitButton() {
		return submit;
	}
	
	public JButton getBackToMenuButton() {
		return backToMenu;
	}
	
	public void displayMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}



