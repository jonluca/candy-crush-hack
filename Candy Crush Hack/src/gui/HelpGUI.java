package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpGUI extends JFrame {
    ImageIcon image = new ImageIcon("images/note.png");
    JLabel note = new JLabel(image);

    public HelpGUI() {
	super("Help");

	setSize(800, 360);
	setResizable(false);
	getContentPane().setBackground(Color.white);
	setLocationRelativeTo(null);
	setVisible(true);

	add(note);

    }

}
