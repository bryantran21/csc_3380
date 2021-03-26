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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class Goober implements ActionListener{
    
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel title;
    private static JButton loginButton;
    private static JButton newButton;
    private static JLabel imageLabel;

    public static void main(String[] args) throws IOException {
        
        panel = new JPanel();
        frame = new JFrame();   
        frame.setBounds(600,250,800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.decode("#36393f"));
        frame.add(panel);
       
        panel.setLayout(null);
        
        title = new JLabel("Welcome to Goober!", JLabel.CENTER);
//        title.setAlignmentX(0);
//        title.setHorizontalAlignment(JLabel.CENTER);
//        title.setVerticalAlignment(JLabel.CENTER);
        title.setBounds(180,120,440,150);
//        title.setSize(200,30);
        title.setFont(new Font(null, Font.CENTER_BASELINE, 45));
        title.setForeground(Color.decode("#dcddde"));
        panel.add(title);

        loginButton = new JButton("Log In");
        loginButton.setBounds(200,250,400,30); 
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.decode("#7289da"));
        loginButton.setForeground(Color.decode("#dcddde"));
        loginButton.addActionListener(new Goober());
        panel.add(loginButton);
        
        newButton = new JButton("Sign Up");
        newButton.setBounds(200,290,400,30); 
        newButton.setFocusable(false);
        newButton.setBackground(Color.decode("#7289da"));
        newButton.setForeground(Color.decode("#dcddde"));
        newButton.addActionListener(new Goober());
        panel.add(newButton);
        
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("Goober");
    }
    
    public void actionPerformed(ActionEvent e){ // Sends the user to the login page and closes this window
        
        if(e.getSource() == loginButton){
            LoginGui myGui = new LoginGui();
            frame.dispose();
        }
        
        if(e.getSource() == newButton) { // Sends the user to an account creation page and closes this window
            
            NewAccount newAcc = new NewAccount();
            frame.dispose();
        }
    }
}
