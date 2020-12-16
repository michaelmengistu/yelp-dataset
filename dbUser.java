/******************************************************************************************************************
 * Author:		Joshua Bailey Gonzalez
 * Project:		CSCE 315 - Project 2 (Phase 3)
 * File:		dbUser.java
 * Date:		September 29, 2020
 * 
 * Purpose:		This file contains the dbUser class definition. The dbUser class
 * 				is intended to abstract the interactions between a database user
 * 				and the database it's connected to.
 * 
 * Members:
 * 		> username is the host name														(private, String)
 * 		> password is the database name 												(private, String)
 * 		> dbTarget is the connection to the database									(private, Connection)
 * 		> dbQueryResult is the last query result made by the user						(private, ResultSet)
 * 
 * Methods:
 * 		> dbUser returns an initialized dbUser object									(public, dbUser)
 * 		> setUsername sets the dbUser's username										(public, void)
 * 		> setPassword sets the dbUSer's password										(public, void)
 * 		> getQueryResult gets the result of the last query made by dbUser.				(public, ResultSet)
 * 		> establishConnection establishes a connnection between dbUser and the dbTarget (public, void)
 * 		> sendQuery queries the dbTarget database with an SQL query						(public, void)
 * 		> closeConnection closes the connection between a dbUser and the dbTarget		(public, void)
 * 
*******************************************************************************************************************/

import java.sql.*;

public class dbUser {
	private String username;
	private String password;
	private database dbTarget;
	private ResultSet dbQueryResult;

	/*
	 * Method: 		dbUser
	 * Returns:		dbUser
	 * 
	 * Purpose: 	Returns a dbUser object with an empty String for
	 * 				the username and password. Also, dbUser initializes 
	 * 				the database object.
	 * 
	 */
	public dbUser() {
		this.username = this.password ="";
		this.dbTarget = new database();
		this.dbQueryResult = null;
	}
	
	/*
	 * Method: 		setUsername
	 * Returns:		void
	 * 
	 * Purpose: 	Setter for the dbUser's username.
	 * 
	 */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	
	/*
	 * Method: 		setPassword
	 * Returns:		void
	 * 
	 * Purpose: 	Setter for the dbUser's password.
	 * 
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	/*
	 * Method: 		getQueryResult
	 * Returns:		ResultSet
	 * 
	 * Purpose: 	Getter for the dbUser's last query result.
	 * 
	 */
	public ResultSet getQueryResult(){
		return this.dbQueryResult;
	}

	/*
	 * Method: 		establishConnection
	 * Returns:		void
	 * 
	 * Purpose: 	Establishes a connection between the dbUser
	 * 				and the dbUser's database.
	 * 
	 */
	public void establishConnection() {
		
		boolean unableToConnect = false;
		
		// checking if user can connect to database
		if (this.username == "") {
			System.out.println("ERROR: USERNAME IS NULL. CANNOT CONNECT TO DATABASE WITH NULL USERNAME.");
			unableToConnect = true;
		}
		if (this.password == "") {
			System.out.println("ERROR: PASSWORD IS NULL. CANNOT CONNECT TO DATABASE WITH NULL PASSWORD.");
			unableToConnect = true;
		}
		
		if (unableToConnect) {
			return;
		}
		
		// if username and password are not null, then attempt to connect to database
		this.dbTarget.connectToDatabase(this.username, this.password);
	
	}
	
	/*
	 * Method: 		sendQuery
	 * Returns:		void
	 * 
	 * Purpose: 	Sends a SQL query to the dbUser's database.
	 * 
	 */
	public void sendQuery(String sqlQuery) {
		this.dbQueryResult = dbTarget.query(sqlQuery);
	}
	
	/*
	 * Method: 		closeConnection
	 * Returns:		void
	 * 
	 * Purpose: 	Closes the dbUser's connection to the the dbUser's database.
	 * 
	 */
	public void closeConnection() {
		this.dbTarget.closeConnection();
	}
}