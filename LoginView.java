
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Creates a login area with 4 buttons
Buttons displays different options according to accType
Acctype defined in Controller
 */
public class LoginView extends JFrame {

    //Global Elements
    private Controller myController;
    private CustomerController cController;
    private ProviderController pController;
    private AdminController aController;
    private RegisterController rc = new RegisterController();
    boolean isCustomer; //The accType 
    private static String loginEmail;

    /*Call method that creates the frame with 4 buttons
       populate buttons according to accType
       type = accType
     */
    public LoginView(String b1, String b2, String b3, String b4, String type, String userEmail) {
        loginEmail = loginOptions(b1, b2, b3, b4, type, userEmail);
      
        setLoginEmail(loginEmail);
    }

    public LoginView(CustomerController exCController) {
        this.cController = exCController;

    }

        public LoginView() {
           
    }
         
        
    //Login area according to accType      
    public String loginOptions(String b1, String b2, String b3, String b4, String type, String userEmail) {
        
         //Frame
        this.setSize(500, 500);
        this.setVisible(true);
        this.setTitle("Star Hair");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Top panel with welcome message
        JPanel headerPanel = new JPanel();
        JLabel welcome = new JLabel("Hi " + userEmail +"! What would you like to do?");
        headerPanel.add(welcome);

        //Main panel to Hold the buttons
        JPanel mainPanel = new JPanel();
        GridLayout manager = new GridLayout(2, 2);
        mainPanel.setLayout(manager);
        manager.setHgap(30);
        manager.setVgap(30);

        //cController = new CustomerController();
        //Creating buttons ans receiving variables according to accType
        JButton btn1 = new JButton(b1);
        JButton btn2 = new JButton(b2);
        JButton btn3 = new JButton(b3);
        JButton btn4 = new JButton(b4);

        //If Customer Account
        if (type.equals("Customer")) {
            //  isCustomer = false;
            btn1.setActionCommand(b1);
            btn2.setActionCommand(b2);
            btn3.setActionCommand(b3);
            btn4.setActionCommand("Logout");
            //Instance of Customer Controller to access ActionEvents
            cController = new CustomerController();
            btn1.addActionListener(cController);
            btn2.addActionListener(cController);
            btn3.addActionListener(cController);
            btn4.addActionListener(cController);

            //If Provider account   
        } else if (type.equals("Service Provider")) {
            //isCustomer = true;
            btn1.setActionCommand(b1);
            btn2.setActionCommand(b2);
            btn3.setActionCommand(b3);
            btn4.setActionCommand("Logout");
            //Instance of Provider Controller to access ActionEvents
            pController = new ProviderController();
            btn1.addActionListener(pController);
            btn2.addActionListener(pController);
            btn3.addActionListener(pController);
            btn4.addActionListener(pController);
       
         } else if (type.equals("Admin")) {
            //isCustomer = true;
            btn1.setActionCommand(b1);
            btn2.setActionCommand(b2);
            btn3.setActionCommand(b3);
            btn4.setActionCommand("Logout");
            //Instance of Provider Controller to access ActionEvents
            aController = new AdminController();
            btn1.addActionListener(aController);
            btn2.addActionListener(aController);
            btn3.addActionListener(aController);
            btn4.addActionListener(aController);
         }

        //resize one of the buttons so all the others will follow the same dimensions
        btn1.setPreferredSize(new Dimension(100, 140));

        //add buttons to Panel
        mainPanel.add(btn1);
        mainPanel.add(btn2);
        mainPanel.add(btn3);
        mainPanel.add(btn4);
        //add Panel to frame      
        this.add(mainPanel);
        //Set frame Layout
        BorderLayout mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        mainLayout.setVgap(30);
        
        return userEmail;

    }
    
public void setLoginEmail(String email){
            System.out.println("from setLogin in Login View" + loginEmail);
            loginEmail = email;
           
        }
        
 public String getEmail(){
        return loginEmail;
    }
   
  
    
    
    
    
}
