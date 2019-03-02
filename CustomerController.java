
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
Controller of the customer area
 */
public class CustomerController implements ActionListener, ListSelectionListener {

    //Global elements
    Model myModel; //DB and Query
    LoginView loginView;
    CustomerView customerView;
    LogoutView logoutView;
    Controller mainController;
  

    public CustomerController() {
        //loginView = new LoginView(this);
        myModel = new Model();
        customerView = new CustomerView(this);
        loginView = new LoginView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Book an appointment method
        if (e.getActionCommand().equals("Book an appointment")) {
            //Display free slots table
   ResultSet rs = myModel.showAvailableSlots();
            customerView.bookApt(rs);


        /*A table with the bookings is displayed
        /Customer select a free slot
        /Hit book an appointment     
        */
        } else if (e.getActionCommand().equals("Book appointment")) {
      
                int row = customerView.t.getSelectedRow();
        String loc = (String) customerView.t.getValueAt(row, 0);
        String provider = (String) customerView.t.getValueAt(row, 1);
        String pEmail = (String) customerView.t.getValueAt(row, 2);
        String fdate = (String) customerView.t.getValueAt(row, 3);
        String ftime = (String) customerView.t.getValueAt(row, 4);  
        String message = ("Location: "+loc + "\r\n Provider: " + provider+ "\r\n Provider Email: " +pEmail+ " \r\nDate: " +fdate+ " \r\nTime: " +ftime);
        
         int q = JOptionPane.showConfirmDialog(null, message, "Confirm your information ", JOptionPane.YES_NO_OPTION);
         
         if (q==0){
             myModel.bookingApt(fdate, ftime, pEmail, loginView.getEmail());
              JOptionPane.showMessageDialog(null, "Your booking has been placed.\r\n You can view it on your upcoming appointments", "Bookings", JOptionPane.DEFAULT_OPTION);
             
             System.out.println(message);
            //customerView.bookApt(loginView.getEmail());
    
             
         }
                
       
            //CODE TO UPDATE DB WITH CUSTOMER EMAIL
        
        //Check appointments already made
        } else if (e.getActionCommand().equals("Check your appointments")) {

            
            ResultSet rs = myModel.showCustBookings(loginView.getEmail());
            customerView.checkApt(rs);

            //Place a complaint
        } else if (e.getActionCommand().equals("Suggestions and Complaints")) {
            ResultSet rs = myModel.selectProvider();
            customerView.setComplaint(rs);
            
            //Logout
        } else if (e.getActionCommand().equals("Logout")) {
            logoutView = new LogoutView();

            int r = logoutView.logout();
            //Would you like to logout? YES
            if (r == 0) {
        try {
                    myModel.newdb.stmt.close();
                    myModel.newdb.rs.close();
                    myModel.newdb.conn.close();
                    //Would you like to logout? NO
                } catch (SQLException ex) {
                    Logger.getLogger(ProviderController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(r);

                //Would you like to logout? NO
            } else if (r == 1) {
                //DO NOTHINHG, JUST CLOSE THE DIALOG BOX

            }
            
        } else if (e.getActionCommand().equals("Filter location")) {
            String text = customerView.filterText.getText();
        if (text.length() == 0) {
          customerView.sorter.setRowFilter(null);
        } else {
          customerView.sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }
        
      }else if (e.getActionCommand().equals("Filter Provider")) {
            String text = customerView.filterText2.getText();
        if (text.length() == 0) {
          customerView.sorter.setRowFilter(null);
        } else {
          customerView.sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }
        }else if (e.getActionCommand().equals("Place complaint")) {
                      int row = customerView.t.getSelectedRow();
        
        String pEmail = (String) customerView.t.getValueAt(row, 2);
        customerView.complaint.getText();
     
    
           
            //Save complaint into db
           myModel.setComplaint(pEmail, loginView.getEmail(), customerView.complaint.getText());
         JOptionPane.showMessageDialog(null, "Your complaint has been placed", "Complaint", JOptionPane.DEFAULT_OPTION);
        //customerView.complaint.setText("");
        
            
        } else if (e.getActionCommand().equals("Cancel Appointment")) {
            
              int row = customerView.checkAptTable.getSelectedRow();
      
       
        String pEmail = (String) customerView.checkAptTable.getValueAt(row, 2);
        String fdate = (String) customerView.checkAptTable.getValueAt(row, 0);
        String ftime = (String) customerView.checkAptTable.getValueAt(row, 1);      
        String cEmail = (String) customerView.checkAptTable.getValueAt(row, 4);
        String message = ("Provider Email: " +pEmail+ " \r\nDate: " +fdate+ " \r\nTime: " +ftime+ " \r\nCustomer: " +cEmail);
        
         int q = JOptionPane.showConfirmDialog(null, message, "Would you like to cancel this appointment?", JOptionPane.YES_NO_OPTION);
         
         if (q==0){
             myModel.cancelApt(fdate, ftime, pEmail, cEmail);
              JOptionPane.showMessageDialog(null, "This booking has been cancelled.\r\n", "Bookings", JOptionPane.DEFAULT_OPTION);
       
         }
             
        
                 
            
        
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       /*
        int row = customerView.t.getSelectedRow();
        String loc = (String) customerView.t.getValueAt(row, 0);
        String provider = (String) customerView.t.getValueAt(row, 1);
        String pEmail = (String) customerView.t.getValueAt(row, 2);
        String fdate = (String) customerView.t.getValueAt(row, 3);
        String ftime = (String) customerView.t.getValueAt(row, 4);  
        String message = ("Location: "+loc + " Provider: " + provider+ " Provider Email: " +pEmail+ " Date: " +fdate+ " Time: " +ftime);
        
         int q = JOptionPane.showConfirmDialog(null, message, "Confirm your information ", JOptionPane.YES_NO_OPTION);
         
         if (q==0){
             
             System.out.println(message);
             
         }
         /*
         int row2 = customerView.complaintTable.getSelectedRow();
        String loc2 = (String) customerView.complaintTable.getValueAt(row2, 0);
        String provider2 = (String) customerView.complaintTable.getValueAt(row2, 1);
        String pEmail2 = (String) customerView.complaintTable.getValueAt(row2, 2);
        String fdate2 = (String) customerView.complaintTable.getValueAt(row2, 3);
        String ftime2 = (String) customerView.complaintTable.getValueAt(row2, 4);  
        String message2 = ("Location: "+loc2 + " Provider: " + provider2+ " Provider Email: " +pEmail2+ " Date: " +fdate2+ " Time: " +ftime2);
        
         int r = JOptionPane.showConfirmDialog(null, message2, "Confirm your information ", JOptionPane.YES_NO_OPTION);
         
         if (q==0){
             
             System.out.println(message2);
             
         }
       */
       
     
        }
        }

       
    
    

