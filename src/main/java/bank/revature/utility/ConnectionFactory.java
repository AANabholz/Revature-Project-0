package bank.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	
// Variables
	private static final String URL = System.getenv("DATABASE_ENDPOINT_URL");
	private static final String USERNAME = System.getenv("DATABASE_USERNAME");
	private static final String PASSWORD = System.getenv("DATABASE_PASSWORD");
	
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connected to the database...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
