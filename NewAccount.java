/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class NewAccount implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel titleLabel;
    private static JLabel userType;
    private static JComboBox userClass;
    private static JLabel fullLabel;
    private static JTextField fullName;
    private static JLabel emailLabel;
    private static JTextField email;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel pwLabel;
    private static JPasswordField pwText;
    private static JLabel pwLabel2;
    private static JPasswordField pwText2;
    private static JButton createAcc;
    private static JLabel success;

    //GUI stuff for making new account with fields for account type (student/tutor), name, email, username, 
    //password, password confirmation and create acct button
    NewAccount() {

        frame = new JFrame();
        panel = new JPanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(600, 250, 350, 400);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);

        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        titleLabel = new JLabel("Create a Goober Account");
        titleLabel.setBounds(50, 5, 350, 30);
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 20));
        panel.add(titleLabel);

        userType = new JLabel("Are you a Student or Tutor?");
        userType.setBounds(10, 50, 175, 25);
        userType.setForeground(Color.green);
        panel.add(userType);

        String[] options = {"Student", "Tutor"};
        userClass = new JComboBox(options);
        userClass.setBounds(175, 50, 100, 25);
        userClass.setBackground(Color.DARK_GRAY);
        userClass.setForeground(Color.green);
        panel.add(userClass);

        fullLabel = new JLabel("Enter Full Name: ");
        fullLabel.setBounds(10, 90, 150, 25);
        fullLabel.setForeground(Color.green);
        panel.add(fullLabel);

        fullName = new JTextField();
        fullName.setBounds(120, 90, 150, 25);
        fullName.setBackground(Color.DARK_GRAY);
        fullName.setForeground(Color.green);
        fullName.setBorder(border);
        panel.add(fullName);

        emailLabel = new JLabel("Enter Email: ");
        emailLabel.setBounds(10, 130, 150, 25);
        emailLabel.setForeground(Color.green);
        panel.add(emailLabel);

        email = new JTextField();
        email.setBounds(120, 130, 150, 25);
        email.setBackground(Color.DARK_GRAY);
        email.setForeground(Color.green);
        email.setBorder(border);
        panel.add(email);

        userLabel = new JLabel("Create Username: ");
        userLabel.setBounds(10, 170, 150, 25);
        userLabel.setForeground(Color.green);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(120, 170, 150, 25);
        userText.setBackground(Color.DARK_GRAY);
        userText.setForeground(Color.green);
        userText.setBorder(border);
        panel.add(userText);

        pwLabel = new JLabel("Create Password: ");
        pwLabel.setBounds(10, 210, 150, 25);
        pwLabel.setForeground(Color.green);
        panel.add(pwLabel);

        pwText = new JPasswordField();
        pwText.setBounds(120, 210, 150, 25);
        pwText.setBackground(Color.DARK_GRAY);
        pwText.setForeground(Color.GREEN);
        pwText.setBorder(border);
        panel.add(pwText);

        pwLabel2 = new JLabel("Confirm Password: ");
        pwLabel2.setBounds(10, 250, 150, 25);
        pwLabel2.setForeground(Color.green);
        panel.add(pwLabel2);

        pwText2 = new JPasswordField();
        pwText2.setBounds(120, 250, 150, 25);
        pwText2.setBackground(Color.DARK_GRAY);
        pwText2.setForeground(Color.GREEN);
        pwText2.setBorder(border);
        panel.add(pwText2);

        createAcc = new JButton("Create Account");
        createAcc.setBounds(10, 290, 150, 25);
        createAcc.setBackground(Color.GREEN);
        createAcc.setBorder(border);
        createAcc.setFocusable(false);
        createAcc.addActionListener(this);
        panel.add(createAcc);

        success = new JLabel("");
        success.setBounds(10, 320, 300, 25);
        success.setForeground(Color.GREEN);
        panel.add(success);

        frame.setVisible(true);
    }

    //checks if fields are left empty and if passwords match
    public boolean verifyFields() {
        String fname = fullName.getText();
        String femail = email.getText();
        String uname = userText.getText();
        String pass1 = pwText.getText();
        String pass2 = pwText2.getText();

        if (fname.trim().equals("") || femail.trim().equals("") || uname.trim().equals("") || pass1.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } else if (!(pass1.equals(pass2))) {
            JOptionPane.showMessageDialog(null, "Password Doesn't Match", "Confirm Password", 2);
            return false;
        } else {
            return true;
        }

    }

    //checks if username exists in database
    public boolean checkusername(String username) {
        PreparedStatement st;
        ResultSet rs;

        boolean username_exist = false;
        String query = "SELECT * FROM `users` WHERE `username`=?";
        try {
            st = my_CNX.getConnection().prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Chooser Anothter One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username_exist;
    }

    //checks if email is in database
    public boolean checkemail(String email) {
        PreparedStatement st;
        ResultSet rs;

        boolean email_exist = false;
        String query = "SELECT * FROM `users` WHERE `Email`=?";
        try {
            st = my_CNX.getConnection().prepareStatement(query);
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                email_exist = true;
                JOptionPane.showMessageDialog(null, "This Email is Already Taken, Chooser Anothter One", "Email Failed", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email_exist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String fullname = fullName.getText();
        String user = userText.getText();
        String password = pwText.getText();
        String type = userClass.getSelectedItem().toString();
        String gemail = email.getText();
//
//        if (verifyFields()) {
//            if (!checkusername(user)) {
//                if (!checkemail(gemail)) {
//                    success.setText("Login Successful!");
//                }
//            }
//        }

        if (verifyFields()) {
            if (!checkusername(user)) {
                if (!checkemail(gemail)) {
                    PreparedStatement ps;
                    ResultSet rs;
                    String registerUserQuery = "INSERT INTO `users`(`Full_Name`, `username`, `password`, `type`, `Email`) VALUES (?,?,?,?,?)";

                    try {
                        ps = my_CNX.getConnection().prepareStatement(registerUserQuery);
                        ps.setString(1, fullname);
                        ps.setString(2, user);
                        ps.setString(3, password);
                        ps.setString(4, type);
                        ps.setString(5, gemail);

                        if (ps.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(null, "Your Accoutn Has Been Created");
                            welcome nice = new welcome();
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Check Your Information");

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
    }

}
