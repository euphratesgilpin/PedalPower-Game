package pedalPower;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class gameBoard extends JPanel {

	private static JFrame bigframe;
	private static gameBoard mainInstance; // Static reference to main gameBoard
	static Map<Integer, JLabel> labelMap = new HashMap<>(); // Maps positions to labels
	static player[] pl;
	static boolean turn = true;
	static JFrame turnframe = new JFrame();
	// private static boolean cycleLaneBuilt = false; // Tracks if cycle lane was
	// built
	private static int sq;
	private JComponent[] labels = new JComponent[160];
	private static JFrame buildFrame;
	private static gameBoard miniBoard;
	private static String colourlane = " ";
	private static boolean valid = false;
	private static JButton[][] buttons = new JButton[4][4];
	private static boolean built;
	private static boolean builtcyc;
	public static JPanel emptyPanel;
	public static JPanel infoPanel;
	public static int[] allSquares;
	public static JPanel player1Panel;
	public static JPanel player2Panel;
	public static JPanel player3Panel;
	public static JPanel player4Panel;
	private static JLabel player1Money;
	private static JLabel player1Material;
	private static JLabel player2Money;
	private static JLabel player2Material;
	private static JLabel player3Money;
	private static JLabel player3Material;
	private static JLabel player4Money;
	private static JLabel player4Material;
	private static JButton infoButton;

	public gameBoard(boolean isMainBoard) {
		super(new GridLayout(10, 16));
		labelMap = new HashMap<>(); // Each instance gets its own map

		if (isMainBoard) {
			if (mainInstance == null) {
				mainInstance = this; // ✅ Ensure only one main board exists
			}
		}

		initialiseBoard(isMainBoard);
	}

	public Map<Integer, JLabel> getLabelMap() {
		return labelMap;
	}

	public void setLabels(JLabel[] l) {
		this.labels = l;
	}

	public JComponent[] getLabels() {
		return this.labels;
	}

	public void initialiseBoard(boolean isMainBoard) {

		// Create the labels and set their background colours
		for (int i = 0; i < this.getLabels().length; i++) {
			JLabel help = (JLabel) (this.getLabels()[i] = new JLabel());
			this.getLabels()[i].setOpaque(true);
			this.getLabels()[i].setBackground(Color.LIGHT_GRAY);
			((JLabel) this.getLabels()[i]).setHorizontalAlignment(JLabel.CENTER);
			((JLabel) this.getLabels()[i]).setVerticalAlignment(JLabel.CENTER);

			labelMap.put(i, help); // Store reference for easy updates

			add(this.getLabels()[i]);

			int row = i / 16;
			int col = i % 16;
			this.getLabels()[i].setBackground(Color.LIGHT_GRAY);
			this.getLabels()[i].setOpaque(true);

			// Create thicker borders along the centre lines
			int top = 1, left = 1, bottom = 1, right = 1;
			if (row == 4) {
				if (col != 6 && col != 7 && col != 8 && col != 9) {
					bottom = 5;
				}
			} else if (row == 5) {
				if (col != 6 && col != 7 && col != 8 && col != 9) {
					top = 5;
				}
			}
			if (col == 7) {
				if (row != 3 && row != 4 && row != 5 && row != 6) {
					right = 5;
				}
			} else if (col == 8) {
				if (row != 3 && row != 4 && row != 5 && row != 6) {
					left = 5;
				}
			}
			this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK)); // thicker
			// border
			// for
			// centre
			// lines
			// excluding
			// centre
			// section
			// adjusting borders and colours for central squares
			// top line
			if (i == 54) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 70) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 55 || i == 56 || i == 103 || i == 104) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 86) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 102) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 71 || i == 72) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 87 || i == 88) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 57) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 73) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 88) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 89) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}
			if (i == 105) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
				this.getLabels()[i].setBackground(Color.GRAY);
			}

			// colour borders - central cases (top and bottom lines filled)
			if ((i > 17 && i < 22) || (i > 48 && i < 52) || (i > 25 && i < 30) || (i > 59 && i < 63)
					|| (i > 96 && i < 100) || (i > 129 && i < 134) || (i > 107 && i < 111) || (i > 137 && i < 142)) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
			}
			// colour borders - left edge cases (left, top and bottom lines filled)
			if (i == 17 || i == 48 || i == 59 || i == 96 || i == 129 || i == 107) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
			}
			// colour borders - right edge cases (right, top and bottom lines filled)
			if (i == 30 || i == 52 || i == 63 || i == 100 || i == 111 || i == 142) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
			}
			// colour borders - top (left, right, top filled)
			if (i == 118 || i == 121) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
			}
			// colour borders - bottom (left, bottom, right filled)
			if (i == 38 || i == 41) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
			}
			// singles - can't group
			if (i == 22) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));
			}
			if (i == 25) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
			}
			if (i == 134) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
			}
			if (i == 137) {
				this.getLabels()[i].setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.BLACK));
			}

			add(this.getLabels()[i]);
		}

		// Adding colour details

		this.getLabels()[71].setBackground(Color.YELLOW);
		this.getLabels()[72].setBackground(Color.GREEN);
		this.getLabels()[87].setBackground(Color.RED);
		this.getLabels()[88].setBackground(Color.BLUE);

		this.getLabels()[71].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.getLabels()[72].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.getLabels()[87].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.getLabels()[88].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		// yellow section
		for (int i = 17; i < 23; i++) {
			this.getLabels()[i].setBackground(Color.YELLOW);
		}
		this.getLabels()[38].setBackground(Color.YELLOW);
		for (int i = 48; i < 53; i++) {
			this.getLabels()[i].setBackground(Color.YELLOW);
		}

		// green section
		for (int i = 25; i < 31; i++) {
			this.getLabels()[i].setBackground(Color.GREEN);
		}
		this.getLabels()[41].setBackground(Color.GREEN);
		for (int i = 59; i < 64; i++) {
			this.getLabels()[i].setBackground(Color.GREEN);
		}

		// red section
		for (int i = 96; i < 101; i++) {
			this.getLabels()[i].setBackground(Color.RED);
		}
		this.labels[118].setBackground(Color.RED);
		for (int i = 129; i < 135; i++) {
			this.getLabels()[i].setBackground(Color.RED);
		}

		// blue section
		for (int i = 107; i < 112; i++) {
			this.getLabels()[i].setBackground(Color.BLUE);
		}
		this.getLabels()[121].setBackground(Color.BLUE);
		for (int i = 137; i < 143; i++) {
			this.getLabels()[i].setBackground(Color.BLUE);
		}

		((JLabel) this.getLabels()[40]).setText("1");
		((JLabel) this.getLabels()[95]).setText("2");
		((JLabel) this.getLabels()[119]).setText("3");
		((JLabel) this.getLabels()[64]).setText("4");

		((JLabel) this.getLabels()[40]).setFont(new Font("Arial", Font.PLAIN, 24));
		((JLabel) this.getLabels()[95]).setFont(new Font("Arial", Font.PLAIN, 24));
		((JLabel) this.getLabels()[119]).setFont(new Font("Arial", Font.PLAIN, 24));
		((JLabel) this.getLabels()[64]).setFont(new Font("Arial", Font.PLAIN, 24));

		// Set the preferred size of the grid panel
		if (isMainBoard == false) {
			setPreferredSize(new Dimension(400, 300));
		} else {
			setPreferredSize(new Dimension(800, 800));
		}
	}

	static void createAndShowGUI(player[] pl) {
		// Create and setup the main frame
		bigframe = new JFrame("GameBoard Demo");
		bigframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bigframe.setSize(1220, 800); // Updated frame size

		// Create the main panel with BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());

		// Create game board with fixed size
		mainInstance = new gameBoard(true);
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.setPreferredSize(new Dimension(800, 800)); // Ensures it doesn't shrink
		gamePanel.add(mainInstance, BorderLayout.CENTER);
		mainPanel.add(gamePanel, BorderLayout.WEST); // Add game panel to left side

		// Create an empty panel to fill the remaining space on the right
		emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(400, 800)); // Ensures enough space for player info
		emptyPanel.setLayout(new BorderLayout()); // Use BorderLayout for better control

		// Create a start button
		JButton start = new JButton("Start");
		emptyPanel.add(start, BorderLayout.NORTH); // Place the button at the top

		// Create a panel to hold player info in a 2x2 grid layout
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns with spacing

		// Player 1 (Top Left)
		player1Panel = new JPanel(new GridLayout(3, 1));
		player1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border
		JLabel player1Label = new JLabel(pl[0].getPlayerName());
		player1Money = new JLabel("Money: R" + pl[0].getMoney());
		player1Material = new JLabel("Material: " + pl[0].getMaterial());
		player1Panel.add(player1Label);
		player1Panel.add(player1Money);
		player1Panel.add(player1Material);

		// Player 2 (Top Right)
		player2Panel = new JPanel(new GridLayout(3, 1));
		player2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border
		JLabel player2Label = new JLabel(pl[1].getPlayerName());
		player2Money = new JLabel("Money: R" + pl[1].getMoney());
		player2Material = new JLabel("Material: " + pl[1].getMaterial());
		player2Panel.add(player2Label);
		player2Panel.add(player2Money);
		player2Panel.add(player2Material);

		// Player 3 (Bottom Left)
		player3Panel = new JPanel(new GridLayout(3, 1));
		player3Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border
		JLabel player3Label = new JLabel(pl[2].getPlayerName());
		player3Money = new JLabel("Money: R" + pl[2].getMoney());
		player3Material = new JLabel("Material: " + pl[2].getMaterial());
		player3Panel.add(player3Label);
		player3Panel.add(player3Money);
		player3Panel.add(player3Material);

		// Player 4 (Bottom Right)
		player4Panel = new JPanel(new GridLayout(3, 1));
		player4Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional border
		JLabel player4Label = new JLabel(pl[3].getPlayerName());
		player4Money = new JLabel("Money: R" + pl[3].getMoney());
		player4Material = new JLabel("Material: " + pl[3].getMaterial());
		player4Panel.add(player4Label);
		player4Panel.add(player4Money);
		player4Panel.add(player4Material);

		// Add all player panels directly to infoPanel in correct order
		infoPanel.add(player1Panel); // Top-left
		infoPanel.add(player2Panel); // Top-right
		infoPanel.add(player3Panel); // Bottom-left
		infoPanel.add(player4Panel); // Bottom-right

		emptyPanel.add(infoPanel, BorderLayout.CENTER);
		mainPanel.add(emptyPanel, BorderLayout.EAST);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start.setEnabled(false);
				emptyPanel.remove(start); // Remove the start button

				// Create and add the new "Show Info" button
				JButton newButton = new JButton("Show Info");
				emptyPanel.add(newButton, BorderLayout.NORTH);

				newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Create a JDialog for the popup
						JDialog popup = new JDialog(bigframe, "Info", true); // Modal dialog
						popup.setSize(300, 200);

						// Add the message content
						JLabel message = new JLabel("<html><div style='text-align: center;'>"
								+ "Game Information:<br>Purchase Cycle Lane: -2R, -1M<br>Build Docking Station: +10R, +5M<br>Full Journey Around Board: +16R, +8M<br>Action Card: +Bonus!<br>Event Card: -Funds... Beware"
								+ "</div></html>", SwingConstants.CENTER);
						popup.add(message, BorderLayout.CENTER);

						// Add an "OK" button to close the dialog
						JButton okButton = new JButton("OK");
						okButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								popup.dispose(); // Close the popup when the button is pressed
							}
						});
						popup.add(okButton, BorderLayout.SOUTH);

						// Calculate the position for the top-right corner of the screen
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						int x = screenSize.width - popup.getWidth() - 10; // 10px padding from the right
						int y = 10; // 10px padding from the top
						popup.setLocation(x, y);

						// Set the dialog visible
						popup.setVisible(true);
					}
				});

				emptyPanel.revalidate();
				emptyPanel.repaint();

				takeTurn(pl[0]);
			}
		});

		bigframe.add(mainPanel);
		bigframe.setVisible(true);

		startPos(pl);
	}

	public static gameBoard getMainInstance() {
		return mainInstance;
	}

	public static void setPlayerPosition(int oldPos, int newPos, ImageIcon icon, player p) {

		gameBoard mainBoard = gameBoard.getMainInstance();
		if (mainBoard == null) {
			return;
		}

		Map<Integer, JLabel> map = mainBoard.getLabelMap();
		if (map == null) {
			System.out.println("Error: labelMap is null!");
			return;
		}

		// Remove old icon
		if (oldPos != -1 && map.containsKey(oldPos)) {
			((JLabel) mainInstance.getLabels()[oldPos]).setIcon(null);
		} else {
			System.out.println("Old position not found.");
		}

		// Set new icon
		if (map.containsKey(newPos)) {
			((JLabel) mainInstance.getLabels()[newPos]).setIcon(icon);
		} else {
			System.out.println("New position not found in labelMap.");
		}

		if (oldPos == -1) {
		} else {
			for (int i = 0; i < pl[0].getDockingSquares().length; i++) {
				if (pl[0].getDockingSquares()[i] == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[0].getPlayerDS());
				}
			}
			for (int i = 0; i < pl[1].getDockingSquares().length; i++) {
				if (pl[1].getDockingSquares()[i] == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[1].getPlayerDS());
				}
			}
			for (int i = 0; i < pl[2].getDockingSquares().length; i++) {
				if (pl[2].getDockingSquares()[i] == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[2].getPlayerDS());
				}
			}
			for (int i = 0; i < pl[3].getDockingSquares().length; i++) {
				if (pl[3].getDockingSquares()[i] == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[3].getPlayerDS());
				}
			}
		}
		if (oldPos == -1) {

		} else {
			if (p.getID() == 1) {
				if (pl[1].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[1].getImage());
				} else if (pl[2].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[2].getImage());
				} else if (pl[3].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[3].getImage());
				}
			} else if (p.getID() == 2) {
				if (pl[0].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[0].getImage());
				} else if (pl[2].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[2].getImage());
				} else if (pl[3].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[3].getImage());
				}
			} else if (p.getID() == 3) {
				if (pl[0].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[0].getImage());
				} else if (pl[1].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[1].getImage());
				} else if (pl[3].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[3].getImage());
				}
			} else if (p.getID() == 4) {
				if (pl[0].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[0].getImage());
				} else if (pl[1].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[1].getImage());
				} else if (pl[2].getCurrentSquare() == oldPos) {
					((JLabel) mainInstance.getLabels()[oldPos]).setIcon(pl[2].getImage());
				}
			}
		}

		// Force UI refresh
		SwingUtilities.invokeLater(() -> {
			mainBoard.revalidate();
			mainBoard.repaint();
		});
	}

	public static void revalid() {
		// Update the labels with new values
		player1Money.setText("Money: R" + pl[0].getMoney());
		player1Material.setText("Material: " + pl[0].getMaterial());

		player2Money.setText("Money: R" + pl[1].getMoney());
		player2Material.setText("Material: " + pl[1].getMaterial());

		player3Money.setText("Money: R" + pl[2].getMoney());
		player3Material.setText("Material: " + pl[2].getMaterial());

		player4Money.setText("Money: R" + pl[3].getMoney());
		player4Material.setText("Material: " + pl[3].getMaterial());

		// Optional: Revalidate and repaint the panels to ensure the UI updates visually
		infoPanel.revalidate();
		infoPanel.repaint();
	}

	public JLabel getTile(int position) {
		return labelMap.get(position);
	}

	public static void startPos(player[] play) {
		player[] pla = startPage.pl;
		int count = 0;
		for (player p : pla) {
			count++;
		}
		for (int i = 1; i <= count; i++) {
			if (i == 1) {
				setVisualStart(1, play);
			}
			if (i == 2) {
				setVisualStart(2, play);
			}
			if (i == 3) {
				setVisualStart(3, play);
			}
			if (i == 4) {
				setVisualStart(4, play);
			}
		}
	}

	public static void setVisualStart(int pNum, player[] play) {
		if (pNum == 1) {
			ImageIcon temp = new ImageIcon("Images/one.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			ImageIcon onep = new ImageIcon(img0);
			setPlayerPosition(-1, play[0].getCurrentSquare(), onep, play[0]);
		} else if (pNum == 2) {
			ImageIcon temp = new ImageIcon("Images/two.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			ImageIcon twop = new ImageIcon(img0);
			setPlayerPosition(-1, play[1].getCurrentSquare(), twop, play[1]);
		} else if (pNum == 3) {
			ImageIcon temp = new ImageIcon("Images/three.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			ImageIcon threep = new ImageIcon(img0);
			setPlayerPosition(-1, play[2].getCurrentSquare(), threep, play[2]);
		} else if (pNum == 4) {
			ImageIcon temp = new ImageIcon("Images/four.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			ImageIcon fourp = new ImageIcon(img0);
			setPlayerPosition(-1, play[3].getCurrentSquare(), fourp, play[3]);
		} else {
			System.out.println("something went wrong with visual player assignment");
		}
	}

	public static player[] initPlayers(int num, String[] names) {
		pl = new player[num];

		player p1 = new player(names[0]);
		square.assignStartSQUARES(p1.getID());
		// board.setPlayerPosition(40, 40, p1.getImage());
		pl[0] = p1;
		player p2 = new player(names[1]);
		square.assignStartSQUARES(p2.getID());
		// board.setPlayerPosition(95, 95, p2.getImage());
		pl[1] = p2;
		/*
		 * if (num == 3) { player p3 = new player();
		 * square.assignStartSQUARES(p3.getID()); // board.setPlayerPosition(119, 119,
		 * p3.getImage()); pl[2] = p3; }
		 */
		if (num == 4) {
			player p3 = new player(names[2]);
			square.assignStartSQUARES(p3.getID());
			// board.setPlayerPosition(119, 119, p3.getImage());
			player p4 = new player(names[3]);
			square.assignStartSQUARES(p4.getID());
			// board.setPlayerPosition(63, 63, p4.getImage());
			pl[2] = p3;
			pl[3] = p4;
		}
		return pl;
	}

	public static void move(player player, Runnable onMoveComplete) {
		int squ = player.getCurrentSquare();
		int newSqu = square.updateActiveSQUARES(squ, turnframe);
		player.setCurrentSquare(newSqu);

		setPlayerPosition(squ, newSqu, player.getImage(), player);

		testStartQuad(player, squ, newSqu, () -> {

			// Wait for event completion before proceeding
			eventOccurs(newSqu, player, () -> {
				// After the event is handled (Close is pressed), proceed with claiming square
				claimSquare(player, onMoveComplete);
			});
		});
	}

	public static void testStartQuad(player p, int firstsquare, int square, Runnable onClose) {
		// need to update resources - monthly stipend reneeeeews!!!!!
		if (p.getID() == 1) {
			if (firstsquare != 40 && firstsquare != 24 && firstsquare != 8 && firstsquare != 9 && firstsquare != 10
					&& firstsquare != 11 && firstsquare != 12 && firstsquare != 13 && firstsquare != 14
					&& firstsquare != 15 && firstsquare != 31 && firstsquare != 47) {

				if (square == 40 || square == 24 || square == 8 || square == 9 || square == 10 || square == 11
						|| square == 12 || square == 13 || square == 14 || square == 15 || square == 31
						|| square == 47) {
					goPop(onClose, p);
				} else {
					onClose.run();
				}
			} else {
				onClose.run();
			}
		} else if (p.getID() == 2) {
			if (firstsquare != 95 && firstsquare != 94 && firstsquare != 93 && firstsquare != 92 && firstsquare != 91
					&& firstsquare != 90 && firstsquare != 106 && firstsquare != 122 && firstsquare != 123
					&& firstsquare != 124 && firstsquare != 125 && firstsquare != 126) {

				if (square == 95 || square == 94 || square == 93 || square == 92 || square == 91 || square == 90
						|| square == 106 || square == 122 || square == 123 || square == 124 || square == 125
						|| square == 126) {
					goPop(onClose, p);
				} else {
					onClose.run();
				}
			} else {
				onClose.run();
			}
		} else if (p.getID() == 3) {
			if (firstsquare != 119 && firstsquare != 135 && firstsquare != 151 && firstsquare != 150
					&& firstsquare != 149 && firstsquare != 148 && firstsquare != 147 && firstsquare != 146
					&& firstsquare != 145 && firstsquare != 144 && firstsquare != 128 && firstsquare != 112) {

				if (square == 119 || square == 135 || square == 151 || square == 150 || square == 149 || square == 148
						|| square == 147 || square == 146 || square == 145 || square == 144 || square == 128
						|| square == 112) {
					goPop(onClose, p);
				} else {
					onClose.run();
				}
			} else {
				onClose.run();
			}
		} else if (p.getID() == 4) {
			if (firstsquare != 64 && firstsquare != 65 && firstsquare != 66 && firstsquare != 67 && firstsquare != 68
					&& firstsquare != 69 && firstsquare != 53 && firstsquare != 37 && firstsquare != 36
					&& firstsquare != 35 && firstsquare != 34 && firstsquare != 33) {

				if (square == 64 || square == 65 || square == 66 || square == 67 || square == 68 || square == 69
						|| square == 53 || square == 37 || square == 36 || square == 35 || square == 34
						|| square == 33) {
					goPop(onClose, p);
				} else {
					onClose.run();
				}
			} else {
				onClose.run();
			}
		}
	}

	public static void goPop(Runnable onClose, player p) {
		p.setMoney(p.getMoney() + 16);
		p.setMaterial(p.getMaterial() + 8);

		JFrame popFrame = new JFrame();
		popFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popFrame.setSize(600, 450);
		popFrame.setLocationRelativeTo(null);

		ImageIcon bob = new ImageIcon("Images/gopop.png");

		// Resize the image to fit within the JFrame (600x450)
		Image img0 = bob.getImage().getScaledInstance(500, 350, Image.SCALE_SMOOTH);
		ImageIcon resizedBob = new ImageIcon(img0);

		JPanel imgPanel = new JPanel();
		JPanel bPanel = new JPanel();
		bPanel.setPreferredSize(new Dimension(150, 300));

		JLabel img = new JLabel();
		JButton close = new JButton("Close");

		bPanel.add(close);

		close.addActionListener(e -> {
			popFrame.dispose();
			if (onClose != null) {
				onClose.run(); // Execute the next step (e.g., moving the player)
			}
		});

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - popFrame.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - popFrame.getHeight() - 10; // 10px padding from the bottom

		popFrame.setLocation(x, y); // Set the location of the popup

		img.setIcon(resizedBob);
		imgPanel.add(img);
		popFrame.add(imgPanel, BorderLayout.WEST);
		popFrame.add(bPanel, BorderLayout.EAST);

		popFrame.setVisible(true);
		popFrame.revalidate();
		popFrame.repaint();

		revalid();

	}

	public static void eventOccurs(int sq, player p, Runnable onEventComplete) {
		int[] action = square.getActionSQUARES();
		int[] event = square.getEventSQUARES();

		int[] psq = {};
		if (p.getID() == 1) {
			psq = square.getfp1sq();
		}
		if (p.getID() == 2) {
			psq = square.getfp2sq();
		}
		if (p.getID() == 3) {
			psq = square.getfp3sq();
		}
		if (p.getID() == 4) {
			psq = square.getfp4sq();
		}

		for (int i = 0; i < action.length; i++) {
			if (action[i] == sq) {
				for (int j = 0; j < psq.length; j++) {
					if (psq[j] == sq) {
						cardLibrary.genCard("Action", p, onEventComplete);
						revalid();
						return; // Stop further execution until close is pressed
					}
				}
			}
		}

		for (int i = 0; i < event.length; i++) {
			if (event[i] == sq) {
				for (int j = 0; j < psq.length; j++) {
					if (psq[j] == sq) {
						cardLibrary.genCard("Event", p, onEventComplete);
						revalid();
						return;
					}
				}
			}
		}

		// If no event occurs, run the next step immediately
		if (onEventComplete != null) {
			onEventComplete.run();
		}
	}

	public static void claimSquare(player p, Runnable onClaimComplete) {
		JFrame frame = new JFrame("Claim Section?");
		sq = p.getCurrentSquare();

		// assign landed on square to initial section square for testing
		// green
		if (sq == 40 || sq == 24 || sq == 8 || sq == 10 || sq == 11 || sq == 9) {
			sq = 40;
		} // 1
		if (sq == 12 || sq == 13 || sq == 15 || sq == 31 || sq == 47 || sq == 14) {
			sq = 12;
		} // 2
		if (sq == 46 || sq == 45 || sq == 44 || sq == 42 || sq == 58 || sq == 43) {
			sq = 46;
		} // 3
		if (sq == 74 || sq == 75 || sq == 76 || sq == 78 || sq == 79 || sq == 77) {
			sq = 74;
		} // 4
			// blue
		if (sq == 95 || sq == 94 || sq == 93 || sq == 92 || sq == 91 || sq == 90 || sq == 106) {
			sq = 95;
		} // 1
		if (sq == 122 || sq == 123 || sq == 124 || sq == 125 || sq == 127 || sq == 126) {
			sq = 122;
		} // 2
		if (sq == 143 || sq == 158 || sq == 157 || sq == 156 || sq == 155 || sq == 159) {
			sq = 143;
		} // 3
		if (sq == 154 || sq == 153 || sq == 136 || sq == 120 || sq == 152) {
			sq = 154;
		} // 4
			// red
		if (sq == 119 || sq == 135 || sq == 151 || sq == 149 || sq == 148 || sq == 147 || sq == 150) {
			sq = 119;
		} // 1
		if (sq == 146 || sq == 145 || sq == 144 || sq == 112 || sq == 113 || sq == 128) {
			sq = 146;
		} // 2
		if (sq == 114 || sq == 115 || sq == 116 || sq == 117 || sq == 85 || sq == 101) {
			sq = 114;
		} // 3
		if (sq == 84 || sq == 83 || sq == 81 || sq == 82 || sq == 80) {
			sq = 84;
		} // 4
			// yellow
		if (sq == 64 || sq == 65 || sq == 66 || sq == 67 || sq == 68 || sq == 69) {
			sq = 64;
		} // 1
		if (sq == 53 || sq == 37 || sq == 36 || sq == 34 || sq == 33 || sq == 35) {
			sq = 53;
		} // 2
		if (sq == 32 || sq == 16 || sq == 1 || sq == 2 || sq == 3 || sq == 0) {
			sq = 32;
		} // 3
		if (sq == 4 || sq == 5 || sq == 6 || sq == 23 || sq == 39 || sq == 7) {
			sq = 4;
		} // 4

		// If section not available, no popup
		int[] test1 = square.getp1sq();
		int[] test2 = square.getp2sq();
		int[] test3 = square.getp3sq();
		int[] test4 = square.getp4sq();

		for (int i = 0; i < test1.length; i++) {
			if (test1[i] == sq) {
				if (p.getID() == 1) {
					onClaimComplete.run();
					return;
				} else {
					donate(frame, p, pl[0], () -> {
						onClaimComplete.run();

					});
					return;
				}
			}
		}
		for (int i = 0; i < test2.length; i++) {
			if (test2[i] == sq) {
				if (p.getID() == 2) {
					onClaimComplete.run();
					return;
				} else {
					donate(frame, p, pl[1], () -> {
						onClaimComplete.run();

					});
					return;
				}
			}
		}
		for (int i = 0; i < test3.length; i++) {
			if (test3[i] == sq) {
				if (p.getID() == 3) {
					onClaimComplete.run();
					return;
				} else {
					donate(frame, p, pl[2], () -> {
						onClaimComplete.run();

					});
					return;
				}
			}
		}
		for (int i = 0; i < test4.length; i++) {
			if (test4[i] == sq) {
				if (p.getID() == 4) {
					onClaimComplete.run();
					return;
				} else {
					donate(frame, p, pl[3], () -> {
						onClaimComplete.run();

					});
					return;
				}
			}
		}

		// If docking station, no popup
		int[] dstest = square.getdockingSQUARES();
		for (int i = 0; i < dstest.length; i++) {
			if (dstest[i] == sq) {
				onClaimComplete.run();
				return;
			}
		}

		// Popup for claiming
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);

		// Panel with GridBagLayout for proper positioning
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 20, 10, 20); // Padding

		// Add text label at the top
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2; // Make label span both columns
		gbc.anchor = GridBagConstraints.CENTER; // Center the label
		JLabel text = new JLabel(
				"<html><div style='text-align: center;'>You must roll a 1, 2 or 3 to claim this section.<br>Do you want to attempt to claim?</div></html>");
		panel.add(text, gbc);

		// Reset gridwidth for buttons
		gbc.gridwidth = 1;
		gbc.gridy = 1; // Move to next row

		// "Yes" button
		JButton yes = new JButton("Yes");
		yes.setFocusable(false);
		panel.add(yes, gbc);

		// "No" button
		gbc.gridx = 1; // Move to the right column
		JButton no = new JButton("No");
		no.setFocusable(false);
		panel.add(no, gbc);

		// "Yes" button action
		yes.addActionListener(e -> {
			boolean eep = dice.roll1Dice(frame);
			frame.dispose(); // Close the frame after the dice roll

			// Check the result and show corresponding dialog relative to the frame
			if (eep == true) {
				// Show "Section Claimed!" message relative to frame
				JDialog dialog = new JDialog(frame, "Claim Status", true); // Make it modal
				dialog.setSize(300, 150); // Size of the dialog

				p.setSO(p.getSO() + 1);

				// Create the content for the dialog
				JLabel messageLabel = new JLabel("Section Claimed!", SwingConstants.CENTER);
				dialog.add(messageLabel, BorderLayout.CENTER);

				// Create an "OK" button to close the dialog
				JButton okButton = new JButton("OK");
				okButton.addActionListener(event -> dialog.dispose()); // Close dialog when pressed
				dialog.add(okButton, BorderLayout.SOUTH);

				// Calculate position to center dialog relative to the frame
				int x = frame.getX() + (frame.getWidth() - 300) / 2; // Horizontal centering
				int y = frame.getY() + (frame.getHeight() - 150) / 2; // Vertical centering

				dialog.setLocation(x, y); // Set the position of the dialog relative to frame
				dialog.setVisible(true); // Show the dialog
				setColours(p, sq); // Call setColours() after dialog

				square.setpsq(p, sq); // Update square's state
			} else {
				// Show "Claim Attempt Failed!" message relative to frame
				JDialog dialog = new JDialog(frame, "Claim Status", true); // Make it modal
				dialog.setSize(300, 150); // Size of the dialog

				// Create the content for the dialog
				JLabel messageLabel = new JLabel("Claim Attempt Failed!", SwingConstants.CENTER);
				dialog.add(messageLabel, BorderLayout.CENTER);

				// Create an "OK" button to close the dialog
				JButton okButton = new JButton("OK");
				okButton.addActionListener(event -> dialog.dispose()); // Close dialog when pressed
				dialog.add(okButton, BorderLayout.SOUTH);

				// Calculate position to center dialog relative to the frame
				int x = frame.getX() + (frame.getWidth() - 300) / 2; // Horizontal centering
				int y = frame.getY() + (frame.getHeight() - 150) / 2; // Vertical centering

				dialog.setLocation(x, y); // Set the position of the dialog relative to frame
				dialog.setVisible(true); // Show the dialog
			}

			// Run any final actions after the dialog closes
			onClaimComplete.run();
		});

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - frame.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - frame.getHeight() - 130; // 10px padding from the bottom

		frame.setLocation(x, y); // Set the location of the popup

		// "No" button action
		no.addActionListener(e -> {
			frame.dispose();
			onClaimComplete.run();
		});

		// Add panel to frame
		frame.add(panel);
		frame.setVisible(true);
	}

	public static void donate(JFrame frame, player paying, player payee, Runnable completed) {
		JFrame aaaaa = new JFrame("Donations Please!");
		aaaaa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aaaaa.setSize(400, 200);
		aaaaa.setLocationRelativeTo(null);

		// Panel with GridBagLayout for proper positioning
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 20, 10, 20); // Padding

		// Add text label at the top
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2; // Make label span both columns
		gbc.anchor = GridBagConstraints.CENTER; // Center the label
		JLabel text = new JLabel("<html><div style='text-align: center;'>You have landed on " + payee.getPlayerName()
				+ "'s square.<br>Would you like to donate to their work?</div></html>");
		panel.add(text, gbc);

		// Reset gridwidth for buttons
		gbc.gridwidth = 1;
		gbc.gridy = 1; // Move to next row

		// "Yes" button
		JButton yes = new JButton("Yes");
		yes.setFocusable(false);
		panel.add(yes, gbc);

		// "No" button
		gbc.gridx = 1; // Move to the right column
		JButton no = new JButton("No");
		no.setFocusable(false);
		panel.add(no, gbc);

		// "Yes" button action
		yes.addActionListener(e -> {
			valid = testResources(paying);
			if (valid == true) {
				// Show "Section Claimed!" message relative to frame
				JDialog dialog = new JDialog(aaaaa, "Donation Made!", true); // Make it modal
				dialog.setSize(300, 150); // Size of the dialog

				// Create the content for the dialog
				JLabel messageLabel = new JLabel(
						"You donated 2 Rands and 1 Materials to " + payee.getPlayerName() + "!", SwingConstants.CENTER);
				dialog.add(messageLabel, BorderLayout.CENTER);

				// Create an "OK" button to close the dialog
				JButton okButton = new JButton("OK");
				okButton.addActionListener(event -> dialog.dispose()); // Close dialog when pressed
				dialog.add(okButton, BorderLayout.SOUTH);

				// Calculate position to center dialog relative to the frame
				int x = aaaaa.getX() + (aaaaa.getWidth() - 300) / 2; // Horizontal centering
				int y = aaaaa.getY() + (aaaaa.getHeight() - 150) / 2; // Vertical centering

				dialog.setLocation(x, y); // Set the position of the dialog relative to frame
				dialog.setVisible(true); // Show the dialog

				payee.setMoney(payee.getMoney() + 2);
				payee.setMaterial(payee.getMaterial() + 1);

				revalid();
				aaaaa.dispose();
				// Run any final actions after the dialog closes
				completed.run();
			}
		});

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - aaaaa.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - aaaaa.getHeight() - 130; // 10px padding from the bottom

		aaaaa.setLocation(x, y); // Set the location of the popup

		// "No" button action
		no.addActionListener(e -> {
			aaaaa.dispose();
			completed.run();
		});

		// Add panel to frame
		aaaaa.add(panel);
		aaaaa.setVisible(true);
	}

	public static void setColours(player p, int sq) {
		// doing the opposite to the last section - reverse assigning the squares
		String colour = " ";

		if (p.getID() == 1) {
			colour = "#a0ff66";
		}
		if (p.getID() == 2) {
			colour = "#6689ff";
		}
		if (p.getID() == 3) {
			colour = "#ff6666";
		}
		if (p.getID() == 4) {
			colour = "#fffd66";
		}
		// assign landed on square to initial section square for testing
		// green
		if (sq == 40) {
			mainInstance.getLabels()[40].setBackground(Color.decode(colour));
			mainInstance.getLabels()[24].setBackground(Color.decode(colour));
			mainInstance.getLabels()[8].setBackground(Color.decode(colour));
			mainInstance.getLabels()[10].setBackground(Color.decode(colour));
			mainInstance.getLabels()[11].setBackground(Color.decode(colour));
		} // 1
		if (sq == 12) {
			mainInstance.getLabels()[12].setBackground(Color.decode(colour));
			mainInstance.getLabels()[13].setBackground(Color.decode(colour));
			mainInstance.getLabels()[15].setBackground(Color.decode(colour));
			mainInstance.getLabels()[31].setBackground(Color.decode(colour));
			mainInstance.getLabels()[47].setBackground(Color.decode(colour));
		} // 2
		if (sq == 46) {
			mainInstance.getLabels()[46].setBackground(Color.decode(colour));
			mainInstance.getLabels()[45].setBackground(Color.decode(colour));
			mainInstance.getLabels()[44].setBackground(Color.decode(colour));
			mainInstance.getLabels()[42].setBackground(Color.decode(colour));
			mainInstance.getLabels()[58].setBackground(Color.decode(colour));
		} // 3
		if (sq == 74) {
			mainInstance.getLabels()[74].setBackground(Color.decode(colour));
			mainInstance.getLabels()[75].setBackground(Color.decode(colour));
			mainInstance.getLabels()[76].setBackground(Color.decode(colour));
			mainInstance.getLabels()[78].setBackground(Color.decode(colour));
			mainInstance.getLabels()[79].setBackground(Color.decode(colour));
		} // 4
			// blue
		if (sq == 95) {
			mainInstance.getLabels()[95].setBackground(Color.decode(colour));
			mainInstance.getLabels()[94].setBackground(Color.decode(colour));
			mainInstance.getLabels()[93].setBackground(Color.decode(colour));
			mainInstance.getLabels()[92].setBackground(Color.decode(colour));
			mainInstance.getLabels()[91].setBackground(Color.decode(colour));
			mainInstance.getLabels()[90].setBackground(Color.decode(colour));
		} // 1
		if (sq == 122) {
			mainInstance.getLabels()[122].setBackground(Color.decode(colour));
			mainInstance.getLabels()[123].setBackground(Color.decode(colour));
			mainInstance.getLabels()[124].setBackground(Color.decode(colour));
			mainInstance.getLabels()[125].setBackground(Color.decode(colour));
			mainInstance.getLabels()[127].setBackground(Color.decode(colour));
		} // 2
		if (sq == 143) {
			mainInstance.getLabels()[143].setBackground(Color.decode(colour));
			mainInstance.getLabels()[158].setBackground(Color.decode(colour));
			mainInstance.getLabels()[157].setBackground(Color.decode(colour));
			mainInstance.getLabels()[156].setBackground(Color.decode(colour));
			mainInstance.getLabels()[155].setBackground(Color.decode(colour));
		} // 3
		if (sq == 154) {
			mainInstance.getLabels()[154].setBackground(Color.decode(colour));
			mainInstance.getLabels()[153].setBackground(Color.decode(colour));
			mainInstance.getLabels()[136].setBackground(Color.decode(colour));
			mainInstance.getLabels()[120].setBackground(Color.decode(colour));
		} // 4
			// red
		if (sq == 119) {
			mainInstance.getLabels()[119].setBackground(Color.decode(colour));
			mainInstance.getLabels()[135].setBackground(Color.decode(colour));
			mainInstance.getLabels()[151].setBackground(Color.decode(colour));
			mainInstance.getLabels()[149].setBackground(Color.decode(colour));
			mainInstance.getLabels()[148].setBackground(Color.decode(colour));
			mainInstance.getLabels()[147].setBackground(Color.decode(colour));
		} // 1
		if (sq == 146) {
			mainInstance.getLabels()[146].setBackground(Color.decode(colour));
			mainInstance.getLabels()[145].setBackground(Color.decode(colour));
			mainInstance.getLabels()[144].setBackground(Color.decode(colour));
			mainInstance.getLabels()[112].setBackground(Color.decode(colour));
			mainInstance.getLabels()[113].setBackground(Color.decode(colour));
		} // 2
		if (sq == 114) {
			mainInstance.getLabels()[114].setBackground(Color.decode(colour));
			mainInstance.getLabels()[115].setBackground(Color.decode(colour));
			mainInstance.getLabels()[116].setBackground(Color.decode(colour));
			mainInstance.getLabels()[117].setBackground(Color.decode(colour));
			mainInstance.getLabels()[85].setBackground(Color.decode(colour));
		} // 3
		if (sq == 84) {
			mainInstance.getLabels()[84].setBackground(Color.decode(colour));
			mainInstance.getLabels()[83].setBackground(Color.decode(colour));
			mainInstance.getLabels()[81].setBackground(Color.decode(colour));
			mainInstance.getLabels()[80].setBackground(Color.decode(colour));
		} // 4
			// yellow
		if (sq == 64) {
			mainInstance.getLabels()[64].setBackground(Color.decode(colour));
			mainInstance.getLabels()[65].setBackground(Color.decode(colour));
			mainInstance.getLabels()[66].setBackground(Color.decode(colour));
			mainInstance.getLabels()[67].setBackground(Color.decode(colour));
			mainInstance.getLabels()[68].setBackground(Color.decode(colour));
		} // 1
		if (sq == 53) {
			mainInstance.getLabels()[53].setBackground(Color.decode(colour));
			mainInstance.getLabels()[37].setBackground(Color.decode(colour));
			mainInstance.getLabels()[36].setBackground(Color.decode(colour));
			mainInstance.getLabels()[34].setBackground(Color.decode(colour));
			mainInstance.getLabels()[33].setBackground(Color.decode(colour));
		} // 2
		if (sq == 32) {
			mainInstance.getLabels()[32].setBackground(Color.decode(colour));
			mainInstance.getLabels()[16].setBackground(Color.decode(colour));
			mainInstance.getLabels()[1].setBackground(Color.decode(colour));
			mainInstance.getLabels()[2].setBackground(Color.decode(colour));
			mainInstance.getLabels()[3].setBackground(Color.decode(colour));
		} // 3
		if (sq == 4) {
			mainInstance.getLabels()[4].setBackground(Color.decode(colour));
			mainInstance.getLabels()[5].setBackground(Color.decode(colour));
			mainInstance.getLabels()[6].setBackground(Color.decode(colour));
			mainInstance.getLabels()[23].setBackground(Color.decode(colour));
			mainInstance.getLabels()[39].setBackground(Color.decode(colour));
		} // 4
	}

	public static void takeTurn(player curr) {

		testGoodEnding(() -> {

			builtcyc = false;
			// menu

			turnframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			if (curr == pl[0]) {
				turnframe = new JFrame(pl[0].getPlayerName() + "'s Turn");
			}
			if (curr == pl[1]) {
				turnframe = new JFrame(pl[1].getPlayerName() + "'s Turn");
			}
			if (curr == pl[2]) {
				turnframe = new JFrame(pl[2].getPlayerName() + "'s Turn");
			}
			if (curr == pl[3]) {
				turnframe = new JFrame(pl[3].getPlayerName() + "'s Turn");
			}

			turnframe.setSize(400, 300);
			turnframe.setLocationRelativeTo(null); // Centers the frame on the screen

			// Create a panel with GridBagLayout to center the buttons
			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL; // Makes buttons expand horizontally
			gbc.insets = new Insets(10, 20, 10, 20); // Top, Left, Bottom, Right spacing

			JButton move = new JButton("Move");
			JButton buildcyc = new JButton("Build Cycle Lane");
			JButton buildds = new JButton("Build Docking Station");

			move.setFocusable(false);
			buildcyc.setFocusable(false);
			buildds.setFocusable(false);

			// Setting weighty to distribute space equally
			gbc.weighty = 1;

			// Add buttons with spacing
			gbc.gridy = 0; // First button
			panel.add(move, gbc);

			gbc.gridy = 1; // Second button
			gbc.insets = new Insets(5, 20, 5, 20); // Less space between buttons
			panel.add(buildcyc, gbc);

			gbc.gridy = 2; // Third button
			gbc.insets = new Insets(10, 20, 10, 20); // More space below the last button
			panel.add(buildds, gbc);

			// build cycle lane
			// build docking station
			// move
			// claim section?
			// yes - event?
			// yes but no event - add to their inventory, end turn

			// no - nothing else happens, end turn

			move.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					move(curr, () -> {
						turnframe.dispose(); // Close window only after claim decision
						nextTurn(curr);
					});
				}
			});

			ActionListener cycAL = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(builtcyc);
					buildCyc(curr, () -> {
						if (builtcyc == true) {
							buildds.setEnabled(false);
							turnframe.revalidate();
							turnframe.repaint();
						}
					});

				}
			};

			buildcyc.addActionListener(cycAL);

			ActionListener dsAL = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					buildDS(curr, () -> {
						System.out.println(built);

						if (built == true) {
							turnframe.dispose();
							revalid();
							nextTurn(curr);
						}
					});

				}
			};

			buildds.addActionListener(dsAL);

			// Get screen size
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

			// Calculate the X position for bottom-right (screen width - popup width)
			int x = screenSize.width - turnframe.getWidth() - 10; // 10px padding from the right

			// Calculate the Y position for bottom-right (screen height - popup height)
			int y = screenSize.height - turnframe.getHeight() - 100; // 10px padding from the bottom

			turnframe.setLocation(x, y); // Set the location of the popup

			turnframe.add(panel);
			turnframe.setVisible(true);
		});
	}

	public static void nextTurn(player current) {
		player next = pl[0];
		if (current == pl[0]) {
			next = pl[1];
		}
		if (current == pl[1]) {
			next = pl[2];
		}
		if (pl.length > 2 && current == pl[2]) {
			next = pl[3];
		}
		if (pl.length > 3 && current == pl[3]) {
			next = pl[0];
		}

		takeTurn(next);
	}

	public static void buildCyc(player p, Runnable gogogo) {
		buildFrame = new JFrame("Build Cycle Lane");
		buildFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildFrame.setSize(550, 300);
		buildFrame.setLocationRelativeTo(null);

		// ✅ Create a separate mini-board (not linked to the main board)
		miniBoard = new gameBoard(false);
		miniBoard.setSize(400, 300);

		int[] claimedSquares = {};
		if (p.getID() == 1) {
			claimedSquares = square.getfp1sq();
		}
		if (p.getID() == 2) {
			claimedSquares = square.getfp2sq();
		}
		if (p.getID() == 3) {
			claimedSquares = square.getfp3sq();
		}
		if (p.getID() == 4) {
			claimedSquares = square.getfp4sq();
		}

		builtcyc = false;
		miniBoard.convertClaimedSquaresToButtons(claimedSquares, p, buildFrame, () -> {
			System.out.println(builtcyc);
			gogogo.run();
		});

		buildFrame.add(miniBoard, BorderLayout.WEST);
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(150, 300));

		JButton cancel = new JButton("Cancel Selection");

		ActionListener cancelAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/// builtcyc = false;
				buildFrame.dispose();
				miniBoard = null;
			}
		};
		cancel.addActionListener(cancelAL);

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - buildFrame.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - buildFrame.getHeight() - 100; // 10px padding from the bottom

		buildFrame.setLocation(x, y); // Set the location of the popup

		panel2.add(cancel);
		buildFrame.add(panel2, BorderLayout.EAST);
		buildFrame.setVisible(true);

	}

	public void convertClaimedSquaresToButtons(int[] claimedSquares, player p, JFrame frame, Runnable goo) {

		if (p.getID() == 1) {
			colourlane = "#207600";
		}
		if (p.getID() == 2) {
			colourlane = "#003790";
		}
		if (p.getID() == 3) {
			colourlane = "#950000";
		}
		if (p.getID() == 4) {
			colourlane = "#879000";
		}

		// Create the buttons
		JButton button0 = new JButton(" ");
		JButton button1 = new JButton(" ");
		JButton button2 = new JButton(" ");
		JButton button3 = new JButton(" ");
		JButton button4 = new JButton(" ");
		JButton button5 = new JButton(" ");
		JButton button6 = new JButton(" ");
		JButton button7 = new JButton(" ");
		JButton button8 = new JButton(" ");
		JButton button9 = new JButton(" ");
		JButton button10 = new JButton(" ");
		JButton button11 = new JButton(" ");
		JButton button12 = new JButton(" ");
		JButton button13 = new JButton(" ");
		JButton button14 = new JButton(" ");
		JButton button15 = new JButton(" ");
		JButton button16 = new JButton(" ");
		JButton button17 = new JButton(" ");
		JButton button18 = new JButton(" ");
		JButton button19 = new JButton(" ");
		JButton button20 = new JButton(" ");
		JButton button21 = new JButton(" ");
		JButton button22 = new JButton(" ");
		JButton button23 = new JButton(" ");
		JButton button24 = new JButton(" ");
		JButton button25 = new JButton(" ");
		JButton button26 = new JButton(" ");
		JButton button27 = new JButton(" ");
		JButton button28 = new JButton(" ");
		JButton button29 = new JButton(" ");
		JButton button30 = new JButton(" ");
		JButton button31 = new JButton(" ");
		JButton button32 = new JButton(" ");
		JButton button33 = new JButton(" ");
		JButton button34 = new JButton(" ");
		JButton button35 = new JButton(" ");
		JButton button36 = new JButton(" ");
		JButton button37 = new JButton(" ");
		JButton button38 = new JButton(" ");
		JButton button39 = new JButton(" ");
		JButton button40 = new JButton(" ");
		JButton button41 = new JButton(" ");
		JButton button42 = new JButton(" ");
		JButton button43 = new JButton(" ");
		JButton button44 = new JButton(" ");
		JButton button45 = new JButton(" ");
		JButton button46 = new JButton(" ");
		JButton button47 = new JButton(" ");
		JButton button48 = new JButton(" ");
		JButton button49 = new JButton(" ");
		JButton button50 = new JButton(" ");
		JButton button51 = new JButton(" ");
		JButton button52 = new JButton(" ");
		JButton button53 = new JButton(" ");
		JButton button54 = new JButton(" ");
		JButton button55 = new JButton(" ");
		JButton button56 = new JButton(" ");
		JButton button57 = new JButton(" ");
		JButton button58 = new JButton(" ");
		JButton button59 = new JButton(" ");
		JButton button60 = new JButton(" ");
		JButton button61 = new JButton(" ");
		JButton button62 = new JButton(" ");
		JButton button63 = new JButton(" ");
		JButton button64 = new JButton(" ");
		JButton button65 = new JButton(" ");
		JButton button66 = new JButton(" ");
		JButton button67 = new JButton(" ");
		JButton button68 = new JButton(" ");
		JButton button69 = new JButton(" ");
		JButton button70 = new JButton(" ");
		JButton button71 = new JButton(" ");
		JButton button72 = new JButton(" ");
		JButton button73 = new JButton(" ");
		JButton button74 = new JButton(" ");
		JButton button75 = new JButton(" ");
		JButton button76 = new JButton(" ");
		JButton button77 = new JButton(" ");
		JButton button78 = new JButton(" ");
		JButton button79 = new JButton(" ");

		button0.setFocusable(false);
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		button5.setFocusable(false);
		button6.setFocusable(false);
		button7.setFocusable(false);
		button8.setFocusable(false);
		button9.setFocusable(false);
		button10.setFocusable(false);
		button11.setFocusable(false);
		button12.setFocusable(false);
		button13.setFocusable(false);
		button14.setFocusable(false);
		button15.setFocusable(false);
		button16.setFocusable(false);
		button17.setFocusable(false);
		button18.setFocusable(false);
		button19.setFocusable(false);
		button20.setFocusable(false);
		button21.setFocusable(false);
		button22.setFocusable(false);
		button23.setFocusable(false);
		button24.setFocusable(false);
		button25.setFocusable(false);
		button26.setFocusable(false);
		button27.setFocusable(false);
		button28.setFocusable(false);
		button29.setFocusable(false);
		button30.setFocusable(false);
		button31.setFocusable(false);
		button32.setFocusable(false);
		button33.setFocusable(false);
		button34.setFocusable(false);
		button35.setFocusable(false);
		button36.setFocusable(false);
		button37.setFocusable(false);
		button38.setFocusable(false);
		button39.setFocusable(false);
		button40.setFocusable(false);
		button41.setFocusable(false);
		button42.setFocusable(false);
		button43.setFocusable(false);
		button44.setFocusable(false);
		button45.setFocusable(false);
		button46.setFocusable(false);
		button47.setFocusable(false);
		button48.setFocusable(false);
		button49.setFocusable(false);
		button50.setFocusable(false);
		button51.setFocusable(false);
		button52.setFocusable(false);
		button53.setFocusable(false);
		button54.setFocusable(false);
		button55.setFocusable(false);
		button56.setFocusable(false);
		button57.setFocusable(false);
		button58.setFocusable(false);
		button59.setFocusable(false);
		button60.setFocusable(false);
		button61.setFocusable(false);
		button62.setFocusable(false);
		button63.setFocusable(false);
		button64.setFocusable(false);
		button65.setFocusable(false);
		button66.setFocusable(false);
		button67.setFocusable(false);
		button68.setFocusable(false);
		button69.setFocusable(false);
		button70.setFocusable(false);
		button71.setFocusable(false);
		button72.setFocusable(false);
		button73.setFocusable(false);
		button74.setFocusable(false);
		button75.setFocusable(false);
		button76.setFocusable(false);
		button77.setFocusable(false);
		button78.setFocusable(false);
		button79.setFocusable(false);

		// Add action listeners for the buttons
		ActionListener buttonListener0 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[40].setBackground(Color.decode(colourlane));
				square.removeLabel(40, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener1 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[24].setBackground(Color.decode(colourlane));
				square.removeLabel(24, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener2 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[8].setBackground(Color.decode(colourlane));
				square.removeLabel(8, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener3 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[10].setBackground(Color.decode(colourlane));
				square.removeLabel(10, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener4 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[11].setBackground(Color.decode(colourlane));
				square.removeLabel(11, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener5 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[12].setBackground(Color.decode(colourlane));
				square.removeLabel(12, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener6 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[13].setBackground(Color.decode(colourlane));
				square.removeLabel(13, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener7 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[15].setBackground(Color.decode(colourlane));
				square.removeLabel(15, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener8 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[31].setBackground(Color.decode(colourlane));
				square.removeLabel(31, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener9 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[47].setBackground(Color.decode(colourlane));
				square.removeLabel(47, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener10 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[46].setBackground(Color.decode(colourlane));
				square.removeLabel(46, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener11 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[45].setBackground(Color.decode(colourlane));
				square.removeLabel(45, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener12 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[44].setBackground(Color.decode(colourlane));
				square.removeLabel(44, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener13 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[42].setBackground(Color.decode(colourlane));
				square.removeLabel(42, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener14 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[58].setBackground(Color.decode(colourlane));
				square.removeLabel(58, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener15 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[74].setBackground(Color.decode(colourlane));
				square.removeLabel(74, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener16 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[75].setBackground(Color.decode(colourlane));
				square.removeLabel(75, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener17 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[76].setBackground(Color.decode(colourlane));
				square.removeLabel(76, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener18 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[78].setBackground(Color.decode(colourlane));
				square.removeLabel(78, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener19 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[79].setBackground(Color.decode(colourlane));
				square.removeLabel(79, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener20 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[95].setBackground(Color.decode(colourlane));
				square.removeLabel(95, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener21 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[94].setBackground(Color.decode(colourlane));
				square.removeLabel(94, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener22 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[93].setBackground(Color.decode(colourlane));
				square.removeLabel(93, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener23 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[92].setBackground(Color.decode(colourlane));
				square.removeLabel(92, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener24 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[91].setBackground(Color.decode(colourlane));
				square.removeLabel(91, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener25 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[90].setBackground(Color.decode(colourlane));
				square.removeLabel(90, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener26 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[122].setBackground(Color.decode(colourlane));
				square.removeLabel(122, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener27 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[123].setBackground(Color.decode(colourlane));
				square.removeLabel(123, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener28 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[124].setBackground(Color.decode(colourlane));
				square.removeLabel(124, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener29 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[125].setBackground(Color.decode(colourlane));
				square.removeLabel(125, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener30 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[127].setBackground(Color.decode(colourlane));
				square.removeLabel(127, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener31 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[143].setBackground(Color.decode(colourlane));
				square.removeLabel(143, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener32 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[158].setBackground(Color.decode(colourlane));
				square.removeLabel(158, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener33 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[157].setBackground(Color.decode(colourlane));
				square.removeLabel(157, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener34 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[156].setBackground(Color.decode(colourlane));
				square.removeLabel(156, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener35 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[155].setBackground(Color.decode(colourlane));
				square.removeLabel(155, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener36 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[154].setBackground(Color.decode(colourlane));
				square.removeLabel(154, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener37 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[153].setBackground(Color.decode(colourlane));
				square.removeLabel(153, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener38 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[136].setBackground(Color.decode(colourlane));
				square.removeLabel(136, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener39 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[120].setBackground(Color.decode(colourlane));
				square.removeLabel(120, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener40 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[119].setBackground(Color.decode(colourlane));
				square.removeLabel(119, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener41 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[135].setBackground(Color.decode(colourlane));
				square.removeLabel(135, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener42 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[151].setBackground(Color.decode(colourlane));
				square.removeLabel(151, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener43 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[149].setBackground(Color.decode(colourlane));
				square.removeLabel(149, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener44 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[148].setBackground(Color.decode(colourlane));
				square.removeLabel(148, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener45 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[147].setBackground(Color.decode(colourlane));
				square.removeLabel(147, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener46 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[146].setBackground(Color.decode(colourlane));
				square.removeLabel(146, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener47 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[145].setBackground(Color.decode(colourlane));
				square.removeLabel(145, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener48 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[144].setBackground(Color.decode(colourlane));
				square.removeLabel(144, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener49 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[112].setBackground(Color.decode(colourlane));
				square.removeLabel(112, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener50 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[113].setBackground(Color.decode(colourlane));
				square.removeLabel(113, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener51 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[114].setBackground(Color.decode(colourlane));
				square.removeLabel(114, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener52 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[115].setBackground(Color.decode(colourlane));
				square.removeLabel(115, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener53 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[116].setBackground(Color.decode(colourlane));
				square.removeLabel(116, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener54 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[117].setBackground(Color.decode(colourlane));
				square.removeLabel(117, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener55 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[85].setBackground(Color.decode(colourlane));
				square.removeLabel(85, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener56 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[84].setBackground(Color.decode(colourlane));
				square.removeLabel(84, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener57 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[83].setBackground(Color.decode(colourlane));
				square.removeLabel(83, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener58 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[81].setBackground(Color.decode(colourlane));
				square.removeLabel(81, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		// start edits
		ActionListener buttonListener59 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[80].setBackground(Color.decode(colourlane));
				square.removeLabel(80, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener60 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[64].setBackground(Color.decode(colourlane));
				square.removeLabel(64, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener61 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[65].setBackground(Color.decode(colourlane));
				square.removeLabel(65, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener62 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[66].setBackground(Color.decode(colourlane));
				square.removeLabel(66, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener63 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[67].setBackground(Color.decode(colourlane));
				square.removeLabel(67, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener64 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[68].setBackground(Color.decode(colourlane));
				square.removeLabel(68, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener65 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[53].setBackground(Color.decode(colourlane));
				square.removeLabel(53, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener66 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[37].setBackground(Color.decode(colourlane));
				square.removeLabel(37, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener67 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[36].setBackground(Color.decode(colourlane));
				square.removeLabel(36, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener68 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[34].setBackground(Color.decode(colourlane));
				square.removeLabel(34, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener69 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[33].setBackground(Color.decode(colourlane));
				square.removeLabel(33, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener70 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[32].setBackground(Color.decode(colourlane));
				square.removeLabel(32, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener71 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[16].setBackground(Color.decode(colourlane));
				square.removeLabel(16, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener72 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[1].setBackground(Color.decode(colourlane));
				square.removeLabel(1, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener73 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[2].setBackground(Color.decode(colourlane));
				square.removeLabel(2, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener74 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[3].setBackground(Color.decode(colourlane));
				square.removeLabel(3, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener75 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[4].setBackground(Color.decode(colourlane));
				square.removeLabel(4, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener76 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[5].setBackground(Color.decode(colourlane));
				square.removeLabel(5, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener77 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[6].setBackground(Color.decode(colourlane));
				square.removeLabel(6, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener78 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[23].setBackground(Color.decode(colourlane));
				square.removeLabel(23, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};
		ActionListener buttonListener79 = e -> {
			valid = testResources(p);
			if (valid == true) {
				mainInstance.getLabels()[39].setBackground(Color.decode(colourlane));
				square.removeLabel(39, p);
				p.setCB(p.getCB() + 1);
				builtcyc = true;
				frame.dispose();
				revalid();
				goo.run();
			}
		};

		button0.addActionListener(buttonListener0);
		button1.addActionListener(buttonListener1);
		button2.addActionListener(buttonListener2);
		button3.addActionListener(buttonListener3);
		button4.addActionListener(buttonListener4);
		button5.addActionListener(buttonListener5);
		button6.addActionListener(buttonListener6);
		button7.addActionListener(buttonListener7);
		button8.addActionListener(buttonListener8);
		button9.addActionListener(buttonListener9);
		button10.addActionListener(buttonListener10);
		button11.addActionListener(buttonListener11);
		button12.addActionListener(buttonListener12);
		button13.addActionListener(buttonListener13);
		button14.addActionListener(buttonListener14);
		button15.addActionListener(buttonListener15);
		button16.addActionListener(buttonListener16);
		button17.addActionListener(buttonListener17);
		button18.addActionListener(buttonListener18);
		button19.addActionListener(buttonListener19);
		button20.addActionListener(buttonListener20);
		button21.addActionListener(buttonListener21);
		button22.addActionListener(buttonListener22);
		button23.addActionListener(buttonListener23);
		button24.addActionListener(buttonListener24);
		button25.addActionListener(buttonListener25);
		button26.addActionListener(buttonListener26);
		button27.addActionListener(buttonListener27);
		button28.addActionListener(buttonListener28);
		button29.addActionListener(buttonListener29);
		button30.addActionListener(buttonListener30);
		button31.addActionListener(buttonListener31);
		button32.addActionListener(buttonListener32);
		button33.addActionListener(buttonListener33);
		button34.addActionListener(buttonListener34);
		button35.addActionListener(buttonListener35);
		button36.addActionListener(buttonListener36);
		button37.addActionListener(buttonListener37);
		button38.addActionListener(buttonListener38);
		button39.addActionListener(buttonListener39);
		button40.addActionListener(buttonListener40);
		button41.addActionListener(buttonListener41);
		button42.addActionListener(buttonListener42);
		button43.addActionListener(buttonListener43);
		button44.addActionListener(buttonListener44);
		button45.addActionListener(buttonListener45);
		button46.addActionListener(buttonListener46);
		button47.addActionListener(buttonListener47);
		button48.addActionListener(buttonListener48);
		button49.addActionListener(buttonListener49);

		button50.addActionListener(buttonListener50);
		button51.addActionListener(buttonListener51);
		button52.addActionListener(buttonListener52);
		button53.addActionListener(buttonListener53);
		button54.addActionListener(buttonListener54);
		button55.addActionListener(buttonListener55);
		button56.addActionListener(buttonListener56);
		button57.addActionListener(buttonListener57);
		button58.addActionListener(buttonListener58);
		button59.addActionListener(buttonListener59);
		button60.addActionListener(buttonListener60);
		button61.addActionListener(buttonListener61);
		button62.addActionListener(buttonListener62);
		button63.addActionListener(buttonListener63);
		button64.addActionListener(buttonListener64);
		button65.addActionListener(buttonListener65);
		button66.addActionListener(buttonListener66);
		button67.addActionListener(buttonListener67);
		button68.addActionListener(buttonListener68);
		button69.addActionListener(buttonListener69);
		button70.addActionListener(buttonListener70);
		button71.addActionListener(buttonListener71);
		button72.addActionListener(buttonListener72);
		button73.addActionListener(buttonListener73);
		button74.addActionListener(buttonListener74);
		button75.addActionListener(buttonListener75);
		button76.addActionListener(buttonListener76);
		button77.addActionListener(buttonListener77);
		button78.addActionListener(buttonListener78);
		button79.addActionListener(buttonListener79);

		// Check the conditions for claimed squares and update labels

		for (int sq = 0; sq < claimedSquares.length; sq++) {
			if (claimedSquares[sq] == 40) {
				remove(labels[40]);
				labels[40] = button0;
				add(button0, 40);
			}
			if (claimedSquares[sq] == 24) {
				remove(labels[24]);
				labels[24] = button1;
				add(button1, 24);
			}
			if (claimedSquares[sq] == 8) {
				remove(labels[8]);
				labels[8] = button2;
				add(button2, 8);
			}
			if (claimedSquares[sq] == 10) {
				remove(labels[10]);
				labels[10] = button3;
				add(button3, 10);
			}
			if (claimedSquares[sq] == 11) {
				remove(labels[11]);
				labels[11] = button4;
				add(button4, 11);
			}

			if (claimedSquares[sq] == 12) {
				remove(labels[12]);
				labels[12] = button5;
				add(button5, 12);
			}
			if (claimedSquares[sq] == 13) {
				remove(labels[13]);
				labels[13] = button6;
				add(button6, 13);
			}
			if (claimedSquares[sq] == 15) {
				remove(labels[15]);
				labels[15] = button7;
				add(button7, 15);
			}
			if (claimedSquares[sq] == 31) {
				remove(labels[31]);
				labels[31] = button8;
				add(button8, 31);
			}
			if (claimedSquares[sq] == 47) {
				remove(labels[47]);
				labels[47] = button9;
				add(button9, 47);
			}

			if (claimedSquares[sq] == 46) {
				remove(labels[46]);
				labels[46] = button10;
				add(button10, 46);
			}
			if (claimedSquares[sq] == 45) {
				remove(labels[45]);
				labels[45] = button11;
				add(button11, 45);
			}
			if (claimedSquares[sq] == 44) {
				remove(labels[44]);
				labels[44] = button12;
				add(button12, 44);
			}
			if (claimedSquares[sq] == 42) {
				remove(labels[42]);
				labels[42] = button13;
				add(button13, 42);
			}
			if (claimedSquares[sq] == 58) {
				remove(labels[58]);
				labels[58] = button14;
				add(button14, 58);
			}
			if (claimedSquares[sq] == 74) {
				remove(labels[74]);
				labels[74] = button15;
				add(button15, 74);
			}
			if (claimedSquares[sq] == 75) {
				remove(labels[75]);
				labels[75] = button16;
				add(button16, 75);
			}
			if (claimedSquares[sq] == 76) {
				remove(labels[76]);
				labels[76] = button17;
				add(button17, 76);
			}
			if (claimedSquares[sq] == 78) {
				remove(labels[78]);
				labels[78] = button18;
				add(button18, 78);
			}
			if (claimedSquares[sq] == 79) {
				remove(labels[79]);
				labels[79] = button19;
				add(button19, 79);
			}

			if (claimedSquares[sq] == 95) {
				remove(labels[95]);
				labels[95] = button20;
				add(button20, 95);
			}
			if (claimedSquares[sq] == 94) {
				remove(labels[94]);
				labels[94] = button21;
				add(button21, 94);
			}
			if (claimedSquares[sq] == 93) {
				remove(labels[93]);
				labels[93] = button22;
				add(button22, 93);
			}
			if (claimedSquares[sq] == 92) {
				remove(labels[92]);
				labels[92] = button23;
				add(button23, 92);
			}
			if (claimedSquares[sq] == 91) {
				remove(labels[91]);
				labels[91] = button24;
				add(button24, 91);
			}
			if (claimedSquares[sq] == 90) {
				remove(labels[90]);
				labels[90] = button25;
				add(button25, 90);
			}

			if (claimedSquares[sq] == 122) {
				remove(labels[122]);
				labels[122] = button26;
				add(button26, 122);
			}
			if (claimedSquares[sq] == 123) {
				remove(labels[123]);
				labels[123] = button27;
				add(button27, 123);
			}
			if (claimedSquares[sq] == 124) {
				remove(labels[124]);
				labels[124] = button28;
				add(button28, 124);
			}
			if (claimedSquares[sq] == 125) {
				remove(labels[125]);
				labels[125] = button29;
				add(button29, 125);
			}
			if (claimedSquares[sq] == 127) {
				remove(labels[127]);
				labels[127] = button30;
				add(button30, 127);
			}

			if (claimedSquares[sq] == 143) {
				remove(labels[143]);
				labels[143] = button31;
				add(button31, 143);
			}
			if (claimedSquares[sq] == 158) {
				remove(labels[158]);
				labels[158] = button32;
				add(button32, 158);
			}
			if (claimedSquares[sq] == 157) {
				remove(labels[157]);
				labels[157] = button33;
				add(button33, 157);
			}
			if (claimedSquares[sq] == 156) {
				remove(labels[156]);
				labels[156] = button34;
				add(button34, 156);
			}
			if (claimedSquares[sq] == 155) {
				remove(labels[155]);
				labels[155] = button35;
				add(button35, 155);
			}
			if (claimedSquares[sq] == 154) {
				remove(labels[154]);
				labels[154] = button36;
				add(button36, 154);
			}
			if (claimedSquares[sq] == 153) {
				remove(labels[153]);
				labels[153] = button37;
				add(button37, 153);
			}
			if (claimedSquares[sq] == 136) {
				remove(labels[136]);
				labels[136] = button38;
				add(button38, 136);
			}
			if (claimedSquares[sq] == 120) {
				remove(labels[120]);
				labels[120] = button39;
				add(button39, 120);
			}

			if (claimedSquares[sq] == 119) {
				remove(labels[119]);
				labels[119] = button40;
				add(button40, 119);
			}
			if (claimedSquares[sq] == 135) {
				remove(labels[135]);
				labels[135] = button41;
				add(button41, 135);
			}
			if (claimedSquares[sq] == 151) {
				remove(labels[151]);
				labels[151] = button42;
				add(button42, 151);
			}
			if (claimedSquares[sq] == 149) {
				remove(labels[149]);
				labels[149] = button43;
				add(button43, 149);
			}
			if (claimedSquares[sq] == 148) {
				remove(labels[148]);
				labels[148] = button44;
				add(button44, 148);
			}
			if (claimedSquares[sq] == 147) {
				remove(labels[147]);
				labels[147] = button45;
				add(button45, 147);
			}

			if (claimedSquares[sq] == 146) {
				remove(labels[146]);
				labels[146] = button46;
				add(button46, 146);
			}
			if (claimedSquares[sq] == 145) {
				remove(labels[145]);
				labels[145] = button47;
				add(button47, 145);
			}
			if (claimedSquares[sq] == 144) {
				remove(labels[144]);
				labels[144] = button48;
				add(button48, 144);
			}
			if (claimedSquares[sq] == 112) {
				remove(labels[112]);
				labels[112] = button49;
				add(button49, 112);
			}
			if (claimedSquares[sq] == 113) {
				remove(labels[113]);
				labels[113] = button50;
				add(button50, 113);
			}

			if (claimedSquares[sq] == 114) {
				remove(labels[114]);
				labels[114] = button51;
				add(button51, 114);
			}
			if (claimedSquares[sq] == 115) {
				remove(labels[115]);
				labels[115] = button52;
				add(button52, 115);
			}
			if (claimedSquares[sq] == 116) {
				remove(labels[116]);
				labels[116] = button53;
				add(button53, 116);
			}
			if (claimedSquares[sq] == 117) {
				remove(labels[117]);
				labels[117] = button54;
				add(button54, 117);
			}
			if (claimedSquares[sq] == 85) {
				remove(labels[85]);
				labels[85] = button55;
				add(button55, 85);
			}

			if (claimedSquares[sq] == 84) {
				remove(labels[84]);
				labels[84] = button56;
				add(button56, 84);
			}
			if (claimedSquares[sq] == 83) {
				remove(labels[83]);
				labels[83] = button57;
				add(button57, 83);
			}
			if (claimedSquares[sq] == 81) {
				remove(labels[81]);
				labels[81] = button58;
				add(button58, 81);
			}
			if (claimedSquares[sq] == 80) {
				remove(labels[80]);
				labels[80] = button59;
				add(button59, 80);
			}

			if (claimedSquares[sq] == 64) {
				remove(labels[64]);
				labels[64] = button60;
				add(button60, 64);
			}
			if (claimedSquares[sq] == 65) {
				remove(labels[65]);
				labels[65] = button61;
				add(button61, 65);
			}
			if (claimedSquares[sq] == 66) {
				remove(labels[66]);
				labels[66] = button62;
				add(button62, 66);
			}
			if (claimedSquares[sq] == 67) {
				remove(labels[67]);
				labels[67] = button63;
				add(button63, 67);
			}
			if (claimedSquares[sq] == 68) {
				remove(labels[68]);
				labels[68] = button64;
				add(button64, 68);
			}

			if (claimedSquares[sq] == 53) {
				remove(labels[53]);
				labels[53] = button65;
				add(button65, 53);
			}
			if (claimedSquares[sq] == 37) {
				remove(labels[37]);
				labels[37] = button66;
				add(button66, 37);
			}
			if (claimedSquares[sq] == 36) {
				remove(labels[36]);
				labels[36] = button67;
				add(button67, 36);
			}
			if (claimedSquares[sq] == 34) {
				remove(labels[34]);
				labels[34] = button68;
				add(button68, 34);
			}
			if (claimedSquares[sq] == 33) {
				remove(labels[33]);
				labels[33] = button69;
				add(button69, 33);
			}

			if (claimedSquares[sq] == 32) {
				remove(labels[32]);
				labels[32] = button70;
				add(button70, 32);
			}
			if (claimedSquares[sq] == 16) {
				remove(labels[16]);
				labels[16] = button71;
				add(button71, 16);
			}
			if (claimedSquares[sq] == 1) {
				remove(labels[1]);
				labels[1] = button72;
				add(button72, 1);
			}
			if (claimedSquares[sq] == 2) {
				remove(labels[2]);
				labels[2] = button73;
				add(button73, 2);
			}
			if (claimedSquares[sq] == 3) {
				remove(labels[3]);
				labels[3] = button74;
				add(button74, 3);
			}
			if (claimedSquares[sq] == 4) {
				remove(labels[4]);
				labels[4] = button74;
				add(button75, 4);
			}
			if (claimedSquares[sq] == 5) {
				remove(labels[5]);
				labels[5] = button76;
				add(button76, 5);
			}
			if (claimedSquares[sq] == 6) {
				remove(labels[6]);
				labels[6] = button77;
				add(button77, 6);
			}
			if (claimedSquares[sq] == 23) {
				remove(labels[23]);
				labels[23] = button78;
				add(button78, 23);
			}
			if (claimedSquares[sq] == 39) {
				remove(labels[39]);
				labels[39] = button79;
				add(button79, 39);
			}

		}

		// Refresh the UI after all changes
		revalidate();
		repaint();
	}

	public static boolean testResources(player p) {

		valid = false;

		int money = p.getMoney();
		int mat = p.getMaterial();

		JFrame frame = new JFrame("Build Cycle Lane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 300);
		frame.setLocationRelativeTo(null);

		if (money < 2 || mat < 1) {
			String output = "Not enough Rands or Materials!";

			// Create a JDialog centered on the frame
			JDialog dialog = new JDialog(frame, "Warning", true);
			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(frame); // Centers the dialog
			dialog.setLayout(new BorderLayout());

			JLabel messageLabel = new JLabel(output, SwingConstants.CENTER);
			JButton okButton = new JButton("OK");

			// Close dialog when OK is clicked
			okButton.addActionListener(e -> dialog.dispose());

			JPanel buttonPanel = new JPanel();
			buttonPanel.add(okButton);

			dialog.add(messageLabel, BorderLayout.CENTER);
			dialog.add(buttonPanel, BorderLayout.SOUTH);

			dialog.setVisible(true);

			valid = false;
		} else {
			p.depleteResources();
			valid = true;
		}

		frame.setVisible(false);

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - frame.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - frame.getHeight() - 100; // 10px padding from the bottom

		frame.setLocation(x, y); // Set the location of the popup

		return valid;

	}

	public static void buildDS(player p, Runnable onbuildcomplete) {
		// test if owned section - get p1sq/ etc
		// test if all squares are not in fp1sq/ etc anymore to confirm all bought
		// if owned and all squares bought then allow purchase

		JFrame frame = new JFrame("Docking Stations");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Header Label
		JLabel header = new JLabel("Claim Docking Station", SwingConstants.CENTER);
		header.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(header, BorderLayout.NORTH);

		// Main panel with GridLayout (4 rows, 1 column)
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 4 rows, spacing of 5px

		// Row labels
		String[] rowLabels = { "Green  ", "Blue     ", "Red      ", "Yellow " };

		// Create buttons in a 4x4 layout
		for (int row = 0; row < 4; row++) {
			JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Left align, spacing
			JLabel rowHeader = new JLabel(rowLabels[row]);
			rowHeader.setFont(new Font("Arial", Font.BOLD, 14));
			rowPanel.add(rowHeader);

			for (int col = 0; col < 4; col++) {
				buttons[row][col] = new JButton(String.valueOf(col + 1)); // Create button
				rowPanel.add(buttons[row][col]); // Add to row panel
			}
			mainPanel.add(rowPanel);
		}
		int[] sectionSquares = {};
		if (p.getID() == 1) {
			sectionSquares = square.getp1sq();
		}
		if (p.getID() == 2) {
			sectionSquares = square.getp2sq();
		}
		if (p.getID() == 3) {
			sectionSquares = square.getp3sq();
		}
		if (p.getID() == 4) {
			sectionSquares = square.getp4sq();
		}

		// int[] allSquares = {};
		if (p.getID() == 1) {
			allSquares = square.getfp1sq();
		}
		if (p.getID() == 2) {
			allSquares = square.getfp2sq();
		}
		if (p.getID() == 3) {
			allSquares = square.getfp3sq();
		}
		if (p.getID() == 4) {
			allSquares = square.getfp4sq();
		}

		// tests that player owns section and all squares bought (presence of section
		// header square in first array,
		// NONE of squares in second array (they are removed from it once purchased)

		buttons[0][0].setEnabled(false);
		buttons[0][1].setEnabled(false);
		buttons[0][2].setEnabled(false);
		buttons[0][3].setEnabled(false);
		buttons[1][0].setEnabled(false);
		buttons[1][1].setEnabled(false);
		buttons[1][2].setEnabled(false);
		buttons[1][3].setEnabled(false);
		buttons[2][0].setEnabled(false);
		buttons[2][1].setEnabled(false);
		buttons[2][2].setEnabled(false);
		buttons[2][3].setEnabled(false);
		buttons[3][0].setEnabled(false);
		buttons[3][1].setEnabled(false);
		buttons[3][2].setEnabled(false);
		buttons[3][3].setEnabled(false);

		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 40) {
				buttons[0][0].setEnabled(true);
				buttons[0][0].revalidate(); // Force UI update
				buttons[0][0].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 40 || allSquares[j] == 24 || allSquares[j] == 8 || allSquares[j] == 10
							|| allSquares[j] == 11 || allSquares[j] == 501) {
						buttons[0][0].setEnabled(false);
						buttons[0][0].revalidate(); // Force UI update
						buttons[0][0].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[0][0].setEnabled(false);
				buttons[0][0].revalidate(); // Force UI update
				buttons[0][0].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 12) {
				buttons[0][1].setEnabled(true);
				buttons[0][1].revalidate(); // Force UI update
				buttons[0][1].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 12 || allSquares[j] == 13 || allSquares[j] == 15 || allSquares[j] == 31
							|| allSquares[j] == 47 || allSquares[j] == 502) {
						buttons[0][1].setEnabled(false);
						buttons[0][1].revalidate(); // Force UI update
						buttons[0][1].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[0][1].setEnabled(false);
				buttons[0][1].revalidate(); // Force UI update
				buttons[0][1].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 46) {
				buttons[0][2].setEnabled(true);
				buttons[0][2].revalidate(); // Force UI update
				buttons[0][2].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 46 || allSquares[j] == 45 || allSquares[j] == 44 || allSquares[j] == 42
							|| allSquares[j] == 58 || allSquares[j] == 503) {
						buttons[0][2].setEnabled(false);
						buttons[0][2].revalidate(); // Force UI update
						buttons[0][2].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[0][2].setEnabled(false);
				buttons[0][2].revalidate(); // Force UI update
				buttons[0][2].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 74) {
				buttons[0][3].setEnabled(true);
				buttons[0][3].revalidate(); // Force UI update
				buttons[0][3].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 74 || allSquares[j] == 75 || allSquares[j] == 76 || allSquares[j] == 78
							|| allSquares[j] == 79 || allSquares[j] == 504) {
						buttons[0][3].setEnabled(false);
						buttons[0][3].revalidate(); // Force UI update
						buttons[0][3].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[0][3].setEnabled(false);
				buttons[0][3].revalidate(); // Force UI update
				buttons[0][3].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 95) {
				buttons[1][0].setEnabled(true);
				buttons[1][0].revalidate(); // Force UI update
				buttons[1][0].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 95 || allSquares[j] == 94 || allSquares[j] == 93 || allSquares[j] == 92
							|| allSquares[j] == 91 || allSquares[j] == 90 || allSquares[j] == 505) {
						buttons[1][0].setEnabled(false);
						buttons[1][0].revalidate(); // Force UI update
						buttons[1][0].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[1][0].setEnabled(false);
				buttons[1][0].revalidate(); // Force UI update
				buttons[1][0].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 122) {
				buttons[1][1].setEnabled(true);
				buttons[1][1].revalidate(); // Force UI update
				buttons[1][1].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 122 || allSquares[j] == 123 || allSquares[j] == 124 || allSquares[j] == 125
							|| allSquares[j] == 127 || allSquares[j] == 506) {
						buttons[1][1].setEnabled(false);
						buttons[1][1].revalidate(); // Force UI update
						buttons[1][1].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[1][1].setEnabled(false);
				buttons[1][1].revalidate(); // Force UI update
				buttons[1][1].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 143) {
				buttons[1][2].setEnabled(true);
				buttons[1][2].revalidate(); // Force UI update
				buttons[1][2].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 143 || allSquares[j] == 158 || allSquares[j] == 157 || allSquares[j] == 156
							|| allSquares[j] == 155 || allSquares[j] == 507) {
						buttons[1][2].setEnabled(false);
						buttons[1][2].revalidate(); // Force UI update
						buttons[1][2].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[1][2].setEnabled(false);
				buttons[1][2].revalidate(); // Force UI update
				buttons[1][2].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 154) {
				buttons[1][3].setEnabled(true);
				buttons[1][3].revalidate(); // Force UI update
				buttons[1][3].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 154 || allSquares[j] == 153 || allSquares[j] == 136 || allSquares[j] == 120
							|| allSquares[j] == 508) {
						buttons[1][3].setEnabled(false);
						buttons[1][3].revalidate(); // Force UI update
						buttons[1][3].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[1][3].setEnabled(false);
				buttons[1][3].revalidate(); // Force UI update
				buttons[1][3].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 119) {
				buttons[2][0].setEnabled(true);
				buttons[2][0].revalidate(); // Force UI update
				buttons[2][0].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 119 || allSquares[j] == 135 || allSquares[j] == 151 || allSquares[j] == 149
							|| allSquares[j] == 148 || allSquares[j] == 147 || allSquares[j] == 509) {
						buttons[2][0].setEnabled(false);
						buttons[2][0].revalidate(); // Force UI update
						buttons[2][0].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[2][0].setEnabled(false);
				buttons[2][0].revalidate(); // Force UI update
				buttons[2][0].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 146) {
				buttons[2][1].setEnabled(true);
				buttons[2][1].revalidate(); // Force UI update
				buttons[2][1].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 146 || allSquares[j] == 145 || allSquares[j] == 144 || allSquares[j] == 112
							|| allSquares[j] == 113 || allSquares[j] == 510) {
						buttons[2][1].setEnabled(false);
						buttons[2][1].revalidate(); // Force UI update
						buttons[2][1].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[2][1].setEnabled(false);
				buttons[2][1].revalidate(); // Force UI update
				buttons[2][1].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 114) {
				buttons[2][2].setEnabled(true);
				buttons[2][2].revalidate(); // Force UI update
				buttons[2][2].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 114 || allSquares[j] == 115 || allSquares[j] == 116 || allSquares[j] == 117
							|| allSquares[j] == 85 || allSquares[j] == 511) {
						buttons[2][2].setEnabled(false);
						buttons[2][2].revalidate(); // Force UI update
						buttons[2][2].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[2][2].setEnabled(false);
				buttons[2][2].revalidate(); // Force UI update
				buttons[2][2].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 84) {
				buttons[2][3].setEnabled(true);
				buttons[2][3].revalidate(); // Force UI update
				buttons[2][3].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 84 || allSquares[j] == 83 || allSquares[j] == 81 || allSquares[j] == 80
							|| allSquares[j] == 512) {
						buttons[2][3].setEnabled(false);
						buttons[2][3].revalidate(); // Force UI update
						buttons[2][3].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[2][3].setEnabled(false);
				buttons[2][3].revalidate(); // Force UI update
				buttons[2][3].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 64) {
				buttons[3][0].setEnabled(true);
				buttons[3][0].revalidate(); // Force UI update
				buttons[3][0].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 64 || allSquares[j] == 65 || allSquares[j] == 66 || allSquares[j] == 67
							|| allSquares[j] == 68 || allSquares[j] == 513) {
						buttons[3][0].setEnabled(false);
						buttons[3][0].revalidate(); // Force UI update
						buttons[3][0].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[3][0].setEnabled(false);
				buttons[3][0].revalidate(); // Force UI update
				buttons[3][0].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 53) {
				buttons[3][1].setEnabled(true);
				buttons[3][1].revalidate(); // Force UI update
				buttons[3][1].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 53 || allSquares[j] == 37 || allSquares[j] == 36 || allSquares[j] == 34
							|| allSquares[j] == 33 || allSquares[j] == 514) {
						buttons[3][1].setEnabled(false);
						buttons[3][1].revalidate(); // Force UI update
						buttons[3][1].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[3][1].setEnabled(false);
				buttons[3][1].revalidate(); // Force UI update
				buttons[3][1].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 32) {
				buttons[3][2].setEnabled(true);
				buttons[3][2].revalidate(); // Force UI update
				buttons[3][2].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 32 || allSquares[j] == 16 || allSquares[j] == 1 || allSquares[j] == 2
							|| allSquares[j] == 3 || allSquares[j] == 515) {
						buttons[3][2].setEnabled(false);
						buttons[3][2].revalidate(); // Force UI update
						buttons[3][2].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[3][2].setEnabled(false);
				buttons[3][2].revalidate(); // Force UI update
				buttons[3][2].repaint();
			}
		}
		for (int i = 0; i < sectionSquares.length; i++) {
			if (sectionSquares[i] == 4) {
				buttons[3][3].setEnabled(true);
				buttons[3][3].revalidate(); // Force UI update
				buttons[3][3].repaint();
				for (int j = 0; j < allSquares.length; j++) {
					if (allSquares[j] == 4 || allSquares[j] == 5 || allSquares[j] == 6 || allSquares[j] == 23
							|| allSquares[j] == 39 || allSquares[j] == 516) {
						buttons[3][3].setEnabled(false);
						buttons[3][3].revalidate(); // Force UI update
						buttons[3][3].repaint();
						break; // Stop checking once we find a match
					}
				}
				break;
			} else {
				buttons[3][3].setEnabled(false);
				buttons[3][3].revalidate(); // Force UI update
				buttons[3][3].repaint();
			}
		}

		buttons[0][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);

				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 501;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[9]).setIcon(p.getPlayerDS());
				p.setDockingSquares(9);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[0][1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 502;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[14]).setIcon(p.getPlayerDS());
				p.setDockingSquares(14);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[0][2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 503;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[43]).setIcon(p.getPlayerDS());
				p.setDockingSquares(43);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[0][3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 504;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[77]).setIcon(p.getPlayerDS());
				p.setDockingSquares(77);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[1][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 505;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[106]).setIcon(p.getPlayerDS());
				p.setDockingSquares(106);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[1][1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 506;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[126]).setIcon(p.getPlayerDS());
				p.setDockingSquares(126);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[1][2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 507;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[159]).setIcon(p.getPlayerDS());
				p.setDockingSquares(159);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[1][3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 508;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[152]).setIcon(p.getPlayerDS());
				p.setDockingSquares(152);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[2][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 509;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[150]).setIcon(p.getPlayerDS());
				p.setDockingSquares(150);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[2][1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 510;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[128]).setIcon(p.getPlayerDS());
				p.setDockingSquares(128);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[2][2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 511;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[101]).setIcon(p.getPlayerDS());
				p.setDockingSquares(101);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[2][3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 512;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[82]).setIcon(p.getPlayerDS());
				p.setDockingSquares(82);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[3][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 513;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[69]).setIcon(p.getPlayerDS());
				p.setDockingSquares(69);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[3][1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 514;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[35]).setIcon(p.getPlayerDS());
				p.setDockingSquares(35);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[3][2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 515;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[0]).setIcon(p.getPlayerDS());
				p.setDockingSquares(0);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});
			}
		});
		buttons[3][3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setMoney(p.getMoney() + 10);
				p.setMaterial(p.getMaterial() + 5);
				built = true;
				System.out.println(p.getMoney() + " " + p.getMaterial() + " " + built);
				frame.dispose();
				p.setDSC(p.getDSC() + 1);
				for (int i = 0; i < allSquares.length; i++) {
					if (allSquares[i] == -1) {
						allSquares[i] = 516;
						break;
					}
				}
				((JLabel) mainInstance.getLabels()[7]).setIcon(p.getPlayerDS());
				p.setDockingSquares(7);
				revalid();
				dsclaim(() -> {
					onbuildcomplete.run();
				});

			}
		});

		// Add the "Cancel" button at the bottom
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Close the frame when clicked
				built = false;
				onbuildcomplete.run();
			}
		});

		// Panel to hold the cancel button
		JPanel cancelPanel = new JPanel();
		cancelPanel.add(cancelButton); // Add the cancel button to the cancelPanel

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - frame.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - frame.getHeight() - 100; // 10px padding from the bottom

		frame.setLocation(x, y); // Set the location of the popup

		// Add the cancelPanel to the bottom of the frame
		frame.add(cancelPanel, BorderLayout.SOUTH);

		// Add the mainPanel (grid of buttons) to the center of the frame
		frame.add(mainPanel, BorderLayout.CENTER);

		// Show the frame
		frame.setVisible(true);

	}

	public static void dsclaim(Runnable moveOn) {
		JFrame frame = new JFrame("You built a docking station!");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);

		ImageIcon bob = new ImageIcon("Images/dsclaim.png");

		// Resize the image to fit within the JFrame (600x450)
		Image img0 = bob.getImage().getScaledInstance(500, 350, Image.SCALE_SMOOTH);
		ImageIcon resizedBob = new ImageIcon(img0);

		JPanel imgPanel = new JPanel();
		JPanel bPanel = new JPanel();
		bPanel.setPreferredSize(new Dimension(150, 300));

		JLabel img = new JLabel();
		JButton close = new JButton("Close");

		bPanel.add(close);

		close.addActionListener(e -> {
			frame.dispose();
			if (moveOn != null) {
				moveOn.run(); // Execute the next step (e.g., moving the player)
			}
		});

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Calculate the X position for bottom-right (screen width - popup width)
		int x = screenSize.width - frame.getWidth() - 10; // 10px padding from the right

		// Calculate the Y position for bottom-right (screen height - popup height)
		int y = screenSize.height - frame.getHeight() - 100; // 10px padding from the bottom

		frame.setLocation(x, y); // Set the location of the popup

		img.setIcon(resizedBob);
		imgPanel.add(img);
		frame.add(imgPanel, BorderLayout.WEST);
		frame.add(bPanel, BorderLayout.EAST);

		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

	}

	public static void badEnding() {

		turnframe.dispose();

		JFrame frame = new JFrame("Bad Ending");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 250);
		frame.setLocationRelativeTo(null); // Centers the frame on the screen

		// Create a panel with GridBagLayout
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Multi-line text in JLabel (HTML allows line breaks)
		JLabel intro = new JLabel("<html><div style='text-align: center;'>"
				+ "Careful! You never know what life is going<br>to throw at you, and it won’t always be good.<br>An unexpected event has pushed you into the<br>red and unfortunately that means the project has to end.\r\n"
				+ "<br><br>Thanks for playing!\r\n" + "" + "</div></html>");
		intro.setHorizontalAlignment(SwingConstants.CENTER);
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

				bigframe.dispose();
				gameStats(()->{});
			}
		});

		frame.add(panel);
		frame.setVisible(true);
	}

	public static void testGoodEnding(Runnable yippee) {

		// Retrieve and filter docking squares (remove -1 values)
		int[] dockingSquares0 = filterNegativeOnes(pl[0].getDockingSquares());
		int[] dockingSquares1 = filterNegativeOnes(pl[1].getDockingSquares());
		int[] dockingSquares2 = filterNegativeOnes(pl[2].getDockingSquares());
		int[] dockingSquares3 = filterNegativeOnes(pl[3].getDockingSquares());

		// Calculate total length
		int totalLength = dockingSquares0.length + dockingSquares1.length + dockingSquares2.length
				+ dockingSquares3.length;
		int[] combinedDockingSquares = new int[totalLength];

		// Copy filtered data into new array
		System.arraycopy(dockingSquares0, 0, combinedDockingSquares, 0, dockingSquares0.length);
		System.arraycopy(dockingSquares1, 0, combinedDockingSquares, dockingSquares0.length, dockingSquares1.length);
		System.arraycopy(dockingSquares2, 0, combinedDockingSquares, dockingSquares0.length + dockingSquares1.length,
				dockingSquares2.length);
		System.arraycopy(dockingSquares3, 0, combinedDockingSquares,
				dockingSquares0.length + dockingSquares1.length + dockingSquares2.length, dockingSquares3.length);

		if (combinedDockingSquares.length < 16) {
			yippee.run();
		} else {
			goodEnding();
		}

	}

	private static int[] filterNegativeOnes(int[] array) {
		return Arrays.stream(array).filter(num -> num != -1).toArray();
	}

	public static void goodEnding() {
		gameStats( () -> {
		SwingUtilities.invokeLater(() -> new ScrollingCredits());
		});
	}

	public static void gameStats(Runnable finished) {
		JFrame frame = new JFrame("Instructions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null); // Centers the frame on the screen

		// Create a panel with GridBagLayout
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Multi-line text in JLabel (HTML allows line breaks)
		JLabel intro = new JLabel("<html><div style='text-align: center;'>" + "Game Stats:\r\n"
				+ "<br><br>Player 1:<br>-	Sections owned: "+pl[0].getSO()+"\r\n" + "<br>-	Cycle Lanes purchased: " + pl[0].getCB()+ "\r\n"
				+ "<br>-	Docking Stations Claimed: "+pl[0].getDSC()+"\r\n" + "<br><br>Player 2:<br>-	Sections owned: "+pl[1].getSO()+"\r\n"
				+ "<br>-	Cycle Lanes purchased: " +  pl[1].getCB() + "\r\n" + "<br>-	Docking Stations Claimed: "+pl[1].getDSC()+"\r\n"
				+ "<br><br>Player 3:<br>-	Sections owned: "+pl[2].getSO()+"\r\n" + "<br>-	Cycle Lanes purchased: "+  pl[2].getCB()+"\r\n"
				+ "<br>-	Docking Stations Claimed: "+pl[2].getDSC()+"\r\n" + "<br><br>Player 4:<br>-	Sections owned: "+pl[3].getSO()+"\r\n"
				+ "<br>-	Cycle Lanes purchased: "+ pl[3].getCB()+"\r\n" + "<br>-	Docking Stations Claimed: "+pl[3].getDSC()+"\r\n" + "</div></html>");
		intro.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setPreferredSize(new Dimension(600, 600));
		intro.setPreferredSize(new Dimension(500, 500));
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
				finished.run();
			}
		});

		frame.add(panel);
		frame.setVisible(true);
	}


    public static void main() {
		startPage.instructions();
	}
}
