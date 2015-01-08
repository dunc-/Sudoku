
/* Description: Methods to run the b game
 * Author: Chris Duncan
 * Date Modified: 1/4/15
 * Version: 1.6
 * Copyright 2015, Chris Duncan, All rights reserved.
 *
 * If you need backround information, be sure to read the readme.txt included with the files necessary to run the project
*/

public class Methods 
{
	public static void start(byte[][] b) {
		int count = 0;
		for(count = 0; count < 729; count++)
			b[count][0] = (byte) (1 + (count % 9));
	}
	
	public static void tryGame(byte[][] b, byte start)
	{
		java.util.Random generator = new java.util.Random(System.currentTimeMillis());
		byte i = start;
		int trys = 0;
		do {	
			trys += 1;
			boolean noblanks = true;
			i = start;
	    	while((i < 81) && (noblanks)) {
				byte num = (byte) generator.nextInt(9);
				byte pos = (byte) generator.nextInt(81);
				i = Methods.select(b, num, pos, i);
				boolean standalone = false;
				do {
					standalone = false;
					byte count;
					byte incount;
					for(count = 0; count < 81; count++) {
						byte nzeros = 0;
						for(incount = 0; incount < 9; incount++) {
							if(b[count * 9 + incount][i] == 0)
								nzeros += 1;
							else
								num = (byte) (b[count * 9 + incount][i] - 1);
							if(nzeros == 9)
								noblanks = false;
						}			
						if((nzeros == 8) && (num < 10)) {
							i = Methods.select(b, num, count, i);
							standalone = true;
						}
					}
				} while(standalone);
			}
			MyGame.i = i;
		} while((i != 81) && (trys < 500));  //should never need more than 500
	}
	
	public static byte select(byte[][] b, byte num, byte pos, byte i) {
		if((b[pos*9 + num][i] == 0) || (b[pos*9 + num][i] > 9))
			return i;
		i += 1;
    	int count = 0;
    	for(count = 0; count < 729; count++)
			b[count][i] = b[count][i - 1];
		for(count = 0; count < 9; count++)
			b[pos*9 + count][i] = 0;
		byte row =   (byte) (pos/9);
		for(count = 0; count < 9; count++)
			b[row * 81 + count * 9 + num][i] = 0;
		byte column =   (byte) (pos%9);
		for(count = 0; count < 9; count++)
			b[column * 9 + count * 81 + num][i] = 0;
		int bRow =  (pos/27)*243;
		int bCol = (byte) (((pos%9)/3)*27);
		byte incount;
		for(incount = 0; incount < 3; incount++) {
			for(count = 0; count < 3; count++)
				b[bRow + bCol + count * 9 + incount * 81 + num ][i] = 0;
		}
		b[pos*9 + num][i] = (byte) (num + 11); //selected now 11 to 19
		return i;
	}
}
