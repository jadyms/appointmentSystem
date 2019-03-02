
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {

    String[][] data = new String[20][4];
    CustomerView customerView;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public db() {
        connect(); //connection with the db
    }

    //connect to the "Star" db
    public void connect() {
        try {
            // Load the database driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String dbServer = "jdbc:mysql://localhost:3306/star";
            String user = "root";
            String password = "";

            // Get a connection to the database
            conn = DriverManager.getConnection(dbServer, user, password);

            // Get a statement from the connection
            stmt = conn.createStatement();

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

    //execute query that insert data into db
    public void executeInsert(String query) {
        try {

            stmt.execute(query);
       
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
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

    
    //execute query that displays data into db
    public boolean executeQuery(String query) {
        try {
            rs = stmt.executeQuery(query);

            // This code is telling us whether we have any results
            // in our database or not
            if (rs.isBeforeFirst()) {
                return true;
           
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
    
    //execute query that returns Rs data into db
  public ResultSet newQuery(String query) {
        try {
            rs = stmt.executeQuery(query);

    
            if (rs.isBeforeFirst()) {
                return rs;
              
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Null value");
        }

        return null;
    }
  
}
