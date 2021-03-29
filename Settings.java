package main;

/**
 *
 * @author Anthony Duong
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static main.DOMmodifyXML.deleteAccount;
import static main.DOMmodifyXML.updateEmail;
import static main.DOMmodifyXML.updatePassword;
import static main.DOMreadXML.returnUser;

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
    private static JPasswordField newPWText2;
    private static JLabel errorLabel2;
    private static JLabel errorLabel3;
    private static JButton pwSubmit;
    private static JButton pwCancel;
    private static JFrame successFrame;
    private static JPanel successPanel;
    private static JLabel successLabel;
    private static JLabel successLabel2;
    private static JButton deleteBtn;
    private static JFrame deleteFrame;
    private static JPanel deletePanel;
    private static JLabel deleteTitle;
    private static JLabel deleteLabel;
    private static JButton deleteYes;
    private static JButton deleteNo;
    private static JFrame deleteFrame2;
    private static JPanel deletePanel2;
    private static JLabel deleteLabel2;
    private static JButton deleteOK;

    private User currentUser;

    Settings(User passedUser) {         // A prompt that shows the user their account information and allows them to change certain login credentials
        currentUser = passedUser;

        panel = new JPanel();
        frame = new JFrame();
        frame.setBounds(600, 250, 600, 400);
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

        emailChangeBtn = new JButton("Change Email");
        emailChangeBtn.setBounds(400, 150, 170, 30);
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

        pwChangeBtn = new JButton("Change Password");
        pwChangeBtn.setBounds(400, 190, 170, 30);
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
        homeBtn.setBounds(400, 10, 80, 30);
        homeBtn.setBackground(Color.decode("#7289da"));
        homeBtn.setForeground(Color.decode("#dcddde"));
        homeBtn.setFocusable(false);
        homeBtn.addActionListener(this);
        panel.add(homeBtn);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(490, 10, 80, 30);
        logoutBtn.setBackground(Color.decode("#7289da"));
        logoutBtn.setForeground(Color.decode("#dcddde"));
        logoutBtn.setFocusable(false);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        frame.setIconImage(image.getImage());

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Account Settings");

    }

    public void changeEmail() {         // A prompt that lets the user change their email

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
        errorLabel.setBounds(90, 150, 400, 30);
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
        emailCancel.setBounds(135, 220, 120, 30);
        emailCancel.setBackground(Color.decode("#7289da"));
        emailCancel.setForeground(Color.decode("#dcddde"));
        emailCancel.setFocusable(false);
        emailCancel.addActionListener(this);
        emailPanel.add(emailCancel);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        emailFrame.setIconImage(image.getImage());
        emailFrame.add(emailPanel);
        emailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emailFrame.setTitle("Goober - Account Settings");
        emailFrame.setVisible(true);
    }

    public void changePW() {        // A prompt that allows the user to change their password

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

        newPWText2 = new JPasswordField();
        newPWText2.setBounds(150, 160, 200, 30);
        newPWText2.setForeground(Color.decode("#23272a"));
        newPWText2.setBackground(Color.decode("#99aab5"));
        pwPanel.add(newPWText2);

        errorLabel2 = new JLabel("Your new password cannot match the old password");
        errorLabel2.setBounds(40, 190, 400, 30);
        errorLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        errorLabel2.setForeground(Color.RED);
        errorLabel2.setVisible(false);
        pwPanel.add(errorLabel2);

        errorLabel3 = new JLabel("");
        errorLabel3.setBounds(0, 190, 400, 30);
        errorLabel3.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        errorLabel3.setHorizontalAlignment(JLabel.CENTER);
        errorLabel3.setForeground(Color.RED);
        errorLabel3.setVisible(false);
        pwPanel.add(errorLabel3);

        pwSubmit = new JButton("Save Password");
        pwSubmit.setBounds(120, 220, 160, 30);
        pwSubmit.setBackground(Color.decode("#7289da"));
        pwSubmit.setForeground(Color.decode("#dcddde"));
        pwSubmit.setFocusable(false);
        pwSubmit.addActionListener(this);
        pwPanel.add(pwSubmit);

        pwCancel = new JButton("Cancel");
        pwCancel.setBounds(120, 260, 160, 30);
        pwCancel.setBackground(Color.decode("#7289da"));
        pwCancel.setForeground(Color.decode("#dcddde"));
        pwCancel.setFocusable(false);
        pwCancel.addActionListener(this);
        pwPanel.add(pwCancel);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        pwFrame.setIconImage(image.getImage());
        pwFrame.add(pwPanel);
        pwFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pwFrame.setTitle("Goober - Account Settings");
        pwFrame.setVisible(true);

    }

    public void successMsg(String text1, String text2) {        // Notifies the user that their password or email has successfully been changed

        successFrame = new JFrame();
        successFrame.setBounds(600, 250, 350, 200);
        successFrame.setLocationRelativeTo(null);
        successPanel = new JPanel();
        successPanel.setLayout(null);
        successPanel.setBackground(Color.decode("#36393f"));

        successLabel = new JLabel(text1);
        successLabel.setBounds(0, 30, 350, 30);
        successLabel.setFont(new Font(null, Font.CENTER_BASELINE, 13));
        successLabel.setHorizontalAlignment(JLabel.CENTER);
        successLabel.setForeground(Color.decode("#dcddde"));
        successPanel.add(successLabel);

        successLabel2 = new JLabel(text2);
        successLabel2.setBounds(0, 50, 350, 30);
        successLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 13));
        successLabel2.setHorizontalAlignment(JLabel.CENTER);
        successLabel2.setForeground(Color.decode("#dcddde"));
        successPanel.add(successLabel2);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        successFrame.setIconImage(image.getImage());
        successFrame.add(successPanel);
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setTitle("Goober - Account Settings");
        successFrame.setVisible(true);
    }

    public void deleteAcc() {       // A prompt that asks for the user to confirm to delete an account

        deleteFrame = new JFrame();
        deleteFrame.setBounds(600, 250, 350, 200);
        deleteFrame.setLocationRelativeTo(null);
        deletePanel = new JPanel();
        deletePanel.setLayout(null);
        deletePanel.setBackground(Color.decode("#36393f"));

        deleteTitle = new JLabel("Delete Account");
        deleteTitle.setBounds(0, 20, 350, 30);
        deleteTitle.setFont(new Font(null, Font.CENTER_BASELINE, 25));
        deleteTitle.setHorizontalAlignment(JLabel.CENTER);
        deleteTitle.setForeground(Color.decode("#dcddde"));
        deletePanel.add(deleteTitle);

        deleteLabel = new JLabel("Are you sure you want to delete your account?");
        deleteLabel.setBounds(0, 60, 350, 30);
        deleteLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        deleteLabel.setHorizontalAlignment(JLabel.CENTER);
        deleteLabel.setForeground(Color.decode("#dcddde"));
        deletePanel.add(deleteLabel);

        deleteYes = new JButton("Yes");
        deleteYes.setBounds(60, 100, 80, 30);
        deleteYes.setBackground(Color.decode("#7289da"));
        deleteYes.setForeground(Color.decode("#dcddde"));
        deleteYes.setFocusable(false);
        deleteYes.addActionListener(this);
        deletePanel.add(deleteYes);

        deleteNo = new JButton("No");
        deleteNo.setBounds(190, 100, 80, 30);
        deleteNo.setBackground(Color.decode("#7289da"));
        deleteNo.setForeground(Color.decode("#dcddde"));
        deleteNo.setFocusable(false);
        deleteNo.addActionListener(this);
        deletePanel.add(deleteNo);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        deleteFrame.setIconImage(image.getImage());
        deleteFrame.add(deletePanel);
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setTitle("Goober - Account Settings");
        deleteFrame.setVisible(true);

    }

    public void deleteMsg() {       // Notifies the user that the account has successfully been deleted

        deleteFrame2 = new JFrame();
        deleteFrame2.setBounds(600, 250, 350, 200);
        deleteFrame2.setLocationRelativeTo(null);
        deletePanel2 = new JPanel();
        deletePanel2.setLayout(null);
        deletePanel2.setBackground(Color.decode("#36393f"));

        deleteLabel2 = new JLabel("Your account has successfully been deleted.");
        deleteLabel2.setBounds(0, 30, 350, 30);
        deleteLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 13));
        deleteLabel2.setHorizontalAlignment(JLabel.CENTER);
        deleteLabel2.setForeground(Color.decode("#dcddde"));
        deletePanel2.add(deleteLabel2);

        deleteOK = new JButton("Okay");
        deleteOK.setBounds(130, 100, 80, 30);
        deleteOK.setBackground(Color.decode("#7289da"));
        deleteOK.setForeground(Color.decode("#dcddde"));
        deleteOK.setFocusable(false);
        deleteOK.addActionListener(this);
        deletePanel2.add(deleteOK);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        deleteFrame2.setIconImage(image.getImage());
        deleteFrame2.add(deletePanel2);
        deleteFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame2.setTitle("Goober - Account Settings");
        deleteFrame2.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == logoutBtn)         // Logs the user out of the application
        {
            LoginGui login = new LoginGui();
            frame.dispose();
        }

        if (e.getSource() == homeBtn)           // Returns the user to the homepage
        {
            HomePage home = new HomePage(currentUser);
            frame.dispose();
        }

        if (e.getSource() == emailChangeBtn)        // Brings up a prompt that allows the user to change their email
        {
            changeEmail();
        }

        if (e.getSource() == emailCancel)           // Closes the email change prompt
        {
            emailFrame.dispose();
        }

        if (e.getSource() == emailSubmit)       // Checks the validity of the entered email and saves it if it passes
        {
            if (currentUser.getEmail().equals(newEmailText.getText())) {        // Denies the email change if the new email matches the current email

                errorLabel.setText("Please enter a different email.");
                newEmailText.setText("");
            } else if (newEmailText.getText().isEmpty()) {          // Denies the email change if there is no email entered

                errorLabel.setText("Please enter a valid email.");
                newEmailText.setText("");
            } else {                // Successfully saves the email change

                updateEmail(currentUser.getEmail(), newEmailText.getText());
                currentUser = returnUser(newEmailText.getText());
                String successText = "Email has been successfully changed to";
                String successText2 = currentUser.getEmail() + ".";
                successMsg(successText, successText2);
                emailLabel.setText("Email Address: " + currentUser.getEmail());
                currentEmail.setText("Current Email: " + currentUser.getEmail());
                emailFrame.dispose();
            }
        }

        if (e.getSource() == pwChangeBtn)       // Brings up a prompt to change passwords
        {
            changePW();
        }

        if (e.getSource() == pwCancel) {        // Closes the change password prompt
            pwFrame.dispose();
        }

        if (e.getSource() == pwSubmit) {        // Checks the validity of the password and saves it if it passes

            if (!currentPWText.getText().equalsIgnoreCase(currentUser.getPassword())) {

                errorLabel3.setText("Incorrect Password");
                errorLabel3.setVisible(true);
                errorLabel2.setVisible(false);
                currentPWText.setText("");
                newPWText.setText("");
                newPWText2.setText("");
            } else if (newPWText.getText().equals(currentUser.getPassword())) {         // Denies the password change if the new password matches the current password

                errorLabel2.setVisible(true);
                errorLabel3.setVisible(false);
                currentPWText.setText("");
                newPWText.setText("");
                newPWText2.setText("");
            } else if (newPWText.getText().isEmpty() || newPWText2.getText().isEmpty()) {       // Checks if any of the password fields are blank

                errorLabel3.setText("Passwords cannot be blank");
                errorLabel3.setVisible(true);
                errorLabel2.setVisible(false);
                currentPWText.setText("");
                newPWText.setText("");
                newPWText2.setText("");
            } else if (!newPWText.getText().equals(newPWText2.getText())) {         // Denies the password change if confirm password does not match the new password

                errorLabel3.setText("Passwords do not match");
                errorLabel3.setVisible(true);
                errorLabel2.setVisible(false);
                currentPWText.setText("");
                newPWText.setText("");
                newPWText2.setText("");

            } else {        // Successfully saves the password change

                updatePassword(currentUser.getEmail(), newPWText.getText());
                currentUser = returnUser(emailLabel.getText());
                String successText = "";
                String successText2 = "Your password has successfully been changed.";
                successMsg(successText, successText2);
                pwFrame.dispose();
            }
        }

        if (e.getSource() == deleteBtn)         // Brings up a prompt to confirm removal of an account
        {
            deleteAcc();
        }

        if (e.getSource() == deleteYes)         // Confirms the option to delete the account, closes the current prompt and brings the user back to the login page
        {
            deleteAccount(currentUser.getEmail());
            LoginGui login = new LoginGui();
            deleteMsg();
            frame.dispose();
            deleteFrame.dispose();
        }

        if (e.getSource() == deleteNo)          // Cancels the option to delete account
        {
            deleteFrame.dispose();
        }

        if (e.getSource() == deleteOK)      // Notifies the user that their account has been deleted
        {

            deleteFrame2.dispose();
        }
    }

}
