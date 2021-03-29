package main;

/**
 *
 * @author Bryan, Blake, Anthony
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import main.postMeetingReview;
import main.SearchGui;
import static main.DOMreadXML.returnUser;

public class HomePage implements ActionListener {

	private static JPanel panel;
	private static JFrame frame;
	private static JLabel titleLabel;
	private static JButton searchButton;
	private static JButton reviewButton;
	private static JLabel success;
	private JList upcoming;
	private static JScrollPane scrollPane;
	private JLabel upcomingLabel;
	private static JButton logoutBtn;
	private static JButton settingsBtn;
	private User currentUser;
	private static JButton classButton;
	private static JButton scheduleButton;
	String upcomingString[] = new String[1];

	HomePage(User passedUser) {
		currentUser = passedUser;
		int sizeCounter = 0;
		
		for (int s = 0; s < 7; s++) {
			sizeCounter = sizeCounter + currentUser.getSchedule().week[s].meetingList.size();
		}
		upcomingString = new String[sizeCounter];
		int stringCounter = 0;
		for (int i = 0; i < 7; i++) {
			if (currentUser.getSchedule().week[i].meetingList.size() != 0) {
				for (int j = 0; j < currentUser.getSchedule().week[i].meetingList.size(); j++) {
					User tutorUser = returnUser(currentUser.getSchedule().week[i].meetingList.get(j).meetingWith);
					String newMeetingString = currentUser.getSchedule().week[i].dayName + " - "
							+ currentUser.getSchedule().week[i].meetingList.get(j).classCode + " | "
							+ tutorUser.getFirstName() + " " + tutorUser.getLastName() + " ("
							+ currentUser.getSchedule().week[i].meetingList.get(j).meetingWith + ")";
					upcomingString[stringCounter] = newMeetingString;
					stringCounter++;
				}
			}
		}


		panel = new JPanel();
		frame = new JFrame();
		frame.setBounds(600, 250, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setBackground(Color.decode("#36393f"));

		panel.setLayout(null);

		titleLabel = new JLabel("Welcome back, " + currentUser.getFirstName()); // Add user's name
		titleLabel.setBounds(0, 50, 800, 100);
		titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 40));
		titleLabel.setForeground(Color.decode("#dcddde"));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(titleLabel);

		success = new JLabel("");
		success.setBounds(10, 100, 300, 25);
		panel.add(success);

		upcoming = new JList(upcomingString);
		upcoming.setForeground(Color.decode("#23272a"));
		upcoming.setBackground(Color.decode("#99aab5"));

		scrollPane = new JScrollPane(upcoming);
		scrollPane.setViewportView(upcoming);
		scrollPane.setBounds(150, 325, 500, 150);
		upcoming.setLayoutOrientation(JList.VERTICAL);
		panel.add(scrollPane);

		upcomingLabel = new JLabel("Upcoming Meetings");
		upcomingLabel.setBounds(150, 305, 300, 25);
		upcomingLabel.setFont(new Font(null, Font.PLAIN, 10));
		upcomingLabel.setForeground(Color.decode("#dcddde"));
		panel.add(upcomingLabel);

		settingsBtn = new JButton("Settings");
		settingsBtn.setBounds(590, 10, 90, 30);
		settingsBtn.setBackground(Color.decode("#7289da"));
		settingsBtn.setForeground(Color.decode("#dcddde"));
		settingsBtn.setFocusable(false);
		settingsBtn.addActionListener(this);
		panel.add(settingsBtn);

		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(690, 10, 80, 30);
		logoutBtn.setBackground(Color.decode("#7289da"));
		logoutBtn.setForeground(Color.decode("#dcddde"));
		logoutBtn.setFocusable(false);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);

		ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
		frame.setIconImage(image.getImage());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Goober - Homepage");

		if (currentUser.getRole().equals("Tutor")) {        // Changes the homepage to the tutor's perspective if a tutor logs in
			classButton = new JButton("Add/View Classes");
			classButton.setBounds(250, 175, 300, 40);
			classButton.setBackground(Color.decode("#7289da"));
			classButton.setForeground(Color.decode("#dcddde"));
			classButton.setFocusable(false);
			classButton.addActionListener(this);
			panel.add(classButton);

			scheduleButton = new JButton("Manage Schedule");
			scheduleButton.setBounds(250, 225, 300, 40);
			scheduleButton.setBackground(Color.decode("#7289da"));
			scheduleButton.setForeground(Color.decode("#dcddde"));
			scheduleButton.setFocusable(false);
			scheduleButton.addActionListener(this);
			panel.add(scheduleButton);

		} else {                                            // Changes the homepage to the student's perspective if a student logs in
			searchButton = new JButton("Schedule A Meeting With A Tutor");
			searchButton.setBounds(250, 175, 300, 40);
			searchButton.setBackground(Color.decode("#7289da"));
			searchButton.setForeground(Color.decode("#dcddde"));
			searchButton.setFocusable(false);
			searchButton.addActionListener(this);
			panel.add(searchButton);

			reviewButton = new JButton("Post-Meeting Review");
			reviewButton.setBounds(250, 225, 300, 40);
			reviewButton.setBackground(Color.decode("#7289da"));
			reviewButton.setForeground(Color.decode("#dcddde"));
			reviewButton.setFocusable(false);
			reviewButton.addActionListener(this);
			panel.add(reviewButton);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) // Sends the user to a window to search for tutors
		{
			new SearchGui(currentUser);
			frame.dispose();
		}

		if (e.getSource() == reviewButton) // Sends the user to a page to rate a previous tutor
		{
			new postMeetingReview(currentUser);
			frame.dispose();
		}

		if (e.getSource() == settingsBtn) { // Sends the user to the settings page
			new Settings(currentUser);
			frame.dispose();
		}

		if (e.getSource() == logoutBtn) // Logs the user out of the application
		{
			new LoginGui();
			frame.dispose();
		}

		if (e.getSource() == classButton) { // Sends the user to a page to manage classes
			new ClassGui(currentUser);
			frame.dispose();
		}

		if (e.getSource() == scheduleButton) {	// Sends the user to a page to manage availability
			frame.dispose();
			new Schedule(currentUser);

		}
	}
}
