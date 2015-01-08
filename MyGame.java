
/* Description: Runs the game and creates the game panel the viewer interacts with
 * Author: Chris Duncan
 * Date Modified: 1/5/15
 * Version: 1.6
 * Copyright 2015, Chris Duncan, All rights reserved.
 *
 * If you need backround information, be sure to read the readme.txt included with the files necessary to run the project!
*/

import java.awt.*;
import javax.swing.*;

public class MyGame {

	public static byte[][] b = new byte[729][82];
	public static byte i = 0;
	public static final int winWidth = 777;
	public static final int winHeight = 636;
	public static final Color MY_GREY = new Color(192, 192, 192);

	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				show();
			}
		});
	}

	public static void show() {
		Methods.start(b);
		final byte border = 14;
		JFrame board = new JFrame("Board");
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setResizable(true); // Cannot resize the playing window
		board.setSize(winWidth, winHeight);
		board.setLocation(0, 0);
		board.setLayout(new BorderLayout());	   
		board.add(new GamePanel(new Dimension(winWidth, border)), BorderLayout.NORTH); // Creates the border
		board.add(new GamePanel(new Dimension(winWidth, border)), BorderLayout.SOUTH); // See above
		board.add(new GamePanel(new Dimension(border, winHeight)), BorderLayout.EAST); // See above
		board.add(new GamePanel(new Dimension(0, winHeight)), BorderLayout.WEST); // See above
	    Display p = new Display();
	    p.setBackground(MY_GREY);
	    board.add(p, BorderLayout.CENTER);
	    board.setVisible(true);
	}
}
