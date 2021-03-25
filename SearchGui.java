package main;

/**
 *
 * @author Darrion Rudd, Anthony Duong, Blake Lalonde
 * @since 3/16/2021
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import static main.DOMreadXML.avgRating;
import static main.DOMreadXML.listOfTutors;
//import static main.postMeetingReview;

public class SearchGui implements ActionListener {

	private JFrame frame;
	private JList list;
	private JPanel scrollPanel;
	private JScrollPane scrollPane;
	private JLabel searchLabel;
	private JTextField searchText;
	private JButton searchBtn;
	private JLabel errorLabel;
	private JButton selectBtn;
	private JPanel tutorPanel;
	private JButton logoutBtn;
	private JButton homeBtn;
	private JLabel titleLabel;
	private JLabel tutorLabel;
	private JLabel dayLabel;
	private JComboBox dayBox;
	private JLabel timeLabel;
	private JComboBox Days;
	private JComboBox Times;
	private JButton scheduleBtn;
	private JFrame confirmFrame;
	private JPanel confirmPanel;
	private JLabel confirmLabel1;
	private JLabel confirmLabel2;
	private JButton confirmBtn;
	private JFrame successFrame;
	private JPanel successPanel;
	private JLabel successLabel1;
	private JLabel successLabel2;
	private User currentUser;
	private String daySelect;
	private String timeSelect;

	User tutors[] = listOfTutors(); // Receives the list of tutors from the database
	String tutorNames[] = new String[100];

	public class ComparatorUser implements Comparator { // A comparator function used to alphabetically compare users

		public int compare(Object arg0, Object arg1) {
			User user0 = (User) arg0;
			User user1 = (User) arg1;

			String userZero = user0.getLastName() + user0.getFirstName();
			String userOne = user1.getLastName() + user1.getFirstName();

			int flag = userZero.compareTo(userOne);
			return flag;
		}
	}

	public void sortTutors() // A sort function that sorts tutors alphabetically
	{

		int count = 0;
		User temp = null;

		ComparatorUser comparator = new ComparatorUser();

		for (int i = 0; tutors[i] != null; i++) // For loop that runs through entire list of tutors
		{

			count++;
		}
		Arrays.sort(tutors, 0, count, comparator); // Sorts users alphabetically

		for (int i = 0; tutors[i] != null; i++) // Creates a string array of tutors with the first and last name, sorted
		{
			String avg = String.valueOf(avgRating(tutors[i].getEmail()));
			int maxLength = (avg.length() < 4) ? avg.length() : 4;
			avg = avg.substring(0, maxLength);
			tutorNames[i] = tutors[i].getLastName() + ", " + tutors[i].getFirstName() + " (" + avg + ")";
		}

//		Arrays.sort(tutorNames,0,count);
	}

	public SearchGui(User passedUser) {

		currentUser = passedUser;
		sortTutors();

		frame = new JFrame();
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);

		// LEFT PANEL (panel that holds the list of available tutors and the selection
		// button)

		scrollPanel = new JPanel();

		list = new JList(tutorNames);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setForeground(Color.decode("#23272a"));
		list.setBackground(Color.decode("#99aab5"));

		searchLabel = new JLabel("Enter course: ");
		searchLabel.setForeground(Color.decode("#dcddde"));
		searchLabel.setBounds(10, 10, 100, 25);
		scrollPanel.add(searchLabel);

		searchText = new JTextField();
		searchText.setBounds(100, 10, 110, 25);
		scrollPanel.add(searchText);

		searchBtn = new JButton("Search");
		searchBtn.setBounds(220, 10, 80, 25);
		searchBtn.setBackground(Color.decode("#7289da"));
		searchBtn.setForeground(Color.decode("#dcddde"));
		searchBtn.setFocusable(false);
		searchBtn.addActionListener(this);
		scrollPanel.add(searchBtn);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		scrollPane.setBounds(100, 50, 200, 400);
		scrollPanel.add(scrollPane);

		errorLabel = new JLabel("");
		errorLabel.setBounds(0, 460, 400, 35);
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		errorLabel.setForeground(Color.RED);
		scrollPanel.add(errorLabel);

		selectBtn = new JButton("Select Tutor");
		selectBtn.setBounds(100, 500, 200, 30);
		selectBtn.setBackground(Color.decode("#7289da"));
		selectBtn.setForeground(Color.decode("#dcddde"));
		selectBtn.setFocusable(false);
		selectBtn.addActionListener(this);
		scrollPanel.add(selectBtn);

		scrollPanel.setBackground(Color.decode("#36393f"));
		scrollPanel.setLayout(null);

		// RIGHT PANEL (panel that labels the selected tutor, slider for rating, and
		// submission button to update tutor's rating in DB)

		tutorPanel = new JPanel();
		tutorPanel.setLayout(null);
		tutorPanel.setBackground(Color.decode("#36393f"));

		titleLabel = new JLabel();
		titleLabel.setBounds(0, 125, 400, 30);
		titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setForeground(Color.decode("#dcddde"));
		titleLabel.setText("");
		tutorPanel.add(titleLabel);

		tutorLabel = new JLabel();
		tutorLabel.setBounds(0, 150, 400, 30);
		tutorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 25));
		tutorLabel.setHorizontalAlignment(JLabel.CENTER);
		tutorLabel.setForeground(Color.decode("#dcddde"));
		tutorPanel.add(tutorLabel);

		dayLabel = new JLabel("Select a day: ");
		dayLabel.setBounds(75, 200, 150, 25);
		dayLabel.setForeground(Color.decode("#dcddde"));
		dayLabel.setVisible(false);
		tutorPanel.add(dayLabel);

		timeLabel = new JLabel("Select a time: ");
		timeLabel.setBounds(75, 240, 150, 25);
		timeLabel.setForeground(Color.decode("#dcddde"));
		timeLabel.setVisible(false);
		tutorPanel.add(timeLabel);

		String[] dayOptions = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		Days = new JComboBox(dayOptions);
		Days.setBounds(175, 200, 150, 25);
		Days.setForeground(Color.decode("#23272a"));
		Days.setBackground(Color.decode("#99aab5"));
		Days.setVisible(false);
		tutorPanel.add(Days);

		String[] timeOptions = { "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM",
				"12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM" };

		Times = new JComboBox(timeOptions);
		Times.setBounds(175, 240, 150, 25);
		Times.setForeground(Color.decode("#23272a"));
		Times.setBackground(Color.decode("#99aab5"));
		Times.setVisible(false);
		tutorPanel.add(Times);

		scheduleBtn = new JButton("Schedule Meeting");
		scheduleBtn.setBounds(100, 500, 200, 30);
		scheduleBtn.setBackground(Color.decode("#7289da"));
		scheduleBtn.setForeground(Color.decode("#dcddde"));
		scheduleBtn.setFocusable(false);
		scheduleBtn.addActionListener(this);
		scheduleBtn.setVisible(false);
		tutorPanel.add(scheduleBtn);

		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(300, 10, 80, 30);
		logoutBtn.setBackground(Color.decode("#7289da"));
		logoutBtn.setForeground(Color.decode("#dcddde"));
		logoutBtn.setFocusable(false);
		logoutBtn.addActionListener(this);
		tutorPanel.add(logoutBtn);

		homeBtn = new JButton("Home");
		homeBtn.setBounds(210, 10, 80, 30);
		homeBtn.setBackground(Color.decode("#7289da"));
		homeBtn.setForeground(Color.decode("#dcddde"));
		homeBtn.setFocusable(false);
		homeBtn.addActionListener(this);
		tutorPanel.add(homeBtn);

		frame.setLayout(new GridLayout(1, 2));
		frame.add(scrollPanel);
		frame.add(tutorPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Goober - Search for Tutors");
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void confirmPage(String text1, String text2) {

		confirmFrame = new JFrame();
		confirmFrame.setBounds(600, 250, 400, 150);
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

		confirmLabel2 = new JLabel(text2);
		confirmLabel2.setBounds(0, 30, 400, 30);
		confirmLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		confirmLabel2.setHorizontalAlignment(JLabel.CENTER);
		confirmLabel2.setForeground(Color.decode("#dcddde"));
		confirmPanel.add(confirmLabel2);

		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(160, 70, 80, 30);
		confirmBtn.setBackground(Color.decode("#7289da"));
		confirmBtn.setForeground(Color.decode("#dcddde"));
		confirmBtn.setFocusable(false);
		confirmBtn.addActionListener(this);
		confirmPanel.add(confirmBtn);

		confirmFrame.add(confirmPanel);
		confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		confirmFrame.setTitle("Goober - Tutor Confirmation");
		confirmFrame.setVisible(true);
		confirmFrame.setResizable(false);

	}

	public void successPage(String text1, String text2) {

		successFrame = new JFrame();
		successFrame.setBounds(600, 250, 400, 150);
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

		successLabel2 = new JLabel(text2);
		successLabel2.setBounds(0, 40, 400, 30);
		successLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		successLabel2.setHorizontalAlignment(JLabel.CENTER);
		successLabel2.setForeground(Color.decode("#dcddde"));
		successPanel.add(successLabel2);

		successFrame.add(successPanel);
		successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		successFrame.setTitle("Goober - Tutor Confirmation");
		successFrame.setVisible(true);
		successFrame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int index = list.getSelectedIndex();
		if (e.getSource() == selectBtn) {

			if (tutors[index] == null) { // If no tutor was selected from the list

				errorLabel.setText("No tutor selected.  Please select a tutor.");
				titleLabel.setText("");
				tutorLabel.setText("");
				scheduleBtn.setVisible(false);
				Days.setVisible(false);
				Times.setVisible(false);
				dayLabel.setVisible(false);
				timeLabel.setVisible(false);

			} else {

				tutorLabel.setText(tutors[index].getFirstName() + " " + tutors[index].getLastName());
				titleLabel.setText("You have selected");
				errorLabel.setText("");
				scheduleBtn.setVisible(true);
				Days.setVisible(true);
				Times.setVisible(true);
				dayLabel.setVisible(true);
				timeLabel.setVisible(true);

			}
		}
		if (e.getSource() == scheduleBtn) {
			timeSelect = Times.getSelectedItem().toString();
			daySelect = Days.getSelectedItem().toString();
			String confirmText = "Schedule to meet with " + tutors[index].getFirstName() + " "
					+ tutors[index].getLastName();
			String confirmText2 = "on " + daySelect + " at " + timeSelect + "?";
			confirmPage(confirmText, confirmText2);

		}

		if (e.getSource() == confirmBtn) {

			String successText1 = "You have successfully scheduled to meet with " + tutors[index].getFirstName() + " "
					+ tutors[index].getLastName();
			String successText2 = " on " + daySelect + " at " + timeSelect + ".";
			HomePage homepage = new HomePage(currentUser);
			successPage(successText1, successText2);
			confirmFrame.dispose();
			frame.dispose();
		}

		if (e.getSource() == logoutBtn) {

			LoginGui login = new LoginGui();
			frame.dispose();
		}

		if (e.getSource() == homeBtn) {

			HomePage home = new HomePage(currentUser);
			frame.dispose();
		}
	}

}
