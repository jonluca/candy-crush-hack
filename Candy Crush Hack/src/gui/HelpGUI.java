package gui;

import javax.swing.*;
import java.awt.*;

public class HelpGUI extends JFrame {
    ImageIcon image = new ImageIcon("images/note.png");
    JLabel note = new JLabel(image);

    public HelpGUI() {
        super("Help");

        setSize(800, 436);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        setVisible(true);
        add(note);
    }

}
