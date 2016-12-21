package other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Borrowed and modified from CS201 at USC, Fall 2016. Helpful functions that
 * are variadic and allow massive manipulation quickly
 */
public class AppearanceSettings {

    // sets background color of JComponents passed in
    @SafeVarargs
    public static <T extends JComponent> void setBackground(Color backGround, T... components) {

	for (T component : components)
	    component.setBackground(backGround);
    }

    // Adds everything to the grid layout correctly and identically
    public static void addToGrid(JPanel add, JComponent... labels) {
	boolean check = false;
	for (int i = 0; i < labels.length; i += 2) {
	    JPanel topPanel = new JPanel();
	    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
	    topPanel.setBackground(Color.white);
	    topPanel.add(Box.createHorizontalStrut(20));
	    topPanel.add(labels[i]);
	    topPanel.add(labels[i + 1]);

	    add.add(topPanel);
	}

    }

    // sets font of supplied JComponents
    @SafeVarargs
    public static <T extends JComponent> void setFont(Font font, T... components) {

	for (T component : components)
	    component.setFont(font);
    }

    // centers the text for the passed in labels
    @SafeVarargs
    public static void setTextAlignment(JTextField... labels) {

	for (JTextField label : labels)
	    label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // centers the text for the passed in labels
    @SafeVarargs
    public static void setColumn(JFormattedTextField... labels) {

	for (JFormattedTextField label : labels)
	    label.setColumns(7);
    }

    // centers the text for the passed in labels
    @SafeVarargs
    public static void setMax(JFormattedTextField... labels) {

	for (JFormattedTextField label : labels)
	    label.setText("65535");
    }

    // sets size of components
    @SafeVarargs
    public static <T extends JComponent> void setSize(int x, int y, T... components) {

	Dimension dim = new Dimension(x, y);

	for (T component : components) {
	    component.setPreferredSize(dim);
	}
    }

}