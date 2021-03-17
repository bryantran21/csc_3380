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
import javax.swing.JPanel;
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
    
    HomePage(){
        panel = new JPanel();
        frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(1000,500,600,500);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
        
        titleLabel = new JLabel("Welcome to Goober! :D");
        titleLabel.setBounds(250,100,500,100);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE,20));
        panel.add(titleLabel);
        
        welcomeLabel = new JLabel("Hi, **username**");
        welcomeLabel.setBounds(0,0,100,80);
        panel.add(welcomeLabel);
        
        searchButton = new JButton("Search For Tutors");
        searchButton.setBounds(250, 200, 200, 25);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        panel.add(searchButton);
        
        reviewButton = new JButton("Post-Meeting Review");
        reviewButton.setBounds(250, 235, 200, 25);
        reviewButton.setFocusable(false);
        reviewButton.addActionListener(this);
        panel.add(reviewButton);
                 
        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        panel.add(success);
        
        frame.setVisible(true);

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