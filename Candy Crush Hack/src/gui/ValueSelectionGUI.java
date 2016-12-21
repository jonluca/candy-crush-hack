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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    private JLabel lives, color, jelly, coconut, lollipop, lucky, wrapped, hands, ufo, paint;
    private JFormattedTextField livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf, handstf, ufotf,
	    painttf;
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
	setValues();
	setVisible(true);
    }

    private void setValues() {
	RandomAccessFile raf = null;
	// The following all read the current values from the save file as a
	// frame of reference to the user
	try {
	    raf = new RandomAccessFile(file, "rw");
	    byte[] data = new byte[2];

	    // Color
	    raf.seek(80); // Go to byte at offset position 80.
	    raf.readFully(data);
	    colortf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Jelly
	    raf.seek(92);
	    raf.readFully(data);
	    jellytf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Coconut
	    raf.seek(104);
	    raf.readFully(data);
	    coconuttf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Lollipop
	    raf.seek(116);
	    raf.readFully(data);
	    lollipoptf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Lucky
	    raf.seek(152);
	    raf.readFully(data);
	    luckytf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Wrapped
	    raf.seek(164);
	    raf.readFully(data);
	    wrappedtf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Hands
	    raf.seek(176);
	    raf.readFully(data);
	    handstf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // UFO
	    raf.seek(212);
	    raf.readFully(data);
	    ufotf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // paint
	    raf.seek(224);
	    raf.readFully(data);
	    painttf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	    // Lives
	    raf.seek(656);
	    raf.readFully(data);
	    livestf.setText(Integer.toString(((data[0] & 0xFF) << 8) | (data[1] & 0xFF)));

	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(ourWindow, "File not found!", "Reading Error", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(ourWindow, "Error reading from file! Is it a valid save game?",
		    "Reading Error", JOptionPane.ERROR_MESSAGE);
	    save.setEnabled(false);
	    e.printStackTrace();
	} finally {
	    try {
		raf.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

    public class ExitWindowListener extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
	    cchack.setVisible(true);
	    dispose();
	}

    }

    public void initializeComponents() {
	setSize(500, 650);
	setResizable(false);
	getContentPane().setBackground(Color.white);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	addWindowListener(new ExitWindowListener());
	setLayout(new FlowLayout());
	setLocationRelativeTo(null);
	setJMenuBar(new Menu((JFrame) this));

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
		    int handsInt = Integer.parseInt(handstf.getText());
		    int ufoInt = Integer.parseInt(ufotf.getText());
		    int paintInt = Integer.parseInt(painttf.getText());

		    // Pass along selected values to the function that does the
		    // actual modification of the save game file
		    hexEdits actualHack = new hexEdits(ourWindow, file, livesInt, colorInt, jellyInt, coconutInt,
			    lollipopInt, luckyInt, wrappedInt, handsInt, ufoInt, paintInt);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(ourWindow, "Error writing to file! Check console for stack trace!",
			    "Writing Error", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	});

	maxAll.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		AppearanceSettings.setMax(livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf, handstf,
			ufotf, painttf);
	    }
	});

	lives = new JLabel("Lives");
	color = new JLabel("Color Bombs");
	jelly = new JLabel("Jelly Fish");
	coconut = new JLabel("Coconut Bomb");
	lollipop = new JLabel("Lollipop Hammer");
	lucky = new JLabel("Lucky Candy");
	wrapped = new JLabel("Wrapped and Striped");
	hands = new JLabel("Hand Swaps");
	ufo = new JLabel("UFO Drops");
	paint = new JLabel("Paint");

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
	handstf = new JFormattedTextField(decimalFormat);
	ufotf = new JFormattedTextField(decimalFormat);
	painttf = new JFormattedTextField(decimalFormat);

	AppearanceSettings.setColumn(livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf, handstf,
		ufotf, painttf);

	livestf.getDocument().addDocumentListener(new validNumber(livestf));
	colortf.getDocument().addDocumentListener(new validNumber(colortf));
	jellytf.getDocument().addDocumentListener(new validNumber(jellytf));
	coconuttf.getDocument().addDocumentListener(new validNumber(coconuttf));
	lollipoptf.getDocument().addDocumentListener(new validNumber(lollipoptf));
	luckytf.getDocument().addDocumentListener(new validNumber(luckytf));
	wrappedtf.getDocument().addDocumentListener(new validNumber(wrappedtf));
	handstf.getDocument().addDocumentListener(new validNumber(handstf));
	ufotf.getDocument().addDocumentListener(new validNumber(ufotf));
	painttf.getDocument().addDocumentListener(new validNumber(painttf));

	AppearanceSettings.setSize(300, 50, lives, color, jelly, coconut, lollipop, lucky, wrapped, hands, ufo, paint);
	AppearanceSettings.setSize(80, 50, livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf,
		handstf, ufotf, painttf);
	AppearanceSettings.setBackground(Color.white, lives, color, jelly, coconut, lollipop, lucky, wrapped, livestf,
		colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf, hands, handstf, painttf, paint);
	AppearanceSettings.setFont(Constants.regularFont, lives, color, jelly, coconut, lollipop, lucky, wrapped, hands,
		livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf, handstf, ufo, ufotf, paint,
		painttf);

	AppearanceSettings.setTextAlignment(livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf,
		handstf, ufotf, painttf);
    }

    public void createGUI() {
	JPanel numbers = new JPanel();
	numbers.setLayout(new GridLayout(10, 1));
	numbers.setSize(400, 500);

	AppearanceSettings.addToGrid(numbers, lives, livestf, color, colortf, jelly, jellytf, coconut, coconuttf,
		lollipop, lollipoptf, lucky, luckytf, wrapped, wrappedtf, hands, handstf, ufo, ufotf, paint, painttf);

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
	// Check if empty, and if valid int
	if (!text.getText().trim().equals("") && isStringInt(text.getText())) {
	    // hacky way of maximizing value - it'll fit in a long, then just
	    // create an int with max 65535
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
