/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Toolkit;


public class LoginPage implements ActionListener {

    private static JPanel panel;
    private static JFrame frame;
    private static JLabel titleLabel;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel pwLabel;
    private static JPasswordField pwText;
    private static JButton loginButton;
    private static JLabel newAccLabel;
    private static JButton newAccButton;
    private static JLabel success;

    LoginPage() {
        panel = new JPanel();
        frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();         
        frame.setBounds(600, 250, 350, 250);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        titleLabel = new JLabel("Welcome to Goober!");
        titleLabel.setBounds(80, 5, 200, 30);
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 20));
        panel.add(titleLabel);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(10, 40, 80, 25);
        userLabel.setForeground(Color.GREEN);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(90, 40, 165, 25);
        userText.setBackground(Color.DARK_GRAY);
        userText.setForeground(Color.green);
        userText.setBorder(border);
        panel.add(userText);

        pwLabel = new JLabel("Password: ");
        pwLabel.setBounds(10, 80, 80, 25);
        pwLabel.setForeground(Color.GREEN);
        panel.add(pwLabel);

        pwText = new JPasswordField();
        pwText.setBounds(90, 80, 165, 25);
        pwText.setBackground(Color.DARK_GRAY);
        pwText.setForeground(Color.green);
        pwText.setBorder(border);
        panel.add(pwText);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 120, 80, 25);
        loginButton.setBackground(Color.GREEN);
        loginButton.setBorder(border);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        success.setForeground(Color.GREEN);
        panel.add(success);

        newAccLabel = new JLabel("New Account?");
        newAccLabel.setBounds(10, 180, 100, 20);
        newAccLabel.setForeground(Color.green);
        newAccLabel.setFont(new Font(null, Font.PLAIN, 10));
        panel.add(newAccLabel);

        newAccButton = new JButton("Click here!");
        newAccButton.setBounds(80, 180, 90, 20);
        newAccButton.setBackground(Color.green);
        newAccButton.setFont(new Font(null, Font.PLAIN, 10));
        newAccButton.setFocusable(false);
        newAccButton.addActionListener(this);
        panel.add(newAccButton);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            PreparedStatement st;
            ResultSet rs;
            String user = userText.getText();
            String password = pwText.getText();

            String query = "SELECT * FROM `users` WHERE `username`=? AND `password`=?";

            try {
                st = my_CNX.getConnection().prepareStatement(query);
                
                st.setString(1, user);
                st.setString(2, password);
                rs = st.executeQuery();
                
                if(rs.next()){
                    success.setText("Login Successful!");
                    welcome nice = new welcome();
                    frame.dispose();
                }else{
                    success.setText("Invalid Username or Password! Try Again.");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(user + ", " + password);

        }
        if (e.getSource() == newAccButton) {
            NewAccount newAcc = new NewAccount();
            frame.dispose();
        }

    }
}
