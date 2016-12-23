package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {

    private JFrame frame;

    public Menu(JFrame frame) {
        this.frame = frame;
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
                JOptionPane.showMessageDialog(frame,
                        "© 2017 JonLuca De Caro\nhttp://jonlu.ca\nhttps://github.com/jonluca/candy-crush-hack", "About",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        menu.add(about);

        // a group of JMenuItems
        help = new JMenuItem("Help");
        help.getAccessibleContext().setAccessibleDescription("Help");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new HelpGUI();
            }
        });
        menu.add(help);

        add(menu);

    }
}
