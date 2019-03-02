
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

// ActionListener for Register Panel
public class RegisterController implements ActionListener {

    //Global elements
    View mainView; //Main view with home panel
    Model myModel; //DB and Query
    RegisterView reg; //Register View with form
    Controller mainController; //Main Controller


    public RegisterController() {
        myModel = new Model(); //DB and Query
        reg = new RegisterView(this); //Register View with form
        //mainController = new Controller();
    }

    //Displaying form according to account type
    public RegisterController(String accType) {
        reg.registerNewUser(accType);
    }

    //Action Events
    @Override
    public void actionPerformed(ActionEvent e) {

        /*The register form contains a home button
            If user decide to go back to main page
            Hit button Home
         */
        if (e.getActionCommand().equals("home")) {

            mainController = new Controller();
            reg.dispose(); //dispose register form frame
            mainController.getClass();//call main panel

        //submit registration form as provider
        } else if (e.getActionCommand().equals("true")) {
            try {
            //Boolean true = Provider registration
            
            //get input from the form
            String fname = reg.getFname();
            String lname = reg.getLname();
            String mobile = reg.getMobile();
            String pmail = reg.getEmail();
            String password = reg.getPassword();
            String location = reg.getLocat();
            String regStatus = "P";
            String admResp = "admin@admin.com";
        
             if(!fname.equals("") &&!lname.equals("")&& !mobile.equals("")&& !pmail.equals("") && !password.equals("") && !location.equals("")){
            //To be inserted on the db
            System.out.println(fname + " " + lname + " " + mobile + " " + pmail + " " + password + " " + location);
            
            //calling insert on the db method 
            myModel.createProvider(fname, lname, mobile, pmail, password, location, regStatus, admResp);
            
            //JDialog to let provider know his registration is under approval
            reg.regStatus();

            //Call home panel
            mainController = new Controller();
            
            //Dispose registration form Panel
            reg.dispose();
            mainController.getClass();
            }else{
                JOptionPane.showMessageDialog(null, "Fields cannot be blank");
            }

            //submit registration form as Customer
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equals("false")) {
            try {
            ////Boolean false = Customer registration
            
            //get input from the form
            String fname = reg.getFname();
            String lname = reg.getLname();
            String mobile = reg.getMobile();
            String cEmail = reg.getEmail();
            String password = reg.getPassword();

            if(!fname.equals("") &&!lname.equals("")&& !mobile.equals("")&& !cEmail.equals("") && !password.equals("")){
            //To be inserted on the db
            System.out.println(fname + " " + lname + " " + mobile + " " + cEmail + " " + password);
            
            //calling insert on the db method 
            myModel.createCustomer(fname, lname, mobile, cEmail, password);
            
            //JDialog to let customer know his registration was successful   
            reg.customerStatus();

            //Call home panel
            mainController = new Controller();
            
            //Dispose registration form Panel
            reg.dispose();
            mainController.getClass();
            }else{
                JOptionPane.showMessageDialog(null, "Fields cannot be blank");
            }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
