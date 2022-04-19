package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginGUI extends JFrame {
	 
	private JLabel userEmail = new JLabel("Email Address:");
	private JLabel userPassword = new JLabel("Password:");
	private JButton login = new JButton("Login");
	private JButton guestLogin = new JButton("Continue as Guest");
	private JTextField userName = new JTextField(10);
	private JTextField password = new JTextField(10);
	private JTextField loginMessage = new JTextField(30);

	public LoginGUI() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setSize(400, 200);
		
		setLayout(new GridBagLayout());
		setTitle("Movie Theater Login"); 
		this.setLayout(layout);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(8,0,0,10);
		c.gridx = 0;
		c.gridy = 0;
		this.add(userEmail, c);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(userPassword, c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(userName, c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(password, c);
		
		c.insets = new Insets(25,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 50; 
		this.add(login, c);
		
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1;
		c.gridy = 3;
		this.add(guestLogin, c);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void loginActionListener(ActionListener LoginButton) {
		this.login.addActionListener(LoginButton);
	}
	
	public void guestActionListener(ActionListener LoginButton) {
		this.guestLogin.addActionListener(LoginButton);
	}
	
	public JTextField getUserNameInput() {
		return userName;
	}
	public JTextField getPasswordInput() {
		return password;
	}
	public JTextField getLoginMsg() {
		return loginMessage;
	}
	public JButton getLoginButton() {
		return login;
	}
	public JButton getGuestLoginButton() {
		return guestLogin;
	}
	public void displayMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}


