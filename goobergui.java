package goobergui;

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

public class GooberGUI implements ActionListener {

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
    
    public static void main(String[] args) {
        
        panel = new JPanel();
        frame = new JFrame();
        frame.setBounds(600, 250, 350, 250);
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
        loginButton.addActionListener(new GooberGUI());
        panel.add(loginButton);
        
        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        success.setForeground(Color.GREEN);
        panel.add(success);
        
        newAccLabel = new JLabel("New Account?");
        newAccLabel.setBounds(10, 180, 100, 20);
        newAccLabel.setForeground(Color.green);
        newAccLabel.setFont(new Font(null,Font.PLAIN,10));
        panel.add(newAccLabel);
        
        newAccButton = new JButton("Click here!");
        newAccButton.setBounds(80, 180, 90, 20);
        newAccButton.setBackground(Color.green);
        newAccButton.setFont(new Font(null,Font.PLAIN,10));
        newAccButton.setFocusable(false);
        newAccButton.addActionListener(new GooberGUI());
        panel.add(newAccButton);
        
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == loginButton){
            String user = userText.getText();
            String password = pwText.getText();
            System.out.println(user + ", " + password);
            
            if(user.equals("aduong2") && password.equals("1234")) {
            success.setText("Login Successful!");
            }else{
                success.setText("Invalid Username or Password! Try Again.");
                userText.setText("");
                pwText.setText("");
            }
        }
        
        if(e.getSource() == newAccButton){
            
            NewAccount newAcc = new NewAccount();
            frame.dispose();
        } 
    }
}
