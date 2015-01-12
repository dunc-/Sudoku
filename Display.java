
/* Description: Create the GUI used for the game and create interactive abilities
 * Author: Chris Duncan
 * Date Modified: 1/6/15
 * Version: 1.6
 * Copyright 2015, Chris Duncan, All rights reserved.
 *
 * If you need backround information, be sure to read the readme.txt included with the files necessary to run the project!
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class Display extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private int dispWidth = 557;
    private int dispHeight = 580;
    private int buttonWidth = 200;
    private final Color MY_GREEN = new Color(0, 153, 0); 
    private final Color MY_BLUE = new Color(0, 102, 204);
    private final Color MY_PURPLE = new Color(148, 0, 211);

    public Display() {
    	addMouseListener(new MouseAdapter() {
    		public void mousePressed(MouseEvent m) {
    			selectNumber(m.getX(), m.getY());
    		}
    	});
    	this.setLayout(new BorderLayout());
    	JPanel button = new JPanel(); // Button panel
    	button.setPreferredSize(new Dimension(buttonWidth, dispHeight));
        button.setBackground(MY_GREEN);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(55); // Creates symetry
        flowLayout.setHgap(100); // See above
        button.setLayout(flowLayout);
        Buttons enter = new Buttons("Enter Your Own", "enter");
        enter.addActionListener(this);
        button.add(enter);
        Buttons startHard = new Buttons("Start - Hard", "startHard");
        startHard.addActionListener(this);
        button.add(startHard);
        Buttons startMedium = new Buttons("Start - Medium", "startMedium");
        startMedium.addActionListener(this);
        button.add(startMedium);
        Buttons startEasy = new Buttons("Start - Easy", "startEasy");
        startEasy.addActionListener(this);
        button.add(startEasy);
        Buttons goBack = new Buttons("Go Back One", "goBack");
        goBack.addActionListener(this);
        button.add(goBack);
        Buttons solve = new Buttons("Solve It", "solve");
        solve.addActionListener(this);
        button.add(solve);
        this.add(button, BorderLayout.WEST); // Adds the button panel to the general display panel
    }

    private void selectNumber(int x, int y) {
        int numPos[] = {3,63,124,187,248,309,372,433,494};
        final byte SPACING = 19; // For spacing the numbers out evenly
        if(x < buttonWidth + numPos[0])
            return; // Returns nothing if it's not in the sudoku playing area
        x -= buttonWidth - numPos[0];
        byte count; // For use in loops
        byte xPos = 0;
        for(count = 0; count < 9; count++) { // Finds x position
            if(x > numPos[count])
                xPos = count;
        }
        byte yPos = 0; 
        for(count = 0; count < 9; count++) { // Finds y position
            if(y > numPos[count])
                yPos = count;
        }
        byte position = (byte) (xPos + yPos*9); // The number position of 0-80
        byte xNum = 0;
        x -= numPos[xPos];
        for(count = 0; count < 3; count++) {
            if(x > SPACING * count)
                xNum = count;
        }
        byte yNum = 0;
        y -=  numPos[yPos]; 
        for(count = 0; count < 3; count++) {
            if(y >  SPACING * count)
                yNum = count;
        }
        byte number = (byte) (xNum + yNum*3);
        MyGame.i = (byte) Methods.select(MyGame.b, number, position, MyGame.i);
        repaint(buttonWidth, 0, dispWidth, dispHeight); // Redraws the board
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(dispWidth + buttonWidth, dispHeight);
    }
    
    protected void paintComponent(Graphics g) {
        final byte FOOT = 24;
        final byte NUM_X = 11;
        final byte NUM_Y = 54;
        final byte BLANK_SIZE = 59;
        final byte PENCIL_X = 4;
        final byte PENCIL_Y = 18;
        final byte S_PENCIL_X = 20;
        final byte S_PENCIL_Y = 19;
        final int FOOT_MESSAGE_X = 96;
        final int FOOT_MESSAGE_Y = 574;
        final int FOOT_NUMBER_X = 211;
        final int FOOT_NUMBER_Y = 574;
        int BigLines[] = {0, 184, 369, 554, 577};
        int SmallLines[] = {62, 123, 247, 308, 432, 493};
        int numPos[] = {3, 63, 124, 187, 248, 309, 372, 433, 494};
        Font selected = new Font("Arial", Font.ROMAN_BASELINE, 70);
        Font foot = new Font("Arial", Font.ROMAN_BASELINE, 20);
        Font pencil = new Font("Arial", Font.ROMAN_BASELINE, 20);
        super.paintComponent(g); //paint the component's JPanel     
        g.setColor(MY_BLUE);
        g.setFont(pencil);
        byte count;
        for(count = 0; count < 5; count++)
            g.fillRect(0, BigLines[count], dispWidth + buttonWidth, 3);
        for(count = 0; count < 6; count++)
            g.drawLine(0, SmallLines[count], dispWidth + buttonWidth, SmallLines[count]);
        g.fillRect(BigLines[0] + buttonWidth , 0, 3, dispHeight);
        g.fillRect(BigLines[1] + buttonWidth , 0, 3, dispHeight - FOOT);
        g.fillRect(BigLines[2] + buttonWidth , 0, 3, dispHeight - FOOT);
        g.fillRect(BigLines[3] + buttonWidth , 0, 3, dispHeight);
        for(count = 0; count < 6; count++)
            g.drawLine(SmallLines[count] + buttonWidth, 0, SmallLines[count] + buttonWidth, dispHeight -FOOT);
        g.setFont(foot);
        g.drawString("This is Step        in the Sudoku Solution", FOOT_MESSAGE_X + buttonWidth, FOOT_MESSAGE_Y);
        g.drawString(String.valueOf(MyGame.i), FOOT_NUMBER_X + buttonWidth, FOOT_NUMBER_Y);
        byte numCount;
        for(numCount = 0; numCount < 81; numCount++) {
            g.setColor(MY_BLUE);
            byte zeros = 0;
            byte outerCount;
            for(outerCount = 0; outerCount < 3; outerCount++) {
                for(count = 0; count < 3; count++) {
                    byte pencilnumber = MyGame.b[count + outerCount*3 + numCount*9][ MyGame.i];
                    if(pencilnumber > 0) {
                        if(pencilnumber < 10) {
                            g.setFont(pencil);
                            g.drawString(String.valueOf(pencilnumber ), numPos[numCount % 9] + (count*S_PENCIL_X) + PENCIL_X + buttonWidth, numPos[numCount / 9] + outerCount * S_PENCIL_Y + PENCIL_Y);
                        } else {
                            g.setFont(selected);
                            g.drawString(String.valueOf(pencilnumber - 10), numPos[numCount % 9] + buttonWidth + NUM_X, numPos[numCount / 9] + NUM_Y);
                        }
                    }
                    else
                        zeros += 1;
                }
            }
            if(zeros == 9) {
                g.setColor(MY_PURPLE);
                g.fillRect(numPos[numCount%9] + buttonWidth, numPos[numCount/9], BLANK_SIZE, BLANK_SIZE);
            }
        }
    } 

    public void actionPerformed(ActionEvent a) {       
        if (a.getActionCommand() == "enter")
            MyGame.i = 0;
        else if (a.getActionCommand() == "startHard") {
             Methods.tryGame(MyGame.b, (byte) 0);
             MyGame.i = 20;
        } else if (a.getActionCommand() == "startMedium") {
             Methods.tryGame(MyGame.b, (byte) 0);
             MyGame.i = 35;
        } else if (a.getActionCommand() == "startEasy") {
             Methods.tryGame(MyGame.b, (byte) 0);
             MyGame.i = 50;
        } else if (a.getActionCommand() == "solve")
            Methods.tryGame(MyGame.b, MyGame.i);
        else if (a.getActionCommand() == "goBack") {
            if (MyGame.i > 0)
            MyGame.i -= 1;
        }
        repaint(buttonWidth, 0, dispWidth, dispHeight);
    }
}
