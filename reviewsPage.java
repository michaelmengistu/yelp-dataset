/******************************************************************************************************************
 * Author:		Joshua Bailey Gonzalez
 * Project:		CSCE 315 - Project 2 (Phase 3)
 * File:		reviewsPage.java
 * Date:		September 29, 2020
 * 
 * Purpose:		This file contains the reviewsPage class definition. The reviewsPage 
 * 				list all reviews and relevant information for each business. However,
 * 				a reviewsPage object can only be created with a business_id which provides
 * 				a weak form of error handling as the only business IDs passed to this class
 * 				will be those queried from the main page.
 * 
 * Members:
 * 		> user
 * 		> business_id
 * 		> reviewsTable
 * 		> writeButton
 * 		> REVIEWS_PAGE_TITLE
 * 		> REVIEWS_PAGE_WIDTH
 * 		> REVIEWS_PAGE_HEIGHT
 * 		> MAX_VISIBLE_REVIEWS
 * 		> START_OF_REVIEW_DATA
 * 		> REVIEWS_COLUMN_COUNT
 * 
 * Methods:
 * 
 * 
 * 
*******************************************************************************************************************/

import java.sql.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// TODO: Place things in nicely

public class reviewsPage extends JFrame{

	public static dbUser user;
	private String business_id;
	
	private JTable reviewsTable;
	private JButton writeButton;
	
	private static final String REVIEWS_PAGE_TITLE = "REVIEWS";
	private static final int REVIEWS_PAGE_WIDTH = 800;
	private static final int REVIEWS_PAGE_HEIGHT = 600;
	
	private static final int MAX_VISIBLE_REVIEWS = 45;
	private static final int START_OF_REVIEW_DATA = 4;
	private static final int REVIEWS_COLUMN_COUNT = 9;
	
	public reviewsPage(String business_id) throws SQLException {
		this.business_id = business_id;
		
		setLayout(new FlowLayout());
		

		reviewsTable = new JTable();
		
		String columns[] = {"STARS", "USEFUL", "FUNNY", "COOL", "TEXT", "DATE", };
		
		user.sendQuery("SELECT * FROM reviews WHERE business_id LIKE \'" + business_id + "\';");
		ResultSet queryResult = user.getQueryResult();
		
		DefaultTableModel dftm  = new DefaultTableModel(columns, 0);
		if (queryResult != null) {
		
			int rowNum = 0;
			while (queryResult.next() && rowNum < MAX_VISIBLE_REVIEWS) {
				
				// TODO: fix this with for loops?
				Object[] row = {queryResult.getString(4),queryResult.getString(5),queryResult.getString(6),
						queryResult.getString(7), queryResult.getString(8), queryResult.getString(9) };
				
				dftm.addRow(row);
				
				rowNum++;
			}
			
			reviewsTable.setModel(dftm);
		}
		
		reviewsTable.setPreferredScrollableViewportSize(new Dimension(600, 500));
		reviewsTable.setFillsViewportHeight(true);
		
		writeButton = new JButton("Write Reviews to File");
		writeButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        writeReviewsToFile();
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(reviewsTable);
		add(scrollPane);
		
		add(writeButton);
		
		initializePage();
	}
	
	
	
	private void initializePage() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(REVIEWS_PAGE_TITLE);
		setSize(REVIEWS_PAGE_WIDTH, REVIEWS_PAGE_HEIGHT);
		
		setResizable(false);
		setVisible(true);
	}
	
	private void writeReviewsToFile() {
		
		File file = new File("ReviewsOf-" + business_id + ".csv");
		FileWriter fWriter = null;
		
		try {
			fWriter = new FileWriter(file);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintWriter pWriter = new PrintWriter(fWriter);
		
		ResultSet queryResult = user.getQueryResult();
		if (queryResult != null) {

			try {
				
				// make sure query result starts at first row
				queryResult.first();
				
				// cycle through all queries
				while (queryResult.next()) {
					
					// cycle through all the columns with relevant data 
					for(int colNum = START_OF_REVIEW_DATA; colNum < REVIEWS_COLUMN_COUNT; colNum++) {
						
						// write data
						pWriter.print(queryResult.getString(colNum));
						
						// if its the last column add a newline, otherwise write a comma
						if (colNum == REVIEWS_COLUMN_COUNT - 1) {
							pWriter.print("\n");
						}
						else {
							pWriter.print(",");
						}
						
					}
				}
			} 
			catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		
		pWriter.close();
	}
	

	public static void main(String[] args) throws SQLException {
		
		user = new dbUser();
		user.setUsername("jbgonzalez018");
		user.setPassword("127007661");
		
		user.establishConnection();
		
		reviewsPage GUI = new reviewsPage("El4FC8jcawUVgw_0EIcbaQ");
		
		user.closeConnection();
	}
}
