
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AdminView extends JFrame {

    Model myModel;  //DB and Query
    JTextField tfemail = new JTextField(20); //user email
    JTextField tfpass = new JTextField(20); //user password
    JDialog frame;
    private String[][] data = new String[15][4]; //table
    JTable t;
    AdminController aController;

    public AdminView() {
        myModel = new Model();
    }

    //Changinh the registration status of a provider
    public void providerApproval(ResultSet rs) {
        
        String[] columnName = {"Provider", "Name", "Last Name", "Status"};

        //populating the table with db info
        try {
            int i = 0;
            while (rs.next()) {

                data[i][0] = rs.getString("pEmail");
                data[i][1] = rs.getString("fName");
                data[i][2] = rs.getString("lName");
                data[i][3] = rs.getString("regStatus");

                i++;
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

        }

        //Table model
        DefaultTableModel model = new DefaultTableModel(data, columnName);
        //create table and apply model
        t = new JTable(model);
        //only one row can be selected at time
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //columns auto resizeable
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //  t.getSelectionModel().addListSelectionListener((ListSelectionListener) aController);
        //create scroll pane with scroll bars
        JScrollPane sp = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //Panel with appointmnets information
        JPanel myPanel1 = new JPanel();
        //Add elements to the panel
        myPanel1.add(sp);
        //Populating main Panel admin info
        String message = "Provider accounts";
        String b1 = ("Approve provider");
        String b2 = ("Deny Provider");
        String b3 = ("Logout");

        //Pass variables to Main Panel
        mainPanel(message, b1, b2, b3, myPanel1);

    }

    public void userActivities() {

        newFrame(500, 500);

        FlowLayout flow = new FlowLayout();
        this.setLayout(flow);

        JPanel myPanel1 = new JPanel();

        JLabel ltype = new JLabel("Select an account type");
        // create an empty combo box with items of type String
        JComboBox<String> type = new JComboBox<String>();

        // add items to the combo box
        type.addItem("Customer");
        type.addItem("Provider");

        JLabel ltype2 = new JLabel("Select an user");
        // create an empty combo box with items of type String
        JComboBox<String> type2 = new JComboBox<String>();

        // add items to the combo box
        type2.addItem("User 1");
        type2.addItem("User 2");

        JPanel myPanel2 = new JPanel();

        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        JButton logout = new JButton("Logout");
        JButton home = new JButton("Home");

        myPanel1.add(ltype);
        myPanel1.add(type);
        myPanel1.add(ltype2);
        myPanel1.add(type2);
        myPanel2.add(confirm);

        myPanel2.add(cancel);
        myPanel2.add(home);
        myPanel2.add(logout);
        BorderLayout manager = new BorderLayout();
        this.setLayout(manager);

        this.add(myPanel1, BorderLayout.NORTH);
        this.add(myPanel2, BorderLayout.SOUTH);

        this.validate();
        this.repaint();

    }

    public void db() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //String dbServer = "jdbc:mysql://localhost:3306/testing_gui";
            String dbServer = "jdbc:mysql://localhost:3306/Star";
            String user = "root";
            String password = "";
            String query = "SELECT * FROM providers";

            // Get a connection to the database
            conn = DriverManager.getConnection(dbServer, user, password);

            // Get a statement from the connection
            stmt = conn.createStatement();

            // Execute the query
            rs = stmt.executeQuery(query);

            // Loop through the result set
            int i = 0;
            while (rs.next()) {

                data[i][0] = rs.getString("pEmail");
                data[i][1] = rs.getString("fname");
                data[i][2] = rs.getString("lname");
                data[i][3] = rs.getString("regStatus");
                data[i][4] = rs.getString("admResp");
                i++;

            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void createAdmin() {
        newFrame(500, 500);

        FlowLayout flow = new FlowLayout();
        this.setLayout(flow);

        JPanel myPanel1 = new JPanel();

        JLabel lemail = new JLabel("E-mail: ");

        JLabel lpass = new JLabel("Password: ");

        JPanel myPanel2 = new JPanel();

        JButton confirm = new JButton("Create Account");
        confirm.setActionCommand("createAccount");
        //confirm.addActionListener();

        JButton logout = new JButton("Logout");
        JButton home = new JButton("Home");

        myPanel1.add(lemail);
        myPanel1.add(tfemail);
        myPanel1.add(lpass);
        myPanel1.add(tfpass);
        myPanel2.add(confirm);

        myPanel2.add(home);
        myPanel2.add(logout);
        BorderLayout manager = new BorderLayout();
        this.setLayout(manager);

        this.add(myPanel1, BorderLayout.NORTH);
        this.add(myPanel2, BorderLayout.SOUTH);

        this.validate();
        this.repaint();

    }

    public String getEmail() {

        return tfemail.getText();

    }

    public String getPass() {

        return tfpass.getText();

    }

     //Settings of a new frame
    private void newFrame(int h, int w) {
        frame = new JDialog(frame, "Provider area");
        frame.setSize(h, w);
        frame.setModal(true);
        frame.setTitle("Star Hair");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.validate();
        frame.repaint();
    }
    /*Main Panel with 3 Panels to be used for every Customer option
    String message- receives a welcome message to be placed on myPanel1
    String b1 and b2 are buttons labels and actionCommand on footer myPanel2 myPanel3 - 
    JPanel myPanel3 receives the myPanel2 created by the method which calls MainPanel
     */
    public void mainPanel(String message, String b1, String b2, String b3, JPanel myPanel2) {
        //New frame
        newFrame(500, 500);
        //Panel 1 for welcome message
        JPanel myPanel1 = new JPanel();
        //Panel 3 for footer buttons
        JPanel myPanel3 = new JPanel();
        //message for Panel 1
        JLabel myLabel = new JLabel(message);//according to method calling it
        //Footer buttons for Panel 3
        JButton btn1 = new JButton(b1);
        JButton btn2 = new JButton(b2);
        //Add Action command
        btn1.setActionCommand(b1);
        btn2.setActionCommand(b2);

        JButton btn3 = new JButton(b3);
        aController = new AdminController();
        btn1.addActionListener(aController);
        btn2.addActionListener(aController);
        btn3.addActionListener(aController);

        myPanel1.add(myLabel);

        myPanel3.add(btn1);
        myPanel3.add(btn2);
        myPanel3.add(btn3);

        BorderLayout manager = new BorderLayout();
        frame.setLayout(manager);

        frame.add(myPanel1, BorderLayout.NORTH);
        frame.add(myPanel2, BorderLayout.CENTER);
        frame.add(myPanel3, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

}
