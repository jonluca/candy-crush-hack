package gui;

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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import logic.hexEdits;
import other.AppearanceSettings;
import other.Constants;

public class ValueSelectionGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lives, color, jelly, coconut, lollipop, lucky, wrapped;
    private JFormattedTextField livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf;
    private JButton save, goBack, maxAll;
    private CCHack cchack;
    private File file;
    private JFrame ourWindow;

    public ValueSelectionGUI(File file, CCHack cchack) throws IOException {
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
	setSize(500, 470);
	setResizable(false);
	getContentPane().setBackground(Color.white);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	addWindowListener(new ExitWindowListener());
	setLayout(new FlowLayout());
	setLocationRelativeTo(null);
	setJMenuBar(new Menu());

	save = new JButton("Save");
	goBack = new JButton("Quit");
	maxAll = new JButton("Max All");

	goBack.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		System.exit(0);
	    }
	});

	save.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    int livesInt = Integer.parseInt(livestf.getText());
		    int colorInt = Integer.parseInt(colortf.getText());
		    int jellyInt = Integer.parseInt(jellytf.getText());
		    int coconutInt = Integer.parseInt(coconuttf.getText());
		    int lollipopInt = Integer.parseInt(lollipoptf.getText());
		    int luckyInt = Integer.parseInt(luckytf.getText());
		    int wrappedInt = Integer.parseInt(wrappedtf.getText());

		    hexEdits actualHack = new hexEdits(ourWindow, file, livesInt, colorInt, jellyInt, coconutInt,
			    lollipopInt, luckyInt, wrappedInt);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(ourWindow, "Error writing to file! Check console for stack trace!",
			    "Writing Error", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	});

	maxAll.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		AppearanceSettings.setMax(livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);
	    }
	});

	lives = new JLabel("Lives");
	color = new JLabel("Color Bombs");
	jelly = new JLabel("Jelly Fish");
	coconut = new JLabel("Coconut Bomb");
	lollipop = new JLabel("Lollipop Hammer");
	lucky = new JLabel("Lucky Candy");
	wrapped = new JLabel("Wrapped and Striped");

	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
	DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
	decimalFormat.setGroupingUsed(false);

	livestf = new JFormattedTextField(decimalFormat);
	colortf = new JFormattedTextField(decimalFormat);
	jellytf = new JFormattedTextField(decimalFormat);
	coconuttf = new JFormattedTextField(decimalFormat);
	lollipoptf = new JFormattedTextField(decimalFormat);
	luckytf = new JFormattedTextField(decimalFormat);
	wrappedtf = new JFormattedTextField(decimalFormat);

	AppearanceSettings.setColumn(livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);

	livestf.getDocument().addDocumentListener(new validNumber(livestf));
	colortf.getDocument().addDocumentListener(new validNumber(colortf));
	jellytf.getDocument().addDocumentListener(new validNumber(jellytf));
	coconuttf.getDocument().addDocumentListener(new validNumber(coconuttf));
	lollipoptf.getDocument().addDocumentListener(new validNumber(lollipoptf));
	luckytf.getDocument().addDocumentListener(new validNumber(luckytf));
	wrappedtf.getDocument().addDocumentListener(new validNumber(wrappedtf));

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
	bottomButtons.add(maxAll);
	bottomButtons.add(goBack);
	bottomButtons.setPreferredSize(new Dimension(400, 80));

	add(numbers);
	add(bottomButtons);
	add(Box.createVerticalStrut(20));

    }

    // People of the future, don't judge me for the following code
    private void checkIfValid(JTextField text) {
	if (!text.getText().trim().equals("") && isStringInt(text.getText())) {
	    long val = Long.parseLong(text.getText());
	    if (val > 65535) {
		JOptionPane.showMessageDialog(this, "Max size is 65535", "Number Error", JOptionPane.ERROR_MESSAGE);
		Runnable set65 = new Runnable() {
		    @Override
		    public void run() {
			text.setText("65535");
		    }
		};
		SwingUtilities.invokeLater(set65);
	    }
	    if (val < 0) {
		JOptionPane.showMessageDialog(this, "Value cannot be negative!", "Number Error",
			JOptionPane.ERROR_MESSAGE);
		Runnable set0 = new Runnable() {
		    @Override
		    public void run() {
			text.setText("0");
		    }
		};
		SwingUtilities.invokeLater(set0);
	    }
	} else {
	    if (!text.getText().trim().equals("")) {
		JOptionPane.showMessageDialog(this, "Not a number!", "Text Field Error", JOptionPane.ERROR_MESSAGE);
	    }
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

	public validNumber(JTextField in) {
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