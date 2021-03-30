package main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Darrion Rudd
 * @since 3/16/2021
 */
class Search extends JFrame{


   public Search(User passedUser){
	   User currentUser = passedUser;
	   setBounds(600,250,400,150);
	   setLocationRelativeTo(null);
	   setResizable(false);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
       JPanel borderPanel = (JPanel)this.getContentPane();
       JPanel centerPanel = new JPanel();
       borderPanel.setBackground(Color.decode("#36393f"));
       centerPanel.setBackground(Color.decode("#36393f"));
       JTextField t = new JTextField(10);                           
       JButton b = new JButton("Search");
       b.addActionListener(new ActionListener(){                    //Create a button with action "Call Results()"
           public void actionPerformed(ActionEvent e){
               Results(t.getText(), currentUser);
               }
       });
       b.setBackground(Color.decode("#7289da"));
       b.setForeground(Color.decode("#dcddde"));
       
       
       JButton h = new JButton("HOME");
       h.addActionListener(new ActionListener(){                    //Create a button with action "Call Results()"
           public void actionPerformed(ActionEvent e){
               new HomePage(passedUser);
               dispose();
               }
       });
       h.setBackground(Color.decode("#7289da"));
       h.setForeground(Color.decode("#dcddde"));
       
       borderPanel.setLayout(new BorderLayout());                   //Setup larger panel with border layout
       borderPanel.add(h, BorderLayout.NORTH);                      //Add HOME button to north position
       
       JLabel e = new JLabel("Enter course name:");
       e.setForeground(Color.decode("#dcddde"));
       centerPanel.add(e);           //Add Label to central panel 
       t.setForeground(Color.decode("#23272a"));
       t.setBackground(Color.decode("#99aab5"));
       centerPanel.add(t);                                          //Add a textfield next
       
       
       centerPanel.add(b);                                          //Add the button to central panel
       borderPanel.add(centerPanel, BorderLayout.CENTER);           //Add the central panel to the center of the larger panel
       
       setVisible(true);                                            //Frame to be made visible
   }
   
   public void Results(String course, User passedUser){
        String tutors[] = {"Darrion Rudd", "Miguel Rojo"};                         //**INPUT FROM DATABASE!!**
       
        setBounds(0,0,800,600);
 	   	setLocationRelativeTo(null);
 	   	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().removeAll();                          //Clear leftover items from content pane 
        JPanel borderPanel = (JPanel) this.getContentPane();
        borderPanel.setLayout(new BorderLayout());
        borderPanel.setBackground(Color.decode("#36393f"));
        JLabel z = new JLabel("SEARCH: Tutors for course " + course);
        z.setForeground(Color.decode("#dcddde"));
        borderPanel.add(z);
      
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(tutors.length, 2));    //Grid layout with n rows, 2 columns

      for (String tutor : tutors)                                   //make a label and button for every item from database
      {
         JButton button = new JButton("Add");
         button.setBackground(Color.decode("#7289da"));
         button.setForeground(Color.decode("#dcddde"));
         button.addActionListener(new ActionListener(){                    //Create a button with action "Call Results()"
           public void actionPerformed(ActionEvent e){
               /*ADD TUTOR FUNCTION GOES HERE ** e.g. student.addTutor(tutor);*/
               }});
         
         JLabel tutorLabel = new JLabel(tutor);
         tutorLabel.setForeground(Color.decode("#dcddde"));
         
         centerPanel.add(tutorLabel);
         centerPanel.add(button);
         centerPanel.setBackground(Color.decode("#36393f"));
      }
      
      JScrollPane scrollPane = new JScrollPane(centerPanel);         //convert grid to scroll pane
      borderPanel.add(scrollPane, BorderLayout.CENTER);             //add scroll pane
      setVisible(true);                                              //make it visible
   
   }
}
