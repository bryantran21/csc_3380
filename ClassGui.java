package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import main.Classes.classClass;
import static main.DOMmodifyXML.addClass;
import static main.DOMreadXML.returnUser;
import static main.DOMmodifyXML.deleteCourse;

public class ClassGui implements ActionListener{

	private JPanel panel;
	private JFrame frame;
	private JLabel titleLabel;
	private JLabel errorLabel;
	private JScrollPane scrollPane;
	private JButton homeButton;
	private JButton logoutButton;
	private JTextField addText;
	private JButton addButton;
	private JLabel courseLabel;
	private JLabel gradeLabel;
	private JComboBox gradeBox;
	private JLabel exampleLabel;
	private JFrame confirmFrame;
	private JPanel confirmPanel;
	private JLabel confirmLabel1;
	private JButton confirmBtnAdd;
	private JButton confirmBtnDel;
	private JButton declineBtn;
	private JFrame successFrame;
	private JLabel scrollPaneLabel;
	private JPanel successPanel;
	private JLabel successLabel1;
	private JButton deleteClassButton;

	int courseArrayListSize = 0;
	ArrayList<classClass> courseArrayList = new ArrayList<classClass>();
	String courses[] = new String[1];
	String coursesSorted[] = new String[500];
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private User currentUser;
	private JList courseList;

	public class ComparatorUser implements Comparator { // A comparator function used to alphabetically compare users

		public int compare(Object arg0, Object arg1) {
			String course0 = (String) arg0;
			String course1 = (String) arg1;

			String courseZero = course0;
			String courseOne = course1;

			int flag = courseZero.compareTo(courseOne);
			return flag;
		}
	}

	public void sortCourses() // A sort function that sorts tutors alphabetically
	{
		ComparatorUser comparator = new ComparatorUser();

		Arrays.sort(courses, 0, courseArrayListSize, comparator); // Sorts users alphabetically

		for (int i = 0; i < courseArrayListSize; i++) // Creates a string array of tutors with the first and last name,
														// sorted
		{
			coursesSorted[i] = courses[i];
		}

//		Arrays.sort(tutorNames,0,count);
	}

	public ClassGui(User passedUser) {

		currentUser = passedUser;
		courses = new String[currentUser.getclassList().size()];

		frame = new JFrame();
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);
//        scrollPanel = new JPanel();		// Right panel that hosts objects

//        submittedLabel = new JLabel(); 	// Label that shows up after rating is submitted
		panel = new JPanel();
		panel.setBackground(Color.decode("#36393f"));

		String emptyArray[] = { "" };

		courseList = new JList(emptyArray);
		courseList.setForeground(Color.decode("#23272a"));
		courseList.setBackground(Color.decode("#99aab5"));

		courseArrayList = currentUser.getclassList();
		courseArrayListSize = courseArrayList.size();

		for (int increment = 0; increment < courseArrayList.size(); increment++) {
			courses[increment] = courseArrayList.get(increment).className;
		}
		coursesSorted = new String[courseArrayListSize];
		sortCourses();
		courseList.setListData(coursesSorted);
		courseList.setLayoutOrientation(JList.VERTICAL);

		scrollPane = new JScrollPane(); // Allows scrolling through list of tutors
		scrollPane.setViewportView(courseList);
		scrollPane.setBounds(258, 375, 283, 100);
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

		String[] grades = { "A", "B", "C", "D", "F" };
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

		deleteClassButton = new JButton("Delete Selected Class");
		deleteClassButton.setBounds(315, 500, 170, 30);
		deleteClassButton.setBackground(Color.decode("#7289da"));
		deleteClassButton.setForeground(Color.decode("#dcddde"));
		deleteClassButton.setFocusable(false);
		deleteClassButton.addActionListener(this);
		panel.add(deleteClassButton);

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

		confirmBtnAdd = new JButton("Confirm");
		confirmBtnAdd.setBounds(110, 70, 80, 30);
		confirmBtnAdd.setBackground(Color.decode("#7289da"));
		confirmBtnAdd.setForeground(Color.decode("#dcddde"));
		confirmBtnAdd.setFocusable(false);
		confirmBtnAdd.setVisible(false);
		confirmBtnAdd.addActionListener(this);
		confirmPanel.add(confirmBtnAdd);

		confirmBtnDel = new JButton("Confirm");
		confirmBtnDel.setBounds(110, 70, 80, 30);
		confirmBtnDel.setBackground(Color.decode("#7289da"));
		confirmBtnDel.setForeground(Color.decode("#dcddde"));
		confirmBtnDel.setFocusable(false);
		confirmBtnDel.setVisible(false);
		confirmBtnDel.addActionListener(this);
		confirmPanel.add(confirmBtnDel);

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
		
                if (e.getSource() == addButton) {
			if (addText.getText().isEmpty()) {
				errorLabel.setText("Please enter a course number");
			} else if (gradeBox.getSelectedIndex() >= 2) {
				errorLabel.setText("You do not have an appropriate grade to tutor this class.");
			} else {
				String confirmText1 = "Do you want to add " + addText.getText().toUpperCase()
						+ " as one of your classes to tutor?";
				confirmPage(confirmText1);
				confirmBtnAdd.setVisible(true);
				confirmBtnDel.setVisible(false);
				errorLabel.setText("");
			}
		}
                if (e.getSource() == deleteClassButton) {
			if (courseList.getSelectedIndex() == -1) {
				errorLabel.setText("Please select a class you would like to delete.");
			} else {
				String confirmText1 = "Do you want to delete " + courses[courseList.getSelectedIndex()]
						+ " from your classes to tutor?";
				
				confirmPage(confirmText1);
				confirmBtnAdd.setVisible(false);
				confirmBtnDel.setVisible(true);
				
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
		if (e.getSource() == confirmBtnAdd) {

			String successText1 = "You have successfully addded " + addText.getText().toUpperCase() + ".";
			successPage(successText1);
			addClass(currentUser.getEmail(), addText.getText().toUpperCase(), gradeBox.getSelectedItem().toString());
			currentUser = returnUser(currentUser.getEmail()); // Updates current user
			courseArrayList = currentUser.getclassList();
			courseArrayListSize = courseArrayList.size();
			courses = new String[courseArrayListSize];
			coursesSorted = new String[courseArrayListSize];

			for (int increment = 0; increment < courseArrayListSize; increment++) {
				courses[increment] = courseArrayList.get(increment).className;
			}
			sortCourses();
			courseList.setListData(coursesSorted);
			confirmFrame.dispose();
			addText.setText("");
			gradeBox.setSelectedIndex(0);
		}
		if(e.getSource() == confirmBtnDel) {
			int index = courseList.getSelectedIndex();
                        String successText1 = "You have successfully deleted " + courses[courseList.getSelectedIndex()] + ".";
			successPage(successText1);
			deleteCourse(currentUser.getEmail(), coursesSorted[index]);
			currentUser = returnUser(currentUser.getEmail()); // Updates current user
			courseArrayList = currentUser.getclassList();
			courseArrayListSize = courseArrayList.size();
			courses = new String[courseArrayListSize];
			coursesSorted = new String[courseArrayListSize];
                        
                        

			for (int increment = 0; increment < courseArrayListSize; increment++) {
				courses[increment] = courseArrayList.get(increment).className;
			}
			sortCourses();
			courseList.setListData(coursesSorted);
			confirmFrame.dispose();
		}
		if (e.getSource() == declineBtn) {
			confirmFrame.dispose();
                        addText.setText("");
		}
		
	}

}
