// file: TicTacToeFrame.java
// CS 330 - Spring 2014 Watts
// Written by Dr. Watts
/*
TicTacToe Frame class; extends Frame class to contain TicTacToe panel
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

public class TicTacToeFrame extends JFrame 
{
	TicTacToeFrame ()
	{
		TicTacToePanel ttt = new TicTacToePanel ();
		getContentPane().add (ttt);
		setSize (312, 430);
		setLocation (600, 400);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);	
		setResizable (false);    
		setVisible (true);
	}

	public static void main (String[] args)
	{ // Initialize game 
		TicTacToeFrame tttFrame = new TicTacToeFrame ();
	}
}
