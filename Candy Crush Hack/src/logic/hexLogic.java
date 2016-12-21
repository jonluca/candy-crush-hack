package logic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.AppearanceSettings;

public class hexLogic extends JFrame {
    private JLabel lives, color, jelly, coconut, lollipop, lucky, wrapped;
    private JTextField livestf, colortf, jellytf, coconuttf, lollipoptf, luckytf, wrappedtf;
    private JButton save, goBack;

    public hexLogic(File file) throws IOException {
	super("Select Values");
	Path source = Paths.get(file.getAbsolutePath());
	Path destination = Paths.get(file.getAbsolutePath() + ".bak");
	Files.copy(source, destination);
    }

    public void initializeComponents() {
	setSize(800, 200);
	setResizable(false);
	getContentPane().setBackground(Color.white);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new FlowLayout());
	setLocationRelativeTo(null);

	save = new JButton("Save");
	goBack = new JButton("Return");

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

	AppearanceSettings.setSize(150, 50, lives, color, jelly, coconut, lollipop, lucky, wrapped, livestf, colortf,
		jellytf, coconuttf, lollipoptf, luckytf, wrappedtf);
    }

    public void createGUI() {

    }
}
