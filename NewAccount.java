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
        frame.setBounds(600,250,310,450);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        
        panel.setLayout(null);
        
        titleLabel = new JLabel("Create a Goober Account");
        titleLabel.setBounds(25, 5, 350, 30);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 20));
        panel.add(titleLabel);
        
        userType = new JLabel("Are you a Student or Tutor?");
        userType.setBounds(10,50,175,25);
        panel.add(userType);
        
        String[] options = {"Student", "Tutor"};
        userClass = new JComboBox(options);
        userClass.setBounds(175, 50, 100, 25);
        panel.add(userClass);
        
        emailLabel = new JLabel("Enter a valid e-mail: ");
        emailLabel.setBounds(10,90,150,25);
        panel.add(emailLabel);
        
        emailText = new JTextField();
        emailText.setBounds(130,90,150,25);
        panel.add(emailText);
        
        firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setBounds(10, 130, 150, 25);
        panel.add(firstNameLabel);
        
        firstNameText = new JTextField();
        firstNameText.setBounds(130, 130, 150, 25);
        panel.add(firstNameText);
        
        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(10, 170, 150, 25);
        panel.add(lastNameLabel);
        
        lastNameText = new JTextField();
        lastNameText.setBounds(130, 170, 150, 25);
        panel.add(lastNameText);
        
        pwLabel = new JLabel("Create Password: ");
        pwLabel.setBounds(10, 210, 150, 25);
        panel.add(pwLabel);
        
        pwText = new JPasswordField();
        pwText.setBounds(130, 210, 150, 25);
        panel.add(pwText);
        
        pw2Label = new JLabel("Confirm Password: ");
        pw2Label.setBounds(10, 250, 150, 25);
        panel.add(pw2Label);
        
        pw2Text = new JPasswordField();
        pw2Text.setBounds(130, 250, 150, 25);
        panel.add(pw2Text);
        
        createAcc = new JButton("Create Account");
        createAcc.setBounds(130, 290, 150, 25);
        createAcc.setFocusable(false);
        createAcc.addActionListener(this);
        panel.add(createAcc);
        
        rtnLabel = new JLabel("Already have an account?");
        rtnLabel.setBounds(10,375,150,25);
        rtnLabel.setFont(new Font(null,Font.PLAIN,10));
        panel.add(rtnLabel);
        
        rtnButton = new JButton("Click Here!");
        rtnButton.setBounds(140,375,100,25);
        rtnButton.setFocusable(false);
        rtnButton.addActionListener(this);
        panel.add(rtnButton);
        
        errorLabel = new JLabel("");
        errorLabel.setBounds(10,325,250,25);
        panel.add(errorLabel);
        
        frame.setVisible(true);
        frame.setResizable(false);
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
