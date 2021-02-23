package goober;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
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

    public static void main(String[] args) {
        
        panel = new JPanel();
        frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();         
        frame.setBounds(600,250,350,250);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
       
        Border border = BorderFactory.createLineBorder(Color.green);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        
        title = new JLabel("Welcome to Goober!", JLabel.CENTER);
//        title.setAlignmentX(0);
//        title.setHorizontalAlignment(JLabel.CENTER);
//        title.setVerticalAlignment(JLabel.CENTER);
        title.setBounds(75,10,200,30);
//        title.setSize(200,30);
        title.setForeground(Color.green);
        title.setFont(new Font(null, Font.CENTER_BASELINE, 20));
        panel.add(title);

        loginButton = new JButton("Log In");
        loginButton.setBounds(120,70,90,30); 
        loginButton.setBackground(Color.green);
        loginButton.setForeground(Color.BLACK);
        loginButton.setBorder(border);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new Goober());
        panel.add(loginButton);
        
        newButton = new JButton("Sign Up");
        newButton.setBounds(120,110,90,30); 
        newButton.setBackground(Color.green);
        newButton.setForeground(Color.BLACK);
        newButton.setBorder(border);
        newButton.setFocusable(false);
        newButton.addActionListener(new Goober());
        panel.add(newButton);
        
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == loginButton){
            LoginGui myGui = new LoginGui();
            frame.dispose();
        }
        
        if(e.getSource() == newButton) {
            
            NewAccount newAcc = new NewAccount();
            frame.dispose();

        }
    }
}
