
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
Class to be called when logout is hit
Displays a JOptionPane with Yes or No option
 */
public class LogoutView extends JFrame {

//    Controller mcontroller;
    //Constructor
    public LogoutView() {
    }

    //Return 0 if user choose to logout and 1 if user hits No
    public int logout() {
        //yes = 0  //no = 1
        int r = JOptionPane.showConfirmDialog(null,
                "Would you like to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION);

        return r;
    }
}
