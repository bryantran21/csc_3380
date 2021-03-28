package main;

/**
 *
 * @author Bryan Tran, Anthony Duong
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import static main.DOMmodifyXML.addClass;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import static main.DOMmodifyXML.setAvailability;
import static main.DOMreadXML.returnUser;

public class Schedule implements ActionListener, ItemListener {

    private JFrame frame;
    private JPanel panel;
    private JButton logoutButton;
    private JButton homeButton;
    private JLabel scheduleTitle;
    private JLabel scheduleLabel;
    private JLabel scheduleLabel2;
    private JLabel updateLabel;
    private JRadioButton mondayRadio;
    private JRadioButton tuesdayRadio;
    private JRadioButton wednesdayRadio;
    private JRadioButton thursdayRadio;
    private JRadioButton fridayRadio;
    private JRadioButton saturdayRadio;
    private JRadioButton sundayRadio;
    private JButton updateBtn;
    private JFrame confirmFrame;
    private JPanel confirmPanel;
    private JLabel confirmLabel;
    private JLabel confirmLabel2;
    private JButton confirmBtn;
    private JButton declineBtn;
    private JFrame successFrame;
    private JPanel successPanel;
    private JLabel successLabel;
    private JLabel successLabel2;

    private String daySelect = "";
    private User currentUser;

    String dayArray[] = {"", "", "", "", "", "", ""};

    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public Schedule(User passedUser) {

        currentUser = passedUser;

        frame = new JFrame();
        frame.setBounds(600, 250, 600, 400);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#36393f"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setTitle("Goober - Manage Schedule");
        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        frame.setIconImage(image.getImage());

        homeButton = new JButton("Home");
        homeButton.setBounds(400, 10, 80, 30);
        homeButton.setBackground(Color.decode("#7289da"));
        homeButton.setForeground(Color.decode("#dcddde"));
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);
        panel.add(homeButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(490, 10, 80, 30);
        logoutButton.setBackground(Color.decode("#7289da"));
        logoutButton.setForeground(Color.decode("#dcddde"));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        scheduleTitle = new JLabel("Availability");
        scheduleTitle.setBounds(25, 30, 400, 40);
        scheduleTitle.setFont(new Font(null, Font.CENTER_BASELINE, 30));
        scheduleTitle.setForeground(Color.decode("#dcddde"));
        panel.add(scheduleTitle);

        scheduleLabel = new JLabel("Current Availibility: ");
        scheduleLabel.setBounds(23, 60, 400, 40);
        scheduleLabel.setFont(new Font(null, Font.CENTER_BASELINE, 13));
        scheduleLabel.setForeground(Color.decode("#dcddde"));
        panel.add(scheduleLabel);

        scheduleLabel2 = new JLabel(currentUser.getEmail());
        scheduleLabel2.setBounds(23, 80, 600, 40);
        scheduleLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 13));
        scheduleLabel2.setForeground(Color.decode("#dcddde"));
        panel.add(scheduleLabel2);

        updateLabel = new JLabel("Change your availibilty to: ");
        updateLabel.setBounds(23, 120, 400, 40);
        updateLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
        updateLabel.setForeground(Color.decode("#dcddde"));
        panel.add(updateLabel);

        mondayRadio = new JRadioButton();
        mondayRadio.setText("Monday");
        mondayRadio.setForeground(Color.decode("#dcddde"));
        mondayRadio.setBounds(25, 150, 75, 30);
        mondayRadio.setBackground(Color.decode("#36393f"));
        mondayRadio.setFocusable(false);
        mondayRadio.addItemListener(this);
        panel.add(mondayRadio);

        tuesdayRadio = new JRadioButton();
        tuesdayRadio.setText("Tuesday");
        tuesdayRadio.setForeground(Color.decode("#dcddde"));
        tuesdayRadio.setBackground(Color.decode("#36393f"));
        tuesdayRadio.setBounds(100, 150, 75, 30);
        tuesdayRadio.setFocusable(false);
        tuesdayRadio.addItemListener(this);
        panel.add(tuesdayRadio);

        wednesdayRadio = new JRadioButton();
        wednesdayRadio.setText("Wednesday");
        wednesdayRadio.setForeground(Color.decode("#dcddde"));
        wednesdayRadio.setBackground(Color.decode("#36393f"));
        wednesdayRadio.setBounds(175, 150, 100, 30);
        wednesdayRadio.setFocusable(false);
        wednesdayRadio.addItemListener(this);
        panel.add(wednesdayRadio);

        thursdayRadio = new JRadioButton();
        thursdayRadio.setText("Thursday");
        thursdayRadio.setForeground(Color.decode("#dcddde"));
        thursdayRadio.setBackground(Color.decode("#36393f"));
        thursdayRadio.setBounds(270, 150, 100, 30);
        thursdayRadio.setFocusable(false);
        thursdayRadio.addItemListener(this);
        panel.add(thursdayRadio);

        fridayRadio = new JRadioButton();
        fridayRadio.setText("Friday");
        fridayRadio.setForeground(Color.decode("#dcddde"));
        fridayRadio.setBackground(Color.decode("#36393f"));
        fridayRadio.setBounds(70, 175, 60, 30);
        fridayRadio.setFocusable(false);
        fridayRadio.addItemListener(this);
        panel.add(fridayRadio);

        saturdayRadio = new JRadioButton();
        saturdayRadio.setText("Saturday");
        saturdayRadio.setForeground(Color.decode("#dcddde"));
        saturdayRadio.setBackground(Color.decode("#36393f"));
        saturdayRadio.setBounds(130, 175, 80, 30);
        saturdayRadio.setFocusable(false);
        saturdayRadio.addItemListener(this);
        panel.add(saturdayRadio);

        sundayRadio = new JRadioButton();
        sundayRadio.setText("Sunday");
        sundayRadio.setForeground(Color.decode("#dcddde"));
        sundayRadio.setBackground(Color.decode("#36393f"));
        sundayRadio.setBounds(210, 175, 75, 30);
        sundayRadio.setFocusable(false);
        sundayRadio.addItemListener(this);
        panel.add(sundayRadio);

        updateBtn = new JButton("Update Availibility");
        updateBtn.setBounds(70, 250, 200, 30);
        updateBtn.setBackground(Color.decode("#7289da"));
        updateBtn.setForeground(Color.decode("#dcddde"));
        updateBtn.setFocusable(false);
        updateBtn.addActionListener(this);
        panel.add(updateBtn);

    }

    public void updateSchedule(String text1) {

        confirmFrame = new JFrame();
        confirmFrame.setBounds(600, 250, 415, 150);
        confirmFrame.setLocationRelativeTo(null);
        confirmPanel = new JPanel();
        confirmPanel.setLayout(null);
        confirmPanel.setBackground(Color.decode("#36393f"));
        confirmFrame.setVisible(true);
        confirmFrame.add(confirmPanel);
        confirmFrame.setTitle("Goober - Manage Schedule");
        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        confirmFrame.setIconImage(image.getImage());

        confirmLabel = new JLabel("Are you sure you want to update your availibilty to");
        confirmLabel.setBounds(0, 10, 400, 30);
        confirmLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        confirmLabel.setHorizontalAlignment(JLabel.CENTER);
        confirmLabel.setForeground(Color.decode("#dcddde"));
        confirmPanel.add(confirmLabel);

        confirmLabel2 = new JLabel(text1);
        confirmLabel2.setBounds(0, 30, 400, 30);
        confirmLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        confirmLabel2.setHorizontalAlignment(JLabel.CENTER);
        confirmLabel2.setForeground(Color.decode("#dcddde"));
        confirmPanel.add(confirmLabel2);

        confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(110, 70, 80, 30);
        confirmBtn.setBackground(Color.decode("#7289da"));
        confirmBtn.setForeground(Color.decode("#dcddde"));
        confirmBtn.setFocusable(false);
        confirmBtn.addActionListener(this);
        confirmPanel.add(confirmBtn);

        declineBtn = new JButton("Decline");
        declineBtn.setBounds(210, 70, 80, 30);
        declineBtn.setBackground(Color.decode("#7289da"));
        declineBtn.setForeground(Color.decode("#dcddde"));
        declineBtn.setFocusable(false);
        declineBtn.addActionListener(this);
        confirmPanel.add(declineBtn);
    }

    public void successPage(String text1) {

        successFrame = new JFrame();
        successFrame.setBounds(600, 250, 415, 150);
        successFrame.setLocationRelativeTo(null);
        successPanel = new JPanel();
        successPanel.setLayout(null);
        successPanel.setBackground(Color.decode("#36393f"));

        successLabel = new JLabel("You have successfully updated your availibility to");
        successLabel.setBounds(0, 30, 400, 30);
        successLabel.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        successLabel.setHorizontalAlignment(JLabel.CENTER);
        successLabel.setForeground(Color.decode("#dcddde"));
        successPanel.add(successLabel);

        successLabel2 = new JLabel(text1);
        successLabel2.setBounds(0, 50, 400, 30);
        successLabel2.setFont(new Font(null, Font.CENTER_BASELINE, 12));
        successLabel2.setHorizontalAlignment(JLabel.CENTER);
        successLabel2.setForeground(Color.decode("#dcddde"));
        successPanel.add(successLabel2);

        ImageIcon image = new ImageIcon("src/main/GooberGLogo.png");
        successFrame.setIconImage(image.getImage());
        successFrame.add(successPanel);
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setTitle("Goober - Tutor Confirmation");
        successFrame.setVisible(true);
        successFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {

            HomePage home = new HomePage(currentUser);
            frame.dispose();
        }
        if (e.getSource() == logoutButton) {

            LoginGui login = new LoginGui();
            frame.dispose();
        }

        if (e.getSource() == updateBtn) {

            int counter = 0;
            while (dayArray[counter].equals("") && counter < 6) {
                if (counter < 6) {
                    counter++;
                }
            }
            daySelect = dayArray[counter];
            for (int i = counter + 1; i < 7; i++) {
                if ((dayArray[i].compareTo("") != 0)) {
                    daySelect = daySelect + ", " + dayArray[i];
                }
            }
            if (daySelect.equals("")) {
                daySelect = "'no days available'";
            }

            String text1 = daySelect + "?";
            updateSchedule(text1);
        }

        if (e.getSource() == confirmBtn) { // ***THIS NEEDS TO WORK WITH BACKEND***

            String availability = "";
            for (int i = 0; i < 7; i++) {
                if ((dayArray[i].compareTo("") != 0)) {
                    availability = "available";
                } else {
                    availability = "unavailabile";
                }

                String dayParameter = "";

                switch (i) {
                    case 0:
                        dayParameter = "Monday";
                        break;
                    case 1:
                        dayParameter = "Tuesday";
                        break;
                    case 2:
                        dayParameter = "Wednesday";
                        break;
                    case 3:
                        dayParameter = "Thursday";
                        break;
                    case 4:
                        dayParameter = "Friday";
                        break;
                    case 5:
                        dayParameter = "Saturday";
                        break;
                    case 6:
                        dayParameter = "Sunday";
                        break;
                }
                setAvailability(currentUser.getEmail(), dayParameter, availability);
            }
            currentUser = returnUser(currentUser.getEmail());

            String text1 = daySelect + ".";
            scheduleLabel2.setText(daySelect + ".");
            successPage(text1);
            confirmFrame.dispose();
        }

        if (e.getSource() == declineBtn) {

            confirmFrame.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        daySelect = "";
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object source = e.getSource();
            if (source == mondayRadio) {
                dayArray[0] = "Monday";
            }
            if (source == tuesdayRadio) {
                dayArray[1] = "Tuesday";
            }
            if (source == wednesdayRadio) {
                dayArray[2] = "Wednesday";
            }
            if (source == thursdayRadio) {
                dayArray[3] = "Thursday";
            }
            if (source == fridayRadio) {
                dayArray[4] = "Friday";
            }
            if (source == saturdayRadio) {
                dayArray[5] = "Saturday";
            }
            if (source == sundayRadio) {
                dayArray[6] = "Sunday";
            }
        }
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            Object source = e.getSource();
            if (source == mondayRadio) {
                dayArray[0] = "";
            }
            if (source == tuesdayRadio) {
                dayArray[1] = "";
            }
            if (source == wednesdayRadio) {
                dayArray[2] = "";
            }
            if (source == thursdayRadio) {
                dayArray[3] = "";
            }
            if (source == fridayRadio) {
                dayArray[4] = "";
            }
            if (source == saturdayRadio) {
                dayArray[5] = "";
            }
            if (source == sundayRadio) {
                dayArray[6] = "";
            }
        }
    }
}
