
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
Controller of the Provider area 
 */
public class ProviderController implements ActionListener {

    //Global Elements
    Model myModel;
    LoginView loginView;
    CustomerView customerView;
    LogoutView logoutView;
    ProviderView providerView;

    public ProviderController() {
        myModel = new Model();
        providerView = new ProviderView();
        loginView = new LoginView();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //View upcoming appointments
        if (e.getActionCommand().equals("View upcoming appointments")) {
            ResultSet rs = myModel.showProvBookings(loginView.getEmail());
            providerView.viewApt(rs);

            //Provider confirms customer appointment
        } else if (e.getActionCommand().equals("Confirm appointment")) {

            int row = providerView.t.getSelectedRow();

            String pEmail = (String) providerView.t.getValueAt(row, 0);
            String fdate = (String) providerView.t.getValueAt(row, 1);
            String ftime = (String) providerView.t.getValueAt(row, 2);
            String cEmail = (String) providerView.t.getValueAt(row, 3);
            String message = ("Provider Email: " + pEmail + " \r\nDate: " + fdate + " \r\nTime: " + ftime + " \r\nCustomer: " + cEmail);

            int q = JOptionPane.showConfirmDialog(null, message, "Would you like to confirm this appointment?", JOptionPane.YES_NO_OPTION);

            if (q == 0) {
                myModel.confirmApt(fdate, ftime, pEmail, cEmail);
                JOptionPane.showMessageDialog(null, "This booking has been confirmed.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);

                System.out.println("Apt confirmed");
            }
            //Provider cancels appointment
        } else if (e.getActionCommand().equals("Cancel appointment")) {

            int row = providerView.t.getSelectedRow();

            String pEmail = (String) providerView.t.getValueAt(row, 0);
            String fdate = (String) providerView.t.getValueAt(row, 1);
            String ftime = (String) providerView.t.getValueAt(row, 2);
            String cEmail = (String) providerView.t.getValueAt(row, 3);
            String message = ("Provider Email: " + pEmail + " \r\nDate: " + fdate + " \r\nTime: " + ftime + " \r\nCustomer: " + cEmail);

            int q = JOptionPane.showConfirmDialog(null, message, "Would you like to cancel this appointment?", JOptionPane.YES_NO_OPTION);

            if (q == 0) {
                myModel.cancelApt(fdate, ftime, pEmail, cEmail);
                JOptionPane.showMessageDialog(null, "This booking has been cancelled.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);

            }
            System.out.println("Apt cancelled");

            //Set provider availability
        } else if (e.getActionCommand().equals("Set availability")) {

            providerView.setAvailability();

            //Set status of appointments
        } else if (e.getActionCommand().equals("Appointment Status")) {
            ResultSet rs = myModel.showProvBookings(loginView.getEmail());
            providerView.setBkStatus(rs);

            //set slot as free
        } else if (e.getActionCommand().equals("Set as available")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String fdate = dateFormat.format(providerView.chooser.getDate());
            String ftime = String.valueOf(providerView.times.getSelectedItem());
            String pEmail = loginView.getEmail();
            System.out.println(dateFormat.format(providerView.chooser.getDate()));
            String slotStatus = "A"; //Available
            myModel.setSlotAs(fdate, ftime, pEmail, slotStatus);

            System.out.println("Free " + fdate + ftime + pEmail + slotStatus);
            JOptionPane.showMessageDialog(null, "This slot has been marked as available.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);

            //set slot as not available
        } else if (e.getActionCommand().equals("Set as not available")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fdate = dateFormat.format(providerView.chooser.getDate());
            String ftime = String.valueOf(providerView.times.getSelectedItem());
            String pEmail = loginView.getEmail();
            System.out.println(dateFormat.format(providerView.chooser.getDate()));
            String slotStatus = "NA"; //Non-Available
            myModel.setSlotAs(fdate, ftime, pEmail, slotStatus);
            System.out.println("Non-Avaiable" + fdate + ftime + pEmail + slotStatus);
            JOptionPane.showMessageDialog(null, "This slot has been marked as not available.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);

            //Provider change status of appointment to completed
        } else if (e.getActionCommand().equals("Completed")) {

            int row = providerView.t.getSelectedRow();

            String pEmail = (String) providerView.t.getValueAt(row, 0);
            String fdate = (String) providerView.t.getValueAt(row, 1);
            String ftime = (String) providerView.t.getValueAt(row, 2);
            String cEmail = (String) providerView.t.getValueAt(row, 3);
            String message = ("Provider Email: " + pEmail + " \r\nDate: " + fdate + " \r\nTime: " + ftime + " \r\nCustomer: " + cEmail);

            int q = JOptionPane.showConfirmDialog(null, message, "Would you like to set as completed?", JOptionPane.YES_NO_OPTION);

            if (q == 0) {
                myModel.CompletedApt(fdate, ftime, pEmail, cEmail);
                JOptionPane.showMessageDialog(null, "This booking has been completed.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);

                System.out.println("Apt completed");
            }
            //Provider change status of appointment to cancelled
        } else if (e.getActionCommand().equals("No-Show")) {

            int row = providerView.t.getSelectedRow();

            String pEmail = (String) providerView.t.getValueAt(row, 0);
            String fdate = (String) providerView.t.getValueAt(row, 1);
            String ftime = (String) providerView.t.getValueAt(row, 2);
            String cEmail = (String) providerView.t.getValueAt(row, 3);
            String message = ("Provider Email: " + pEmail + " \r\nDate: " + fdate + " \r\nTime: " + ftime + " \r\nCustomer: " + cEmail);

            int q = JOptionPane.showConfirmDialog(null, message, "Would you like to set as no-show?", JOptionPane.YES_NO_OPTION);

            if (q == 0) {
                myModel.noShowApt(fdate, ftime, pEmail, cEmail);
                JOptionPane.showMessageDialog(null, "This booking has been marked as no-show.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);

                System.out.println("Apt no show");
            }

            //Logout              
        } else if (e.getActionCommand().equals("Logout")) {
            //call Joption pane
            logoutView = new LogoutView();
            int r = logoutView.logout();
            //Would you like to logout? YES
            if (r == 0) {
                System.exit(r);
                try {
                    myModel.newdb.stmt.close();
                    myModel.newdb.rs.close();
                    myModel.newdb.conn.close();
                    //Would you like to logout? NO
                } catch (SQLException ex) {
                    Logger.getLogger(ProviderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (r == 1) {
                //DO NOTHINHG, JUST CLOSE THE DIALOG BOX
            }

        }
    }

}
