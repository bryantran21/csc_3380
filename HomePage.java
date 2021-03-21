package main;

/**
 *
 * @author Bryan
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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

public class HomePage implements ActionListener
{  
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
    String upcomingString[] = {"Monday, March 22   -   csc3380 @ 5:00p","Tuesday, March 23   -   csc3501 @ 9:30a"};

    
    HomePage(){
        panel = new JPanel();
        frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(600,250,800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        titleLabel = new JLabel("Welcome back,");	// Add user's name
        titleLabel.setBounds(0,100,800,100);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE,40));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);
                
        searchButton = new JButton("Search For Tutors");
        searchButton.setBounds(250, 225, 300, 40);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        panel.add(searchButton);
        
        reviewButton = new JButton("Post-Meeting Review");
        reviewButton.setBounds(250, 275, 300, 40);
        reviewButton.setFocusable(false);
        reviewButton.addActionListener(this);
        panel.add(reviewButton);
                 
        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        panel.add(success);
        
        upcoming = new JList(upcomingString);
        
        scrollPane = new JScrollPane(upcoming);
        scrollPane.setViewportView(upcoming);
        scrollPane.setBounds(250,375,300,150);
        upcoming.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane);
        
        upcomingLabel = new JLabel("Upcoming Meetings");
        upcomingLabel.setBounds(250,355,300,25);
        upcomingLabel.setFont(new Font(null,Font.PLAIN,10));
        panel.add(upcomingLabel);
        
        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Homepage");

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton)
		{
			Search search = new Search();
			frame.dispose();
		}
		if (e.getSource() == reviewButton)
		{
			postMeetingReview pmr = new postMeetingReview();
			frame.dispose();
		}
		
	}
}