// file: TicTacToeApplet.java
// CS 330 - Spring 2014 - Watts
// Written by Dr. Watts
/*
TicTacToe game interface; extendsApplet class to contain TicTacToe panel
*/

import javax.swing.*;
import java.awt.*;

public class TicTacToeApplet extends JApplet 
{
	TicTacToePanel ttt = null;

	public void init ()
	{
		setSize (312, 430);
		setLocation (600, 400);
		ttt = new TicTacToePanel ();
		add (ttt);
	}

	public void start() {}
	public void stop() {}
	public void destroy() {}

	public void paint (Graphics g)
	{
        	super.paint(g);  
		ttt.repaint();
	}
}
