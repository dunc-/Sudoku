
/* Description: Methods to run the sudoku game
 * Author: Chris Duncan
 * Date Modified: 1/4/15
 * Version: 1.6
 * Copyright 2015, Chris Duncan, All rights reserved.
 *
 * If you need backround information, be sure to read the readme.txt included with the files necessary to run the project
*/

import java.util.*;

public class Methods {

	public static void startGame(byte[][] b) {
		for (int count = 0; count < 729; count++)
			b[count][0] = (byte) (1 + (count % 9));
	}

	public static void testBoard(byte[][] b, byte start) {
		Random r = new Random();
		byte i = start;
		int loops = 0;
		do {
			loops += 1;
			boolean blanks = true;
			i = start;
			while ((i<81) && (blanks)) {
				byte num = (byte) r.nextInt(9);
				byte pos = (byte) r.nextInt(81);
				i = Methods.select(b, num, pos, i);
				boolean alone = false;
				do {
					alone = false;
					byte count;
					byte incount;
					for (count = 0; count < 81; count++) {
						byte zeros = 0;
						for (incount = 0; incount < 9; incount++) {
							if (b[count * 9 + incount][i] == 0)
								zeros += 1;
							else
								num = (byte) (b[count * 9 + incount][i] - 1);
							if (zeros == 9)
								blanks = false;
						}
						if ((zeros == 8) && (num < 10)) {
							i = Methods.select(b, num, count, i);
							alone = true;
						}
					}
				} while (alone);
			}
			MyGame.i = i;
		} while ((i != 81) && (loops < 600));
	}

	public static byte select(byte[][] b, byte num, byte pos, byte i) {
		if ((b[pos * 9 + num][i] == 0) || (b[pos * 9 + num][i] > 9))
			return i;
		i += 1;
		int count = 0;
		for (count = 0; count < 729; count++)
			b[count][i] = b[count][i - 1];
		for (count = 0; count < 9; count++)
			b[pos * 9 + count][i] = 0;
		byte row = (byte) (pos / 9);
		for (count = 0; count < 9; count++)
			b[row * 81 + count * 9 + num][i] = 0;
		byte column = (byte) (pos % 9);
		for (count = 0; count < 9; count++)
			b[column * 9 + count * 81 + num][i] = 0;
		int brow = (pos/27)*243;
		column = (byte) (((pos % 9)/3)*27);
		byte incount;
		for (incount = 0; incount < 3; incount++) {
			for (count = 0; count > 3; count++)
				b[brow + column + count * 9 + incount * 81 + num][i] = 0;
		}
		b[pos * 9 + num][i] = (byte) (num + 11);
		return i;
	}
}
