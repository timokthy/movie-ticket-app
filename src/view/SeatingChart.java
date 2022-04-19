package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The seating chart for the auditorium
 * @author Timothy Mok
 *
 */
@SuppressWarnings("serial")
public class SeatingChart extends JFrame {
	
	// defaults to all empty seats
	private String takenSeats = "0000000000000000000000000";
	private JTextArea seats = new JTextArea();
	private JScrollPane seatingChart = new JScrollPane(seats);
	private JButton backButton = new JButton("Back");
	
	/**
	 * Constructor of the seating chart
	 */
	public SeatingChart() {
		Container contentPane = getContentPane();
		
		seats.setText(makeSeatingChart());
		contentPane.add(seatingChart);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(backButton);
		contentPane.add(buttonPanel);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        pack();
        this.setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Sets a seat as taken with an X
	 * @param takenSeats
	 */
	public void setTakenSeats(String takenSeats) {
		this.takenSeats = takenSeats;
		seats.setText(makeSeatingChart());
	}
	
	/**
	 * Makes the seating chart outline
	 * @return the seating chart
	 */
	private String makeSeatingChart() {
		int counter = 0;
		String seatingDisplay = "";
		seatingDisplay += "\n" + addHyphens();
		for (int row = 0; row < 5; row++) {
			seatingDisplay += "\n" + addSpaces();
			seatingDisplay += "\n     ";
			
			for (int col = 0; col < 5; col++) {
				if(takenSeats.charAt(counter) == '1') {
					seatingDisplay += "|   " + "X" + "   ";
				}
				else {
					if(counter < 9) {
						seatingDisplay += "|   " + (counter + 1) + "   ";
					}
					else {
						seatingDisplay += "|  " + (counter + 1) + "  ";
					}
					
				}
				counter += 1;
			}
			
			seatingDisplay += "|";
			seatingDisplay += "\n" + addSpaces();
			seatingDisplay += "\n" + addHyphens();
		}
		seatingDisplay += "\n";
		return seatingDisplay;
	}

	/**
	 * Helper function to make the seating chart
	 * @return a line of hyphens to for the outline
	 */
	private String addHyphens() {
		String temp = "";
		temp += "     ";
		for (int j = 0; j < 5; j++) {
			temp += "+-----";
		}
		temp += "+";
		return temp;
	}

	/**
	 * Helper function to add spaces to the seating chart
	 * @return spaces for the seating chart
	 */
	private String addSpaces() {
		String temp = "";
		temp += "     ";
		for (int j = 0; j < 5; j++) {
			temp += "|        ";
		}
		temp += "|     ";
		return temp;
	}
	
	/**
	 * Adds an action listener to the back button
	 * @param backActionListener
	 */
	public void addBackActionListener(ActionListener backActionListener) {
		this.backButton.addActionListener(backActionListener);
	}
	
	/**
	 * Gets the back button
	 * @return the back button
	 */
	public JButton getBackButton() {
    	return backButton;
    }
	
	/**
	 * Displays an error message to the user
	 * @param errorMessage
	 */
	public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
