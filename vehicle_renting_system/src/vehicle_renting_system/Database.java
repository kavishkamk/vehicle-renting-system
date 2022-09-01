package vehicle_renting_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	//mySql  work bench details
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/vehicle_renting_system";
	private static final String USER          = "root";
	private static final String PASSWORD      = "KV727madhu1999";
	
	// get connection to the database
	public static Connection getDBConnection() throws SQLException{
		
		Connection connection = null;
		
		try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
		
		try {
			connection = DriverManager.getConnection(DB_CONNECTION, USER, PASSWORD);
			System.out.println("Connection Success with Database");
			return connection;
		}
		catch (SQLException exc) {
			System.out.println(exc.getMessage());
		}
		
		return connection;	
	}

}
