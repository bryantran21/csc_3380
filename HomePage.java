package main;

/**
 *
 * @author Bryan
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import main.postMeetingReview;
import main.Search;
import main.SearchGui;

public class HomePage implements ActionListener {

    private static JPanel panel;
    private static JFrame frame;
    private static JLabel titleLabel;
    private static JLabel welcomeLabel;
    private static JButton searchButton;
    private static JButton reviewButton;
    private static JLabel success;
    private JList upcoming;
    private static JScrollPane scrollPane;
    private JLabel upcomingLabel;
    private static JButton logoutBtn;
    private User currentUser;
    private static JButton classButton;
    private static JButton scheduleButton;

    String upcomingString[] = {"Monday, March 22   -   csc3380 @ 5:00p", "Tuesday, March 23   -   csc3501 @ 9:30a"};

    HomePage(User passedUser) {
        currentUser = passedUser;

        panel = new JPanel();
        frame = new JFrame();
        frame.setBounds(600, 250, 800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.decode("#36393f"));

        panel.setLayout(null);

        titleLabel = new JLabel("Welcome back, " + currentUser.getFirstName());	// Add user's name
        titleLabel.setBounds(0, 100, 800, 100);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 40));
        titleLabel.setForeground(Color.decode("#dcddde"));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        panel.add(success);

        upcoming = new JList(upcomingString);
        upcoming.setForeground(Color.decode("#23272a"));
        upcoming.setBackground(Color.decode("#99aab5"));

        scrollPane = new JScrollPane(upcoming);
        scrollPane.setViewportView(upcoming);
        scrollPane.setBounds(250, 375, 300, 150);
        upcoming.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane);

        upcomingLabel = new JLabel("Upcoming Meetings");
        upcomingLabel.setBounds(250, 355, 300, 25);
        upcomingLabel.setFont(new Font(null, Font.PLAIN, 10));
        upcomingLabel.setForeground(Color.decode("#dcddde"));
        panel.add(upcomingLabel);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(690, 10, 80, 30);
        logoutBtn.setBackground(Color.decode("#7289da"));
        logoutBtn.setForeground(Color.decode("#dcddde"));
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Homepage");

        if (currentUser.getRole().equals("Tutor")) {
            classButton = new JButton("Add/View Classes");
            classButton.setBounds(250, 225, 300, 40);
            classButton.setBackground(Color.decode("#7289da"));
            classButton.setForeground(Color.decode("#dcddde"));
            classButton.setFocusable(false);
            classButton.addActionListener(this);
            panel.add(classButton);

            scheduleButton = new JButton("Manage Schedule");
            scheduleButton.setBounds(250, 275, 300, 40);
            scheduleButton.setBackground(Color.decode("#7289da"));
            scheduleButton.setForeground(Color.decode("#dcddde"));
            scheduleButton.setFocusable(false);
            scheduleButton.addActionListener(this);
            panel.add(scheduleButton);

        } else {
            searchButton = new JButton("Search For Tutors");
            searchButton.setBounds(250, 225, 300, 40);
            searchButton.setBackground(Color.decode("#7289da"));
            searchButton.setForeground(Color.decode("#dcddde"));
            searchButton.setFocusable(false);
            searchButton.addActionListener(this);
            panel.add(searchButton);

            reviewButton = new JButton("Post-Meeting Review");
            reviewButton.setBounds(250, 275, 300, 40);
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
            SearchGui search = new SearchGui(currentUser);
            frame.dispose();
        }
        if (e.getSource() == reviewButton) // Sends the user to a page to rate a previous tutor
        {
            postMeetingReview pmr = new postMeetingReview(currentUser);
            frame.dispose();
        }
        if (e.getSource() == logoutBtn) // Logs the user out of the application
        {
            LoginGui login = new LoginGui();
            frame.dispose();
        }
        if (e.getSource() == classButton)
        {
            ClassGui clgui = new ClassGui(currentUser);
            frame.dispose();
        }
        if(e.getSource() == scheduleButton)
        {
            Schedule sch = new Schedule();
            frame.dispose();
        }
    }
}
