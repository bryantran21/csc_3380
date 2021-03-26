package main;

/**
 * @author Bryan Tran
 * @since 3/22/21
 * @about adding/viewing classes for each student
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.swing.JTextField;
import main.DOMmodifyXML;
import main.DOMreadXML;
import static main.DOMmodifyXML.addClass;

public class ClassGui implements ActionListener {

    private JPanel panel;
    private JFrame frame;
    private JButton selectButton;
    private JLabel titleLabel;
    private JLabel tutorLabel;
    private JLabel submittedLabel;
    private JPanel scrollPanel;
    private JPanel tutorPanel;
    private JLabel errorLabel;
    private JList list;
    private JButton returnButton;
    private JLabel newLabel;
    private JScrollPane scrollPane;
    private JButton submitButton;
    private JFrame smallFrame;
    private JPanel smallPanel;
    private JButton homeButton;
    private JButton logoutButton;
    private JTextField addText;
    private JButton addButton;
    private JLabel nameLabel;
    private JComboBox gradeBox;
    private JLabel courseLabel;
    private JLabel gradeLabel;
    private JLabel exampleLabel;

    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private User currentUser;

    public ClassGui(User passedUser) {

        currentUser = passedUser;

        frame = new JFrame();
        frame.setBounds(0, 0, 800, 600);
        frame.setLocationRelativeTo(null);
//        scrollPanel = new JPanel();		// Right panel that hosts objects
//        scrollPane = new JScrollPane();	// Allows scrolling through list of tutors
//        submittedLabel = new JLabel(); 	// Label that shows up after rating is submitted
        panel = new JPanel();
        panel.setBackground(Color.decode("#36393f"));

        // LEFT PANEL (panel that holds the list of available tutors and the selection button)
//        scrollPane.setViewportView(list);	// Pane that allows scrolling through the tutors
//        scrollPane.setBounds(500, 30, 200, 430);
//        list.setLayoutOrientation(JList.VERTICAL);
       
        
        errorLabel = new JLabel("");
        errorLabel.setBounds(0, 350, 800, 35);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);
      
        
        String[] grades = {"A", "B", "C", "D", "F"};
        gradeBox = new JComboBox(grades);
        gradeBox.setBounds(416, 200, 125, 30);
        panel.add(gradeBox);

        addText = new JTextField();
        addText.setBounds(416, 150, 125, 30);
        panel.add(addText);

        addButton = new JButton("Add Class");
        addButton.setBounds(416, 250, 125, 30);
        addButton.setBackground(Color.decode("#7289da"));
        addButton.setForeground(Color.decode("#dcddde"));
        addButton.addActionListener(this);
        panel.add(addButton);

        courseLabel = new JLabel("Enter course: ");
        courseLabel.setForeground(Color.decode("#dcddde"));
        courseLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        courseLabel.setBounds(258, 150, 125, 25);
        panel.add(courseLabel);

        gradeLabel = new JLabel("Enter grade: ");
        gradeLabel.setForeground(Color.decode("#dcddde"));
        gradeLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        gradeLabel.setBounds(258, 200, 125, 25);
        panel.add(gradeLabel);

        exampleLabel = new JLabel("(Ex. CSC 3380)");
        exampleLabel.setForeground(Color.decode("#dcddde"));
        exampleLabel.setFont(new Font(null, Font.PLAIN, 10));
        exampleLabel.setBounds(258, 170, 120, 25);
        panel.add(exampleLabel);
//
//        scrollPanel.setLayout(null);	// Entire panel that hosts list of tutors, scroll pane, and button
//        scrollPanel.add(scrollPane);

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

        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
        panel.setLayout(null);
        frame.setTitle("Manage Classes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
        	if (gradeBox.getSelectedIndex() != 0 || gradeBox.getSelectedIndex() != 1)
        		errorLabel.setText("You do not have an appropriate grade to tutor this class.");
        	else
        	{
        		addClass(currentUser.getEmail(), addText.getText().toUpperCase(), gradeBox.getSelectedItem().toString());
    			errorLabel.setText("");
        	}
        }
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
