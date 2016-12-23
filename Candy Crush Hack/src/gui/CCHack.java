package gui;

import other.AppearanceSettings;
import other.Constants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CCHack extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFileChooser file;
    private JFrame ourFrame;
    private JButton selectButton, helpButton;
    private File selectedFile;
    private JLabel selectLabel, infoLabel;

    public CCHack() {
        super("Candy Crush Save Game Hack");
        initializeComponents();
        createGUI();
    }

    public static void main(String[] args) {
        CCHack cchack = new CCHack();
        cchack.setVisible(true);
    }

    private void createGUI() {
        // Info label top
        infoLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(Constants.topFont);
        JPanel top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setSize(800, 150);
        top.add(infoLabel);

        add(top);

        //
        add(selectLabel);

        add(Box.createVerticalGlue());
        // Bottom buttons
        AppearanceSettings.setSize(350, 50, selectButton, helpButton);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        bottom.setBackground(Color.white);
        bottom.setSize(800, 50);
        bottom.add(selectButton);
        bottom.add(helpButton);

        add(bottom);

    }

    private void initializeComponents() {

        ourFrame = this;

        setSize(800, 200);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setJMenuBar(new Menu((JFrame) this));

        selectLabel = new JLabel("Please select your save_##########.dat");
        infoLabel = new JLabel("Welcome to Candy Crush Saga Hacks!");
        selectButton = new JButton("Choose File");
        helpButton = new JButton("Help");

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                file = new JFileChooser("Choose File");
                // set default home
                String userhome = System.getProperty("user.home");
                file.setCurrentDirectory(new File(userhome + System.getProperty("file.separator") + "Desktop"));
                // only allow .dats
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat files", "dat");

                file.setFileFilter(filter);
                file.setAcceptAllFileFilterUsed(false);

                int returnValue = file.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = file.getSelectedFile();
                    try {
                        // start hack
                        ValueSelectionGUI logic = new ValueSelectionGUI(selectedFile, (CCHack) ourFrame);
                        setVisible(false);
                    } catch (IOException e) {
                        // They've run this program multiple times - might over
                        // write their back up from a messed up file. Tell them
                        // to move it and try again
                        JOptionPane.showMessageDialog(ourFrame,
                                "Unable to save backup! Check if your file + .bak already exists. If it does, move it and try again.",
                                "Backup Save Error", JOptionPane.ERROR_MESSAGE);
                        setVisible(true);
                    }
                }
            }
        });
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new HelpGUI();
            }
        });

    }

}
