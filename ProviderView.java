import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


public class ProviderView extends JFrame {

    //Global elements
    private String[][] data = new String[20][5];
    private String[][] setdata = new String[10][5];
    Model myModel;
    LoginView loginView;
    JDialog frame;
    ProviderController pController;
    LocalDate ld;
    static JDateChooser chooser;
    static JTable t;
    static String newNew;
    static JComboBox<String> times;
    private String[] stimes = new String[]{"9:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00"};

    public ProviderView() {

        myModel = new Model(); //DB and Query

    }

    /*This frame will display the main panels after user is logged in
     */
    private void newFrame(int h, int w) {
        frame = new JDialog(frame, "Provider area");
        frame.setSize(h, w);
        frame.setModal(true);
        frame.setTitle("Star Hair");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.validate();
        frame.repaint();
    }

    //provider appointments
    public void viewApt(ResultSet rs) {

        //Panel 1 for welcome message
        String[] columnName = {"Provider", "Date", "Time", "Customer", "Status"};

        //    myModel.showCustBookings();
        //db(pEmail);
        try {
            int i = 0;
            while (rs.next()) {

                data[i][0] = rs.getString("pEmail");
                data[i][1] = rs.getString("fdate");
                data[i][2] = rs.getString("ftime");
                data[i][3] = rs.getString("cEmail");
                data[i][4] = rs.getString("aptStatus");
                i++;

            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
        }

        DefaultTableModel model = new DefaultTableModel(data, columnName);

        t = new JTable(model);

        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // JTable t = new JTable(bkdata, columnName);
        JScrollPane sp = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //t.setColumnSelectionAllowed(false);
        //t.setRowSelectionAllowed(true);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //Panel 1 for welcome message
        JPanel myPanel1 = new JPanel();
        myPanel1.add(sp);

        String message = "View your upcoming appointments";
        String b1 = ("Confirm appointment");
        String b2 = ("Cancel appointment");
        String b3 = ("Logout");

        mainPanel(message, b1, b2, b3, myPanel1);

    }

    //set free and booked slots
    public void setAvailability() {

        JPanel myPanel1 = new JPanel();

        String message = "Set your availability";
        String b1 = ("Set as available");
        String b2 = ("Set as not available ");
        String b3 = ("Logout");

        // references: https://toedter.com/jcalendar/
        chooser = new JDateChooser();
        chooser.setLocale(Locale.UK);
        JLabel selectTime = new JLabel("Select a time");
        times = new JComboBox<String>(stimes);

        myPanel1.add(chooser);
        myPanel1.add(selectTime);
        myPanel1.add(times);
        mainPanel(message, b1, b2, b3, myPanel1);

    }

    //set book as completed or no show
    public void setBkStatus(ResultSet rs) {

        //Panel 1 for welcome message
        String[] columnName = {"pEmail", "fdate", "ftime", "cEmail", "aptStatus"};

        //    myModel.showCustBookings();
        //dbStatus(email);
        try {
            int i = 0;
            // Loop through the result set

            while (rs.next()) {

                setdata[i][0] = rs.getString("pEmail");
                setdata[i][1] = rs.getString("fdate");
                setdata[i][2] = rs.getString("ftime");
                setdata[i][3] = rs.getString("cEmail");
                setdata[i][4] = rs.getString("aptStatus");
                i++;

            }

        } catch (SQLException e) {
            System.out.println("SQL Exception:");
        }

        DefaultTableModel model = new DefaultTableModel(setdata, columnName);

        t = new JTable(model);

        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // JTable t = new JTable(bkdata, columnName);
        JScrollPane sp = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //t.setColumnSelectionAllowed(false);
        //t.setRowSelectionAllowed(true);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // sp.setPreferredSize(getMaximumSize());
        //Panel 1 for welcome message
        JPanel myPanel1 = new JPanel();

        String message = "Set status of the appointment";
        String b1 = ("Completed");
        String b2 = ("No-Show");
        String b3 = ("Logout");

        myPanel1.add(sp);

        mainPanel(message, b1, b2, b3, myPanel1);

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
        pController = new ProviderController();
        btn1.addActionListener(pController);
        btn2.addActionListener(pController);
        btn3.addActionListener(pController);

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
