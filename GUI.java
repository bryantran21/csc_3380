import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{

    private static JPanel panel;
    private static JFrame frame;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JTextField passwordText;
    private static JButton button;
    private static JLabel success;




        public static void main(String[] args) throws Exception {


            panel = new JPanel();

            frame = new JFrame();
            frame.setSize(1000, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.add(panel);

            panel.setLayout(null);

            userLabel = new JLabel("User");
            userLabel.setBounds(10, 20, 80, 25);
            panel.add(userLabel);

            userText = new JTextField();
            userText.setBounds(100, 20, 165, 25);
            panel.add(userText);

            passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(10, 50, 80, 25);
            panel.add(passwordLabel);

            passwordText = new JPasswordField();
            passwordText.setBounds(100, 50, 165, 25);
            panel.add(passwordText);

            button = new JButton("Login");
            button.setBounds(10, 80, 80, 25);
            button.addActionListener(new GUI());
            panel.add(button);

            success = new JLabel("");
            success.setBounds(10, 110, 300, 25);
            panel.add(success);
            success.setText("");


            frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + ", " + password);

        if(user.equals("user") && password.equals("pass")) {
            success.setText("Login successful");
        }
        else {
            success.setText("Incorrect username or password");
        }
    }



}
