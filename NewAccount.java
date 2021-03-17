package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import static main.DOMmodifyXML.createAccount;

public class NewAccount implements ActionListener{
    
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
    
    NewAccount(){
        
        frame = new JFrame();
        panel = new JPanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();         
        frame.setBounds(600,250,350,450);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        
        panel.setLayout(null);
        panel.setBackground(Color.BLACK); 
        Border border = BorderFactory.createLineBorder(Color.green);
        Border border2 = BorderFactory.createLineBorder(Color.DARK_GRAY);
        
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
        
        emailLabel = new JLabel("Enter a valid e-mail: ");
        emailLabel.setBounds(10,90,150,25);
        emailLabel.setForeground(Color.GREEN);
        panel.add(emailLabel);
        
        emailText = new JTextField();
        emailText.setBounds(130,90,150,25);
        emailText.setBackground(Color.DARK_GRAY);
        emailText.setForeground(Color.green);
        emailText.setBorder(border2);
        panel.add(emailText);
        
        firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setBounds(10, 130, 150, 25);
        firstNameLabel.setForeground(Color.green);
        panel.add(firstNameLabel);
        
        firstNameText = new JTextField();
        firstNameText.setBounds(130, 130, 150, 25);
        firstNameText.setBackground(Color.DARK_GRAY);
        firstNameText.setForeground(Color.green);
        firstNameText.setBorder(border2);
        panel.add(firstNameText);
        
        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(10, 170, 150, 25);
        lastNameLabel.setForeground(Color.green);
        panel.add(lastNameLabel);
        
        lastNameText = new JTextField();
        lastNameText.setBounds(130, 170, 150, 25);
        lastNameText.setBackground(Color.DARK_GRAY);
        lastNameText.setForeground(Color.green);
        lastNameText.setBorder(border2);
        panel.add(lastNameText);
        
        pwLabel = new JLabel("Create Password: ");
        pwLabel.setBounds(10, 210, 150, 25);
        pwLabel.setForeground(Color.green);
        panel.add(pwLabel);
        
        pwText = new JPasswordField();
        pwText.setBounds(130, 210, 150, 25);
        pwText.setBackground(Color.DARK_GRAY);
        pwText.setForeground(Color.GREEN);
        pwText.setBorder(border2);
        panel.add(pwText);
        
        pw2Label = new JLabel("Confirm Password: ");
        pw2Label.setBounds(10, 250, 150, 25);
        pw2Label.setForeground(Color.green);
        panel.add(pw2Label);
        
        pw2Text = new JPasswordField();
        pw2Text.setBounds(130, 250, 150, 25);
        pw2Text.setBackground(Color.DARK_GRAY);
        pw2Text.setForeground(Color.GREEN);
        pw2Text.setBorder(border2);
        panel.add(pw2Text);
        
        createAcc = new JButton("Create Account");
        createAcc.setBounds(10, 290, 150, 25);
        createAcc.setBackground(Color.GREEN);
        createAcc.setBorder(border);
        createAcc.setFocusable(false);
        createAcc.addActionListener(this);
        panel.add(createAcc);
        
        rtnLabel = new JLabel("Already have an account?");
        rtnLabel.setBounds(10,375,150,25);
        rtnLabel.setForeground(Color.green);
        rtnLabel.setFont(new Font(null,Font.PLAIN,10));
        panel.add(rtnLabel);
        
        rtnButton = new JButton("Click Here!");
        rtnButton.setBounds(140,375,150,25);
        rtnButton.setBackground(Color.green);
        rtnButton.setBorder(border);
        rtnButton.setFocusable(false);
        rtnButton.addActionListener(this);
        panel.add(rtnButton);
        
        errorLabel = new JLabel("");
        errorLabel.setBounds(10,325,250,25);
        errorLabel.setForeground(Color.green);
        panel.add(errorLabel);
        
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == rtnButton){

            LoginGui myGui = new LoginGui();
            frame.dispose();
        }
        
        if(e.getSource() == createAcc){
            
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String type = userClass.getSelectedItem().toString();
            String email = emailText.getText();
            String pw = pwText.getText();
            String pw2 = pwText.getText();
            String role = (String)userClass.getSelectedItem();
            
            if(firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || emailText.getText().isEmpty() || pwText.getText().isEmpty() || pw2Text.getText().isEmpty()){
       
                errorLabel.setText("Please fill all required fields.");  
            
            } else if(!pw2Text.getText().equals(pwText.getText())) {
            
                    errorLabel.setText("Passwords do not match! Try Again.");
                    pwText.setText("");
                    pw2Text.setText("");
            
            } else if(pw2Text.getText().equals(pwText.getText())){
                
                errorLabel.setText("");
                createAccount(email, firstName, lastName, pw, role);

            }
        }
    }
}
