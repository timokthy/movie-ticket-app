package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The form used by the user to make payments
 * 
 * @author Timothy Mok
 *
 */
@SuppressWarnings("serial")
public class PaymentForm extends JFrame {
	
	private JLabel emailLabel = new JLabel("Email:");
    private JTextField email = new JTextField(20);
    
    private JLabel cardLabel = new JLabel("Credit/Debit Card Number:");
    private JTextField cardNumber = new JTextField(16);
    
    private JButton submitButton = new JButton("Submit");
    private JButton backButton = new JButton("Back");
    
    /**
     * Constructs the payment form GUI
     */
    public PaymentForm() {
    	Container contentPane = getContentPane();
        
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(email);

        JPanel cardPanel = new JPanel();
        cardPanel.add(cardLabel);
        cardPanel.add(cardNumber);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        
        contentPane.add(emailPanel);
        contentPane.add(cardPanel);
        contentPane.add(buttonPanel);
        
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        pack();
        this.setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Get the email field 
     * @return the email text field
     */
    public JTextField getEmail() {
        return email;
    }
    
    /**
     * Gets the credit card field
     * @return the card number field
     */
    public JTextField getCardNumber() {
        return cardNumber;
    }
    
    /**
     * Adds an action listener to the submit button
     * @param submitButtonListener
     */
    public void addSubmitListener(ActionListener submitButtonListener) {
        submitButton.addActionListener(submitButtonListener);
    }
    
    /**
     * Adds an action listener to the back button
     * @param backButtonListener
     */
    public void addBackListener(ActionListener backButtonListener) {
        backButton.addActionListener(backButtonListener);
    }
    
    /**
     * Gets the back button
     * @return the back button
     */
    public JButton getBackButton() {
    	return backButton;
    }
    
    /**
     * Gets the submit button
     * @return the submit button
     */
    public JButton getSubmitButton() {
    	return submitButton;
    }
    
    /**
     * Displays a message to the user
     * @param message
     */
    public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

}
