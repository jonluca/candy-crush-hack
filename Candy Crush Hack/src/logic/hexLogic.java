package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Box;
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
    private File file;
    private JFrame ourWindow;

    public hexLogic(File file, CCHack cchack) throws IOException {
	super("Select Values");
	this.file = file;
	this.ourWindow = this;
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
	setSize(400, 470);
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

	AppearanceSettings.setSize(200, 100, save, goBack);

	goBack.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		System.exit(0);
	    }
	});

	save.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    int livesInt = Integer.parseInt(lives.getText());
		    int colorInt = Integer.parseInt(color.getText());
		    int jellyInt = Integer.parseInt(jelly.getText());
		    int coconutInt = Integer.parseInt(coconut.getText());
		    int lollipopInt = Integer.parseInt(lollipop.getText());
		    int luckyInt = Integer.parseInt(lucky.getText());
		    int wrappedInt = Integer.parseInt(wrapped.getText());

		    hexEdits actualHack = new hexEdits(file, livesInt, colorInt, jellyInt, coconutInt, lollipopInt,
			    luckyInt, wrappedInt);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(ourWindow, "Error writing to file! Check console for stack trace!",
			    "Writing Error", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	});

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
	numbers.setSize(400, 350);

	AppearanceSettings.addToGrid(numbers, lives, livestf, color, colortf, jelly, jellytf, coconut, coconuttf,
		lollipop, lollipoptf, lucky, luckytf, wrapped, wrappedtf);

	JPanel bottomButtons = new JPanel();
	bottomButtons.setLayout(new GridLayout(1, 2));
	bottomButtons.setBackground(Color.white);
	bottomButtons.add(save);
	bottomButtons.add(goBack);
	bottomButtons.setPreferredSize(new Dimension(400, 80));

	add(numbers);
	add(bottomButtons);
	add(Box.createVerticalStrut(20));

    }

    private void checkIfValid(JTextField text) {
	if (isStringInt(text.getText())) {
	    long val = Long.parseLong(text.getText());
	    if (val > 65535) {
		JOptionPane.showMessageDialog(this, "Max size is 65535", "Number Error", JOptionPane.ERROR_MESSAGE);
		text.setText("65535");
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
