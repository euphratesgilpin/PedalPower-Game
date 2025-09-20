package pedalPower;

import javax.swing.ImageIcon;

public class card {
		private Type type;
		// private String imageLink;
		private String descr;
		private int money;
		private int material;
		private ImageIcon img;

		public card(String type, String link, String descr, int money, int material) {
			setType(type);
			setImage(link);
			setDescr(descr);
			setMoney(money);
			setMaterial(material);
		}

		public void setType(String str) {
			if (str.equals("Event")) {
				this.type = Type.EVENT;
			} else if (str.equals("Action")) {
				this.type = Type.ACTION;
			} else {
				System.out.println("Something went wrong with type assignment");
			}
		}
		
		public void setImage(String link) {
			this.img = new ImageIcon(link);
		}
		
		public void setDescr(String desc) {
			this.descr = desc;
		}
		
		public void setMoney(int money) {
			this.money = money;
		}
		
		public void setMaterial(int material) {
			this.material = material;
		}
		
		public String getType() {
			return type.toString();
		}
		
		public ImageIcon getLink() {
			return this.img;
		}
		
		public String getDescr() {
			return this.descr;
		}
		
		public int getMoney() {
			return this.money;
		}
		
		public int getMaterial() {
			return this.material;
		}

		public String getDetails() {
			String output = "Card Type: " + type + "    Image Link: " + img + "      Money Value: " + money + "      Material Value: " + material  + "     Description: " + descr ;
			return output;
		}

		public String toString() {
			return getDetails();
		}
}
