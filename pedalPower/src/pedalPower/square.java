package pedalPower;

import javax.swing.JFrame;

public class square {
	private static int[] SQUARES = { 40, 24, 8, 9, 10, 11, 12, 13, 14, 15, 31, 47, 46, 45, 44, 43, 42, 58, 74, 75, 76,
			77, 78, 79, 95, 94, 93, 92, 91, 90, 106, 122, 123, 123, 125, 126, 127, 143, 159, 158, 157, 156, 155, 154,
			153, 152, 136, 120, 119, 135, 151, 150, 149, 148, 147, 146, 145, 144, 128, 112, 113, 114, 115, 116, 117,
			101, 85, 84, 83, 82, 81, 80, 64, 65, 66, 67, 68, 69, 53, 37, 36, 35, 34, 33, 32, 16, 0, 1, 2, 3, 4, 5, 6, 7,
			23, 39 };
	static int[] activeSQUARES = new int[4]; // needs to be able to be assigned and updated
	private final int[] cycleSQUARES = { 40, 24, 8, 10, 11, 12, 13, 15, 31, 47, 46, 45, 44, 42, 58, 74, 75, 76, 78, 79,
			95, 94, 93, 92, 91, 90, 122, 123, 123, 125, 127, 143, 158, 157, 156, 155, 154, 153, 136, 120, 119, 135, 151,
			150, 148, 147, 146, 145, 144, 112, 113, 114, 115, 116, 117, 85, 84, 83, 81, 80, 64, 65, 66, 67, 68, 53, 37,
			36, 35, 34, 33, 32, 16, 1, 2, 3, 4, 5, 6, 23, 39 };
	//private int[] activeCycleSQUARES = new int[80];
	private static int[] dockingSQUARES = { 9, 14, 43, 77, 106, 126, 159, 152, 149, 128, 101, 82, 69, 35, 0, 7 };
	private int[] activeDockingSQUARES = new int[16];
	private static int[] eventSQUARES = { 11, 45, 94, 154, 146, 116, 33, 6 };
	private static int[] actionSQUARES = { 24, 47, 75, 91, 127, 156, 148, 113, 84, 68, 34, 4 };

	private static int[] p1sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private static int[] p2sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private static int[] p3sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private static int[] p4sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	private static int[] fp1sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1 };
	private static int[] fp2sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1 };
	private static int[] fp3sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1 };
	private static int[] fp4sq = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1 };

	public static int[] getp1sq() {
		return p1sq;
	}

	public static int[] getp2sq() {
		return p2sq;
	}

	public static int[] getp3sq() {
		return p3sq;
	}

	public static int[] getp4sq() {
		return p4sq;
	}

	public static int[] getfp1sq() {
		return fp1sq;
	}

	public static int[] getfp2sq() {
		return fp2sq;
	}

	public static int[] getfp3sq() {
		return fp3sq;
	}

	public static int[] getfp4sq() {
		return fp4sq;
	}

	public static int[] getdockingSQUARES() {
		return dockingSQUARES;
	}

	public static int[] getActionSQUARES() {
		return actionSQUARES;
	}

	static void setpsq(player p, int sq) { // set player squares (ie claimed but not yet cycle lanes)

		for (int i = 0; i <= 31; i++) {
			if (p.getID() == 1 && (p1sq[i] == -1)) {
				if (p1sq[i] == -1) {
					p1sq[i] = sq;
					setFullPsq(sq, p);

				}
				break;
			}
			if (p.getID() == 2 && (p2sq[i] == -1)) {
				if (p2sq[i] == -1) {
					p2sq[i] = sq;
					setFullPsq(sq, p);
				}
				break;
			}
			if (p.getID() == 3 && (p3sq[i] == -1)) {
				if (p3sq[i] == -1) {
					p3sq[i] = sq;
					setFullPsq(sq, p);
				}
				break;
			}
			if (p.getID() == 4 && (p4sq[i] == -1)) {
				if (p4sq[i] == -1) {
					p4sq[i] = sq;
					setFullPsq(sq, p);
				}
				break;
			}
		}
	}

	public static void setFullPsq(int sq, player p) {

		for (int i = 0; i <= 79; i++) {
			if (p.getID() == 1) {
				if (fp1sq[i] == -1) {
					if (sq == 40) {
						fp1sq[i] = 40;
						fp1sq[i + 1] = 24;
						fp1sq[i + 2] = 8;
						fp1sq[i + 3] = 10;
						fp1sq[i + 4] = 11;
					}
					if (sq == 12) {
						fp1sq[i] = 12;
						fp1sq[i + 1] = 13;
						fp1sq[i + 2] = 15;
						fp1sq[i + 3] = 31;
						fp1sq[i + 4] = 47;
					}
					if (sq == 46) {
						fp1sq[i] = 46;
						fp1sq[i + 1] = 45;
						fp1sq[i + 2] = 44;
						fp1sq[i + 3] = 42;
						fp1sq[i + 4] = 58;
					}
					if (sq == 74) {
						fp1sq[i] = 74;
						fp1sq[i + 1] = 75;
						fp1sq[i + 2] = 76;
						fp1sq[i + 3] = 78;
						fp1sq[i + 4] = 79;
					}
					if (sq == 95) {
						fp1sq[i] = 95;
						fp1sq[i + 1] = 94;
						fp1sq[i + 2] = 93;
						fp1sq[i + 3] = 92;
						fp1sq[i + 4] = 91;
						fp1sq[i + 5] = 90;
					}
					if (sq == 122) {
						fp1sq[i] = 122;
						fp1sq[i + 1] = 123;
						fp1sq[i + 2] = 124;
						fp1sq[i + 3] = 125;
						fp1sq[i + 4] = 127;
					}
					if (sq == 143) {
						fp1sq[i] = 143;
						fp1sq[i + 1] = 158;
						fp1sq[i + 2] = 157;
						fp1sq[i + 3] = 156;
						fp1sq[i + 4] = 155;
					}
					if (sq == 154) {
						fp1sq[i] = 154;
						fp1sq[i + 1] = 153;
						fp1sq[i + 2] = 136;
						fp1sq[i + 3] = 120;

					}
					if (sq == 119) {
						fp1sq[i] = 119;
						fp1sq[i + 1] = 135;
						fp1sq[i + 2] = 151;
						fp1sq[i + 3] = 149;
						fp1sq[i + 4] = 148;
						fp1sq[i + 5] = 147;
					}
					if (sq == 146) {
						fp1sq[i] = 146;
						fp1sq[i + 1] = 145;
						fp1sq[i + 2] = 144;
						fp1sq[i + 3] = 112;
						fp1sq[i + 4] = 113;
					}
					if (sq == 114) {
						fp1sq[i] = 114;
						fp1sq[i + 1] = 115;
						fp1sq[i + 2] = 116;
						fp1sq[i + 3] = 117;
						fp1sq[i + 4] = 85;
					}
					if (sq == 84) {
						fp1sq[i] = 84;
						fp1sq[i + 1] = 83;
						fp1sq[i + 2] = 81;
						fp1sq[i + 3] = 80;
					}
					if (sq == 64) {
						fp1sq[i] = 64;
						fp1sq[i + 1] = 65;
						fp1sq[i + 2] = 66;
						fp1sq[i + 3] = 67;
						fp1sq[i + 4] = 68;
					}

					if (sq == 53) {
						fp1sq[i] = 53;
						fp1sq[i + 1] = 37;
						fp1sq[i + 2] = 36;
						fp1sq[i + 3] = 34;
						fp1sq[i + 4] = 33;
					}
					if (sq == 32) {
						fp1sq[i] = 32;
						fp1sq[i + 1] = 16;
						fp1sq[i + 2] = 1;
						fp1sq[i + 3] = 2;
						fp1sq[i + 4] = 3;
					}
					if (sq == 4) {
						fp1sq[i] = 4;
						fp1sq[i + 1] = 5;
						fp1sq[i + 2] = 6;
						fp1sq[i + 3] = 23;
						fp1sq[i + 4] = 39;
					}
					break;
				}

			}
			if (p.getID() == 2) {
				if (fp2sq[i] == -1) {
					if (sq == 40) {
						fp2sq[i] = 40;
						fp2sq[i + 1] = 24;
						fp2sq[i + 2] = 8;
						fp2sq[i + 3] = 10;
						fp2sq[i + 4] = 11;
					}
					if (sq == 12) {
						fp2sq[i] = 12;
						fp2sq[i + 1] = 13;
						fp2sq[i + 2] = 15;
						fp2sq[i + 3] = 31;
						fp2sq[i + 4] = 47;
					}
					if (sq == 46) {
						fp2sq[i] = 46;
						fp2sq[i + 1] = 45;
						fp2sq[i + 2] = 44;
						fp2sq[i + 3] = 42;
						fp2sq[i + 4] = 58;
					}
					if (sq == 74) {
						fp2sq[i] = 74;
						fp2sq[i + 1] = 75;
						fp2sq[i + 2] = 76;
						fp2sq[i + 3] = 78;
						fp2sq[i + 4] = 79;
					}
					if (sq == 95) {
						fp2sq[i] = 95;
						fp2sq[i + 1] = 94;
						fp2sq[i + 2] = 93;
						fp2sq[i + 3] = 92;
						fp2sq[i + 4] = 91;
						fp2sq[i + 5] = 90;
					}
					if (sq == 122) {
						fp2sq[i] = 122;
						fp2sq[i + 1] = 123;
						fp2sq[i + 2] = 124;
						fp2sq[i + 3] = 125;
						fp2sq[i + 4] = 127;
					}
					if (sq == 143) {
						fp2sq[i] = 143;
						fp2sq[i + 1] = 158;
						fp2sq[i + 2] = 157;
						fp2sq[i + 3] = 156;
						fp2sq[i + 4] = 155;
					}
					if (sq == 154) {
						fp2sq[i] = 154;
						fp2sq[i + 1] = 153;
						fp2sq[i + 2] = 136;
						fp2sq[i + 3] = 120;

					}
					if (sq == 119) {
						fp2sq[i] = 119;
						fp2sq[i + 1] = 135;
						fp2sq[i + 2] = 151;
						fp2sq[i + 3] = 149;
						fp2sq[i + 4] = 148;
						fp2sq[i + 5] = 147;
					}
					if (sq == 146) {
						fp2sq[i] = 146;
						fp2sq[i + 1] = 145;
						fp2sq[i + 2] = 144;
						fp2sq[i + 3] = 112;
						fp2sq[i + 4] = 113;
					}
					if (sq == 114) {
						fp2sq[i] = 114;
						fp2sq[i + 1] = 115;
						fp2sq[i + 2] = 116;
						fp2sq[i + 3] = 117;
						fp2sq[i + 4] = 85;
					}
					if (sq == 84) {
						fp2sq[i] = 84;
						fp2sq[i + 1] = 83;
						fp2sq[i + 2] = 81;
						fp2sq[i + 3] = 80;
					}
					if (sq == 64) {
						fp2sq[i] = 64;
						fp2sq[i + 1] = 65;
						fp2sq[i + 2] = 66;
						fp2sq[i + 3] = 67;
						fp2sq[i + 4] = 68;
					}

					if (sq == 53) {
						fp2sq[i] = 53;
						fp2sq[i + 1] = 37;
						fp2sq[i + 2] = 36;
						fp2sq[i + 3] = 34;
						fp2sq[i + 4] = 33;
					}
					if (sq == 32) {
						fp2sq[i] = 32;
						fp2sq[i + 1] = 16;
						fp2sq[i + 2] = 1;
						fp2sq[i + 3] = 2;
						fp2sq[i + 4] = 3;
					}
					if (sq == 4) {
						fp2sq[i] = 4;
						fp2sq[i + 1] = 5;
						fp2sq[i + 2] = 6;
						fp2sq[i + 3] = 23;
						fp2sq[i + 4] = 39;
					}
					break;
				}

			}
			if (p.getID() == 3) {
				if (fp3sq[i] == -1) {
					if (sq == 40) {
						fp3sq[i] = 40;
						fp3sq[i + 1] = 24;
						fp3sq[i + 2] = 8;
						fp3sq[i + 3] = 10;
						fp3sq[i + 4] = 11;
					}
					if (sq == 12) {
						fp3sq[i] = 12;
						fp3sq[i + 1] = 13;
						fp3sq[i + 2] = 15;
						fp3sq[i + 3] = 31;
						fp3sq[i + 4] = 47;
					}
					if (sq == 46) {
						fp3sq[i] = 46;
						fp3sq[i + 1] = 45;
						fp3sq[i + 2] = 44;
						fp3sq[i + 3] = 42;
						fp3sq[i + 4] = 58;
					}
					if (sq == 74) {
						fp3sq[i] = 74;
						fp3sq[i + 1] = 75;
						fp3sq[i + 2] = 76;
						fp3sq[i + 3] = 78;
						fp3sq[i + 4] = 79;
					}
					if (sq == 95) {
						fp3sq[i] = 95;
						fp3sq[i + 1] = 94;
						fp3sq[i + 2] = 93;
						fp3sq[i + 3] = 92;
						fp3sq[i + 4] = 91;
						fp3sq[i + 5] = 90;
					}
					if (sq == 122) {
						fp3sq[i] = 122;
						fp3sq[i + 1] = 123;
						fp3sq[i + 2] = 124;
						fp3sq[i + 3] = 125;
						fp3sq[i + 4] = 127;
					}
					if (sq == 143) {
						fp3sq[i] = 143;
						fp3sq[i + 1] = 158;
						fp3sq[i + 2] = 157;
						fp3sq[i + 3] = 156;
						fp3sq[i + 4] = 155;
					}
					if (sq == 154) {
						fp3sq[i] = 154;
						fp3sq[i + 1] = 153;
						fp3sq[i + 2] = 136;
						fp3sq[i + 3] = 120;
						
					}
					if (sq == 119) {
						fp3sq[i] = 119;
						fp3sq[i + 1] = 135;
						fp3sq[i + 2] = 151;
						fp3sq[i + 3] = 149;
						fp3sq[i + 4] = 148;
						fp3sq[i + 5] = 147;
					}
					if (sq == 146) {
						fp3sq[i] = 146;
						fp3sq[i + 1] = 145;
						fp3sq[i + 2] = 144;
						fp3sq[i + 3] = 112;
						fp3sq[i + 4] = 113;
					}
					if (sq == 114) {
						fp3sq[i] = 114;
						fp3sq[i + 1] = 115;
						fp3sq[i + 2] = 116;
						fp3sq[i + 3] = 117;
						fp3sq[i + 4] = 85;
					}
					if (sq == 84) {
						fp3sq[i] = 84;
						fp3sq[i + 1] = 83;
						fp3sq[i + 2] = 81;
						fp3sq[i + 3] = 80;
					}
					if (sq == 64) {
						fp3sq[i] = 64;
						fp3sq[i + 1] = 65;
						fp3sq[i + 2] = 66;
						fp3sq[i + 3] = 67;
						fp3sq[i + 4] = 68;
					}

					if (sq == 53) {
						fp3sq[i] = 53;
						fp3sq[i + 1] = 37;
						fp3sq[i + 2] = 36;
						fp3sq[i + 3] = 34;
						fp3sq[i + 4] = 33;
					}
					if (sq == 32) {
						fp3sq[i] = 32;
						fp3sq[i + 1] = 16;
						fp3sq[i + 2] = 1;
						fp3sq[i + 3] = 2;
						fp3sq[i + 4] = 3;
					}
					if (sq == 4) {
						fp3sq[i] = 4;
						fp3sq[i + 1] = 5;
						fp3sq[i + 2] = 6;
						fp3sq[i + 3] = 23;
						fp3sq[i + 4] = 39;
					}
					break;

				}

			}
			if (p.getID() == 4) {
				if (fp4sq[i] == -1) {
					if (sq == 40) {
						fp4sq[i] = 40;
						fp4sq[i + 1] = 24;
						fp4sq[i + 2] = 8;
						fp4sq[i + 3] = 10;
						fp4sq[i + 4] = 11;
					}
					if (sq == 12) {
						fp4sq[i] = 12;
						fp4sq[i + 1] = 13;
						fp4sq[i + 2] = 15;
						fp4sq[i + 3] = 31;
						fp4sq[i + 4] = 47;
					}
					if (sq == 46) {
						fp4sq[i] = 46;
						fp4sq[i + 1] = 45;
						fp4sq[i + 2] = 44;
						fp4sq[i + 3] = 42;
						fp4sq[i + 4] = 58;
					}
					if (sq == 74) {
						fp4sq[i] = 74;
						fp4sq[i + 1] = 75;
						fp4sq[i + 2] = 76;
						fp4sq[i + 3] = 78;
						fp4sq[i + 4] = 79;
					}
					if (sq == 95) {
						fp4sq[i] = 95;
						fp4sq[i + 1] = 94;
						fp4sq[i + 2] = 93;
						fp4sq[i + 3] = 92;
						fp4sq[i + 4] = 91;
						fp4sq[i + 5] = 90;
					}
					if (sq == 122) {
						fp4sq[i] = 122;
						fp4sq[i + 1] = 123;
						fp4sq[i + 2] = 124;
						fp4sq[i + 3] = 125;
						fp4sq[i + 4] = 127;
					}
					if (sq == 143) {
						fp4sq[i] = 143;
						fp4sq[i + 1] = 158;
						fp4sq[i + 2] = 157;
						fp4sq[i + 3] = 156;
						fp4sq[i + 4] = 155;
					}
					if (sq == 154) {
						fp4sq[i] = 154;
						fp4sq[i + 1] = 153;
						fp4sq[i + 2] = 136;
						fp4sq[i + 3] = 120;
						
					}
					if (sq == 119) {
						fp4sq[i] = 119;
						fp4sq[i + 1] = 135;
						fp4sq[i + 2] = 151;
						fp4sq[i + 3] = 149;
						fp4sq[i + 4] = 148;
						fp4sq[i + 5] = 147;
					}
					if (sq == 146) {
						fp4sq[i] = 146;
						fp4sq[i + 1] = 145;
						fp4sq[i + 2] = 144;
						fp4sq[i + 3] = 112;
						fp4sq[i + 4] = 113;
					}
					if (sq == 114) {
						fp4sq[i] = 114;
						fp4sq[i + 1] = 115;
						fp4sq[i + 2] = 116;
						fp4sq[i + 3] = 117;
						fp4sq[i + 4] = 85;
					}
					if (sq == 84) {
						fp4sq[i] = 84;
						fp4sq[i + 1] = 83;
						fp4sq[i + 2] = 81;
						fp4sq[i + 3] = 80;
					}
					if (sq == 64) {
						fp4sq[i] = 64;
						fp4sq[i + 1] = 65;
						fp4sq[i + 2] = 66;
						fp4sq[i + 3] = 67;
						fp4sq[i + 4] = 68;
					}

					if (sq == 53) {
						fp4sq[i] = 53;
						fp4sq[i + 1] = 37;
						fp4sq[i + 2] = 36;
						fp4sq[i + 3] = 34;
						fp4sq[i + 4] = 33;
					}
					if (sq == 32) {
						fp4sq[i] = 32;
						fp4sq[i + 1] = 16;
						fp4sq[i + 2] = 1;
						fp4sq[i + 3] = 2;
						fp4sq[i + 4] = 3;
					}
					if (sq == 4) {
						fp4sq[i] = 4;
						fp4sq[i + 1] = 5;
						fp4sq[i + 2] = 6;
						fp4sq[i + 3] = 23;
						fp4sq[i + 4] = 39;
					}
					break;

				}

			}
		}

	}

	public static void removeLabel(int sq, player p) {
		for (int i = 0; i <= 79; i++) {
			if (p.getID() == 1 && (fp1sq[i] == sq)) {
				fp1sq[i] = 1000;
				break;
			}
			if (p.getID() == 2 && (fp2sq[i] == sq)) {
				fp2sq[i] = 1000;
				break;
			}
			if (p.getID() == 3 && (fp3sq[i] == sq)) {
				fp3sq[i] = 1000;
				break;
			}
			if (p.getID() == 4 && (fp4sq[i] == sq)) {
				fp4sq[i] = 1000;
				break;
			}
		}
	}

	public int[] getCycleSQUARES() {
		return cycleSQUARES;
	}

	public int[] getDockingSQUARES() {
		return dockingSQUARES;
	}

	public static int[] getEventSQUARES() {
		return eventSQUARES;
	}

	public static int[] assignStartSQUARES(int numP) {
		if (numP == 1) {
			activeSQUARES[0] = 40;
		} else if (numP == 2) {
			activeSQUARES[1] = 95;
		} else if (numP == 3) {
			activeSQUARES[2] = 119;
		} else if (numP == 4) {
			activeSQUARES[3] = 64;
		} else {
			System.out.println("Something went wrong");
		}
		return activeSQUARES;
	}

	public static int updateActiveSQUARES(int square, JFrame frame) {
		int diceroll = dice.rollDice(frame);
		int key = 0;

		// Find the index of the current square
		for (int i = 0; i < SQUARES.length; i++) {
			if (SQUARES[i] == square) {
				key = i;
				break; // Stop searching once found
			}
		}

		// Calculate new index and wrap around if needed
		int newIndex = (key + diceroll) % SQUARES.length;
		int newSquare = SQUARES[newIndex];

		// Update activeSQUARES
		for (int i = 0; i < 4; i++) {
			if (activeSQUARES[i] == square) {
				activeSQUARES[i] = newSquare;
				return newSquare;
			}
		}
		return -1;
	}
	
	// cant use this method it will mess with square testing need to add to new method
	/*
	public static void removeDockingSquare(int square) {
		if (square > -1) {
			for (int i = 0; i < dockingSQUARES.length; i++) {
				if (dockingSQUARES[i] == square) {
					dockingSQUARES[i] = -1;
				}
			}
		}
	}
	*/ 

}
