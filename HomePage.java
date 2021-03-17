
package goober;

/**
 *
 * @author Bryan
 */

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
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HomePage implements ActionListener
{  
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel titleLabel;
    private static JLabel welcomeLabel;
    private static JLabel searchLabel;
    private static JButton searchButton;
    private static JTextField searchText;
    private static JLabel success;
    
    HomePage(){
        panel = new JPanel();
        frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(1000,500,600,500);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        Border border = BorderFactory.createLineBorder(Color.green);
        Border border2 = BorderFactory.createLineBorder(Color.DARK_GRAY);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        
        titleLabel = new JLabel("Welcome to Goober! :D");
        titleLabel.setBounds(250,100,100,100);
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font(null, Font.CENTER_BASELINE,20));
        panel.add(titleLabel);
        
        welcomeLabel = new JLabel("Hi,**username**");
        welcomeLabel.setBounds(0,0,100,80);
        welcomeLabel.setBackground(Color.GREEN);
        panel.add(welcomeLabel);
        
        searchButton = new JButton("Search");
        searchButton.setBounds(250, 200, 80, 25);
        searchButton.setBackground(Color.GREEN);
        searchButton.setBorder(border);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        panel.add(searchButton);
        
        searchLabel = new JLabel("Search for Tutors: ");
        searchLabel.setBounds(10, 100, 80, 100);
        searchLabel.setForeground(Color.GREEN);
        panel.add(searchLabel);

        searchText = new JTextField();
        searchText.setBounds(90, 50, 165, 25);
        searchText.setBackground(Color.DARK_GRAY);
        searchText.setForeground(Color.green);
        searchText.setBorder(border2);
        panel.add(searchText);
        
        success = new JLabel("");
        success.setBounds(10, 150, 300, 25);
        success.setForeground(Color.GREEN);
        panel.add(success);
        
        frame.setVisible(true);

    }
}