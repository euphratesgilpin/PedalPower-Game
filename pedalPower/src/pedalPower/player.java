package pedalPower;

import java.awt.Image;

import javax.swing.ImageIcon;

public class player {

	private int ID;
	private final int playerNum = 4;
	private static int playerCount = 0;
	private int money = 30;
	private int material = 15;
	private int startSquare;
	private int currentSquare;
	// private boolean activePlayer;
	private ImageIcon playerIcon;
	private ImageIcon playerDS;
	private int[] dockingsq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private String playerName;
	private int sectionC = 0;
	private int cycBought = 0;
	private int dsClaimed = 0;

	public player(String name) {
		if (playerCount <= playerNum) {
			playerCount++;
			setID(playerCount);
			setMoney(money);
			setMaterial(material);
			setStartSquare(playerCount, startSquare);
			currentSquare = startSquare;
			setImageIcon(playerCount);
			setPlayerDS(playerCount);
			setPlayerName(name);
		}
	}
	// 1 green starts 40
	// 4 yellow starts 63
	// 2 blue starts 95
	// 3 red starts 119
	public void setSO(int SC) {
		this.sectionC = SC;
	}
	public void setCB(int CB) {
		this.cycBought = CB;
	}
	public void setDSC(int DSC) {
		this.dsClaimed = DSC;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public void setStartSquare(int pNum, int startSquare) {
		if (pNum == 1) {
			this.startSquare = 40;
		} else if (pNum == 2) {
			this.startSquare = 95;
		} else if (pNum == 3) {
			this.startSquare = 119;
		} else if (pNum == 4) {
			this.startSquare = 64;
		} else {
			System.out.println("something went wrong with player assignment");
		}
	}

	public void setCurrentSquare(int square) {
		this.currentSquare = square;
	}

	public void setImageIcon(int numP) {
		if (numP == 1) {
			ImageIcon temp = new ImageIcon("Images/one.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			this.playerIcon = new ImageIcon(img0);
		} else if (numP == 2) {
			ImageIcon temp = new ImageIcon("Images/two.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			this.playerIcon = new ImageIcon(img0);
		} else if (numP == 3) {
			ImageIcon temp = new ImageIcon("Images/three.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			this.playerIcon = new ImageIcon(img0);
		} else if (numP == 4) {
			ImageIcon temp = new ImageIcon("Images/four.png");
			Image img0 = temp.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
			this.playerIcon = new ImageIcon(img0);
		}
	}

	public void setPlayerDS(int numP) {
		if (numP == 1) {
			ImageIcon temp = new ImageIcon("Images/dsgreen.png");
			Image img0 = temp.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
			this.playerDS = new ImageIcon(img0);
		} else if (numP == 2) {
			ImageIcon temp = new ImageIcon("Images/dsblue.png");
			Image img0 = temp.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
			this.playerDS = new ImageIcon(img0);
		} else if (numP == 3) {
			ImageIcon temp = new ImageIcon("Images/dsred.png");
			Image img0 = temp.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
			this.playerDS = new ImageIcon(img0);
		} else if (numP == 4) {
			ImageIcon temp = new ImageIcon("Images/dsyellow.png");
			Image img0 = temp.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
			this.playerDS = new ImageIcon(img0);
		}
	}

	public void setDockingSquares(int newSquare) {
		for (int i = 0; i < dockingsq.length; i++) {
			if (dockingsq[i] == -1) {
				dockingsq[i] = newSquare;
				break;
			}
		}
	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public int getSO() {
		return this.sectionC;
	}
	public int getCB() {
		return this.cycBought;
	}
	public int getDSC() {
		return this.dsClaimed;
	}
	
	public int[] getDockingSquares() {
		return this.dockingsq;
	}

	public int getID() {
		return this.ID;
	}

	public ImageIcon getPlayerDS() {
		return this.playerDS;
	}

	public int getMoney() {
		return this.money;
	}

	public int getMaterial() {
		return this.material;
	}

	public int getCurrentSquare() {
		return this.currentSquare;
	}

	public ImageIcon getImage() {
		return this.playerIcon;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}

	public String getDetails() {
		String output = "Player Num: " + ID + "     Money: " + money + "      Material: " + material
				+ "     Start Square: " + startSquare;
		return output;
	}

	public String toString() {
		return getDetails();
	}

	/*
	 * public static boolean buildCyclePath() { return buildPath(); }
	 * 
	 * public static boolean buildDockingStation() { return buildStation(); }
	 */

	public void depleteResources() {
		this.money -= 2;
		this.material -= 1;
		System.out.println("Money: " + this.money + " Materials: " + this.material);
	}

}
