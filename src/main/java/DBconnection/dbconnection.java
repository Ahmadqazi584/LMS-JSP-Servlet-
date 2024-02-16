package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
	
	private dbconnection() {
    }

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "javatesting");
                System.out.println("Connection created successfully...");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception : " + ex);
        }

        return connection;
    }
    
}
