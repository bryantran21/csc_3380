package main;

/**
 *
 * @author Anthony Duong
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
    private static JFrame emailFrame;
    private static JPanel emailPanel;
    private static JLabel emailTitle;
    private static JLabel emailSmallLabel;
    private static JLabel currentEmail;
    private static JLabel newEmailLabel;
    private static JTextField newEmailText;
    private static JLabel errorLabel;
    private static JButton emailSubmit;
    private static JButton emailCancel;
    private static JFrame pwFrame;
    private static JPanel pwPanel;
    private static JLabel pwTitle;
    private static JLabel pwSmallLabel;
    private static JLabel currentPWLabel;
    private static JPasswordField currentPWText;
    private static JLabel newPWLabel;
    private static JPasswordField newPWText;
    private static JLabel newPW2Label;
    private static JPasswordField newPW2Text;
    private static JLabel error2Label;
    private static JButton pwSubmit;
    private static JButton pwCancel;

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
        titleLabel.setBounds(25, 30, 400, 40);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 30));
        titleLabel.setForeground(Color.decode("#dcddde"));
        panel.add(titleLabel);

        nameLabel = new JLabel("Name: " + currentUser.getFirstName() + " " + currentUser.getLastName());
        nameLabel.setBounds(25, 100, 400, 40);
        nameLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        nameLabel.setForeground(Color.decode("#dcddde"));
        panel.add(nameLabel);

        emailLabel = new JLabel("Email Address: " + currentUser.getEmail());
        emailLabel.setBounds(25, 150, 300, 30);
        emailLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        emailLabel.setForeground(Color.decode("#dcddde"));
        panel.add(emailLabel);

        emailChangeBtn = new JButton("Change");
        emailChangeBtn.setBounds(400, 150, 90, 30);
        emailChangeBtn.setBackground(Color.decode("#7289da"));
        emailChangeBtn.setForeground(Color.decode("#dcddde"));
        emailChangeBtn.setFocusable(false);
        emailChangeBtn.addActionListener(this);
        panel.add(emailChangeBtn);

        pwLabel = new JLabel("Password");
        pwLabel.setBounds(25, 190, 400, 40);
        pwLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        pwLabel.setForeground(Color.decode("#dcddde"));
        panel.add(pwLabel);

        pwChangeBtn = new JButton("Change");
        pwChangeBtn.setBounds(400, 190, 90, 30);
        pwChangeBtn.setBackground(Color.decode("#7289da"));
        pwChangeBtn.setForeground(Color.decode("#dcddde"));
        pwChangeBtn.setFocusable(false);
        pwChangeBtn.addActionListener(this);
        panel.add(pwChangeBtn);

        deleteBtn = new JButton("Delete Account");
        deleteBtn.setBounds(25, 300, 150, 30);
        deleteBtn.setBackground(Color.decode("#7289da"));
        deleteBtn.setForeground(Color.decode("#dcddde"));
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(this);
        panel.add(deleteBtn);

        homeBtn = new JButton("Home");
        homeBtn.setBounds(600, 10, 80, 30);
        homeBtn.setBackground(Color.decode("#7289da"));
        homeBtn.setForeground(Color.decode("#dcddde"));
        homeBtn.setFocusable(false);
        homeBtn.addActionListener(this);
        panel.add(homeBtn);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(690, 10, 80, 30);
        logoutBtn.setBackground(Color.decode("#7289da"));
        logoutBtn.setForeground(Color.decode("#dcddde"));
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Account Settings");

    }

    public void changeEmail() {

        emailFrame = new JFrame();
        emailFrame.setBounds(600, 250, 400, 300);
        emailFrame.setLocationRelativeTo(null);
        emailPanel = new JPanel();
        emailPanel.setLayout(null);
        emailPanel.setBackground(Color.decode("#36393f"));

        emailTitle = new JLabel("Change Email");
        emailTitle.setBounds(10, 20, 200, 30);
        emailTitle.setFont(new Font(null, Font.CENTER_BASELINE, 25));
        emailTitle.setForeground(Color.decode("#dcddde"));
        emailPanel.add(emailTitle);

        emailSmallLabel = new JLabel("Note: Changing your email will update your login information");
        emailSmallLabel.setBounds(10, 40, 350, 30);
        emailSmallLabel.setFont(new Font(null, Font.CENTER_BASELINE, 10));
        emailSmallLabel.setForeground(Color.decode("#dcddde"));
        emailPanel.add(emailSmallLabel);

        currentEmail = new JLabel("Current Email: " + currentUser.getEmail());
        currentEmail.setBounds(10, 80, 400, 30);
        currentEmail.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        currentEmail.setForeground(Color.decode("#dcddde"));
        emailPanel.add(currentEmail);

        newEmailLabel = new JLabel("New Email: ");
        newEmailLabel.setBounds(10, 120, 400, 30);
        newEmailLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        newEmailLabel.setForeground(Color.decode("#dcddde"));
        emailPanel.add(newEmailLabel);

        newEmailText = new JTextField();
        newEmailText.setBounds(100, 120, 200, 30);
        newEmailText.setForeground(Color.decode("#23272a"));
        newEmailText.setBackground(Color.decode("#99aab5"));
        emailPanel.add(newEmailText);

        errorLabel = new JLabel("");
        errorLabel.setBounds(90, 145, 400, 30);
        errorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        errorLabel.setForeground(Color.RED);
        emailPanel.add(errorLabel);

        emailSubmit = new JButton("Save Email");
        emailSubmit.setBounds(135, 180, 120, 30);
        emailSubmit.setBackground(Color.decode("#7289da"));
        emailSubmit.setForeground(Color.decode("#dcddde"));
        emailSubmit.setFocusable(false);
        emailSubmit.addActionListener(this);
        emailPanel.add(emailSubmit);

        emailCancel = new JButton("Cancel");
        emailCancel.setBounds(155, 220, 80, 30);
        emailCancel.setBackground(Color.decode("#7289da"));
        emailCancel.setForeground(Color.decode("#dcddde"));
        emailCancel.setFocusable(false);
        emailCancel.addActionListener(this);
        emailPanel.add(emailCancel);

        emailFrame.add(emailPanel);
        emailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emailFrame.setTitle("Goober - Account Settings");
        emailFrame.setVisible(true);
    }

    public void changePW() {

//    private static JFrame pwFrame;
//    private static JPanel pwPanel;
//    private static JLabel pwTitle;
//    private static JLabel pwSmallLabel;
//    private static JLabel currentPWLabel;
//    private static JPasswordField currentPWText;
//    private static JLabel newPWLabel;
//    private static JPasswordField newPWText;
//    private static JLabel newPW2Label;
//    private static JPasswordField newPW2Text;
//    private static JButton pwSubmit;
//    private static JButton pwCancel;
        pwFrame = new JFrame();
        pwFrame.setBounds(600, 250, 400, 350);
        pwFrame.setLocationRelativeTo(null);
        pwPanel = new JPanel();
        pwPanel.setLayout(null);
        pwPanel.setBackground(Color.decode("#36393f"));

        pwTitle = new JLabel("Change Password");
        pwTitle.setBounds(10, 20, 300, 30);
        pwTitle.setFont(new Font(null, Font.CENTER_BASELINE, 25));
        pwTitle.setForeground(Color.decode("#dcddde"));
        pwPanel.add(pwTitle);

        pwSmallLabel = new JLabel("Note: Changing your password will update your login information");
        pwSmallLabel.setBounds(10, 40, 350, 30);
        pwSmallLabel.setFont(new Font(null, Font.CENTER_BASELINE, 10));
        pwSmallLabel.setForeground(Color.decode("#dcddde"));
        pwPanel.add(pwSmallLabel);

        currentPWLabel = new JLabel("Current Password: ");
        currentPWLabel.setBounds(10, 80, 200, 30);
        currentPWLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        currentPWLabel.setForeground(Color.decode("#dcddde"));
        pwPanel.add(currentPWLabel);

        currentPWText = new JPasswordField();
        currentPWText.setBounds(150, 80, 200, 30);
        currentPWText.setForeground(Color.decode("#23272a"));
        currentPWText.setBackground(Color.decode("#99aab5"));
        pwPanel.add(currentPWText);

        newPWLabel = new JLabel("New Password: ");
        newPWLabel.setBounds(10, 120, 200, 30);
        newPWLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        newPWLabel.setForeground(Color.decode("#dcddde"));
        pwPanel.add(newPWLabel);

        newPWText = new JPasswordField();
        newPWText.setBounds(150, 120, 200, 30);
        newPWText.setForeground(Color.decode("#23272a"));
        newPWText.setBackground(Color.decode("#99aab5"));
        pwPanel.add(newPWText);

        newPW2Label = new JLabel("Re-type New Password: ");
        newPW2Label.setBounds(10, 160, 200, 30);
        newPW2Label.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        newPW2Label.setForeground(Color.decode("#dcddde"));
        pwPanel.add(newPW2Label);

        newPW2Text = new JPasswordField();
        newPW2Text.setBounds(150, 160, 200, 30);
        newPW2Text.setForeground(Color.decode("#23272a"));
        newPW2Text.setBackground(Color.decode("#99aab5"));
        pwPanel.add(newPW2Text);

        error2Label = new JLabel("Your new password cannot match the old password");
        error2Label.setBounds(40, 185, 400, 30);
        error2Label.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        error2Label.setForeground(Color.RED);
        pwPanel.add(error2Label);

        pwSubmit = new JButton("Save Password");
        pwSubmit.setBounds(110, 220, 160, 30);
        pwSubmit.setBackground(Color.decode("#7289da"));
        pwSubmit.setForeground(Color.decode("#dcddde"));
        pwSubmit.setFocusable(false);
        pwSubmit.addActionListener(this);
        pwPanel.add(pwSubmit);

        pwCancel = new JButton("Cancel");
        pwCancel.setBounds(150, 260, 80, 30);
        pwCancel.setBackground(Color.decode("#7289da"));
        pwCancel.setForeground(Color.decode("#dcddde"));
        pwCancel.setFocusable(false);
        pwCancel.addActionListener(this);
        pwPanel.add(pwCancel);
        
        pwFrame.add(pwPanel);
        pwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pwFrame.setTitle("Goober - Account Settings");
        pwFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == logoutBtn) // Logs the user out of the application
        {
            LoginGui login = new LoginGui();
            frame.dispose();
        }

        if (e.getSource() == homeBtn) // Returns the user to the homepage
        {
            HomePage home = new HomePage(currentUser);
            frame.dispose();
        }

        if (e.getSource() == emailChangeBtn) // Brings up a prompt that allows the user to change their email
        {
            changeEmail();
        }

        if (e.getSource() == emailCancel) // Closes the email change prompt
        {
            emailFrame.dispose();
        }

        if (e.getSource() == emailSubmit) // Checks the validity of the entered email and saves it if it passes
        {
            if (currentUser.getEmail().equals(newEmailText.getText())) {

                errorLabel.setText("Please enter a different email.");
                newEmailText.setText("");
            } else {

                errorLabel.setText("Email has been successfully changed to " + newEmailText.getText()); //this is just hardcoded as a placeholder. supposed to change the currentUser email
                emailLabel = new JLabel("Email Address: " + newEmailText.getText()); // this line does not work btw, emailLabel is not within the emailFrame :(
                emailFrame.dispose();
            }
        }

        if (e.getSource() == pwChangeBtn) {
            changePW();
        }
    }

}
