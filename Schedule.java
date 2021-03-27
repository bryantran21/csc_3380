/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import static main.DOMmodifyXML.addClass;
import javax.swing.JCheckBox;

/**
 *
 * @author Bryan
 */
public class Schedule implements ActionListener {

    private JFrame frame;
    private JButton submitButton;
    private User currentUser;
    private JButton logoutButton;
    private JButton homeButton;
    private JPanel panel;
    private JComboBox daysBox;
    private JButton changeButton;

    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public Schedule(User passedUser) {

        currentUser = passedUser;

        frame = new JFrame();
        frame.setBounds(0, 0, 800, 600);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        homeButton = new JButton();
        panel.setBackground(Color.decode("#36393f"));
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        frame.setIconImage(image.getImage());

        homeButton = new JButton("Home");
        homeButton.setBounds(600, 10, 80, 30);
        homeButton.setBackground(Color.decode("#7289da"));
        homeButton.setForeground(Color.decode("#dcddde"));
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);
        panel.add(homeButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(690, 10, 80, 30);
        logoutButton.setBackground(Color.decode("#7289da"));
        logoutButton.setForeground(Color.decode("#dcddde"));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        changeButton = new JButton("Change Schedule");
        changeButton.setBounds(150, 300, 150, 30);
        changeButton.setBackground(Color.decode("#7289da"));
        changeButton.setForeground(Color.decode("#dcddde"));
        changeButton.setFocusable(false);
        changeButton.addActionListener(this);
        panel.add(changeButton);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        daysBox = new JComboBox(days);
        daysBox.setBounds(150, 150, 125, 30);
        panel.add(daysBox);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            HomePage home = new HomePage(currentUser);
            frame.dispose();
        }
        if (e.getSource() == logoutButton) {
            LoginGui login = new LoginGui();
            frame.dispose();
        }
    }
}
