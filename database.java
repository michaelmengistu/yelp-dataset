/******************************************************************************************************************
 * Author:		Joshua Bailey Gonzalez
 * Project:		CSCE 315 - Project 2 (Phase 3)
 * File:		database.java
 * Date:		September 29, 2020
 * 
 * Purpose:		This file contains the database class definition. The database class
 * 				is intended to be a member of the dbUser class as every user will
 * 				want to connect to a database for our application.
 * 
 * Members:
 * 		> dbHost is the host name													(private, String)
 * 		> dbName is the database name 												(private, String)
 * 		> dbConnection is the connection to the database							(private, Connection)
 * 
 * Methods:
 * 		> database returns an initialized database object							(public, database)
 * 		> getConnection returns the connection to the database						(public, Connection)
 * 		> connectToDatbase connects to the database via the dbConnection member		(public, void)
 * 		> closeConnection closes the current connection to the database				(public, void)
 * 		> query sends a SQL query to the connected database and returns the result  (public, ResultSet)
 * 
*******************************************************************************************************************/

import java.sql.*;

public class database {
	
	private final String dbHost = "csce-315-db.engr.tamu.edu";
	private final String dbName = "db911_group15_project2";
	
	private Connection dbConnection;
	
	/*
	 * Method: 		database
	 * Returns:		database
	 * 
	 * Purpose: 	Returns a database object with a null database connection
	 * 
	 */
	public database() {
		this.dbConnection = null;
	}
	
	/*
	 * Method: 		getConnection
	 * Returns:		Connection
	 * 
	 * Purpose: 	Returns the current connection to the database.
	 * 
	 */
	public Connection getConnection() {
		return this.dbConnection;
	}
	
	/*
	 * Method: 		connectToDatabase
	 * Returns:		void
	 * 
	 * Purpose: 	Connects to the to database with host dbHost and name
	 * 				dbName via dbConnection.
	 * 
	 */
	public void connectToDatabase(String username, String password) {
		
		// only connect if there is not an existing connection
		if (this.dbConnection == null) {
			try {
				this.dbConnection = DriverManager.getConnection(
				          "jdbc:postgresql://" + this.dbHost + "/" + this.dbName, username, password);
			}
			catch (Exception e) {
				e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			}
		}
		
		else {
			System.out.println("ERROR: DATABASE CONNECTION ALREADY EXISTS. CANNOT CONNECT TO DATABASE WITH EXISTING CONECTION.");
		}
	}
	
	/*
	 * Method: 		closeConnection
	 * Returns:		void 
	 * 
	 * Purpose: 	Closes the database connection to dbHost and dbName.
	 * 				Sets dbConnection back to null if successful.
	 * 
	 */
	public void closeConnection() {
		
		// can only close a database connection if there is an existing one
		if (this.dbConnection != null) {
			try {
		      this.dbConnection.close();
		    } 
			catch(Exception e) {
				e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
		    }
		}
		
		else {
			System.out.println("ERROR: NO EXISTING DATABASE CONNECTION. CANNOT CLOSE NULL CONNECTION.");
		}
	}
	
	/*
	 * Method: 		query
	 * Returns:		ResultSet
	 * 
	 * Purpose: 	Returns the result of an SQL query passed to 
	 * 				the database via the dbConnection.
	 * 
	 */
	public ResultSet query(String sqlQuery) {
		
		Statement stm = null;
		ResultSet result = null;
		
		// can only query a connected database
		if (dbConnection != null) {
			try {
				stm = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					    ResultSet.CONCUR_READ_ONLY);
			
			} 
			catch (SQLException e) {
				e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			}
			
			try {
				result = stm.executeQuery(sqlQuery);
			} 
			catch (SQLException e) {
				e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			}
		}
		
		
		return result;
	}
}