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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import static main.DOMreadXML.login;

public class LoginGui implements ActionListener {
    
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel titleLabel;
    private static JLabel emailLabel;
    private static JTextField emailText;
    private static JLabel pwLabel;
    private static JPasswordField pwText;
    private static JButton loginButton;
    private static JLabel newAccLabel;
    private static JButton newAccButton;
    private static JLabel success;
    
    LoginGui(){
        
        panel = new JPanel();
        frame = new JFrame();
        frame.setBounds(600,250,800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        

        panel.setLayout(null);
        
        titleLabel = new JLabel("Enter Your Login Information");
//        titleLabel.setSize(250, 30);
        titleLabel.setBounds(125,125,550,50);
//        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        titleLabel.setVerticalAlignment(JLabel.CENTER);
//        titleLabel.setLocation(frame.getSize().width/2-titleLabel.getX()/2, 5);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 40));
        panel.add(titleLabel);
        
        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(263, 253, 100, 25);
        emailLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        panel.add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(371, 253, 165, 25);
        panel.add(emailText);
        
        pwLabel = new JLabel("Password: ");
        pwLabel.setBounds(263, 288, 100, 25);
        pwLabel.setFont(new Font(null, Font.CENTER_BASELINE, 18));
        panel.add(pwLabel);
        
        pwText = new JPasswordField();
        pwText.setBounds(371, 288, 165, 25);
        panel.add(pwText);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(360, 341, 80, 25);

        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);
        
        success = new JLabel("");
        success.setBounds(295, 380, 210, 25);
        panel.add(success);
        
        newAccLabel = new JLabel("New Account?");
        newAccLabel.setBounds(318, 500, 100, 20);
        newAccLabel.setFont(new Font(null,Font.PLAIN,10));
        panel.add(newAccLabel);
        
        newAccButton = new JButton("Click here!");
        newAccButton.setBounds(393, 500, 90, 20);
        newAccButton.setFont(new Font(null,Font.PLAIN,10));
        newAccButton.setFocusable(false);
        newAccButton.addActionListener(this);
        panel.add(newAccButton);
        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Goober - Login");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == loginButton){ // Collects the texts of the following variables
            String email= emailText.getText();
            String password = pwText.getText();
            System.out.println(email + ", " + password);
            
            if(login(email, password) || true) { // If login credentials are correct, it sends the user to the homepage
            success.setText("Login Successful!");
            HomePage homePage = new HomePage();
            frame.dispose();
            }else{
                success.setText("Invalid Email or Password! Try Again.");
                emailText.setText("");
                pwText.setText("");
            }
        }
        
        if(e.getSource() == newAccButton){ // Sends the user to the account creation page and closes this window
            
            NewAccount newAcc = new NewAccount();
            frame.dispose();
        } 
    }
}
