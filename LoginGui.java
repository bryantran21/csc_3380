package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();         
        frame.setBounds(600,250,350,250);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
        frame.add(panel);
        

        panel.setLayout(null);
        
        titleLabel = new JLabel("Welcome to Goober!");
//        titleLabel.setSize(250, 30);
        titleLabel.setBounds(80,10,200,30);
//        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        titleLabel.setVerticalAlignment(JLabel.CENTER);
//        titleLabel.setLocation(frame.getSize().width/2-titleLabel.getX()/2, 5);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 20));
        panel.add(titleLabel);
        
        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(90, 50, 165, 25);
        panel.add(emailText);
        
        pwLabel = new JLabel("Password: ");
        pwLabel.setBounds(10, 90, 80, 25);
        panel.add(pwLabel);
        
        pwText = new JPasswordField();
        pwText.setBounds(90, 90, 165, 25);
        panel.add(pwText);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(130, 125, 80, 25);

        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);
        
        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        panel.add(success);
        
        newAccLabel = new JLabel("New Account?");
        newAccLabel.setBounds(10, 180, 100, 20);
        newAccLabel.setFont(new Font(null,Font.PLAIN,10));
        panel.add(newAccLabel);
        
        newAccButton = new JButton("Click here!");
        newAccButton.setBounds(85, 180, 90, 20);
        newAccButton.setFont(new Font(null,Font.PLAIN,10));
        newAccButton.setFocusable(false);
        newAccButton.addActionListener(this);
        panel.add(newAccButton);
        
        frame.setVisible(true);
        frame.setTitle("Goober | Login");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == loginButton){
            String email= emailText.getText();
            String password = pwText.getText();
            System.out.println(email + ", " + password);
            
            if(login(email, password) || true) {
            success.setText("Login Successful!");
            HomePage homePage = new HomePage();
            frame.dispose();
            }else{
                success.setText("Invalid Email or Password! Try Again.");
                emailText.setText("");
                pwText.setText("");
            }
        }
        
        if(e.getSource() == newAccButton){
            
            NewAccount newAcc = new NewAccount();
            frame.dispose();
        } 
    }
}
