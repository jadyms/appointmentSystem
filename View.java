
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//View of the main login panel
public class View extends JFrame {

    //Global elements
    private Controller myController;
    CustomerController cController = new CustomerController();
    //Text field 
    private JTextField email;
   private JTextField password;
   // private JPasswordField password;
    //JButton
    JRadioButton customer = new JRadioButton("Customer"); //Acc type customer
    JRadioButton serviceP = new JRadioButton("Service Provider"); //Acc type provider
    JRadioButton admin = new JRadioButton("Admin"); //Acc type admin
    ButtonGroup loginOptions = new ButtonGroup();
    //Provider location
    String[] slocation = new String[]{"", "City Center", "Dundrum", "Liffey Valley", "Phibsborough", "Smithfield"};
    String userEmail;
    
    //Acessing the Action Listeners in the Controller class
    public View(Controller externalController) {
        //Main controller
        this.myController = externalController;
        welcomePanel(); //Main login frame
       
    }
    


    //Creating a new frame
    private void newFrame(int h, int w) {
        this.setSize(h, w);
        this.setVisible(true);
        this.setTitle("Star Hair");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Validate and repaint
    private void settings() {
        this.validate();
        this.repaint();
    }

    //Creating Panels and Buttons
    public void welcomePanel() {
        newFrame(500, 500); //create the frame

        //Top Panel for welcome message
        JPanel myPanel1 = new JPanel();
        this.add(myPanel1); //add Top Panel to the frame
        JLabel welcome = new JLabel("Welcome to Star Hair! Sign in");
        myPanel1.add(welcome);//Welcome message 

        //Panel 2 for sign in session
        JPanel myPanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(myPanel2);//add Panel 2 to the frame

        //Label and Text field for email
        JLabel label1 = new JLabel("E-mail: ");
        gbc.gridx = 0; //leftmost column
        gbc.gridy = 0; //top row
        gbc.gridwidth = 1; //1 cell
        myPanel2.add(label1, gbc);
        email = new JTextField(20);
        gbc.gridx = 1;//middle column
        gbc.gridy = 0;//top row
        gbc.gridwidth = 2;//2 cells
        myPanel2.add(email, gbc);

        //Label and Text field for Password
        JLabel label2 = new JLabel("Password: ");
        gbc.gridx = 0; //leftmost column
        gbc.gridy = 1;//2nd row
        gbc.gridwidth = 1; //1 cell
        myPanel2.add(label2, gbc);
        
           password = new JPasswordField();  
      // password = new JTextField(12);
        gbc.gridx = 1; //middle column
        gbc.gridy = 1; //2nd row
        gbc.gridwidth = 2;//2 cells
       // myPanel2.add(password, gbc);
 myPanel2.add(password, gbc);
        //Grouping JRadioButton to make them mutually exclusive
        loginOptions.add(customer);
        loginOptions.add(serviceP);
        loginOptions.add(admin);

        //Adding JRadioButtons to a box
        Box b = Box.createHorizontalBox();
        b.add(customer);
        b.add(serviceP);
        b.add(admin);

        //setting Box position inside GridBagLayout
        gbc.gridx = 1; //middle column
        gbc.gridy = 3; //3rd row
        gbc.gridwidth = 2;//2 cells
        myPanel2.add(b, gbc);

        //Log in button
        JButton login = new JButton("Log in");
        gbc.gridx = 1; //middle column
        gbc.gridy = 4; //4nd row
        gbc.gridwidth = 3; //3 cells
        myPanel2.add(login, gbc);
        //Actions when login button is hit
        login.addActionListener(myController);
        login.setActionCommand("login");
        //Panel 2 border
        myPanel2.setBorder(new LineBorder(Color.GRAY));

        //Panel 3 for register session
        JPanel myPanel3 = new JPanel();
        JLabel registration = new JLabel("Don´t have an account?");
        JButton register = new JButton("Register");
        //Actions when register button is hit
        register.addActionListener(myController);
        register.setActionCommand("register");
        myPanel3.add(registration);
        myPanel3.add(register);

        //Border Layout for the 3 panels
        BorderLayout panels = new BorderLayout();
        this.setLayout(panels);
        this.add(myPanel1, BorderLayout.NORTH);
        this.add(myPanel2, BorderLayout.CENTER);
        this.add(myPanel3, BorderLayout.SOUTH);
        panels.setVgap(2);
        settings();//validate and repaint

    }
    //Get user e-mail

    public String getEmail() {
        userEmail = email.getText();
       
        return userEmail;
       
    }
    //Get user password

    public String getPassword() throws NoSuchAlgorithmException {
        String plaintext = password.getText();
                    MessageDigest m = MessageDigest.getInstance("MD5");
                    m.reset();
                    m.update(plaintext.getBytes());
                    byte[] digest = m.digest();
                    BigInteger bigInt = new BigInteger(1,digest);
                    String hashtext = bigInt.toString(16);
                    // Now we need to zero pad it if you actually want the full 32 chars.
                    while(hashtext.length() < 32 ){
                      hashtext = "0"+hashtext;
                    }
                 
        // return password.getText();
        return hashtext;
    }
      
    

}
