
import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomerView extends JFrame {

    //Global Elements
    private String[] slocation = new String[]{"", "City Center", "Dundrum", "Liffey Valley", "Phibsborough", "Smithfield"};

    private String[][] data = new String[20][5];
    private String[][] bkdata = new String[15][5];
    String[][] cpdata = new String[15][4];
    JComboBox<String> jcblocation;
    private JComboBox<String> tfnames;
    JTextField filterText;
    JTextField filterText2;
    static JTextArea complaint;
    TableRowSorter<TableModel> sorter;
    TableRowSorter<TableModel> sorter2;
    static JTable t;
    static JTable checkAptTable;
    JTable complaintTable;
    Model myModel;//DB and Query
    CustomerController cController; //Controller of this class
// LoginView loginView;
    JDialog frame; //Frame with customer options

    public CustomerView(CustomerController externalcController) {

        this.cController = externalcController;
    }

    public CustomerView() {

    }

    //get 2D Array Data to be used in Model Class
    public String[][] getData() {

        return data;
    }

    /*This frame will display the main panels after user is logged in
     */
    private void newFrame(int h, int w) {
        frame = new JDialog(frame, "Customer area");
        frame.setSize(h, w);
        frame.setModal(true);
        frame.setTitle("Star Hair");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.validate();
        frame.repaint();
    }

    public void bookApt(ResultSet rs) {

        //Panel with the booking information
        JPanel myPanel = new JPanel();

        String[] columnName = {"Location", "Provider", "Provider Email", "Free date", "Free time"};
      
        try {
            int i = 0;
            while (rs.next()) {

                bkdata[i][0] = rs.getString("Location");
                bkdata[i][1] = rs.getString("Provider");
                bkdata[i][2] = rs.getString("Provider e-mail");
                bkdata[i][3] = rs.getString("Free date");
                bkdata[i][4] = rs.getString("Free time");
                i++;

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
        }

        //creating a table
        DefaultTableModel model = new DefaultTableModel(bkdata, columnName);

        t = new JTable(model);
        t.getSelectionModel().addListSelectionListener(cController);
        sorter = new TableRowSorter<TableModel>(model);
        t.setRowSorter(sorter);
        JLabel label = new JLabel("Filter");
        myPanel.add(label, BorderLayout.WEST);
        filterText = new JTextField(20);
        myPanel.add(filterText, BorderLayout.CENTER);

        JButton button = new JButton("Filter");
        myPanel.add(button, BorderLayout.EAST);
        button.setActionCommand("Filter location");
        button.addActionListener(cController);
       t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane sp = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
        //To be displayed on the Top Panel
        String message = "Book your next appointment";
        //Label of Button 1 and Button 2
        String b1 = ("Book appointment");
        String b2 = ("Logout");
        //Add elements to panel
        myPanel.add(sp);

        //Populating main Panel with Booking appointment data
        mainPanel(message, b1, b2, myPanel);

    }

    /*
    Customer check their own appointments
    Calls method mainPanel to populate the frame with view Apt options
     */
    public void checkApt(ResultSet rs) {
        String[] columnName = {"Free date", "Free time", "Provider", "Status", "Customer"};
        try {
            int i = 0;
            while (rs.next()) {

                data[i][0] = rs.getString("fdate");
                data[i][1] = rs.getString("ftime");
                data[i][2] = rs.getString("pEmail");
                data[i][3] = rs.getString("aptStatus");
                data[i][4] = rs.getString("cEmail");
                i++;
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

        }


        DefaultTableModel model = new DefaultTableModel(data, columnName);
        checkAptTable = new JTable(model);
        checkAptTable.getSelectionModel().addListSelectionListener(cController);

        JScrollPane sp = new JScrollPane(checkAptTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        checkAptTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        checkAptTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //Panel with appointmnets information
        JPanel myPanel = new JPanel();
        //To be displayed on the Top Panel
        String message = "View bookings";
        //Label of Button 1 and Button 2
        String b1 = ("Cancel Appointment");
        String b2 = ("Logout");
        //Add elements to the panel
        myPanel.add(sp);
        //Populating main Panel with appointment data
        mainPanel(message, b1, b2, myPanel);

    }

    /*
    Customer places a complaint
    Calls method mainPanel to populate the frame with Complaint 
     */
    public void setComplaint(ResultSet rs) {

        //Panel with the booking information
        JPanel myPanel = new JPanel();

        BorderLayout manager = new BorderLayout();
        myPanel.setLayout(manager);

        String[] columnName = {"First Name", "Last Name", "Provider Email", "Location"};
        
        try {

            int i = 0;
            while (rs.next()) {

                cpdata[i][0] = rs.getString("fName");
                cpdata[i][1] = rs.getString("lName");
                cpdata[i][2] = rs.getString("pEmail");
                cpdata[i][3] = rs.getString("location");
                i++;

            }
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
        }
        DefaultTableModel model = new DefaultTableModel(cpdata, columnName);

        t = new JTable(model);
        t.getSelectionModel().addListSelectionListener(cController);
        sorter2 = new TableRowSorter<TableModel>(model);
        t.setRowSorter(sorter2);
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.setColumnSelectionAllowed(false);
        t.setRowSelectionAllowed(true);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

         JScrollPane sp = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
        //To be displayed on the Top Panel
        String message = "Hello! Select a provider to complaint about";
        //Label of Button 1 and Button 2
        String b1 = ("Place complaint");
        String b2 = ("Logout");
        //Add elements to panel
        complaint = new JTextArea();
        myPanel.add(sp, BorderLayout.NORTH);
        myPanel.add(complaint, BorderLayout.CENTER);

        //Populating main Panel with Booking appointment data
        mainPanel(message, b1, b2, myPanel);

    }
   
    /*Main Panel with 3 Panels to be used for every Customer option
    String message- receives a welcome message to be placed on myPanel1
    String b1 and b2 are buttons labels and actionCommand on footer panel myPanel3 - 
    JPanel myPanel2 receives the panel created by the method which calls MainPanel
     */
    public void mainPanel(String message, String b1, String b2, JPanel myPanel2) {
        newFrame(600, 600);
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
        //Instance of Customer Controller that holds Action Events     
        cController = new CustomerController();
        btn1.addActionListener(cController);
        btn2.addActionListener(cController);

        //Add Welcome message to Panel 1
        myPanel1.add(myLabel);
        //Add Buttons to Panel 3
        myPanel3.add(btn1);
        myPanel3.add(btn2);

        //Frame Layout
        BorderLayout manager = new BorderLayout();
        frame.setLayout(manager);
        //Adding Panels to the frame
        frame.add(myPanel1, BorderLayout.NORTH);
        frame.add(myPanel2, BorderLayout.CENTER);
        frame.add(myPanel3, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

}
