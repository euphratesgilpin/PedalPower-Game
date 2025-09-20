package pedalPower;

import java.util.Arrays;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class cardLibrary {
	// create card instances
	static card action1 = new card("Action", "Images/Action1.png", "Critical Infrastructure Grant", 20, 10);
	static card action2 = new card("Action", "Images/Action2.png", "Corporate Sponsorship", 20, 10);
	static card action3 = new card("Action", "Images/Action3.png", "Increased Marketing", 16, 8);
	static card action4 = new card("Action", "Images/Action4.png", "Scale Up", 24, 12);
	static card action5 = new card("Action", "Images/Action5.png", "Reducing Grant Risk", 24, 12);
	static card action6 = new card("Action", "Images/Action6.png", "Company Discount Scheme", 18, 9);
	static card action7 = new card("Action", "Images/Action7.png", "Sell Surplus Energy", 18, 9);
	static card action8 = new card("Action", "Images/Action8.png", "Community Fundraising", 16, 8);

	static card event1 = new card("Event", "Images/Event1.png", "Increased Temperature", -8, -4);
	static card event2 = new card("Event", "Images/Event2.png", "Vandalism", -6, -3);
	static card event3 = new card("Event", "Images/Event3.png", "Coal Running Low", -4, -2);
	static card event4 = new card("Event", "Images/Event4.png", "Hailstorm", -6, -3);
	static card event5 = new card("Event", "Images/Event5.png", "Load Shed Power Outage", -4, -2);
	static card event6 = new card("Event", "Images/Event6.png", "Fly Tipping", -8, -4);
	static card event7 = new card("Event", "Images/Event7.png", "Sources Required", -10, -5);
	static card event8 = new card("Event", "Images/Event8.png", "Damage", -10, -5);

	static card[] cardAArray = { action1, action2, action3, action4, action5, action6, action7, action8 };
	static card[] cardEArray = { event1, event2, event3, event4, event5, event6, event7, event8 };

	public static void genCard1(card[] cards, String str) {

		// Print the shuffled array
		System.out.println(cards[0]);

	}

	public static void genCard(String str, player p, Runnable onClose) {
	    JFrame buildFrame = new JFrame(str + " Card");
	    buildFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    buildFrame.setSize(600, 450);
	    buildFrame.setLocationRelativeTo(null);

	    ImageIcon bob = new ImageIcon();

	    if (str.equals("Event")) {
	        List<card> list = new ArrayList<>(Arrays.asList(cardEArray));
	        Collections.shuffle(list);
	        card[] newEArray = list.toArray(new card[0]);
	        bob = newEArray[0].getLink();
	        updateResources(newEArray[0], p);
	        if (p.getMoney() < 0 || p.getMaterial() < 0) {
	        	gameBoard.badEnding();
	        	buildFrame.dispose();
	        }
	        gameBoard.revalid();
	    }

	    if (str.equals("Action")) {
	        List<card> list1 = new ArrayList<>(Arrays.asList(cardAArray));
	        Collections.shuffle(list1);
	        card[] newAArray = list1.toArray(new card[0]);
	        bob = newAArray[0].getLink();
	        updateResources(newAArray[0], p);
	        gameBoard.revalid();
	    }

	    JPanel imgPanel = new JPanel();
	    JPanel bPanel = new JPanel();
	    bPanel.setPreferredSize(new Dimension(150, 300));

	    JLabel img = new JLabel();
	    JButton close = new JButton("Close");

	    bPanel.add(close);
	    
	    close.addActionListener(e -> {
	        buildFrame.dispose();
	        if (onClose != null) {
	        	// testing if player goes into negative
		        
	            onClose.run();  // Execute the next step (e.g., moving the player)
	        }
	    });
	    
	 // Get screen size
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	    // Calculate the X position for bottom-right (screen width - popup width)
	    int x = screenSize.width - buildFrame.getWidth() - 10;  // 10px padding from the right

	    // Calculate the Y position for bottom-right (screen height - popup height)
	    int y = screenSize.height - buildFrame.getHeight() - 10;  // 10px padding from the bottom

	    buildFrame.setLocation(x, y);  // Set the location of the popup

	    img.setIcon(bob);
	    imgPanel.add(img);
	    buildFrame.add(imgPanel, BorderLayout.WEST);
	    buildFrame.add(bPanel, BorderLayout.EAST);

	    buildFrame.setVisible(true);
	    buildFrame.revalidate();
	    buildFrame.repaint();
	}

	public static void updateResources(card card, player p) {
		int moneyAdd = card.getMoney();
		int matAdd = card.getMaterial();
		
		System.out.println("Player: " + p.getID() + "  Money: " + p.getMoney() + "  Material: " + p.getMaterial());
		
		p.setMoney(p.getMoney() + moneyAdd);
		p.setMaterial(p.getMaterial() + matAdd);
		
		System.out.println("Player: " + p.getID() + "  Money: " + p.getMoney() + "  Material: " + p.getMaterial());
	}

}
