package main;

/**
 *
 * @author Blake Lalonde
 * @since 2/26/2021
 * @about A class that creates a java window where a user can give a tutor a
 * rating. Meant for after they've had a meeting.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.Arrays;
import java.util.Comparator;
import javax.swing.ImageIcon;
import static main.DOMreadXML.listOfTutors;
import static main.DOMmodifyXML.ratings;
import static main.DOMreadXML.avgRating;

;

public class postMeetingReview implements ActionListener, ListSelectionListener {

	private JFrame frame;
	private JLabel staticTitleLabel;
	private JLabel titleLabel;
	private JLabel tutorLabel;
	private JLabel submittedLabel;
	private JPanel scrollPanel;
	private JPanel tutorPanel;
	private JLabel errorLabel;
	private JList list;
	private JButton returnButton;
	private JLabel averageRating;
	private JLabel newLabel;
	private JSlider ratingSlider;
	private JScrollPane scrollPane;
	private JButton submitButton;
	private JFrame smallFrame;
	private JPanel smallPanel;
	private User currentUser;
	private JButton logoutButton;
	private JLabel nanLabel;
	private JButton homeButton;

	User tutors[] = listOfTutors(); // Receives the list of tutors from the database
	String tutorNames[] = new String[500];

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
		ComparatorUser comparator = new ComparatorUser();

		for (int i = 0; tutors[i] != null; i++) // For loop that runs through entire list of tutors
		{

			count++;
		}
		Arrays.sort(tutors, 0, count, comparator); // Sorts users alphabetically

		for (int i = 0; tutors[i] != null; i++) // Creates a string array of tutors with the first and last name, sorted
		{
			String avg = String.valueOf(avgRating(tutors[i].getEmail()));
			if (avg.equals("NaN")) {
				avg = "NR";
			} else {
				int maxLength = (avg.length() < 4) ? avg.length() : 4;
				avg = avg.substring(0, maxLength);
			}

			tutorNames[i] = tutors[i].getLastName() + ", " + tutors[i].getFirstName() + " (" + avg + ")";
		}

	}

	public postMeetingReview(User passedUser) {
		currentUser = passedUser;

		sortTutors();

		frame = new JFrame();
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);
		scrollPanel = new JPanel(); // Right panel that hosts objects
		tutorPanel = new JPanel(); // Left panel that hosts objects
		scrollPane = new JScrollPane(); // Allows scrolling through list of tutors
		titleLabel = new JLabel(); // Static text label
		tutorLabel = new JLabel(); // Label that is updated with the selected tutor
		submittedLabel = new JLabel(); // Label that shows up after rating is submitted
		staticTitleLabel = new JLabel();

		// LEFT PANEL (panel that holds the list of available tutors and the selection
		// button)
		{
			list = new JList(tutorNames); // List of all available tutors
			list.setForeground(Color.decode("#23272a"));
			list.setBackground(Color.decode("#99aab5"));
			list.addListSelectionListener(this);

			staticTitleLabel.setText("Select A Tutor");
			staticTitleLabel.setBounds(00, 0, 400, 50); // Static text label
			staticTitleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 20));
			staticTitleLabel.setHorizontalAlignment(JLabel.CENTER);
			staticTitleLabel.setForeground(Color.decode("#dcddde"));

			scrollPane.setViewportView(list); // Pane that allows scrolling through the tutors
			scrollPane.setBounds(100, 50, 200, 450);
			list.setLayoutOrientation(JList.VERTICAL);

			errorLabel = new JLabel("");
			errorLabel.setBounds(0, 515, 400, 35);
			errorLabel.setHorizontalAlignment(JLabel.CENTER);
			errorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
			errorLabel.setForeground(Color.RED);
			scrollPanel.add(errorLabel);

			nanLabel = new JLabel("*NR = No Rating");
			nanLabel.setBounds(10, 45, 90, 25);
			nanLabel.setFont(new Font(null, Font.PLAIN, 10));
			nanLabel.setForeground(Color.decode("#dcddde"));
			scrollPanel.add(nanLabel);

			scrollPanel.setLayout(null); // Entire panel that hosts list of tutors, scroll pane, and button
			scrollPanel.add(staticTitleLabel);
			scrollPanel.add(scrollPane);
			scrollPanel.setBackground(Color.decode("#36393f"));

		}

		// RIGHT PANEL (panel that labels the selected tutor, slider for rating, and
		// submission button to update tutor's rating in DB)
		{
			titleLabel.setBounds(0, 118, 400, 30); // Static text label
			titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
			titleLabel.setForeground(Color.decode("#dcddde"));
			titleLabel.setHorizontalAlignment(JLabel.CENTER);

			tutorLabel.setBounds(0, 140, 400, 30); // Text label that gets updated with selected tutor's name
			tutorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 25));
			tutorLabel.setForeground(Color.decode("#dcddde"));
			tutorLabel.setHorizontalAlignment(JLabel.CENTER);

			averageRating = new JLabel();
			averageRating.setBounds(0, 165, 400, 30);
			averageRating.setFont(new Font(null, Font.PLAIN, 12));
			averageRating.setForeground(Color.decode("#dcddde"));
			averageRating.setHorizontalAlignment(JLabel.CENTER);
			averageRating.setVisible(false);

			int ratingMin = 0; // The minimum rating available for a tutor
			int ratingMax = 10; // The maximum rating available for a tutor
			int ratingInitial = 5; // The starting value on the slider

			ratingSlider = new JSlider(JSlider.HORIZONTAL, ratingMin, ratingMax, ratingInitial); // Slider that allows
																									// to select desired
																									// rating
			ratingSlider.setMajorTickSpacing(1);
			ratingSlider.setPaintLabels(true);
			ratingSlider.setBounds(50, 200, 300, 50);
			ratingSlider.setForeground(Color.decode("#dcddde"));
			ratingSlider.setBackground(Color.decode("#36393f"));
			ratingSlider.setVisible(false);

			submitButton = new JButton("Submit Rating"); // Button that updates the specific tutor's rating average with
															// new number
			submitButton.setBounds(100, 275, 200, 30);
			submitButton.setBackground(Color.decode("#7289da"));
			submitButton.setForeground(Color.decode("#dcddde"));
			submitButton.addActionListener(this);
			submitButton.setVisible(false);

			logoutButton = new JButton("Logout");
			logoutButton.setBounds(300, 10, 80, 30);
			logoutButton.setBackground(Color.decode("#7289da"));
			logoutButton.setForeground(Color.decode("#dcddde"));
			logoutButton.setFocusable(false);
			logoutButton.addActionListener(this);
			tutorPanel.add(logoutButton);

			homeButton = new JButton("Home");
			homeButton.setBounds(210, 10, 80, 30);
			homeButton.setBackground(Color.decode("#7289da"));
			homeButton.setForeground(Color.decode("#dcddde"));
			homeButton.setFocusable(false);
			homeButton.addActionListener(this);
			tutorPanel.add(homeButton);

			tutorPanel.setLayout(null); // Entire panel that hosts three labels, slider, and submission and return
										// buttons
			tutorPanel.add(titleLabel);
			tutorPanel.add(tutorLabel);
			tutorPanel.add(ratingSlider);
			tutorPanel.add(submitButton);
			tutorPanel.add(submittedLabel);
			tutorPanel.add(logoutButton);
			tutorPanel.add(homeButton);
			tutorPanel.add(averageRating);
			tutorPanel.setBackground(Color.decode("#36393f"));
		}

		ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
		frame.setIconImage(image.getImage());
		frame.setLayout(new GridLayout(1, 2));
		frame.add(scrollPanel);
		frame.add(tutorPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Goober - Post Meeting Review");
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void submittedRating(String text) // Creates a window pop-up "notification" that tells the user the rating is
												// give to x tutor
	{
		smallFrame = new JFrame();
		smallFrame.setBounds(600, 250, 400, 150);
		smallFrame.setLocationRelativeTo(null);
		smallPanel = new JPanel();
		smallPanel.setBackground(Color.decode("#36393f"));

		newLabel = new JLabel(text);
		newLabel.setBounds(0, 30, 400, 30);
		newLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
		newLabel.setForeground(Color.decode("#dcddde"));
		newLabel.setHorizontalAlignment(JLabel.CENTER);

		smallPanel.setLayout(null);
		smallPanel.add(newLabel);

		ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
		smallFrame.setIconImage(image.getImage());
		smallFrame.add(smallPanel);
		smallFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		smallFrame.setTitle("Goober - Rating");
		smallFrame.setVisible(true);
		smallFrame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = list.getSelectedIndex();
		if (e.getSource() == submitButton) // Closes the current window and opens up the home page as well as a
											// notification window
		{
			String labelText = "You have given " + tutors[index].getFirstName() + " " + tutors[index].getLastName()
					+ " a rating of " + ratingSlider.getValue();
			new HomePage(currentUser);
			submittedRating(labelText);
			frame.dispose();
			ratings(tutors[index].getEmail(), String.valueOf(ratingSlider.getValue()));
		}
		if (e.getSource() == homeButton) {
			new HomePage(currentUser);
			frame.dispose();
		}
		if (e.getSource() == logoutButton) {
			new LoginGui();
			frame.dispose();
		}
		if (e.getSource() == returnButton) // Closes the current window and returns to the home page
		{
			new HomePage(currentUser);
			frame.dispose();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = list.getSelectedIndex();
		if (!e.getValueIsAdjusting()) {
			if (tutors[index] == null) // If no tutor was selected from the list
			{
				errorLabel.setText("No tutor selected.  Please select a tutor.");
				titleLabel.setText("");
				tutorLabel.setText("");
				ratingSlider.setVisible(false);
				submitButton.setVisible(false);
				averageRating.setVisible(false);
			} else // If an appropriate tutor was selected
			{
				titleLabel.setText("LEAVE A RATING FOR");
				tutorLabel.setText(tutors[index].getFirstName() + " " + tutors[index].getLastName());
				String avg = String.valueOf(avgRating(tutors[index].getEmail()));
				int maxLength = (avg.length() < 4) ? avg.length() : 4; // cuts the string off to "round" to 2 decimal
																		// places
				avg = avg.substring(0, maxLength);
				averageRating.setText("Average rating is: " + avg);
				errorLabel.setText("");
				averageRating.setVisible(true);
				ratingSlider.setVisible(true);
				submitButton.setVisible(true);
			}
		}
	}
}
