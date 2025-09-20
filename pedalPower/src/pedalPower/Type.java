package pedalPower;

public enum Type {
		ACTION("Action Card"), EVENT("Event Card");
	
		private String Type;

		private Type(String genr) {
			Type = genr;
		}

		/**
		 * Allows ImageType to be called to output the text instead of the title of the
		 * genre
		 */
		public String toString() {
			return Type;
		}
}
