package pedalPower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class startPage { // add instructions on how to play?
	static player[] pl;
	static String[] playerNames = new String[4];

	public static void instructions() {
		JFrame frame = new JFrame("Instructions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null); // Centers the frame on the screen

		// Create a panel with GridBagLayout
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Multi-line text in JLabel (HTML allows line breaks)
		JLabel intro = new JLabel("<html><div style='text-align: center;'>"
				+ "Welcome to Pedal Power! Your goal is to light up the entire board, a representation of Makers Valley, Johannesburg, to make the city a safer, more environmentally friendly place.\r\n"
				+ "<br><br>How to play: <br><br>-	Enter your name (players 1 to 4).\r\n"
				+ "<br>-	Click start!\r\n"
				+ "<br>-	A show info button is available throughout the game to check prices.\r\n"
				+ "<br>-	On your turn, you can move, build cycle lanes, or claim docking station reward.\r\n"
				+ "<br>-	On moving if your new square is from an unclaimed section, you may attempt to claim it.\r\n"
				+ "<br>-	You must roll a 3 or below to claim.\r\n"
				+ "<br>-	Cycle lanes can be built on owned squares from anywhere on the board on a player’s turn.\r\n"
				+ "<br>-	Once a section is completed, it unlocks a reward. Claiming this reward gives a bonus but skips your move for that turn. You cannot claim and perform any other actions in a turn.\r\n"
				+ "<br>-	Action and Event cards may pop up when landing in your own section. These must be resolved <br>on that turn and can be positive or negative.\r\n"
				+ "<br>-	One full board cycle (to players’ respective start locations) will give the player bonus resources.\r\n"
				+ "<br>-	The game ends when the board is fully lit up! Try to be the player with the most sections built, but be careful- it will also end if any player goes into debt.\r\n"
				+ ""
				+ "</div></html>");
		intro.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setPreferredSize(new Dimension(600,600));
		intro.setPreferredSize(new Dimension(500,500));
		intro.setFont(new Font("", Font.BOLD, 13));
		
		// "Okay" Button
		JButton okayB = new JButton("Okay");
		okayB.setFocusable(false);

		// Constraints for JLabel (text at the top)
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0; // No extra vertical weight
		gbc.insets = new Insets(10, 10, 20, 10); // Padding around the label
		gbc.anchor = GridBagConstraints.NORTH; // Align to top
		panel.add(intro, gbc);

		// Constraints for Button (placed at the bottom)
		gbc.gridy = 1;
		gbc.weighty = 1; // Pushes button down
		gbc.insets = new Insets(10, 20, 10, 20); // Space around button
		gbc.anchor = GridBagConstraints.SOUTH; // Align button to bottom
		panel.add(okayB, gbc);

		// ActionListener to go back to the menu
		okayB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close instructions window
				menu(); // Open menu window
			}
		});

		frame.add(panel);
		frame.setVisible(true);
	}
	
	public static String names(String na) {
        while (true) {
            JTextField textField = new JTextField();
            Object[] message = {"Enter player name:", textField};
            
            int option = JOptionPane.showOptionDialog(
                null,
                message,
                "Player: " + na,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"OK"}, // Removes Cancel button
                "OK"
            );

            String playerName = textField.getText().trim();

            if (!playerName.isEmpty()) {
                return playerName; // Return the valid name
            }

            // Show an error message if the input is empty
            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	

	public static void menu() {
		playerNames[0] = names("1");
		playerNames[1] = names("2");
		playerNames[2] = names("3");
		playerNames[3] = names("4");
		
		pl = gameBoard.initPlayers(4, playerNames);
		gameBoard.createAndShowGUI(pl);
	}
	
	/* WIP relating to less than 4 players
	public static void menu1() {
		JFrame frame = new JFrame("Player Select");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null); // Centers the frame on the screen

		// Create a panel with GridBagLayout to center the buttons
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL; // Makes buttons expand horizontally
		gbc.insets = new Insets(10, 20, 10, 20); // Top, Left, Bottom, Right spacing

		JButton twopl = new JButton("2");
		JButton threepl = new JButton("3");
		JButton fourpl = new JButton("4");

		twopl.setFocusable(false);
		threepl.setFocusable(false);
		fourpl.setFocusable(false);

		// Setting weighty to distribute space equally
		gbc.weighty = 1;

		// Add buttons with spacing
		gbc.gridy = 0; // First button
		panel.add(twopl, gbc);

		gbc.gridy = 1; // Second button
		gbc.insets = new Insets(5, 20, 5, 20); // Less space between buttons
		panel.add(threepl, gbc);

		gbc.gridy = 2; // Third button
		gbc.insets = new Insets(10, 20, 10, 20); // More space below the last button
		panel.add(fourpl, gbc);

		// Add ActionListener to the buttons
		ActionListener twoListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pl = gameBoard.initPlayers(2);
				gameBoard.createAndShowGUI(pl);
				frame.dispose();
			}
		};

		ActionListener threeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pl = gameBoard.initPlayers(3);
				gameBoard.createAndShowGUI(pl);
				frame.dispose();
			}
		};

		ActionListener fourListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pl = gameBoard.initPlayers(4);
				gameBoard.createAndShowGUI(pl);
				frame.dispose();
			}
		};

		twopl.addActionListener(twoListener);
		threepl.addActionListener(threeListener);
		fourpl.addActionListener(fourListener);

		frame.add(panel);
		frame.setVisible(true);
	}
	*/
}
