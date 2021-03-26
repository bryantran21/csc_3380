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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static main.DOMreadXML.avgRating;
import static main.DOMreadXML.listOfTutors;
import static main.DOMreadXML.tutorsInClass;
import static main.DOMmodifyXML.schedule;
//import static main.postMeetingReview;

public class SearchGui implements ActionListener, ListSelectionListener, ItemListener {

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
	private JComboBox dayBox;
	private JButton scheduleBtn;
	private JFrame confirmFrame;
	private JPanel confirmPanel;
	private JLabel confirmLabel1;
	private JLabel confirmLabel2;
	private JLabel formatLabel;
	private JButton confirmBtn;
	private JButton declineBtn;
	private JFrame successFrame;
	private JPanel successPanel;
	private JLabel successLabel1;
	private JLabel successLabel2;
	private User currentUser;
	private JList newList;
	private JLabel exampleLabel;
	private String daySelect = "";
	private String timeSelect;
	private JRadioButton mondayRadio;
	private JRadioButton tuesdayRadio;
	private JRadioButton wednesdayRadio;
	private JRadioButton thursdayRadio;
	private JRadioButton fridayRadio;
	private JRadioButton saturdayRadio;
	private JRadioButton sundayRadio;

	String dayArray[] = { "", "", "", "", "", "", "" };
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

		frame = new JFrame();
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);

		// LEFT PANEL (panel that holds the list of available tutors and the selection
		// button)

		scrollPanel = new JPanel();

		list = new JList();
		list.setLayoutOrientation(JList.VERTICAL);
		list.setForeground(Color.decode("#23272a"));
		list.setBackground(Color.decode("#99aab5"));
		list.addListSelectionListener(this);

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

		exampleLabel = new JLabel("(Ex. CSC 3380)");
		exampleLabel.setBounds(10, 25, 90, 25);
		exampleLabel.setFont(new Font(null, Font.PLAIN, 10));
		exampleLabel.setForeground(Color.decode("#dcddde"));
		scrollPanel.add(exampleLabel);

		formatLabel = new JLabel("Last Name, First Name (Average Rating)");
		formatLabel.setBounds(100, 55, 200, 25);
		formatLabel.setFont(new Font(null, Font.PLAIN, 10));
		formatLabel.setForeground(Color.decode("#dcddde"));
		formatLabel.setHorizontalAlignment(JLabel.CENTER);
		scrollPanel.add(formatLabel);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		scrollPane.setBounds(100, 75, 200, 375);
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

		mondayRadio = new JRadioButton();
		mondayRadio.setText("Monday");
		mondayRadio.setForeground(Color.decode("#dcddde"));
		mondayRadio.setBounds(150, 200, 400, 30);
		mondayRadio.setBackground(Color.decode("#36393f"));
		tutorPanel.add(mondayRadio);
		mondayRadio.setVisible(false);
		mondayRadio.addItemListener(this);

		tuesdayRadio = new JRadioButton();
		tuesdayRadio.setText("Tuesday");
		tuesdayRadio.setForeground(Color.decode("#dcddde"));
		tuesdayRadio.setBackground(Color.decode("#36393f"));
		tuesdayRadio.setBounds(150, 225, 400, 30);
		tutorPanel.add(tuesdayRadio);
		tuesdayRadio.setVisible(false);
		tuesdayRadio.addItemListener(this);

		wednesdayRadio = new JRadioButton();
		wednesdayRadio.setText("Wednesday");
		wednesdayRadio.setForeground(Color.decode("#dcddde"));
		wednesdayRadio.setBackground(Color.decode("#36393f"));
		wednesdayRadio.setBounds(150, 250, 400, 30);
		tutorPanel.add(wednesdayRadio);
		wednesdayRadio.setVisible(false);
		wednesdayRadio.addItemListener(this);

		thursdayRadio = new JRadioButton();
		thursdayRadio.setText("Thursday");
		thursdayRadio.setForeground(Color.decode("#dcddde"));
		thursdayRadio.setBackground(Color.decode("#36393f"));
		thursdayRadio.setBounds(150, 275, 400, 30);
		tutorPanel.add(thursdayRadio);
		thursdayRadio.setVisible(false);
		thursdayRadio.addItemListener(this);

		fridayRadio = new JRadioButton();
		fridayRadio.setText("Friday");
		fridayRadio.setForeground(Color.decode("#dcddde"));
		fridayRadio.setBackground(Color.decode("#36393f"));
		fridayRadio.setBounds(150, 300, 400, 30);
		tutorPanel.add(fridayRadio);
		fridayRadio.setVisible(false);
		fridayRadio.addItemListener(this);

		saturdayRadio = new JRadioButton();
		saturdayRadio.setText("Saturday");
		saturdayRadio.setForeground(Color.decode("#dcddde"));
		saturdayRadio.setBackground(Color.decode("#36393f"));
		saturdayRadio.setBounds(150, 325, 400, 30);
		tutorPanel.add(saturdayRadio);
		saturdayRadio.setVisible(false);
		saturdayRadio.addItemListener(this);

		sundayRadio = new JRadioButton();
		sundayRadio.setText("Sunday");
		sundayRadio.setForeground(Color.decode("#dcddde"));
		sundayRadio.setBackground(Color.decode("#36393f"));
		sundayRadio.setBounds(150, 350, 400, 30);
		tutorPanel.add(sundayRadio);
		sundayRadio.setVisible(false);
		sundayRadio.addItemListener(this);

		tutorLabel = new JLabel();
		tutorLabel.setBounds(0, 150, 400, 30);
		tutorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 25));
		tutorLabel.setHorizontalAlignment(JLabel.CENTER);
		tutorLabel.setForeground(Color.decode("#dcddde"));
		tutorPanel.add(tutorLabel);

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

		confirmLabel2 = new JLabel(text2);
		confirmLabel2.setBounds(0, 30, 400, 30);
		confirmLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		confirmLabel2.setHorizontalAlignment(JLabel.CENTER);
		confirmLabel2.setForeground(Color.decode("#dcddde"));
		confirmPanel.add(confirmLabel2);

		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(110, 70, 80, 30);
		confirmBtn.setBackground(Color.decode("#7289da"));
		confirmBtn.setForeground(Color.decode("#dcddde"));
		confirmBtn.setFocusable(false);
		confirmBtn.addActionListener(this);
		confirmPanel.add(confirmBtn);

		declineBtn = new JButton("Decline");
		declineBtn.setBounds(210, 70, 80, 30);
		declineBtn.setBackground(Color.decode("#7289da"));
		declineBtn.setForeground(Color.decode("#dcddde"));
		declineBtn.setFocusable(false);
		declineBtn.addActionListener(this);
		confirmPanel.add(declineBtn);

		confirmFrame.add(confirmPanel);
		confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		confirmFrame.setTitle("Goober - Tutor Confirmation");
		confirmFrame.setVisible(true);
		confirmFrame.setResizable(false);

	}

	public void successPage(String text1, String text2) {

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
		if (e.getSource() == searchBtn) {
			String className = searchText.getText().toUpperCase();
			tutors = tutorsInClass(className);

			if (className.equals("")) {
				String nullList[] = new String[1];
				nullList[0] = "";
				list.setListData(nullList);
				scrollPane.add(list);
				errorLabel.setText("<html> Please type in a course and try again.</html>");
			} else if (tutors[0] == null) {
				String nullList[] = new String[1];
				nullList[0] = "";
				list.setListData(nullList);
				scrollPane.add(list);
				errorLabel.setText(
						"<html> There are currently no tutors for this course.<br/>  Please enter a different course.</html>");
			} else {
				sortTutors();
				list.setListData(tutorNames);
				scrollPane.add(list);
				errorLabel.setText("");
			}
		}

		if (e.getSource() == scheduleBtn) {
			String confirmText = "Schedule to meet with " + tutors[index].getFirstName() + " "
					+ tutors[index].getLastName() + " on";
			String confirmText2 = daySelect + "?";
			confirmPage(confirmText, confirmText2);

		}

		if (e.getSource() == confirmBtn) {

			String successText1 = "You have successfully scheduled to meet with " + tutors[index].getFirstName() + " "
					+ tutors[index].getLastName() + " on";
			String successText2 = daySelect + ".";
			HomePage homepage = new HomePage(currentUser);
			successPage(successText1, successText2);
			schedule(currentUser.getEmail(), tutors[index].getEmail(), daySelect, timeSelect);
			confirmFrame.dispose();
			frame.dispose();
		}
		if (e.getSource() == declineBtn) {
			confirmFrame.dispose();
//			titleLabel.setText("");
//			tutorLabel.setText("");
//			scheduleBtn.setVisible(false);
//			mondayRadio.setVisible(false);
//			tuesdayRadio.setVisible(false);
//			wednesdayRadio.setVisible(false);
//			thursdayRadio.setVisible(false);
//			fridayRadio.setVisible(false);
//			saturdayRadio.setVisible(false);
//			sundayRadio.setVisible(false);
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = list.getSelectedIndex();
		if (!e.getValueIsAdjusting()) {

			if (tutors[index] == null) { // If no tutor was selected from the list

				errorLabel.setText("No tutor selected.  Please select a tutor.");
				titleLabel.setText("");
				tutorLabel.setText("");
				scheduleBtn.setVisible(false);
				mondayRadio.setVisible(false);
				tuesdayRadio.setVisible(false);
				wednesdayRadio.setVisible(false);
				thursdayRadio.setVisible(false);
				fridayRadio.setVisible(false);
				saturdayRadio.setVisible(false);
				sundayRadio.setVisible(false);
			} else {

				tutorLabel.setText(tutors[index].getFirstName() + " " + tutors[index].getLastName());
				titleLabel.setText("You have selected");
				errorLabel.setText("");
				scheduleBtn.setVisible(true);
				mondayRadio.setVisible(true);
				tuesdayRadio.setVisible(true);
				wednesdayRadio.setVisible(true);
				thursdayRadio.setVisible(true);
				fridayRadio.setVisible(true);
				saturdayRadio.setVisible(true);
				sundayRadio.setVisible(true);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			daySelect = "";
			Object source = e.getSource();
			if (source == mondayRadio) {
				dayArray[0] = "Monday";
			}
			if (source == tuesdayRadio) {
				dayArray[1] = "Tuesday";
			}
			if (source == wednesdayRadio) {
				dayArray[2] = "Wednesday";
			}
			if (source == thursdayRadio) {
				dayArray[3] = "Thursday";
			}
			if (source == fridayRadio) {
				dayArray[4] = "Friday";
			}
			if (source == saturdayRadio) {
				dayArray[5] = "Saturday";
			}
			if (source == sundayRadio) {
				dayArray[6] = "Sunday";
			}
		}
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			daySelect = "";
			Object source = e.getSource();
			if (source == mondayRadio) {
				dayArray[0] = "";
			}
			if (source == tuesdayRadio) {
				dayArray[1] = "";
			}
			if (source == wednesdayRadio) {
				dayArray[2] = "";
			}
			if (source == thursdayRadio) {
				dayArray[3] = "";
			}
			if (source == fridayRadio) {
				dayArray[4] = "";
			}
			if (source == saturdayRadio) {
				dayArray[5] = "";
			}
			if (source == sundayRadio) {
				dayArray[6] = "";
			}
		}
		for (int i = 0; i < 7; i++) {
			daySelect = daySelect + dayArray[i];
			if ((i + 1 < 7) && (dayArray[i + 1].compareTo("") != 0)) {
				daySelect = daySelect + ", ";
			}
		}
		System.out.print(daySelect);
	}
}
