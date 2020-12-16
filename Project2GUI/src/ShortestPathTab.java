import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class ShortestPathTab {
	
	private HashMap<String, ArrayList<String[]>> adjacencyLists;
	private JLayeredPane ShortestPathPane;
	private JTextField nameField1;
	private JTextField nameField2;
	private JTextField stateField1;
	private JTextField stateField2;
	private JTextField cityField1;
	private JTextField cityField2;
	private JTextField zipField1;
	private JTextField zipField2;
	private JPanel panel_3;
	
	/**
	 * Create the application.
	 */
	public ShortestPathTab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ShortestPathPane = new JLayeredPane();
		ShortestPathPane.setOpaque(true);
		ShortestPathPane.setBounds(100, 100, 1400, 624);
		ShortestPathPane.setBackground(SystemColor.control);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 10, 402, 48);
		ShortestPathPane.add(panel);
		
		JLabel label = new JLabel("Group 15 Database GUI");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 89, 402, 140);
		ShortestPathPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName1 = new JLabel("Business Name");
		lblName1.setBounds(10, 10, 113, 17);
		lblName1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblName1);
		
		nameField1 = new JTextField();
		nameField1.setBounds(10, 37, 284, 27);
		panel_1.add(nameField1);
		nameField1.setColumns(10);
		
		JLabel lblState1 = new JLabel("State");
		lblState1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState1.setBounds(304, 12, 88, 13);
		panel_1.add(lblState1);
		
		stateField1 = new JTextField();
		stateField1.setBounds(304, 37, 88, 27);
		panel_1.add(stateField1);
		stateField1.setColumns(10);
		
		JLabel lblCity1 = new JLabel("City");
		lblCity1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity1.setBounds(10, 74, 46, 17);
		panel_1.add(lblCity1);
		
		cityField1 = new JTextField();
		cityField1.setBounds(10, 103, 183, 27);
		panel_1.add(cityField1);
		cityField1.setColumns(10);
		
		JLabel lblZip1 = new JLabel("Zip");
		lblZip1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZip1.setBounds(212, 78, 46, 17);
		panel_1.add(lblZip1);
		
		zipField1 = new JTextField();
		zipField1.setColumns(10);
		zipField1.setBounds(209, 103, 183, 27);
		panel_1.add(zipField1);
		
		
		JButton btnCloseConn = new JButton("Close");
		btnCloseConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdbcpostgreSQLGUI.closeConnection();
				QueryGUI.disposeFrame();
			}
		});
		btnCloseConn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCloseConn.setBounds(788, 520, 85, 21);
		ShortestPathPane.add(btnCloseConn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(471, 89, 402, 140);
		ShortestPathPane.add(panel_2);
		
		JLabel lblName2 = new JLabel("Business Name");
		lblName2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName2.setBounds(10, 10, 113, 17);
		panel_2.add(lblName2);
		
		nameField2 = new JTextField();
		nameField2.setColumns(10);
		nameField2.setBounds(10, 37, 284, 27);
		panel_2.add(nameField2);
		
		JLabel lblState2 = new JLabel("State");
		lblState2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState2.setBounds(304, 12, 88, 13);
		panel_2.add(lblState2);
		
		stateField2 = new JTextField();
		stateField2.setColumns(10);
		stateField2.setBounds(304, 37, 88, 27);
		panel_2.add(stateField2);
		
		JLabel lblCity2 = new JLabel("City");
		lblCity2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity2.setBounds(10, 74, 46, 17);
		panel_2.add(lblCity2);
		
		cityField2 = new JTextField();
		cityField2.setColumns(10);
		cityField2.setBounds(10, 101, 183, 27);
		panel_2.add(cityField2);
		
		zipField2 = new JTextField();
		zipField2.setColumns(10);
		zipField2.setBounds(209, 101, 183, 27);
		panel_2.add(zipField2);
		
		JLabel lblZip2 = new JLabel("Zip");
		lblZip2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZip2.setBounds(209, 78, 46, 17);
		panel_2.add(lblZip2);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(788, 245, 85, 21);
		ShortestPathPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (panel_3.isVisible())
		    		panel_3.setVisible(false);
		        submitBtnClicked();
		    }
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(22, 297, 856, 213);
		panel_3.setLayout(null);
		panel_3.setVisible(false);
		ShortestPathPane.add(panel_3);
		
	}
	
	/**
	 * helper function for returning the viewable tab pane
	 * @return JLayeredPane ShortestPathPane
	 */
	public JLayeredPane getShortestPathTab() {
		return ShortestPathPane;
	}
	
	/**
	 * Needs the file which holds the edges of the graph.
	 */
	private void submitBtnClicked() {
		ArrayList<String[]> E = new ArrayList<String[]>();
		try {
			Scanner scanEdges = new Scanner(new File("graphreviews.csv"));
			scanEdges.nextLine(); // get rid of header row
			while (scanEdges.hasNextLine()) {
				String[] parsedData = scanEdges.nextLine().split(",");
				E.add(parsedData);
			}
			scanEdges.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// will populate HashMap adjacencyLists, needed for the breadth first search
		createAdjacencyLists(E);
		
		String queryBusiness1 = configureQuery1();
		String queryBusiness2 = configureQuery2();
		
		String b_id1 = "", b_id2 = "";
		ResultSet result1 = jdbcpostgreSQLGUI.queryDatabaseTable(queryBusiness1);
		try {
			result1.next();
			b_id1 = result1.getString(1);
		} catch (SQLException e) { e.printStackTrace(); }
		
		ResultSet result2 = jdbcpostgreSQLGUI.queryDatabaseTable(queryBusiness2);
		try {
			result2.next();
			b_id2 = result2.getString(1);
		} catch (SQLException e) { e.printStackTrace(); }
		
		// breadth first search algorithm, instantiate variables for the class
		ShortestPathBFS bfs = new ShortestPathBFS(adjacencyLists, b_id1, b_id2);
		// get shortest distance will return a path of vertices (business ids)
		ArrayList<String> businessIdPath = bfs.getShortestDistance(); 
		// gets the user ID path given the business ID path
		ArrayList<String> userIdPath = createUserIdPath(businessIdPath); 
		
		// will query the database for each of the id's in the lists and return a new ArrayList of names
		ArrayList<String> businessNames = queryDatabaseBusinesses(businessIdPath);
		ArrayList<String> userNames = queryDatabaseUsers(userIdPath);
		
		formatResults(businessNames, userNames);
	}
	
	/**
	 * formats the query string for the first business
	 * @return query string
	 */
	private String configureQuery1() {
		String name, state, city, zip;
		boolean add_and = false;
		
		String query = "SELECT business_id FROM business WHERE ";
		
		name = nameField1.getText(); 
		city = cityField1.getText();
		state = stateField1.getText();
		zip = zipField1.getText();
		
		if (name != null && !name.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "name LIKE \'%" + name + "%\' ";
		}
		if (city != null && !city.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "city LIKE \'%" + city + "%\' ";
		}
		if (state != null && !state.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "state LIKE \'%" + state.toUpperCase() + "%\' ";
		}
		if (zip != null && !zip.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "postal_code LIKE \'%" + zip + "%\' ";
		}
		
		return query;
	}
	
	/**
	 * formats the query string for the second business
	 * @return query string
	 */
	private String configureQuery2() {
		String name, state, city, zip;
		boolean add_and = false;
		
		String query = "SELECT business_id FROM business WHERE ";
		
		name = nameField2.getText(); 
		city = cityField2.getText();
		state = stateField2.getText();
		zip = zipField2.getText();
		
		if (name != null && !name.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "name LIKE \'%" + name + "%\' ";
		}
		if (city != null && !city.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "city LIKE \'%" + city + "%\' ";
		}
		if (state != null && !state.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "state LIKE \'%" + state.toUpperCase() + "%\' ";
		}
		if (zip != null && !zip.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "postal_code LIKE \'%" + zip + "%\' ";
		}
		
		return query;
	}
	
	/**
	 * converts an ArrayList of businessIds to businessNames by querying the database
	 * @param businessIdList
	 * @return the ArrayList of names
	 */
	private ArrayList<String> queryDatabaseBusinesses(ArrayList<String> businessIdList) {
		ArrayList<String> names = new ArrayList<String>();
		
		for (int i = 0; i < businessIdList.size(); i++) {
			ResultSet rs = jdbcpostgreSQLGUI.queryDatabaseTable(
					"SELECT name FROM business WHERE business_id = \'" + businessIdList.get(i) + "\'");
			try {
				rs.next();
				names.add(rs.getString(1));
			} 
			catch (SQLException e) { e.printStackTrace(); }
		}
		
		return names;
	}
	
	/**
	 * converts an ArrayList of userIds to userNames by querying the database
	 * @param userIdList
	 * @return the ArrayList of names
	 */
	private ArrayList<String> queryDatabaseUsers(ArrayList<String> userIdList) {
		ArrayList<String> names = new ArrayList<String>();
		
		for (int i = 0; i < userIdList.size(); i++) {
			ResultSet rs = jdbcpostgreSQLGUI.queryDatabaseTable(
					"SELECT name FROM users WHERE user_id = \'" + userIdList.get(i) + "\'");
			try {
				rs.next();
				names.add(rs.getString(1));
			} 
			catch (SQLException e) { e.printStackTrace(); }
		}
		
		return names;
	}
	
	/**
	 * Adds labels and sets panel to visible to display results for the shortest path
	 * @param businessNames - ArrayList of business names for shortest path
	 * @param userNames - ArrayList of user names for shortest path
	 */
	private void formatResults(ArrayList<String> businessNames, ArrayList<String> userNames) {
		JLabel lblBusinessList = new JLabel("Business Path :: ");
		lblBusinessList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBusinessList.setBounds(10, 10, 200, 20);
		panel_3.add(lblBusinessList);
		
		String blist = "";
		for (int b = 0; b < businessNames.size(); b++)
			blist += businessNames.get(b) + "    ";
		
		JLabel businessList = new JLabel(blist);
		businessList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		businessList.setBounds(10, 40, 836, 20);
		panel_3.add(businessList);
		
		JLabel lblUserList = new JLabel("User Path :: ");
		lblUserList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserList.setBounds(10, 116, 200, 20);
		panel_3.add(lblUserList);
		
		String ulist = "";
		for (int u = 0; u < userNames.size(); u++)
			ulist += userNames.get(u) + "    ";
		
		JLabel userList = new JLabel(ulist);
		userList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userList.setBounds(10, 146, 836, 20);
		panel_3.add(userList);
		panel_3.setVisible(true);
	}
	
	/**
	 * @param ArrayList of business vertices for the shortest path
	 * @return the ArrayList of edges (user ids) corresponding to the vertices.
	 */
	private ArrayList<String> createUserIdPath(ArrayList<String> businessIdPath) {

		ArrayList<String> userIdPath = new ArrayList<String>();
		
		for (int i = 0; i < businessIdPath.size() - 1; i++) {
			String v1 = businessIdPath.get(i);
			String v2 = businessIdPath.get(i + 1);
			
			ArrayList<String[]> v2List = adjacencyLists.get(v1);
			String userId = "";
			for (int v = 0; v < v2List.size(); v++) {
				if (v2List.get(v)[0].equals(v2)) {
					userId = v2List.get(v)[1];
					break;
				}
			}
			userIdPath.add(userId);
		}
		System.out.println("User Path is ::");
		for(int u = 0; u < userIdPath.size(); u++) {
			System.out.print(userIdPath.get(u) + "   ");
		}
		System.out.println();
		return userIdPath;
	}
	
	/**
	 * Populates the hashmap adjacencyLists. No return
	 * @param E the ArrayList of edges, where the 0th item is the edge value (user id), 
	 * and the 1st and 2nd values are the vertices (business id)
	 */
	private void createAdjacencyLists(ArrayList<String[]> E) {
	    // Adjacency List is a hash map of <String, ArrayList>.
	    // The key string is the business ID for V1
	    // Where each element in the ArrayList is String[2]
	    // String[0] -> the edge's destination (business ID for V2)
	    // String[1] -> edge's user_id (string)
	    adjacencyLists = new HashMap<String, ArrayList<String[]>>();
	    
	    for (int e = 0; e < E.size(); e++) {
	    	// Adding Vertex to undirected Graph
	    	String v1 = E.get(e)[1];
	    	String v2 = E.get(e)[2];
	    	if (!adjacencyLists.containsKey(v1)) {
	    		adjacencyLists.put(v1, new ArrayList<String[]>());
	    	}

	    	if(!adjacencyLists.containsKey(v2)) {
	    		adjacencyLists.put(v2, new ArrayList<String[]>());
	    	}
		}
	    
	    for (int e = 0; e < E.size(); e++) {
	    	String user_id = E.get(e)[0];
	    	String v1 = E.get(e)[1];
	    	String v2 = E.get(e)[2];
	        // Adding Edge (both ways) to the undirected Graph
	        adjacencyLists.get(v1).add(new String[] {v2, user_id});
	        adjacencyLists.get(v2).add(new String[] {v1, user_id});
	        
	    }

	    // Printing Adjacency List. dont do this it takes eternity. Only for testing
//	    adjacencyLists.forEach((vertex, adjacentVertices) -> {
//	        System.out.print("adjacencyList[" + vertex + "]");
//	        int i = 0;
//	        while (i < adjacentVertices.size()) {
//	        	System.out.print(" -> " + adjacentVertices.get(i)[0]);
//	            ++i;
//	        }  
//	        System.out.println();
//	        try {
//				TimeUnit.SECONDS.sleep(1);
//			} 
//	        catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//        });
	}
	
	/**
	 * NO LONGER NEEDED. 
	 * Prints the amount of time a certain query takes.
	 * @param query - query string 
	 * @return ResultSet from query
	 */
	private ResultSet queryTimeElapsed (String query) {
		long startTime, finishTime;
		startTime= System.currentTimeMillis();
		ResultSet result = jdbcpostgreSQLGUI.queryDatabaseTable(query);
		finishTime = System.currentTimeMillis();
		System.out.println("Got the resultset in " + ((finishTime - startTime) / 1000) + " seconds.");
		return result;
	}
	
	/**
	 * NO LONGER NEEDED.
	 * Populates graph file based on queryreviews.csv, which holds the data from the query.
	 * Graph file represents the edges in the graph, 1st col is the edge value (user id), 
	 * 2nd and 3rd cols are vertices corresponding to the edge value (business ids)
	 * SELECT DISTINCT business_id, user_id FROM reviews WHERE stars >= 3
	 */
	public void writeGraphFile() {
		
		try {
			BufferedWriter graphData = new BufferedWriter(new FileWriter("graphreviews.csv"));
			File readfile = new File("queryreviews.csv");
		    Scanner scanfile = new Scanner(readfile);
		    
		    graphData.write("\"User ID\",\"Business A\",\"Business B\"\n");
		    
		    ArrayList<String[]> entrySet = new ArrayList<String[]>();
		    String userId = "";
		    
		    while (scanfile.hasNextLine()) {
		        String data = scanfile.nextLine();
		        // parsedData[0] has the business ID, parsedData[1] has the user ID
		        String[] parsedData = data.split(",");
	        	String currentUserId = parsedData[1];
	        	
	        	// if this is the first User of its kind, set the userId to the current User Id and add to entrySet
	        	if (entrySet.size() == 0) {
	        		userId = currentUserId;
	        		entrySet.add(parsedData);
	        	}
	        	// the User has reviewed another business
	        	else if (userId.equals(currentUserId)) {
	        		entrySet.add(parsedData);
				}
				// we have moved onto a new User Id, which means the user hasn't reviewed any more businesses.
				else {
					// we dont care about people who have only reviewed 1 business; they cannot create an edge.
					if (entrySet.size() > 1) {
						
						// nested for loop to create an edge of the graph
						for (int a = 0; a < entrySet.size() - 1; a++) {
							String businessId_A = entrySet.get(a)[0];
							
							for (int b = a + 1; b < entrySet.size(); b++) {
								String businessId_B = entrySet.get(b)[0];
								graphData.write(userId + "," + businessId_A + "," + businessId_B + "\n");
							}
						}
						
					}
					// clear the entry set for the next User and add the current user to the empty entrySet, resetting the userId as well
					entrySet.clear();
	        		userId = currentUserId;
	        		entrySet.add(parsedData);
				}
		    }
		    System.out.println("Graph file written.");
		    scanfile.close();
		    graphData.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
