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
import java.util.Scanner;
import java.util.stream.Stream;
import static main.DOMreadXML.listOfTutors;
import main.HomePage;
import static main.DOMmodifyXML.ratings;

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
	private JButton returnButton;
	private JList list;
	private JSlider ratingSlider;
	private JScrollPane scrollPane;
	private JButton submitButton;	
	String tutors[] = listOfTutors(); // Receives the list of tutors from the database
	
	
	public void sortTutors()
	{
		int count = 3;					// Hard-coded
		String temp;
		
		for (int i = 0; i < count; i++)
		{
			for (int j = i + 1; j < count; j++)
			{
				if(tutors[i].compareTo(tutors[j]) > 0)
				{
					temp = tutors[i];
					tutors[i] = tutors[j];
					tutors[j] = temp;
				}
			}
		}
	}
	
	
	public postMeetingReview()
	{
		sortTutors();
		
		frame = new JFrame();
		scrollPanel = new JPanel();		// Right panel that hosts objects
		tutorPanel = new JPanel();		// Left panel that hosts objects
		scrollPane = new JScrollPane();	// Allows scrolling through list of tutors
		titleLabel = new JLabel();		// Static text label
		tutorLabel = new JLabel();		// Label that is updated with the selected tutor
		submittedLabel = new JLabel(); 	// Label that shows up after rating is submitted
		
		
		
		// LEFT PANEL (panel that holds the list of available tutors and the selection button)
		{
			list = new JList(tutors);	// List of all available tutors
			list.setBounds(30,30,200,400);

			
			scrollPane.setViewportView(list);	// Pane that allows scrolling through the tutors
			scrollPane.setBounds(150,30,200,300);
			list.setLayoutOrientation(JList.VERTICAL);
			
			selectButton = new JButton("Select Tutor");	// Button that enables a review for selected tutor
			selectButton.setBounds(150,370,200,50);
			selectButton.addActionListener(this);

			
			scrollPanel.setLayout(null);	// Entire panel that hosts list of tutors, scroll pane, and button
			scrollPanel.add(scrollPane);
			scrollPanel.add(selectButton);

			
		}
		
		
		// RIGHT PANEL (panel that labels the selected tutor, slider for rating, and submission button to update tutor's rating in DB)
		{
			titleLabel.setBounds(30,30,400,30);		// Static text label
			titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
			titleLabel.setHorizontalAlignment(JLabel.CENTER);

			
			tutorLabel.setBounds(30,55,400,30);		// Text label that gets updated with selected tutor's name
			tutorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 25));
			tutorLabel.setHorizontalAlignment(JLabel.CENTER);


			int ratingMin = 0;		// The minimum rating available for a tutor
			int ratingMax = 10;		// The maximum rating available for a tutor
			int ratingInitial = 5;	// The starting value on the slider
			
			ratingSlider = new JSlider(JSlider.HORIZONTAL, ratingMin, ratingMax, ratingInitial);	// Slider that allows to select desired rating
			ratingSlider.setMajorTickSpacing(1);
			ratingSlider.setPaintLabels(true);
			ratingSlider.setPaintLabels(true);
			ratingSlider.setBounds(30,100,400,50);
			ratingSlider.setVisible(false);
			
			submitButton = new JButton("Submit Rating");	// Button that updates the specific tutor's rating average with new number
			submitButton.setBounds(30,175,400,30);
			submitButton.addActionListener(this);
			submitButton.setVisible(false);
			
			submittedLabel.setBounds(30,225,400,30);
			submittedLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
			submittedLabel.setHorizontalAlignment(JLabel.CENTER);
			
			returnButton = new JButton("Return to the Homepage");
			returnButton.setBounds(130,370,200,50);
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
		}
		
		
		frame.setLayout(new GridLayout(1,2));
		frame.add(scrollPanel);
		frame.add(tutorPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Goober - Post Meeting Review");
		frame.setSize(1000,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		

		
		
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
				 tutorLabel.setText(tutors[index]);
				 ratingSlider.setVisible(true);
				 submitButton.setVisible(true);
			 }
			 
		 }
		 if(e.getSource() == submitButton)
		 {
			 submittedLabel.setText("You have given " + tutors[index] + " a rating of " + ratingSlider.getValue());
			 submittedLabel.setVisible(true);
			 ratings("mtom@lsu.edu", String.valueOf(ratingSlider.getValue()));
		 }
		 if(e.getSource() == returnButton)
		 {
			 HomePage homepage = new HomePage();
			 frame.dispose();
		 }
	}
}