package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The order form used to buy tickets
 * @author Timothy Mok
 *
 */
@SuppressWarnings("serial")
public class OrderForm extends JFrame {
	
	private JLabel dateLabel = new JLabel("Please select the date you wish to find showtimes for (M-D-Y):");
	
	private JComboBox<String> monthSelection;
	private JComboBox<String> daySelection;
	private JComboBox<String> yearSelection;
	private JButton searchButton = new JButton("Find Movies");
	
	private JTextArea allShowtimes = new JTextArea("Available showtimes will be displayed here after a date is selected.");
	
	private JLabel movieLabel = new JLabel("Please enter index of the movie and time you want to watch:");
	private JTextField movieSelection = new JTextField(2);
	
	private JButton seatButton = new JButton("View Seating Chart");
	
    private JLabel seatLabel = new JLabel("Please select a seat number from the seating chart:");
    private JTextField seatNumber = new JTextField(2);
    
    private JButton payButton = new JButton("Proceed to Payment");
    private JButton menuButton = new JButton("Back to Menu");

    /**
     * Constructor for the order form
     */
    public OrderForm(){
    	
    	String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    	monthSelection = new JComboBox<String>(months);
    	
    	String[] years = {"2021", "2022"};
    	yearSelection = new JComboBox<String>(years);
    	
    	String[] days = new String[31];
    	for(int i = 0; i < 31; i++) {
    		days[i] = "" + (i + 1);
    	}
    	daySelection = new JComboBox<String>(days);

    	
        Container contentPane = getContentPane();
        
        JPanel dateLabelPanel = new JPanel();
        dateLabelPanel.add(dateLabel);

        JPanel datePanel = new JPanel();
        datePanel.add(monthSelection);
        datePanel.add(daySelection);
        datePanel.add(yearSelection);
        datePanel.add(searchButton);

        JScrollPane movies = new JScrollPane(allShowtimes);
        movies.setPreferredSize(new Dimension(50, 200));

        JPanel movieSelectionPanel = new JPanel();
        movieSelectionPanel.add(movieLabel);
        movieSelectionPanel.add(movieSelection);

        JPanel viewSeatsPanel = new JPanel();
        viewSeatsPanel.add(seatButton);
        
        JPanel selectSeatsPanel = new JPanel();
        selectSeatsPanel.add(seatLabel);
        selectSeatsPanel.add(seatNumber);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(payButton);
        buttonPanel.add(menuButton);

        contentPane.add(dateLabelPanel);
        contentPane.add(datePanel);
        contentPane.add(movies);
        contentPane.add(movieSelectionPanel);
        contentPane.add(viewSeatsPanel);
        contentPane.add(selectSeatsPanel);
        contentPane.add(buttonPanel);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        pack();
        this.setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Gets the showtime text area
     * @return the showtime text area
     */
    public JTextArea getAllShowtimes() {
        return allShowtimes;
    }
    
    /**
     * Gets the movie selection text field
     * @return the movie selection text field
     */
    public JTextField getMovieSelection() {
        return movieSelection;
    }
    
    /**
     * Gets the user input selected date from the drop down menus
     * @return the date in Y-M-D format
     */
    public String getDate() {
    	String year = yearSelection.getSelectedItem().toString();
    	String month = "";
    	String day = "";
    	int monthInt = Integer.parseInt(monthSelection.getSelectedItem().toString());
    	int dayInt = Integer.parseInt(daySelection.getSelectedItem().toString());
    	if(monthInt < 10) {
    		month += ("0" + monthInt);
    	}
    	else {
    		month += monthInt;
    	}
    	if(dayInt < 10) {
    		day += ("0" + dayInt);
    	}
    	else {
    		day += dayInt;
    	}
    	return year + "-" + month + "-" + day;
    }
    
    /**
     * Adds an action listener to the find movies button
     * @param searchButtonListener
     */
    public void addSearchListener(ActionListener searchButtonListener) {
        searchButton.addActionListener(searchButtonListener);
    }
    
    /**
     * Adds an action listener for the seating chart button
     * @param seatButtonListener
     */
    public void addSeatListener(ActionListener seatButtonListener) {
        seatButton.addActionListener(seatButtonListener);
    }

    /**
     * Adds an action listener to the proceed to payment button
     * @param payButtonListener
     */
    public void addPayListener(ActionListener payButtonListener) {
        payButton.addActionListener(payButtonListener);
    }
    
    /**
     * Adds an action listener to the back to menu button
     * @param menuActionListener
     */
    public void addBackToMenuActionListener(ActionListener menuActionListener) {
		this.menuButton.addActionListener(menuActionListener);
	}

    /**
     * Displays a message to the user
     * @param message
     */
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /** 
     * Gets the find movies button
     * @return the find movies button
     */
    public JButton getSearchButton() {
    	return searchButton;
    }
    
    /**
     * Gets the view seating chart button
     * @return the seating chart button
     */
    public JButton getSeatsButton() {
    	return seatButton;
    }
    
    /**
     * Gets the proceed to payment button
     * @return the payment button
     */
    public JButton getPayButton() {
    	return payButton;
    }

    /**
     * Gets the back to menu button
     * @return the menu button
     */
    public JButton getBackToMenuButton() {
    	return menuButton;
    }

    /**
     * Displays an error message to the user if an invalid input is entered
     * @param errorMessage
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

	/**
	 * Get the seat number text field
	 * @return the seat number text field
	 */
	public JTextField getSeatNumber() {
		return seatNumber;
	}

}
