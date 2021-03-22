package main;

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
import static main.DOMreadXML.listOfTutors;
import main.HomePage;
import static main.DOMmodifyXML.ratings;
import static main.DOMreadXML.avgRating;;

public class postMeetingReview implements ActionListener
{
	private JFrame frame;
	private JButton selectButton;
	private JLabel titleLabel;
	private JLabel tutorLabel;
	private JLabel submittedLabel;
	private JPanel scrollPanel;
	private JPanel tutorPanel;
	private JLabel errorLabel;
	private JButton A;
	private JList list;
	private JButton returnButton;
	private JLabel averageRating;
	private JLabel newLabel;
	private JSlider ratingSlider;
	private JScrollPane scrollPane;
	private JButton submitButton;
	private JFrame smallFrame;
	private JPanel smallPanel;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	User tutors[] = listOfTutors(); // Receives the list of tutors from the database
	String tutorNames[] = new String[100];
	
	public class ComparatorUser implements Comparator {

	    public int compare(Object arg0, Object arg1) 
	    {
	        User user0 = (User) arg0;
	        User user1 = (User) arg1;
	        
	        String userZero = user0.getLastName() + user0.getFirstName();
	        String userOne = user1.getLastName() + user1.getFirstName();

	        int flag = userZero.compareTo(userOne);
	        return flag;
	    }
	}
	
	public void sortTutors()
	{
		
		int count = 0;					
		User temp = null;
		
		ComparatorUser comparator = new ComparatorUser();
		
		
		for(int i = 0; tutors[i] != null; i++)
		{
			
			count++;
		}
		Arrays.sort(tutors,0,count,comparator);
		
		for(int i = 0; tutors[i] != null; i++)
		{
			tutorNames[i] = tutors[i].getLastName() + ", " + tutors[i].getFirstName();	
		}

		
//		Arrays.sort(tutorNames,0,count);
	}
	
	
	public postMeetingReview()
	{
		sortTutors();
		
		frame = new JFrame();
		frame.setBounds(0,0,800,600);
		frame.setLocationRelativeTo(null);
		scrollPanel = new JPanel();		// Right panel that hosts objects
		tutorPanel = new JPanel();		// Left panel that hosts objects
		scrollPane = new JScrollPane();	// Allows scrolling through list of tutors
		titleLabel = new JLabel();		// Static text label
		tutorLabel = new JLabel();		// Label that is updated with the selected tutor
		submittedLabel = new JLabel(); 	// Label that shows up after rating is submitted
		
		
		
		// LEFT PANEL (panel that holds the list of available tutors and the selection button)
		{
			list = new JList(tutorNames);	// List of all available tutors
			
			scrollPane.setViewportView(list);	// Pane that allows scrolling through the tutors
			scrollPane.setBounds(100,30,200,430);
			list.setLayoutOrientation(JList.VERTICAL);
			
			selectButton = new JButton("Select Tutor");	// Button that enables a review for selected tutor
			selectButton.setBounds(100,500,200,30);
			selectButton.addActionListener(this);

			
			scrollPanel.setLayout(null);	// Entire panel that hosts list of tutors, scroll pane, and button
			scrollPanel.add(scrollPane);
			scrollPanel.add(selectButton);

			
		}
		
		
		// RIGHT PANEL (panel that labels the selected tutor, slider for rating, and submission button to update tutor's rating in DB)
		{
			titleLabel.setBounds(0,118,400,30);		// Static text label
			titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
			titleLabel.setHorizontalAlignment(JLabel.CENTER);

			
			tutorLabel.setBounds(0,140,400,30);		// Text label that gets updated with selected tutor's name
			tutorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 25));
			tutorLabel.setHorizontalAlignment(JLabel.CENTER);

			averageRating = new JLabel();
			averageRating.setBounds(0,165,400,30);
			averageRating.setFont(new Font(null,Font.PLAIN,12));
			averageRating.setHorizontalAlignment(JLabel.CENTER);
			averageRating.setVisible(false);
			

			int ratingMin = 0;		// The minimum rating available for a tutor
			int ratingMax = 10;		// The maximum rating available for a tutor
			int ratingInitial = 5;	// The starting value on the slider
			
			ratingSlider = new JSlider(JSlider.HORIZONTAL, ratingMin, ratingMax, ratingInitial);	// Slider that allows to select desired rating
			ratingSlider.setMajorTickSpacing(1);
			ratingSlider.setPaintLabels(true);
			ratingSlider.setBounds(50,200,300,50);
			ratingSlider.setVisible(false);
			
			submitButton = new JButton("Submit Rating");	// Button that updates the specific tutor's rating average with new number
			submitButton.setBounds(100,275,200,30);
			submitButton.addActionListener(this);
			submitButton.setVisible(false);
			
			returnButton = new JButton("Return to the Homepage");
			returnButton.setBounds(100,500,200,30);
			returnButton.addActionListener(this);
			returnButton.setVisible(true);
			returnButton.setHorizontalAlignment(JLabel.CENTER);
			
			tutorPanel.setLayout(null);		// Entire panel that hosts three labels, slider, and submission and return buttons
			tutorPanel.add(titleLabel);
			tutorPanel.add(tutorLabel);
			tutorPanel.add(ratingSlider);
			tutorPanel.add(submitButton);
			tutorPanel.add(submittedLabel);
			tutorPanel.add(returnButton);
			tutorPanel.add(averageRating);
		}
		
		
		frame.setLayout(new GridLayout(1,2));
		frame.add(scrollPanel);
		frame.add(tutorPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Goober - Post Meeting Review");
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	
	public void submittedRating(String text)
	{
		smallFrame = new JFrame();
		smallFrame.setBounds(600,250,400,150);
		smallFrame.setLocationRelativeTo(null);
		smallPanel = new JPanel();
		
        
        newLabel = new JLabel(text);
        newLabel.setBounds(0,30,400,30);
        newLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        newLabel.setHorizontalAlignment(JLabel.CENTER);
       
        smallPanel.setLayout(null);
        smallPanel.add(newLabel);
        
		smallFrame.add(smallPanel);
        smallFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        smallFrame.setTitle("Goober - Rating");
        smallFrame.setVisible(true);
        smallFrame.setResizable(false);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int index = list.getSelectedIndex();
		 if(e.getSource() == selectButton)
		 {
			 if(tutors[index] == null)
			 {
				 titleLabel.setText("");
				 
				 tutorLabel.setText("Please Select A Tutor");
				 ratingSlider.setVisible(false);
				 submitButton.setVisible(false);
			 }
			 else
			 {
				 titleLabel.setText("LEAVE A RATING FOR");
				 tutorLabel.setText(tutors[index].getFirstName() + " " + tutors[index].getLastName());
				 String avg = String.valueOf(avgRating(tutors[index].getEmail()));
				 int maxLength = (avg.length() < 4)?avg.length():4;
				 avg = avg.substring(0,maxLength);
				 averageRating.setText("Average rating is: " + avg);
				 averageRating.setVisible(true);
				 ratingSlider.setVisible(true);
				 submitButton.setVisible(true);
			 }
			 
		 }
		 if(e.getSource() == submitButton)
		 {
			 String labelText = "You have given " + tutors[index].getFirstName() + " " + tutors[index].getLastName() + " a rating of " + ratingSlider.getValue();
			 HomePage homepage = new HomePage();
			 submittedRating(labelText);
			 frame.dispose();
			 ratings(tutors[index].getEmail(), String.valueOf(ratingSlider.getValue()));
		 }
		 if(e.getSource() == returnButton)
		 {
			 HomePage homepage = new HomePage();
			 frame.dispose();
		 }
	}
}