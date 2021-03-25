package main;

/**
 *
 * @author Anthony Duong
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

public class Settings implements ActionListener {

    private static JPanel panel;
    private static JFrame frame;
    private static JLabel titleLabel;
    private static JButton homeBtn;
    private static JButton logoutBtn;
    private static JLabel nameLabel;
    private static JLabel emailLabel;
    private static JButton emailChangeBtn;
    private static JLabel pwLabel;
    private static JButton pwChangeBtn;
    private static JButton deleteBtn;
    private User currentUser;

    Settings(User passedUser) {
        currentUser = passedUser;

        panel = new JPanel();
        frame = new JFrame();
        frame.setBounds(600, 250, 800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.decode("#36393f"));

        panel.setLayout(null);
        
        titleLabel = new JLabel("Account Settings");
        titleLabel.setBounds(25,30,400,40);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 30));
        titleLabel.setForeground(Color.decode("#dcddde"));
        panel.add(titleLabel);

        nameLabel = new JLabel("Name: " + currentUser.getFirstName() + " " + currentUser.getLastName());
        nameLabel.setBounds(25,100,400,40);
        nameLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        nameLabel.setForeground(Color.decode("#dcddde"));
        panel.add(nameLabel);        
        
        emailLabel = new JLabel("Email Address: " + currentUser.getEmail());
        emailLabel.setBounds(25,150,300,30);
        emailLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));        
        emailLabel.setForeground(Color.decode("#dcddde"));
        panel.add(emailLabel);
        
        emailChangeBtn = new JButton("Change");
        emailChangeBtn.setBounds(400,150,90,30);
        emailChangeBtn.setBackground(Color.decode("#7289da"));
        emailChangeBtn.setForeground(Color.decode("#dcddde"));
        emailChangeBtn.setFocusable(false);
        emailChangeBtn.addActionListener(this);
        panel.add(emailChangeBtn);
                
        pwLabel = new JLabel("Password");
        pwLabel.setBounds(25,190,400,40);
        pwLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));        
        pwLabel.setForeground(Color.decode("#dcddde"));
        panel.add(pwLabel);
        
        pwChangeBtn = new JButton("Change");
        pwChangeBtn.setBounds(400,190,90,30);
        pwChangeBtn.setBackground(Color.decode("#7289da"));
        pwChangeBtn.setForeground(Color.decode("#dcddde"));
        pwChangeBtn.setFocusable(false);
        pwChangeBtn.addActionListener(this);
        panel.add(pwChangeBtn);
        
        deleteBtn = new JButton("Delete Account");
        deleteBtn.setBounds(25,300,150,30);
        deleteBtn.setBackground(Color.decode("#7289da"));
        deleteBtn.setForeground(Color.decode("#dcddde"));
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(this);
        panel.add(deleteBtn);
        
        homeBtn = new JButton("Home");
        homeBtn.setBounds(600,10,80,30);
        homeBtn.setBackground(Color.decode("#7289da"));
        homeBtn.setForeground(Color.decode("#dcddde"));
        homeBtn.setFocusable(false);
        homeBtn.addActionListener(this);
        panel.add(homeBtn);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(690,10,80,30);
        logoutBtn.setBackground(Color.decode("#7289da"));
        logoutBtn.setForeground(Color.decode("#dcddde"));
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Settings");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == logoutBtn) // Logs the user out of the application
        {
            LoginGui login = new LoginGui();
            frame.dispose();
        }

        if (e.getSource() == homeBtn) // Logs the user out of the application
        {
            HomePage home = new HomePage(currentUser);
            frame.dispose();
        }
    }

}
