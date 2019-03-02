import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/*
Controls the admin panel
*/
public class AdminController implements ActionListener {

    View mainView; //Main view with home panel
    Model myModel; //DB and Query
    AdminView adminView; //Admin View with buttons
    Controller mainController; //Main Controller
    LoginView loginView; //User panels according to account type

    public AdminController(){
        myModel = new Model();
        adminView = new AdminView();
        loginView = new LoginView();
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
    //Admin can set provider registration status as Approved or Denied 
    //Incomplete code
     if (e.getActionCommand().equals("Approve a provider")) {
        //Connection with the db   
        ResultSet rs = myModel.selectProvider();
        //Passing db data to method
        adminView.providerApproval(rs);

        }   
    
    }
    
}
