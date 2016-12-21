package logic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.AppearanceSettings;
import gui.CCHack;
import gui.Constants;

public class hexLogic extends JFrame {
    private JLabel lives, color, jelly, coconut, lollipop, lucky, wrapped;
    private JTextField livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf;
    private JButton save, goBack;
    private CCHack cchack;

    public hexLogic(File file, CCHack cchack) throws IOException {
	super("Select Values");
	this.cchack = cchack;
	Path source = Paths.get(file.getAbsolutePath());
	Path destination = Paths.get(file.getAbsolutePath() + ".bak");
	Files.copy(source, destination);
	initializeComponents();
	createGUI();
	setVisible(true);
    }

    public class ExitWindowListener extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
	    cchack.setVisible(true);
	    dispose();
	}

    }

    public void initializeComponents() {
	setSize(400, 450);
	setResizable(false);
	getContentPane().setBackground(Color.white);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	addWindowListener(new ExitWindowListener());
	setLayout(new FlowLayout());
	setLocationRelativeTo(null);

	save = new JButton("Save");
	goBack = new JButton("Quit");

	save.setSize(200, 100);
	goBack.setSize(200, 100);

	lives = new JLabel("Lives");
	color = new JLabel("Color Bombs");
	jelly = new JLabel("Jelly Fish");
	coconut = new JLabel("Coconut Bomb");
	lollipop = new JLabel("Lollipop Hammer");
	lucky = new JLabel("Lucky Candy");
	wrapped = new JLabel("Wrapped and Striped");

	livestf = new JTextField("0");
	colortf = new JTextField("0");
	jellytf = new JTextField("0");
	coconuttf = new JTextField("0");
	lollipoptf = new JTextField("0");
	luckytf = new JTextField("0");
	wrappedtf = new JTextField("0");

	AppearanceSettings.setSize(300, 50, lives, color, jelly, coconut, lollipop, lucky, wrapped);
	AppearanceSettings.setSize(80, 50, livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);
	AppearanceSettings.setBackground(Color.white, lives, color, jelly, coconut, lollipop, lucky, wrapped, livestf,
		colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);
	AppearanceSettings.setFont(Constants.regularFont, lives, color, jelly, coconut, lollipop, lucky, wrapped,
		livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);

	AppearanceSettings.setTextAlignment(livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);
    }

    public void createGUI() {
	JPanel numbers = new JPanel();
	numbers.setLayout(new GridLayout(7, 1));
	numbers.setSize(400, 700);

	AppearanceSettings.addToGrid(numbers, lives, livestf, color, colortf, jelly, jellytf, coconut, coconuttf,
		lollipop, lollipoptf, lucky, luckytf, wrapped, wrappedtf, save, goBack);

	JPanel bottomButtons = new JPanel();
	bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));
	bottomButtons.setBackground(Color.white);
	bottomButtons.add(save);
	bottomButtons.add(goBack);

	numbers.add(bottomButtons);

	add(numbers);

    }

    private void checkIfValid(JTextField text) {
	if (isStringInt(text.getText())) {
	    long val = Long.parseLong(text.getText());
	    if (val > 255) {
		JOptionPane.showMessageDialog(this, "Max size is 255", "Number Error", JOptionPane.ERROR_MESSAGE);
		text.setText("255");
	    }
	    if (val < 0) {
		JOptionPane.showMessageDialog(this, "Value cannot be negative!", "Number Error",
			JOptionPane.ERROR_MESSAGE);
		text.setText("0");
	    }
	} else {
	    JOptionPane.showMessageDialog(this, "Not a number!", "Text Field Error", JOptionPane.ERROR_MESSAGE);
	}
    }

    // Credit to
    // http://stackoverflow.com/questions/12558206/how-can-i-check-if-a-value-is-of-type-integer
    public boolean isStringInt(String s) {
	try {
	    Integer.parseInt(s);
	    return true;
	} catch (NumberFormatException ex) {
	    return false;
	}
    }

    private class validNumber implements DocumentListener {
	JTextField owner;

	public void validNumber(JTextField in) {
	    this.owner = in;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
	    checkIfValid(owner);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	    checkIfValid(owner);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	    checkIfValid(owner);
	}
    }
}
