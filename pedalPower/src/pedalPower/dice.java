package pedalPower;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class dice {

	public static int rollDice(JFrame frame) {
		Random rand = new Random();
		int rand_int1 = rand.nextInt(1, 7);
		int rand_int2 = rand.nextInt(1, 7);

		showRes(rand_int1, rand_int2, frame);

		return rand_int1 + rand_int2;
	}

	public static void showRes(int r, int s, JFrame parentFrame) {
	    // Create a JDialog for the message pop-up
	    JDialog dialog = new JDialog(parentFrame, "Roll the dice", true); // Make it modal

	    // Set the dialog size (you can adjust it as needed)
	    dialog.setSize(300, 150);

	    int total = r + s;
	    // Create the content for the dialog
	    String output = "<html><div style='text-align: center;'>"
				+ "Dice 1 rolls: " + r + "<br>Dice 2 rolls: " + s + "<br>Roll total: "+ total + "</div></html>";
	    JLabel messageLabel = new JLabel(output, SwingConstants.CENTER);

	    // Add the message to the dialog
	    dialog.add(messageLabel, BorderLayout.CENTER);

	    // Create an "OK" button to close the dialog
	    JButton okButton = new JButton("OK");
	    okButton.addActionListener(e -> dialog.dispose());
	    dialog.add(okButton, BorderLayout.SOUTH);

	    // Center the dialog relative to the parent frame
	    dialog.setLocationRelativeTo(parentFrame);

	    // Set the dialog to be visible
	    dialog.setVisible(true);
	}

	public static boolean roll1Dice(JFrame frame) {
		Random rand = new Random();
		int rand_int = rand.nextInt(1, 7);

		showRoll(rand_int, frame);

		if (rand_int < 4) {
			return true;
		} else {
			return false;
		}
	}

	public static void showRoll(int r, JFrame parentFrame) {
		// Create a JDialog for the message pop-up
	    JDialog dialog = new JDialog(parentFrame, "Roll the dice", true); // Make it modal

	    // Set the dialog size (you can adjust it as needed)
	    dialog.setSize(300, 150);

	    // Create the content for the dialog
	    String output = "Dice rolls: " + r;
	    JLabel messageLabel = new JLabel(output, SwingConstants.CENTER);

	    // Add the message to the dialog
	    dialog.add(messageLabel, BorderLayout.CENTER);

	    // Create an "OK" button to close the dialog
	    JButton okButton = new JButton("OK");
	    okButton.addActionListener(e -> dialog.dispose());
	    dialog.add(okButton, BorderLayout.SOUTH);

	    // Calculate the X and Y position to center the dialog relative to parentFrame
	    int x = parentFrame.getX() + (parentFrame.getWidth() - 300) / 2;  // Horizontal centering
	    int y = parentFrame.getY() + (parentFrame.getHeight() - 150) / 2;  // Vertical centering

	    // Set the location of the dialog relative to the parent frame
	    dialog.setLocation(x, y);

	    // Set the dialog to be visible
	    dialog.setVisible(true);
	}
}
