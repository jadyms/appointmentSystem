
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterView extends JFrame {

    //Global Elements
    private RegisterController myRegController;
    private JTextField tfFname;
    private JTextField tfLname;
    private JTextField tfmobile;
    private JComboBox<String> tflocation;
    private JTextField tfEmail;
    private JTextField tfPassword;
    private String[] slocation = new String[]{"", "City Center", "Dundrum", "Liffey Valley", "Phibsborough", "Smithfield"};
    boolean isCustomer;

    public RegisterView(RegisterController externalController) {
        this.myRegController = externalController;
    }
   
    //Registration Form
    public void registerNewUser(String type) {
        //Account type
        if (type.equals("Customer")) {
            isCustomer = false;
        } else if (type.equals("Service Provider")) {
            isCustomer = true;
        
        } 
        //Frame
        this.setSize(500, 500);
        this.setVisible(true);
        this.setTitle("Star Hair");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Panel for Header 
        JPanel topPage = new JPanel();
        JLabel fillForm = new JLabel("Fill out your details: ");
        topPage.add(fillForm);

        //Panel for form 
        JPanel form = new JPanel(new GridBagLayout());
        //Setting Layout
        GridBagConstraints fgbc = new GridBagConstraints();
        fgbc.fill = GridBagConstraints.HORIZONTAL;

        //Form Labels
        JLabel fname = new JLabel("First Name: ");
        fgbc.gridx = 0; //leftmost column
        fgbc.gridy = 0; //top row
        fgbc.gridwidth = 1; //1 cell
        form.add(fname, fgbc);

        JLabel lname = new JLabel("Last Name: ");
        fgbc.gridx = 0; //leftmost column
        fgbc.gridy = 1; // row 1
        fgbc.gridwidth = 1; //1 cell
        form.add(lname, fgbc);

        JLabel lmobile = new JLabel("Mobile: ");
        fgbc.gridx = 0; //leftmost column
        fgbc.gridy = 2; // row 2
        fgbc.gridwidth = 1; //1 cell
        form.add(lmobile, fgbc);

        JLabel location = new JLabel("Location: ");
        fgbc.gridx = 0; //leftmost column
        fgbc.gridy = 3; // row 3
        fgbc.gridwidth = 1; //1 cell
        form.add(location, fgbc);
        location.setVisible(isCustomer);

        JLabel email = new JLabel("E-mail: ");
        fgbc.gridx = 0; //leftmost column
        fgbc.gridy = 4; // row 4
        fgbc.gridwidth = 1; //1 cell
        form.add(email, fgbc);

        JLabel password = new JLabel("Password: ");
        fgbc.gridx = 0; //leftmost column
        fgbc.gridy = 5; // row 5
        fgbc.gridwidth = 1; //1 cell
        form.add(password, fgbc);

        //Form textfield       
        tfFname = new JTextField(25);
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 0; //top row
        fgbc.gridwidth = 3; //3 cell
        form.add(tfFname, fgbc);

        tfLname = new JTextField(25);
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 1; // row 1
        fgbc.gridwidth = 3; //3 cell
        form.add(tfLname, fgbc);

        tfmobile = new JTextField(25);
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 2; // row 2
        fgbc.gridwidth = 3; //3 cell
        form.add(tfmobile, fgbc);

        tflocation = new JComboBox<String>(slocation);
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 3; // row 3
        fgbc.gridwidth = 3; //3 cell
        form.add(tflocation, fgbc);
        tflocation.setVisible(isCustomer);

        tfEmail = new JTextField();
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 4; // row 4
        fgbc.gridwidth = 3; //3 cell
        form.add(tfEmail, fgbc);

        tfPassword = new JTextField();
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 5; // row 5
        fgbc.gridwidth = 3; //3 cell
        form.add(tfPassword, fgbc);

        //Button
        JButton bsubmit = new JButton("Submit");
        fgbc.gridx = 1; //middle column
        fgbc.gridy = 6; // row 6
        fgbc.gridwidth = 3; //3 cell
        form.add(bsubmit, fgbc);
        //Setting button ActionCommand - true if Customer/false if Service Provider
        bsubmit.setActionCommand(String.valueOf(isCustomer));
        bsubmit.addActionListener(myRegController);

        //Frame Layout 
        BorderLayout bl = new BorderLayout();
        this.setLayout(bl);

        //Panel for back of the page in case user is not a service provider
        //but instead want to register as a costumer
        JPanel backPage = new JPanel();
        JLabel lreturn = new JLabel("Are you not a Barber or Hairdresser? Go back to the login page =>");
        JButton bhome = new JButton("Home");
        bhome.setActionCommand("home");
        bhome.addActionListener(myRegController);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 20, 10);//Panel Layout
        backPage.setLayout(fl);
        //Add buttons to Panel
        backPage.add(lreturn);
        backPage.add(bhome);
        //Add 3 Panels to the Frame
        this.add(topPage, BorderLayout.NORTH);
        this.add(form, BorderLayout.CENTER);
        this.add(backPage, BorderLayout.SOUTH);
        this.validate();
        this.repaint();

    }
//Getters
    public String getFname() {
        return tfFname.getText();
    }
    
//get and hash password
    public String getPassword() throws NoSuchAlgorithmException {
             String plaintext = tfPassword.getText();
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

        return hashtext;
   }

    public String getLname() {
        return tfLname.getText();
    }

    public String getMobile() {
        return tfmobile.getText();
    }

    public String getEmail() {
        return tfEmail.getText();
    }

    public String getLocat() {
        return String.valueOf(tflocation.getSelectedItem());
    }

    //Registration message for service provider - after submit is hit
    public void regStatus() {
        JOptionPane.showMessageDialog(this, "Registration under approval. Login to the system to check status");
    }
    
    //Registration message for Customer - after submit is hit
    public void customerStatus() {
        JOptionPane.showMessageDialog(this, "You are registered! Login to check your details");

    }

}
