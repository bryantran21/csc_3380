package main;

/**
 *
 * @author Darrion Rudd
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
import static main.DOMreadXML.listOfTutors;
//import static main.postMeetingReview;

public class SearchGui implements ActionListener{
    
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
    private JRadioButton Monday;
    private JRadioButton Tuesday;
    private JRadioButton Wednesday;
    private JRadioButton Thursday;
    private JRadioButton Friday;
    private JRadioButton Saturday;
    private JRadioButton Sunday;
    private JButton scheduleBtn;
    private JFrame confirmFrame;
    private JPanel confirmPanel;
    private JLabel confirmLabel;
    private JButton confirmBtn;
    private JFrame successFrame;
    private JPanel successPanel;
    private JLabel successLabel;
    
    
    User tutors[] = listOfTutors(); // Receives the list of tutors from the database
    String tutorNames[] = new String[100];
    
	public class ComparatorUser implements Comparator {	// A comparator function used to alphabetically compare users

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
	
	public void sortTutors()			// A sort function that sorts tutors alphabetically
	{
		
		int count = 0;					
		User temp = null;
		
		ComparatorUser comparator = new ComparatorUser();
		
		
		for(int i = 0; tutors[i] != null; i++)		// For loop that runs through entire list of tutors
		{
			
			count++;
		}
		Arrays.sort(tutors,0,count,comparator);		// Sorts users alphabetically
		
		for(int i = 0; tutors[i] != null; i++)		// Creates a string array of tutors with the first and last name, sorted
		{
			tutorNames[i] = tutors[i].getLastName() + ", " + tutors[i].getFirstName();		
		}

		
//		Arrays.sort(tutorNames,0,count);
	}    
    public SearchGui(){
        
        sortTutors();
        
        frame = new JFrame();
        frame.setBounds(0,0,800,600);
        frame.setLocationRelativeTo(null);
        
        // LEFT PANEL (panel that holds the list of available tutors and the selection button)

        scrollPanel = new JPanel();
        
        list = new JList(tutorNames);
        list.setLayoutOrientation(JList.VERTICAL);
        
        searchLabel = new JLabel("Enter Course: ");
        searchLabel.setBounds(10,10,100,25);
        scrollPanel.add(searchLabel);
        
        searchText = new JTextField();
        searchText.setBounds(100,10,110,25);
        scrollPanel.add(searchText);
        
        searchBtn = new JButton("Search");
        searchBtn.setBounds(220,10,80,25);
        searchBtn.setFocusable(false);
        searchBtn.addActionListener(this);
        scrollPanel.add(searchBtn);
        
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setBounds(100,50,200,430);
        scrollPanel.add(scrollPane);
        
        errorLabel = new JLabel("");
        errorLabel.setBounds(0,470,400,35);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setFont(new Font(null,Font.CENTER_BASELINE,12));
	errorLabel.setForeground(Color.RED);
	scrollPanel.add(errorLabel);
        
        selectBtn = new JButton("Select Tutor");
        selectBtn.setBounds(100,500,200,30);
        selectBtn.setFocusable(false);
        selectBtn.addActionListener(this);
        scrollPanel.add(selectBtn);
        
        scrollPanel.setLayout(null);
        
        // RIGHT PANEL (panel that labels the selected tutor, slider for rating, and submission button to update tutor's rating in DB)
        
        tutorPanel = new JPanel();
        tutorPanel.setLayout(null);
        tutorPanel.setVisible(false);
        
        titleLabel = new JLabel("SCHEDULE A MEETING WITH");
        titleLabel.setBounds(0,100,400,30);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        tutorPanel.add(titleLabel);
        
        tutorLabel = new JLabel();
        tutorLabel.setBounds(0,122,400,30);
        tutorLabel.setFont(new Font(null,Font.CENTER_BASELINE,25));
        tutorLabel.setHorizontalAlignment(JLabel.CENTER);
        tutorPanel.add(tutorLabel);
        
        dayLabel = new JLabel("Select Day(s): ");
        dayLabel.setBounds(0,190,150,25);
        dayLabel.setFont(new Font(null,Font.CENTER_BASELINE,18));
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        tutorPanel.add(dayLabel);
        
//        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//        dayBox = new JComboBox(days);
//        dayBox.setBounds(70,190,70,25);
//        tutorPanel.add(dayBox);
        
        Monday = new JRadioButton("Monday");
        Monday.setBounds(0,215,70,25);
        Monday.setFocusable(false);
        tutorPanel.add(Monday);
        
        Tuesday = new JRadioButton("Tuesday");
        Tuesday.setBounds(70,215,80,25);
        Tuesday.setFocusable(false);
        tutorPanel.add(Tuesday);
        
        Wednesday = new JRadioButton("Wednesday");
        Wednesday.setBounds(147,215,100,25);
        Wednesday.setFocusable(false);
        tutorPanel.add(Wednesday);
        
        Thursday = new JRadioButton("Thursday");
        Thursday.setBounds(243,215,80,25);
        Thursday.setFocusable(false);
        tutorPanel.add(Thursday);
        
        Friday = new JRadioButton("Friday");
        Friday.setBounds(50,240,60,25);
        Friday.setFocusable(false);
        tutorPanel.add(Friday);
        
        Saturday = new JRadioButton("Saturday");
        Saturday.setBounds(115,240,80,25);
        Saturday.setFocusable(false);
        tutorPanel.add(Saturday);
        
        Sunday = new JRadioButton("Sunday");
        Sunday.setBounds(191,240,70,25);
        Sunday.setFocusable(false);
        tutorPanel.add(Sunday);
        
        scheduleBtn = new JButton("Schedule Meeting");
        scheduleBtn.setBounds(100,500,200,30);
        scheduleBtn.setFocusable(false);
        scheduleBtn.addActionListener(this);
        tutorPanel.add(scheduleBtn);
        
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(300,10,80,30);
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);
        tutorPanel.add(logoutBtn);
        
        homeBtn = new JButton("Home");
        homeBtn.setBounds(210,10,80,30);
        homeBtn.setFocusable(false);
        homeBtn.addActionListener(this);
        tutorPanel.add(homeBtn);
        
        
        frame.setLayout(new GridLayout(1,2));
        frame.add(scrollPanel);
        frame.add(tutorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Goober - Search for Tutors");
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void confirmPage(String text){
        
        confirmFrame = new JFrame();
        confirmFrame.setBounds(600,250,400,150);
        confirmFrame.setLocationRelativeTo(null);
        confirmPanel = new JPanel();
        confirmPanel.setLayout(null);
        
        confirmLabel = new JLabel(text);
        confirmLabel.setBounds(0,20,400,30);
        confirmLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        confirmLabel.setHorizontalAlignment(JLabel.CENTER);
        confirmPanel.add(confirmLabel);
        
        confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(160,70,80,30);
        confirmBtn.setFocusable(false);
        confirmBtn.addActionListener(this);
        confirmPanel.add(confirmBtn);
        
        confirmFrame.add(confirmPanel);
        confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmFrame.setTitle("Goober - Tutor Confirmation");
        confirmFrame.setVisible(true);
        confirmFrame.setResizable(false);
        
    }
    
    public void successPage(String text){
        
        successFrame = new JFrame();
        successFrame.setBounds(600,250,400,150);
        successFrame.setLocationRelativeTo(null);
        successPanel = new JPanel();
        successPanel.setLayout(null);
        
        successLabel = new JLabel(text);
        successLabel.setBounds(0,20,400,30);
        successLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        successLabel.setHorizontalAlignment(JLabel.CENTER);
        successPanel.add(successLabel);
        
        successFrame.add(successPanel);
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setTitle("Goober - Tutor Confirmation");
        successFrame.setVisible(true);
        successFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int index = list.getSelectedIndex();
        if(e.getSource() == selectBtn){
            
            if(tutors[index] == null){	// If no tutor was selected from the list
                
                errorLabel.setText("No tutor selected.  Please select a tutor.");
                titleLabel.setText("");
                tutorLabel.setText("");

            }else{
                
                tutorPanel.setVisible(true);
                tutorLabel.setText(tutors[index].getFirstName() + " " + tutors[index].getLastName());
                errorLabel.setText("");
                
            }
        }
        if(e.getSource() == scheduleBtn){
            
            String confirmText = "Schedule to meet with " + tutors[index].getFirstName() + " " + tutors[index].getLastName() + " on (days) at (time)?";
            confirmPage(confirmText);
            
        }
        
        if(e.getSource() == confirmBtn){
            
            String successText = "You have successfully scheduled to meet with " + tutors[index].getFirstName() + " " + tutors[index].getLastName() + " on (days) at (time)!";
            HomePage homepage = new HomePage();
            successPage(successText);
            confirmFrame.dispose();
            frame.dispose();
        }
        
        if(e.getSource() == logoutBtn){
            
            LoginGui login = new LoginGui();
            frame.dispose();
        }
        
        if(e.getSource() == homeBtn){
            
            HomePage home = new HomePage();
            frame.dispose();
        }
    }
    
    
}
