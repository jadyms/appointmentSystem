
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class Model {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    db newdb; //Connection with the db

    public Model() {
        //Connection to the db
        newdb = new db();
    }

    //get customer email and password from login session
    public boolean customerLogin(String email, String password) {

        String query = "SELECT * FROM customers WHERE cEmail = '" + email + "' AND password = '" + password + "';";

        boolean r = newdb.executeQuery(query);

        return r;
    }

    //get provider email and password from login session
    public boolean servicePLogin(String email, String password) {

        String query = "SELECT * FROM providers WHERE pEmail = '" + email + "' AND password = '" + password + "';";

        boolean r = newdb.executeQuery(query);

        return r;
    }

    //get admin email and password from login session
    public boolean adminLogin(String email, String password) {

        String query = "SELECT * FROM admin WHERE email = '" + email + "' AND password = '" + password + "';";

        boolean r = newdb.executeQuery(query);
      
        return r;
    }

    //return providers table
    public ResultSet selectProvider() {

        String query = "SELECT * FROM providers;";
        rs = newdb.newQuery(query);
        return rs;
    }

    //return free slots to be booked
    public ResultSet showAvailableSlots() {

        String query = "SELECT p.location AS 'Location', p. fName AS 'Provider', p.pEmail as 'Provider E-mail', b.fdate AS 'Free date', b.ftime AS 'Free time'FROM bookings AS b JOIN providers AS  p ON p.pEmail = b.pEmail WHERE slotStatus = 'A';";

        rs = newdb.newQuery(query);

        return rs;

    }
    
    //return providers details
    public ResultSet setStatus(String email) {

        String query = "SELECT * FROM bookings WHERE pEmail = '" + email + "';";

        rs = newdb.newQuery(query);

        return rs;

    }

    //return customers own bookings
    public ResultSet showCustBookings(String email) {

        String query = "SELECT * FROM bookings WHERE cEmail = '" + email + "';";

        rs = newdb.newQuery(query);

        return rs;

    }
 
    //return providers own bookings
    public ResultSet showProvBookings(String email) {

        String query = "SELECT * FROM bookings WHERE pEmail = '" + email + "' AND cEmail IS NOT NULL;";

        rs = newdb.newQuery(query);

        return rs;

    }

    //register a new customer
    public void createCustomer(String fname, String lname, String mobile, String cEmail, String password) {
        System.out.println(fname + " " + lname + " " + mobile + " " + cEmail + " " + password);

        String query = "INSERT INTO customers "
                + "VALUES ('" + fname + "',"
                + " '" + lname + "',"
                + " '" + mobile + "',"
                + " '" + cEmail + "',"
                + " '" + password + "');";

        newdb.executeInsert(query);

    }

    //register a new provider
    public void createProvider(String fname, String lname, String mobile, String pmail, String password, String location, String regStatus, String admResp) {
        System.out.println(fname + " " + lname + " " + mobile + " " + pmail + " " + password + " " + location);

        String query = "INSERT INTO providers "
                + "VALUES ('" + fname + "',"
                + " '" + lname + "',"
                + " '" + mobile + "',"
                + " '" + pmail + "',"
                + " '" + password + "',"
                + " '" + location + "',"
                + " '" + regStatus + "',"
                + " '" + admResp + "');";

        newdb.executeInsert(query);

    }
    
    //creates a new admin
    public void createAdmin(String email, String password) {
              String query = "INSERT INTO admin "
                + "VALUES ( '" + email + "',"
                + password + "');";

        newdb.executeInsert(query);

    }

    //place a complaint
    public void setComplaint(String pEmail, String cEmail, String complaint) {

        String query = "INSERT INTO providers (cEmail, p_email, complaint, admResp)"
                + "VALUES ('" + cEmail + "'," + " '" + pEmail + "', " + " '" + complaint + "'," + " 'admin@admin.com');";

        newdb.executeInsert(query);

    }

    //customer book an appointment
    public void bookingApt(String fdate, String ftime, String pEmail, String cEmail) {
        System.out.println(fdate + " " + ftime + " " + pEmail + " " + cEmail + " " + "B");

        String query = "UPDATE bookings SET slotStatus='B', cEmail = '" + cEmail + "' WHERE  fdate='" + fdate + "' AND ftime ='" + ftime + "' AND pEmail='" + pEmail + "';";

        newdb.executeInsert(query);
    

    }

    //customer cancel an appointment
    public void cancelApt(String fdate, String ftime, String pEmail, String cEmail) {

        String query = "UPDATE bookings SET aptStatus='Cancelled' WHERE  fdate='" + fdate + "' AND ftime ='" + ftime + "' AND pEmail='" + pEmail + "'AND cEmail='" + cEmail + "';";

        newdb.executeInsert(query);
        System.out.println(query);
    }

    //provider set booking as no-show
    public void noShowApt(String fdate, String ftime, String pEmail, String cEmail) {

        String query = "UPDATE bookings SET aptStatus='No-Show' WHERE  fdate='" + fdate + "' AND ftime ='" + ftime + "' AND pEmail='" + pEmail + "';";

        newdb.executeInsert(query);

    }

    //provider set booking as completed
    public void CompletedApt(String fdate, String ftime, String pEmail, String cEmail) {

        String query = "UPDATE bookings SET aptStatus='Completed' WHERE  fdate='" + fdate + "' AND ftime ='" + ftime + "' AND pEmail='" + pEmail + "';";

        newdb.executeInsert(query);

    }

    //provider set booking as confirmed
    public void confirmApt(String fdate, String ftime, String pEmail, String cEmail) {

        String query = "UPDATE bookings SET aptStatus='Confirmed' WHERE  fdate='" + fdate + "' AND ftime ='" + ftime + "' AND pEmail='" + pEmail + "';";

        newdb.executeInsert(query);

    }

    //provider set slot as free or booked
    public void setSlotAs(String fdate, String ftime, String pEmail, String slotStatus) {

        String query = "INSERT INTO bookings VALUES ('" + pEmail + "','" + fdate + "','" + ftime + "','" + slotStatus + "',NULL,'Pending',NULL);";
        newdb.executeInsert(query);

    }

}
