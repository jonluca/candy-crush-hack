package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {

    public Menu() {
	JMenu menu;
	JMenuItem about, help;

	// Create the menu bar.
	// Build the first menu.
	menu = new JMenu("Candy Crush Hacks");
	menu.getAccessibleContext().setAccessibleDescription("Candy Crush Hacks");

	// a group of JMenuItems
	about = new JMenuItem("About");
	about.getAccessibleContext().setAccessibleDescription("About");
	about.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		System.exit(0);
	    }
	});
	menu.add(about);

	// a group of JMenuItems
	help = new JMenuItem("Help");
	help.getAccessibleContext().setAccessibleDescription("Help");
	help.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		System.exit(0);
	    }
	});
	menu.add(about);

	add(menu);

    }
}