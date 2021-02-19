
package gooberdr;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**++WIP++
 *
 * @author Darrion
 * @since 2/17/2021
 * 
 * @see class User
 * 
 * Displays a window with a message.
 * @since
 */
public class Search {
    User user;
    
    //Default constructor
    public Search(){
        user = new User("Guest");
        //Create and set up the window.
            
            JFrame frame = new JFrame("Search");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel(user.getName + " is searching for a tutor for class " + user.getCourses() + " during these times: \n" + user.getTimes());
            frame.add(label);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
    }
    
}
