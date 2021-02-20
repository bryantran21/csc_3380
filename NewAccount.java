package goobergui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class NewAccount {
    
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel titleLabel;
    private static JLabel userType;
    private static JComboBox userClass;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel pwLabel;
    private static JPasswordField pwText;
    private static JButton createAcc;
    
    NewAccount(){
        
        frame = new JFrame();
        panel = new JPanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();         
        frame.setBounds(600,250,350,250);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
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
        userType.setBounds(10,50,175,25);
        userType.setForeground(Color.green);
        panel.add(userType);
        
        String[] options = {"Student", "Tutor"};
        userClass = new JComboBox(options);
        userClass.setBounds(175, 50, 100, 25);
        userClass.setBackground(Color.DARK_GRAY);
        userClass.setForeground(Color.green);
        panel.add(userClass);
        
        userLabel = new JLabel("Create Username: ");
        userLabel.setBounds(10, 90, 150, 25);
        userLabel.setForeground(Color.green);
        panel.add(userLabel);
        
        userText = new JTextField();
        userText.setBounds(120, 90, 150, 25);
        userText.setBackground(Color.DARK_GRAY);
        userText.setForeground(Color.green);
        userText.setBorder(border);
        panel.add(userText);
        
        pwLabel = new JLabel("Create Password: ");
        pwLabel.setBounds(10, 130, 150, 25);
        pwLabel.setForeground(Color.green);
        panel.add(pwLabel);
        
        pwText = new JPasswordField();
        pwText.setBounds(120, 130, 150, 25);
        pwText.setBackground(Color.DARK_GRAY);
        pwText.setForeground(Color.GREEN);
        pwText.setBorder(border);
        panel.add(pwText);
        
        createAcc = new JButton("Create Account");
        createAcc.setBounds(10, 170, 150, 25);
        createAcc.setBackground(Color.GREEN);
        createAcc.setBorder(border);
        createAcc.setFocusable(false);
        createAcc.addActionListener(new GooberGUI());
        panel.add(createAcc);
        
        frame.setVisible(true);
    }
        
}
