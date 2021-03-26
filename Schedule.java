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
    private JCheckBox timeAvailable;
    private JCheckBox timeAvailable1;
    private JCheckBox timeAvailable2;
    private JCheckBox timeAvailable3;
    private JCheckBox timeAvailable4;
    private JCheckBox timeAvailable5;
    private JCheckBox timeAvailable6;
    private JCheckBox timeAvailable7;
    private JCheckBox timeAvailable8;
    private JCheckBox timeAvailable9;
    private JCheckBox timeAvailable10;
    private JCheckBox timeAvailable11;
    private JCheckBox timeAvailable12;
    private JCheckBox timeAvailable13;
    private JCheckBox timeAvailable14;
    private JCheckBox timeAvailable15;

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

        timeAvailable = new JCheckBox("9:00 AM");
        timeAvailable.setBounds(500, 100, 80, 30);
        panel.add(timeAvailable);
        timeAvailable.setBackground(Color.decode("#36393f"));
        timeAvailable.setForeground(Color.decode("#dcddde"));

        timeAvailable1 = new JCheckBox("9:30 AM");
        timeAvailable1.setBounds(500, 120, 80, 30);
        panel.add(timeAvailable1);
        timeAvailable1.setBackground(Color.decode("#36393f"));
        timeAvailable1.setForeground(Color.decode("#dcddde"));

        timeAvailable2 = new JCheckBox("10:00 AM");
        timeAvailable2.setBounds(500, 140, 80, 30);
        panel.add(timeAvailable2);
        timeAvailable2.setBackground(Color.decode("#36393f"));
        timeAvailable2.setForeground(Color.decode("#dcddde"));

        timeAvailable14 = new JCheckBox("10:30 AM");
        timeAvailable14.setBounds(500, 160, 80, 30);
        panel.add(timeAvailable14);
        timeAvailable14.setBackground(Color.decode("#36393f"));
        timeAvailable14.setForeground(Color.decode("#dcddde"));

        timeAvailable3 = new JCheckBox("11:00 AM");
        timeAvailable3.setBounds(500, 180, 80, 30);
        panel.add(timeAvailable3);
        timeAvailable3.setBackground(Color.decode("#36393f"));
        timeAvailable3.setForeground(Color.decode("#dcddde"));

        timeAvailable4 = new JCheckBox("11:30 AM");
        timeAvailable4.setBounds(500, 200, 80, 30);
        panel.add(timeAvailable4);
        timeAvailable4.setBackground(Color.decode("#36393f"));
        timeAvailable4.setForeground(Color.decode("#dcddde"));

        timeAvailable5 = new JCheckBox("12:00 PM");
        timeAvailable5.setBounds(600, 100, 80, 30);
        panel.add(timeAvailable5);
        timeAvailable5.setBackground(Color.decode("#36393f"));
        timeAvailable5.setForeground(Color.decode("#dcddde"));

        timeAvailable6 = new JCheckBox("12:30 PM");
        timeAvailable6.setBounds(600, 120, 80, 30);
        panel.add(timeAvailable6);
        timeAvailable6.setBackground(Color.decode("#36393f"));
        timeAvailable6.setForeground(Color.decode("#dcddde"));

        timeAvailable7 = new JCheckBox("1:00 PM");
        timeAvailable7.setBounds(600, 140, 80, 30);
        panel.add(timeAvailable7);
        timeAvailable7.setBackground(Color.decode("#36393f"));
        timeAvailable7.setForeground(Color.decode("#dcddde"));

        timeAvailable8 = new JCheckBox("1:30 PM");
        timeAvailable8.setBounds(600, 140, 80, 30);
        panel.add(timeAvailable8);
        timeAvailable8.setBackground(Color.decode("#36393f"));
        timeAvailable8.setForeground(Color.decode("#dcddde"));

        timeAvailable9 = new JCheckBox("2:00 PM");
        timeAvailable9.setBounds(600, 160, 80, 30);
        panel.add(timeAvailable9);
        timeAvailable9.setBackground(Color.decode("#36393f"));
        timeAvailable9.setForeground(Color.decode("#dcddde"));

        timeAvailable10 = new JCheckBox("2:30 PM");
        timeAvailable10.setBounds(600, 180, 80, 30);
        panel.add(timeAvailable10);
        timeAvailable10.setBackground(Color.decode("#36393f"));
        timeAvailable10.setForeground(Color.decode("#dcddde"));

        timeAvailable11 = new JCheckBox("3:00 PM");
        timeAvailable11.setBounds(600, 200, 80, 30);
        panel.add(timeAvailable11);
        timeAvailable11.setBackground(Color.decode("#36393f"));
        timeAvailable11.setForeground(Color.decode("#dcddde"));

        timeAvailable12 = new JCheckBox("3:30 PM");
        timeAvailable12.setBounds(600, 220, 80, 30);
        panel.add(timeAvailable12);
        timeAvailable12.setBackground(Color.decode("#36393f"));
        timeAvailable12.setForeground(Color.decode("#dcddde"));

        timeAvailable13 = new JCheckBox("4:00 PM");
        timeAvailable13.setBounds(600, 240, 80, 30);
        panel.add(timeAvailable13);
        timeAvailable13.setBackground(Color.decode("#36393f"));
        timeAvailable13.setForeground(Color.decode("#dcddde"));

        timeAvailable15 = new JCheckBox("4:30 PM");
        timeAvailable15.setBounds(600, 260, 80, 30);
        panel.add(timeAvailable15);
        timeAvailable15.setBackground(Color.decode("#36393f"));
        timeAvailable15.setForeground(Color.decode("#dcddde"));

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
