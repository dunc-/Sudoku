
/* Description: Improves upon the JButton built into Java
 * Author: Chris Duncan
 * Date Modified: 1/6/15
 * Version: 1.6
 * Copyright 2015, Chris Duncan, All rights reserved.
 *
 * If you need backround information, be sure to read the readme.txt included with the files necessary to run the project
*/

import java.awt.*;

import javax.swing.*;

public class Buttons extends JButton {

    private static final long serialVersionUID = 1L;

    public Buttons(String action, String command) {
    	super(action);
	this.setBorder(BorderFactory.createBevelBorder(0));
	this.setActionCommand(command);        
    }

    public Dimension getPreferredSize() {
        return new Dimension(125, 35);
    }   
}
