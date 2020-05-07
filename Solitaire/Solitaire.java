package Solitaire;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *   Program to play a game of Solitaire
 */

/**
 * @author Kirsten Morgan
 *
 */
public class Solitaire {
	
	// DO NOT CHANGE, REMOVE OR ADD ANY CODE GIVEN IN THIS CLASS

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Solitaire");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Controller control = new Controller();
		frame.getContentPane().add(new Panel(control));
		frame.pack();
		frame.setSize(1000,500);
		
		frame.setVisible(true);
	}

}
