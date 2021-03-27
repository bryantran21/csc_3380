package main;

/*
*
*@author Anthony Duong
*
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import static main.DOMreadXML.returnUser;
import static main.DOMmodifyXML.createAccount;

public class NewAccount implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel titleLabel;
    private static JLabel emailLabel;
    private static JTextField emailText;
    private static JLabel userType;
    private static JComboBox userClass;
    private static JLabel firstNameLabel;
    private static JLabel lastNameLabel;
    private static JTextField firstNameText;
    private static JTextField lastNameText;
    private static JLabel pwLabel;
    private static JPasswordField pwText;
    private static JLabel pw2Label;
    private static JPasswordField pw2Text;
    private static JButton createAcc;
    private static JLabel rtnLabel;
    private static JButton rtnButton;
    private static JLabel errorLabel;

    NewAccount() {

        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(600, 250, 800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        titleLabel = new JLabel("Create a Goober Account");
        titleLabel.setBounds(0, 50, 800, 50);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 45));
        titleLabel.setForeground(Color.decode("#dcddde"));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        userType = new JLabel("Are you a Student or Tutor?");
        userType.setBounds(200, 162, 250, 25);
        userType.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        userType.setForeground(Color.decode("#dcddde"));
        panel.add(userType);

        String[] options = {"Student", "Tutor"};
        userClass = new JComboBox(options);
        userClass.setBounds(460, 162, 100, 25);
        userClass.setForeground(Color.decode("#23272a"));
        userClass.setBackground(Color.decode("#99aab5"));
        panel.add(userClass);

        emailLabel = new JLabel("Enter a valid e-mail: ");
        emailLabel.setBounds(200, 202, 400, 25);
        emailLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        emailLabel.setForeground(Color.decode("#dcddde"));
        panel.add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(412, 202, 150, 25);
        emailText.setForeground(Color.decode("#23272a"));
        emailText.setBackground(Color.decode("#99aab5"));
        panel.add(emailText);

        firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setBounds(200, 242, 400, 25);
        firstNameLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        firstNameLabel.setForeground(Color.decode("#dcddde"));
        panel.add(firstNameLabel);

        firstNameText = new JTextField();
        firstNameText.setBounds(412, 242, 150, 25);
        firstNameText.setForeground(Color.decode("#23272a"));
        firstNameText.setBackground(Color.decode("#99aab5"));
        panel.add(firstNameText);

        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(200, 282, 400, 25);
        lastNameLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        lastNameLabel.setForeground(Color.decode("#dcddde"));
        panel.add(lastNameLabel);

        lastNameText = new JTextField();
        lastNameText.setBounds(412, 282, 150, 25);
        lastNameText.setForeground(Color.decode("#23272a"));
        lastNameText.setBackground(Color.decode("#99aab5"));
        panel.add(lastNameText);

        pwLabel = new JLabel("Create Password: ");
        pwLabel.setBounds(200, 322, 400, 25);
        pwLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        pwLabel.setForeground(Color.decode("#dcddde"));
        panel.add(pwLabel);

        pwText = new JPasswordField();
        pwText.setBounds(412, 322, 150, 25);
        pwText.setForeground(Color.decode("#23272a"));
        pwText.setBackground(Color.decode("#99aab5"));
        panel.add(pwText);

        pw2Label = new JLabel("Confirm Password: ");
        pw2Label.setBounds(200, 362, 400, 25);
        pw2Label.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        pw2Label.setForeground(Color.decode("#dcddde"));
        panel.add(pw2Label);

        pw2Text = new JPasswordField();
        pw2Text.setBounds(412, 362, 150, 25);
        pw2Text.setForeground(Color.decode("#23272a"));
        pw2Text.setBackground(Color.decode("#99aab5"));
        panel.add(pw2Text);

        createAcc = new JButton("Create Account");
        createAcc.setBounds(325, 402, 150, 25);
        createAcc.setBackground(Color.decode("#7289da"));
        createAcc.setForeground(Color.decode("#dcddde"));
        createAcc.setFocusable(false);
        createAcc.addActionListener(this);
        panel.add(createAcc);

        rtnLabel = new JLabel("Already have an account?");
        rtnLabel.setBounds(270, 500, 150, 25);
        rtnLabel.setFont(new Font(null, Font.PLAIN, 12));
        rtnLabel.setForeground(Color.decode("#dcddde"));
        panel.add(rtnLabel);

        rtnButton = new JButton("Click Here!");
        rtnButton.setBounds(420, 503, 100, 20);
        rtnButton.setBackground(Color.decode("#7289da"));
        rtnButton.setForeground(Color.decode("#dcddde"));
        rtnButton.setFocusable(false);
        rtnButton.addActionListener(this);
        panel.add(rtnButton);

        errorLabel = new JLabel("");
        errorLabel.setBounds(0, 445, 800, 35);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);
        panel.setBackground(Color.decode("#36393f"));

        ImageIcon image = new ImageIcon("src/main/Frosty_POG-min.png");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Create A Goober Account");
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Sends user to the login page and closes this window

        if (e.getSource() == rtnButton) {

            LoginGui myGui = new LoginGui();
            frame.dispose();
        }

        if (e.getSource() == createAcc) { // Collects the texts of the following variables

            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String type = userClass.getSelectedItem().toString();
            String email = emailText.getText();
            String pw = pwText.getText();
            String pw2 = pwText.getText();
            String role = (String) userClass.getSelectedItem();

            if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || emailText.getText().isEmpty() || pwText.getText().isEmpty() || pw2Text.getText().isEmpty()) { // Checks if any of the text fields are empty

                errorLabel.setText("Please fill all required fields.");

            } else if (!pw2Text.getText().equals(pwText.getText())) { // Checks if the two password inputs match

                errorLabel.setText("Passwords do not match! Try Again.");
                pwText.setText("");
                pw2Text.setText("");

            } else if (returnUser(email) != null) { // Checks if the entered email already exists in the database
                errorLabel.setText("An account with this email already exists. Please enter a different email address.");
            } else if (pw2Text.getText().equals(pwText.getText())) { // Once it passes all fields, account credentials are stored into database

                errorLabel.setText("");
                createAccount(email, firstName, lastName, pw, role);
                LoginGui login = new LoginGui();
                frame.dispose();

            }

        }
    }
}
