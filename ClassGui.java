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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import main.Classes.classClass;
import main.DOMmodifyXML;
import main.DOMreadXML;
import main.UserSchedule.Meeting;

import static main.DOMmodifyXML.addClass;
import static main.DOMmodifyXML.schedule;

public class ClassGui implements ActionListener {

    private JPanel panel;
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel errorLabel;
    private JScrollPane scrollPane;
    private JButton homeButton;
    private JButton logoutButton;
    private JTextField addText;
    private JButton addButton;
    private JButton deleteBtn;
    private JComboBox gradeBox;
    private JLabel courseLabel;
    private JLabel gradeLabel;
    private JLabel exampleLabel;
    private JFrame confirmFrame;
    private JPanel confirmPanel;
    private JLabel confirmLabel1;
    private JButton confirmBtn;
    private JButton declineBtn;
    private JButton confirmBtn2;
    private JFrame successFrame;
    private JLabel scrollPaneLabel;
    private JPanel successPanel;
    private JLabel successLabel1;

//    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private User currentUser;
    private JList courseList;

    public ClassGui(User passedUser) {

        currentUser = passedUser;

        frame = new JFrame();
        frame.setBounds(0, 0, 800, 600);
        frame.setLocationRelativeTo(null);
//        scrollPanel = new JPanel();		// Right panel that hosts objects

//        submittedLabel = new JLabel(); 	// Label that shows up after rating is submitted
        panel = new JPanel();
        panel.setBackground(Color.decode("#36393f"));

        String emptyArray[] = {""};

        courseList = new JList(emptyArray);
        courseList.setForeground(Color.decode("#23272a"));
        courseList.setBackground(Color.decode("#99aab5"));
        ArrayList<classClass> courseArrayList = new ArrayList<classClass>();
        courseArrayList = currentUser.getclassList();
        
        
        String courses[] = new String[courseArrayList.size()];
//       System.out.print("  108 = " + courseArrayList.get(0).className + "\n");
//       System.out.print("  109 = " + courseArrayList.get(1).className + "\n");
//       System.out.print("courseArrayList.getsize() = " +  courseArrayList.size() + "\n");
        for (int increment = 0; increment < courseArrayList.size(); increment++) {
            //System.out.print(courseArrayList.get(increment).className);
            courses[increment] = courseArrayList.get(increment).className;
//        	System.out.print(increment + ") " +courses[increment] + "\n");

        }
        courseList.setListData(courses);
        courseList.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane();			// Allows scrolling through list of tutors
        scrollPane.setViewportView(courseList);
        scrollPane.setBounds(258, 375, 283, 150);
        panel.add(scrollPane);

        titleLabel = new JLabel("Manage Classes");
        titleLabel.setBounds(0, 65, 800, 60);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 40));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.decode("#dcddde"));
        panel.add(titleLabel);

        scrollPaneLabel = new JLabel("Enrolled Courses To Tutor:");
        scrollPaneLabel.setForeground(Color.decode("#dcddde"));
        scrollPaneLabel.setFont(new Font(null, Font.PLAIN, 10));
        scrollPaneLabel.setBounds(258, 350, 200, 25);
        panel.add(scrollPaneLabel);

        errorLabel = new JLabel("");
        errorLabel.setBounds(0, 300, 800, 35);
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
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        panel.add(addButton);

        deleteBtn = new JButton("Delete Class");
        deleteBtn.setBounds(550, 495, 125, 30);
        deleteBtn.setBackground(Color.decode("#7289da"));
        deleteBtn.setForeground(Color.decode("#dcddde"));
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(this);
        panel.add(deleteBtn);

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

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
        panel.setLayout(null);
        frame.setTitle("Manage Classes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void confirmPage(String text1) {

        confirmFrame = new JFrame();
        confirmFrame.setBounds(600, 250, 415, 150);
        confirmFrame.setLocationRelativeTo(null);
        confirmPanel = new JPanel();
        confirmPanel.setBackground(Color.decode("#36393f"));
        confirmPanel.setLayout(null);

        confirmLabel1 = new JLabel(text1);
        confirmLabel1.setBounds(0, 10, 400, 30);
        confirmLabel1.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        confirmLabel1.setHorizontalAlignment(JLabel.CENTER);
        confirmLabel1.setForeground(Color.decode("#dcddde"));
        confirmPanel.add(confirmLabel1);

        confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(110, 70, 80, 30);
        confirmBtn.setBackground(Color.decode("#7289da"));
        confirmBtn.setForeground(Color.decode("#dcddde"));
        confirmBtn.setFocusable(false);
        confirmBtn.addActionListener(this);
        confirmPanel.add(confirmBtn);

        confirmBtn2 = new JButton("Confirm");
        confirmBtn2.setBounds(110, 70, 80, 30);
        confirmBtn2.setBackground(Color.decode("#7289da"));
        confirmBtn2.setForeground(Color.decode("#dcddde"));
        confirmBtn2.setFocusable(false);
        confirmBtn2.addActionListener(this);
        confirmPanel.add(confirmBtn2);

        declineBtn = new JButton("Decline");
        declineBtn.setBounds(210, 70, 80, 30);
        declineBtn.setBackground(Color.decode("#7289da"));
        declineBtn.setForeground(Color.decode("#dcddde"));
        declineBtn.setFocusable(false);
        declineBtn.addActionListener(this);
        confirmPanel.add(declineBtn);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        confirmFrame.setIconImage(image.getImage());
        confirmFrame.add(confirmPanel);
        confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmFrame.setTitle("Goober - Tutor Confirmation");
        confirmFrame.setVisible(true);
        confirmFrame.setResizable(false);

    }

    public void successPage(String text1) {

        successFrame = new JFrame();
        successFrame.setBounds(600, 250, 415, 150);
        successFrame.setLocationRelativeTo(null);
        successPanel = new JPanel();
        successPanel.setLayout(null);
        successPanel.setBackground(Color.decode("#36393f"));

        successLabel1 = new JLabel(text1);
        successLabel1.setBounds(0, 20, 400, 30);
        successLabel1.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        successLabel1.setHorizontalAlignment(JLabel.CENTER);
        successLabel1.setForeground(Color.decode("#dcddde"));
        successPanel.add(successLabel1);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        successFrame.setIconImage(image.getImage());
        successFrame.add(successPanel);
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setTitle("Goober - Tutor Confirmation");
        successFrame.setVisible(true);
        successFrame.setResizable(false);
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
        if (e.getSource() == addButton) {
            if (addText.getText().isEmpty()) {
                errorLabel.setText("Please enter a course number");
            } else if (gradeBox.getSelectedIndex() >= 2) {
                errorLabel.setText("You do not have an appropriate grade to tutor this class.");
            } else {
                String confirmText1 = "Do you want to add " + addText.getText().toUpperCase() + " as one of your classes to tutor?";
                confirmPage(confirmText1);
                confirmBtn.setVisible(true);
                confirmBtn2.setVisible(false);
                errorLabel.setText("");
            }
        }
        
        if (e.getSource() == deleteBtn){
            
            int index = courseList.getSelectedIndex();
//            if (courseArrayList[index] == null){
//                
//                errorLabel.setText("Please select a course to delete");
//            }else {
            
            String confirmText2 = "Do you want to delete (course selection) from your courses to tutor?";
            confirmPage(confirmText2);
            confirmBtn.setVisible(false);
            confirmBtn2.setVisible(true);
            errorLabel.setText("");
//           }
        }

        if (e.getSource() == confirmBtn) {

            String successText1 = "You have successfully add " + addText.getText().toUpperCase();
            successPage(successText1);
            addClass(currentUser.getEmail(), addText.getText().toUpperCase(), gradeBox.getSelectedItem().toString());
            confirmFrame.dispose();
            addText.setText("");
            gradeBox.setSelectedIndex(0);
        }

        if (e.getSource() == confirmBtn2) {

            String successText2 = "You have successfully deleted (course) from your courses to tutor.";
            successPage(successText2);
            confirmFrame.dispose();
            addText.setText("");
            gradeBox.setSelectedIndex(0);
        }

        if (e.getSource() == declineBtn) {
            confirmFrame.dispose();
        }
    }
}
