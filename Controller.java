
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {

    //Classes as global variables
    View myView; //Home Frame
    Model myModel; //DB and Query
    RegisterController myRegisterController; //Register as new user
    String accType; //Account type

    public Controller() {
        myView = new View(this);// View class - Home Frame 
        myModel = new Model(); //Model class - DB and Query 

    }

    //Actions when the buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        //User fills name, password and select login type --> press login
        if (e.getActionCommand().equals("login")) {
            try {
                //If customer type is selected
                if (myView.customer.isSelected()) {
                    //compare email and password to info in customer table
                    
                   
                    boolean result = myModel.customerLogin(myView.getEmail(), myView.getPassword());
                    // Evaluating the result from the query
                    if (result) { //If success --> login to the system as a customer
                        JOptionPane.showMessageDialog(myView, "Welcome to Star Hairdressers Customer Area");
                        //Define options that will be displayed according to Customer account type
                        String cb1 = "Book an appointment";
                        String cb2 = "Check your appointments";
                        String cb3 = "Suggestions and Complaints";
                        String cb4 = "Logout";
                        String type = "Customer";
                        String userEmail = myView.getEmail();
                        //creating Customer login panel
                        LoginView loginPanel = new LoginView(cb1, cb2, cb3, cb4, type, userEmail);
                        //disposing home panel
                        myView.dispose();

                    } else {// If not success
                        JOptionPane.showMessageDialog(myView, "Incorrect information. Try again");
                    }

                    //If service provider type is selected    
                } else if (myView.serviceP.isSelected()) {

                    //compare email and password to info in providers table
                    boolean result = myModel.servicePLogin(myView.getEmail(), myView.getPassword());
                    // Evaluating the result from the query
                    if (result) {//If success --> login to the system as a provider

                        JOptionPane.showMessageDialog(myView, "Welcome to Star Hairdressers Providers Area");
                        //Define options that will be displayed according to Customer account type
                        String pb1 = "View upcoming appointments";
                        String pb2 = "Set availability";
                        String pb3 = "Appointment Status";
                        String pb4 = "Logout";
                        String type = "Service Provider";
                        String userEmail = myView.getEmail();
                        //creating Provider login panel
                        LoginView loginPanel = new LoginView(pb1, pb2, pb3, pb4, type, userEmail);
                        //disposing home panel
                        myView.dispose();

                    } else {//If not success
                        JOptionPane.showMessageDialog(myView, "Incorrect information. Try again");
                    }

                    //If admin type is selected  
                } else if (myView.admin.isSelected()) {
                    //compare email and password to info in providers table
                    boolean result = myModel.adminLogin(myView.getEmail(), myView.getPassword());
                   
                    // Evaluating the result from the query
                    if (result) {//If success --> login to the system as an admin
                        JOptionPane.showMessageDialog(myView, "Welcome to Star Hairdressers Admin Area");
                        //Define options that will be displayed according to Customer account type
                        String ab1 = "Approve a provider";
                        String ab2 = "User activities";
                        String ab3 = "Create a new admin";
                        String ab4 = "Logout";
                        String type = "Admin";
                        String userEmail = myView.getEmail();
                        //creating Customer login panel
                        LoginView loginPanel = new LoginView(ab1, ab2, ab3, ab4, type, userEmail);
                        //disposing home panel
                        myView.dispose();


                    } else {//If not success

                        JOptionPane.showMessageDialog(myView, "Incorrect information. Try again");

                    }
                    //Login option has to be selected            	
                } else if (myView.loginOptions.getSelection() == null) {

                    JOptionPane.showMessageDialog(myView, "Select a login option");
                }
            } catch (Exception e1) {
                System.out.println("Try again");
            }
        

            //Registering as a new user
        } else if (e.getActionCommand().equals("register")) {

            //JDialog to determine wheter account is for Customer or Provider
            String[] possibilities = {"Service Provider", "Customer"};
            accType = (String) JOptionPane.showInputDialog(
                    myView, "Select your registration type", "Are you a customer or service provider?", JOptionPane.PLAIN_MESSAGE, null,
                    possibilities,
                    possibilities[0]);

            //Instance of the Register Controller Class
            myRegisterController = new RegisterController();
            //Accessing Register View through Register Controller
            //Displaying form according to account type
            myRegisterController.reg.registerNewUser(accType);
            //Disposing home frame
            myView.dispose();
        }

    }

}
