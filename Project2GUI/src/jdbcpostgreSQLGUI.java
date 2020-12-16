import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
//import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
 */
import javax.swing.table.DefaultTableModel;

public class jdbcpostgreSQLGUI 
{
	static Connection conn;
	
	/**
	 * Closes the connection to the database.
	 */
	public static void closeConnection()
	{
		try {
			conn.close();
			System.out.println("Connection closed.");
		} 
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
		}
	}
	
	/**
	 * Given an SQL statement, queries the database using the existing connection
	 * @param sqlStatement the SQL statement
	 * @return the ResultSet from the query
	 */
	public static ResultSet queryDatabaseTable(String sqlStatement)
	{
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery(sqlStatement);
	        return result;
		} 
		catch (Exception e){
			JOptionPane.showMessageDialog(null,"Error accessing Database.");
			return null;
		}
	}
	/**
	 * Called from dbLogin, attempts to connect to database
	 * @param user username inputted by user
	 * @param pswd password inputted by user
	 */
	public static void connectToDatabase(String user, String pswd)
	{
		//Building the connection
		conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/db911_group15_project2?allowMultiQueries=true";
	    	conn = DriverManager.getConnection(url, user, pswd);
		} 
		catch (Exception e) {
			System.out.println("Could not login to database");
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		new QueryGUI();
	}
	
	/**
	 * Launch the login application.
	 * After successful login, QueryGUI will begin.
	 */
	public static void main(String args[]) 
	{
		new dbLogin();
	}
}
