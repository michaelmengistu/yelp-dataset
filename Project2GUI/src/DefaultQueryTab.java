import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
//import javax.swing.JOptionPane;
public class DefaultQueryTab {

	private JLayeredPane layeredBusinessPane;
	private JTextField business_name_field;
	private JTextField city_field;
	private JTextField state_field;
	private JTextField zip_field;
	private JTextField categoriesField;
	private JCheckBox chckbxExcludeCat;
	private JCheckBox chckbxDairyfree;
	private JCheckBox chckbxGlutenfree;
	private JCheckBox chckbxVegan;
	private JCheckBox chckbxSoyfree;
	private JCheckBox chckbxHalal;
	private JCheckBox chckbxKosher;
	private JCheckBox chckbxVegetarian;
	private JComboBox star_min;
	private JComboBox star_max;
	private JTable table;
	private JButton btnWriteToFile;
	private JButton btnCloseConn;
	private DefaultTableModel dftm;
	private static int fileno = 0;
	private boolean isFirstQuery = true;

	/**
	 * Create the application.
	 */
	public DefaultQueryTab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		layeredBusinessPane = new JLayeredPane();
		layeredBusinessPane.setOpaque(true);
		layeredBusinessPane.setBackground(SystemColor.control);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(20, 10, 402, 48);
		//frmDatabaseQueryFilter.getContentPane().add(titlePanel);
		layeredBusinessPane.add(titlePanel);
		
		JLabel lblNewLabel = new JLabel("Group 15 Database GUI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titlePanel.add(lblNewLabel);
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setBounds(20, 68, 402, 440);
		//frmDatabaseQueryFilter.getContentPane().add(fieldPanel);
		layeredBusinessPane.add(fieldPanel);
		
		business_name_field = new JTextField();
		business_name_field.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Business name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("City");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		city_field = new JTextField();
		city_field.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("State");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		state_field = new JTextField();
		state_field.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Zip");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		zip_field = new JTextField();
		zip_field.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Stars");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		star_min = new JComboBox();
		star_min.setFont(new Font("Tahoma", Font.PLAIN, 14));
		star_min.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5}));
		star_min.setSelectedIndex(0);
		
		star_max = new JComboBox();
		star_max.setFont(new Font("Tahoma", Font.PLAIN, 14));
		star_max.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5}));
		star_max.setSelectedIndex(4);
		
		JLabel lblNewLabel_6 = new JLabel("to");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_7 = new JLabel("Dietary Restrictions (select all that apply)");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxDairyfree = new JCheckBox("Dairy-free");
		chckbxDairyfree.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxGlutenfree = new JCheckBox("Gluten-free");
		chckbxGlutenfree.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxVegan = new JCheckBox("Vegan");
		chckbxVegan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxKosher = new JCheckBox("Kosher");
		chckbxKosher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxHalal = new JCheckBox("Halal");
		chckbxHalal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxSoyfree = new JCheckBox("Soy-free");
		chckbxSoyfree.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxVegetarian = new JCheckBox("Vegetarian");
		chckbxVegetarian.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxExcludeCat = new JCheckBox("Exclude");
		
		categoriesField = new JTextField();
		categoriesField.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Categories");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_fieldPanel = new GroupLayout(fieldPanel);
		gl_fieldPanel.setHorizontalGroup(
			gl_fieldPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fieldPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_fieldPanel.createSequentialGroup()
							.addGroup(gl_fieldPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(star_min, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
							.addContainerGap(346, Short.MAX_VALUE))
						.addGroup(gl_fieldPanel.createSequentialGroup()
							.addGroup(gl_fieldPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1)
								.addComponent(business_name_field, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
								.addGroup(gl_fieldPanel.createSequentialGroup()
									.addGroup(gl_fieldPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_fieldPanel.createSequentialGroup()
											.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addGap(129)
											.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_fieldPanel.createSequentialGroup()
											.addGroup(gl_fieldPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_fieldPanel.createSequentialGroup()
													.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(star_max, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
												.addComponent(city_field, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(state_field, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_fieldPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(zip_field, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))))
							.addGap(34))
						.addGroup(gl_fieldPanel.createSequentialGroup()
							.addGroup(gl_fieldPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(chckbxDairyfree, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
								.addComponent(chckbxGlutenfree, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
								.addComponent(chckbxVegan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
							.addGap(60)
							.addGroup(gl_fieldPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxVegetarian, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxSoyfree, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_fieldPanel.createSequentialGroup()
									.addComponent(chckbxHalal, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(chckbxKosher, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
							.addGap(29))
						.addGroup(gl_fieldPanel.createSequentialGroup()
							.addGroup(gl_fieldPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_fieldPanel.createSequentialGroup()
									.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(chckbxExcludeCat))
								.addComponent(categoriesField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(13, Short.MAX_VALUE))))
		);
		gl_fieldPanel.setVerticalGroup(
			gl_fieldPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fieldPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(business_name_field, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(city_field, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(state_field, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(zip_field, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_fieldPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(star_min, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_6))
						.addComponent(star_max, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_7)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxDairyfree)
						.addComponent(chckbxHalal, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxKosher, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxGlutenfree, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxSoyfree, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(chckbxVegan, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxVegetarian, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_fieldPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9)
						.addComponent(chckbxExcludeCat))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(categoriesField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		fieldPanel.setLayout(gl_fieldPanel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(181, 518, 85, 21);
		//frmDatabaseQueryFilter.getContentPane().add(btnSubmit);
		layeredBusinessPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        submitBtnClicked();
		    }
		});
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tablePanel.setBounds(432, 68, 944, 440);
		//frmDatabaseQueryFilter.getContentPane().add(tablePanel);
		layeredBusinessPane.add(tablePanel);
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBorder(new CompoundBorder());
		table.setBounds(917, 336, -901, -322);
		
		JScrollPane scrollPane = new JScrollPane(table);
		// Force the scrollbars to always be displayed
		scrollPane.setHorizontalScrollBarPolicy(
		    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(
		    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		tablePanel.add(scrollPane);
		
		JLabel queryResultsLabel = new JLabel("Query Results");
		queryResultsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		queryResultsLabel.setBounds(435, 37, 106, 21);
		//frmDatabaseQueryFilter.getContentPane().add(queryResultsLabel);
		layeredBusinessPane.add(queryResultsLabel);
		btnCloseConn = new JButton("Close");
		btnCloseConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdbcpostgreSQLGUI.closeConnection();
				QueryGUI.disposeFrame();
			}
		});
		btnCloseConn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCloseConn.setBounds(1291, 518, 85, 21);
		//frmDatabaseQueryFilter.getContentPane().add(btnCloseConn);
		layeredBusinessPane.add(btnCloseConn);
		btnWriteToFile = new JButton("Write To File");
		btnWriteToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				writeToCSV();
			}
		});
		btnWriteToFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWriteToFile.setBounds(458, 518, 118, 21);
		//frmDatabaseQueryFilter.getContentPane().add(btnWriteToFile);
		layeredBusinessPane.add(btnWriteToFile);
	}
	
	public JLayeredPane getDefaultTab() {
		return layeredBusinessPane;
	}
	
	/**
	 * Formats the SQL query string based on inputs from the user.
	 * Will call a function to format the table.
	 */
	private void submitBtnClicked()
	{
		boolean goToQuestion4 = true; 
		String business_name, state, city, zip, categories;
		boolean exclude_categories, dairy_free, gluten_free, vegan, halal, soy_free, vegetarian, kosher;
		Integer s_min, s_max;
		
		try { 
			business_name = business_name_field.getText();
			
		}
		catch (NullPointerException e) { business_name = null;}
		
		try {
			city = city_field.getText();
		}
		catch (NullPointerException e) { 
			city = null;
		}
		
		try { 
			state = state_field.getText();
		}
		catch (NullPointerException e) { state = null;}
		try { 
			zip = zip_field.getText();
		}
		catch (NullPointerException e) { zip = null;}
		try { 
			categories = categoriesField.getText();
		}
		catch (NullPointerException e) { categories = null; }
		
		exclude_categories = chckbxExcludeCat.isSelected();
		dairy_free = chckbxDairyfree.isSelected();
		gluten_free = chckbxGlutenfree.isSelected();
		vegan = chckbxVegan.isSelected();
		halal = chckbxHalal.isSelected();
		soy_free = chckbxSoyfree.isSelected();
		vegetarian = chckbxVegetarian.isSelected();
		kosher = chckbxKosher.isSelected();
		
		s_min = (Integer)star_min.getSelectedItem();
		s_max = (Integer)star_max.getSelectedItem();
		
		String query = "SELECT business.name, business.address, business.city, business.state, "
				+ "business.postal_code, business.stars, business.categories, business.business_id "
				+ "FROM business ";
		String q4Qquery = "SELECT business.name, business.address, business.city, business.state, "
				+ "business.postal_code, business.stars, business.categories, business.business_id "
				+ "FROM business ";
		
		boolean add_and = false;
		
		if (dairy_free || gluten_free || vegan || halal || soy_free || vegetarian || kosher) {
			query += "INNER JOIN attributes ON business.attribute_id = attributes.attributes_id " 
				+ "INNER JOIN dietary_restrictions ON attributes.dietary_restrictions_id = dietary_restrictions.dietary_restrictions_id "
				+ "WHERE ";
			if (dairy_free) {
				query += "dietary_restrictions.dairy_free = \'TRUE\' ";
				add_and = true;
				
			}
			if (gluten_free) {
				if (add_and) query += "AND ";
				else add_and = true;
				query += "dietary_restrictions.gluten_free = \'TRUE\' ";
			}
			if (vegan) {
				if (add_and) query += "AND ";
				else add_and = true;
				query += "dietary_restrictions.vegan = \'TRUE\' ";
			}
			if (halal) {
				if (add_and) query += "AND ";
				else add_and = true;
				query += "dietary_restrictions.halal = \'TRUE\' ";
			}
			if (soy_free) {
				if (add_and) query += "AND ";
				else add_and = true;
				query += "dietary_restrictions.soy_free = \'TRUE\' ";
			}
			if (vegetarian) {
				if (add_and) query += "AND ";
				else add_and = true;
				query += "dietary_restrictions.vegetarian = \'TRUE\' ";
			}
			if (kosher) {
				if (add_and) query += "AND ";
				else add_and = true;
				query += "dietary_restrictions.kosher = \'TRUE\' ";
			}
			goToQuestion4 = false;
		}
		else {
			query += "WHERE ";
			q4Qquery += "WHERE ";
		}
		if (business_name != null && !business_name.isEmpty()) {
			goToQuestion4 = false;
			if (add_and) query += "AND ";
			else add_and = true;
			query += "business.name LIKE \'%" + business_name + "%\' ";
		}
		if (city != null && !city.isEmpty()) {
			if (add_and) query += "AND ";
			else add_and = true;
			query += "business.city LIKE \'%" + city + "%\' ";
			q4Qquery += "business.city LIKE \'%" + city + "%\' ";
		}
		if (state != null && !state.isEmpty()) {
			if (add_and) {
				query += "AND ";
				q4Qquery += "AND ";

			}
			else add_and = true;
			query += "business.state LIKE \'%" + state.toUpperCase() + "%\' ";
			q4Qquery += "business.state LIKE \'%" + state.toUpperCase() + "%\' ";
		}
		if (zip != null && !zip.isEmpty()) {
			goToQuestion4 = false;
			if (add_and) query += "AND ";
			else add_and = true;
			query += "business.postal_code LIKE \'%" + zip + "%\' ";
		}
		if (categories != null && !categories.isEmpty()) {
			if (add_and) {
				query += "AND ";
				q4Qquery += "AND ";
			}
			else add_and = true;
			
			if (!exclude_categories) {
				query += "business.categories LIKE \'%" + categories + "%\' ";
				q4Qquery += "business.categories LIKE \'%" + categories + "%\' ";
			}
			else {
				query += "business.categories NOT LIKE \'%" + categories + "%\' ";
				q4Qquery += "business.categories NOT LIKE \'%" + categories + "%\' ";
			}
		}
		else{
			q4Qquery += "AND business.categories LIKE \'%Restaurants%\' ";
		}
		if (add_and) query += "AND ";
		query += "business.stars BETWEEN " + s_min + " AND " + s_max + " AND business.is_open <> 0";
		q4Qquery += "AND business.stars BETWEEN " + s_min + " AND " + s_max + " AND business.is_open <> 0";

		//System.out.println(query);	
		//System.out.println(q4Qquery);
		
		
		if(goToQuestion4) {
			System.out.println("Question4Func(ResultSet result) fucntion taken.");
			ResultSet result = jdbcpostgreSQLGUI.queryDatabaseTable(q4Qquery);
			ResultSet copyResult = jdbcpostgreSQLGUI.queryDatabaseTable(q4Qquery);
			Question4Func(result, copyResult);
			 
		}
		else {
			System.out.println("configureTableResults(ResultSet result) fucntion taken.");
			ResultSet result = jdbcpostgreSQLGUI.queryDatabaseTable(query);
			configureTableResults(result);
		}

	}
	
	
	
	HashSet<String> names = new HashSet<String>();
	/**
	 * checks database for all non franchise business names and stores in HashSet<String> names
	 * @param result - the inputs given to us From GUI
	 */
	public void nonFranchiseNames(ResultSet result) {
		names.clear();
		if (result != null) { //checks database for all non franchise business names and stores in Hash-set
			try {
				while (result.next()) {
					String businessName = new String();
					businessName = result.getString(1);
					if (names.contains(businessName)) { // remove name if name was found before
						names.remove(businessName);
					} 
					else { // add to names if name not found
						names.add(businessName);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 *  Given a city, find the non-franchise restaurant that receives the most tip
	 *  and display restaurant and tips.
	 * @param result - the inputs given to us From GUI
	 * @param copyResult - copy of the inputs given to us From GUI
	 */
	public void Question4Func(ResultSet result, ResultSet copyResult) {
		 Object finalTable[] = { "Restaurant Name", "Tips"};
		 dftm = new DefaultTableModel(finalTable, 0);

		String ID = new String();
		String name = new String();

		nonFranchiseNames(copyResult);// checks database for all non franchise business names and stores in Hash-set
		System.out.printf("Size of Hashset: %d \n\n", names.size());
		if(names.size() == 0) {
			System.out.printf("Sorry. No matches found!");
		}
		
		ArrayList<String> arrayString = new ArrayList<String>();
		// with the hash-set names
		if (result != null) {// goes threw database again to check Bussines_IDs in tip table
			System.out.println("Checking matches...");
			try {
				while (result.next()) {
					name = result.getString(1); //name of Business 		

					
					Iterator<String> it = names.iterator(); //Hash-Set iterator
					while (it.hasNext()) {// Iterate over a Hash-Set
						ArrayList<String> tempString = new ArrayList<String>();
						String array = it.next();
						if(array.equals(name)) {//checks if Business name is in the Hash-set
							
							ID = result.getString(8); //Match business.name to its Business.ID
							
							String tipTable = "SELECT tip.tip_text  FROM tip WHERE tip.business_id LIKE \'%" + ID + "%\'";
							ResultSet queryResult = jdbcpostgreSQLGUI.queryDatabaseTable(tipTable); //query tip database
							//System.out.printf("\nChecking for business_id = %s matches in tip table.... \n", ID);
							int tipCount = 0;
							if (queryResult != null) { //checks if there are ID matches
								//System.out.printf("business_id = %s match found !", ID);
								try {
									while (queryResult.next()) { // add tip_text for ID matches
										String tip_text = queryResult.getString(1);
										if(tipCount == 0) {
											tempString.add(name);
											tempString.add(tip_text);
											

										}
										else {
											tempString.add(tip_text);
										}
										tipCount++;
									}
								} catch (SQLException e) {e.printStackTrace();}
							}
							if(arrayString.size() <= tempString.size() ) {
								//System.out.printf("\nAarrayString.size() = %d\n", arrayString.size());
								arrayString = new ArrayList<String>(tempString);
							}
							break;
						}
					}
				}
			} 
			catch (SQLException e) {e.printStackTrace();}
			for(int i = 0; i < arrayString.size()-1; i++)
			{
				if(i == 0) {
					Object[] rows = {arrayString.get(i), arrayString.get(i+1) }; //businessRows ={ "Business Name","tip_text"} 
					dftm.addRow(rows); //add row to table
					

				}
				else {
					Object[] rows = {"", arrayString.get(i+1) }; //businessRows ={ "Business Name","tip_text"} 
					dftm.addRow(rows); //add row to table
				}
				
				
			}
			System.out.println("Done");
			table.setModel(dftm);

			if(isFirstQuery) {
				// mouselistener should not be added more than once
		        table.addMouseListener(new QueryGUI.JTableButtonMouseListener(table));
		        isFirstQuery = false;
			} 	

		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Configures and populates the query table in the GUI
	 * @param result - the ResultSet needed to populate the table
	 */
	public void configureTableResults(ResultSet result)
	{
		Object columnNames[] = { "Business Name", "Address", "City", "State", "Zip", "Stars", "Categories", "Reviews" };
		dftm = new DefaultTableModel(columnNames, 0);
		if ( result != null ) {  
			//OUTPUT
			try {
				while (result.next()) {
					JButton btnReviews = configureReviewsButton(result.getString(8));
					
					Object[] row = { result.getString(1), result.getString(2), result.getString(3), 
							result.getString(4), result.getString(5), result.getDouble(6), 
							result.getString(7), btnReviews};
					
					dftm.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}    
        }
		table.setModel(dftm);
		TableCellRenderer buttonRenderer = new QueryGUI.JTableButtonRenderer();
        table.getColumn("Reviews").setCellRenderer(buttonRenderer);
        
		if(isFirstQuery) {
			// mouselistener should not be added more than once
	        table.addMouseListener(new QueryGUI.JTableButtonMouseListener(table));
	        isFirstQuery = false;
		} 	
	}
	
	/**
	 * configures the JButton for reviews in the query table.
	 * @param business_id
	 * @return the JButton for the reviews.
	 */
	public JButton configureReviewsButton(String business_id )
	{
		JButton btnReviews = new JButton("Reviews");
		btnReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new reviewsPage(business_id);
					System.out.println("Opened new reviews page");
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		//frmDatabaseQueryFilter.getContentPane().add(btnReviews);
		layeredBusinessPane.add(btnReviews);
		return btnReviews;
	}
	
	/**
	 * Writes the contents of the table to a CSV file.
	 * Multiple queries will not override the same file.
	 */
	private void writeToCSV()
	{
		try {
			BufferedWriter tableData = new BufferedWriter(new FileWriter("queryresult" + fileno + ".csv"));
			for(int row = 0; row < dftm.getRowCount(); row++ ) {
				String line = "";
				// we will not include the attributes, hours, or reviews IDs in the file
				for(int col = 0; col < dftm.getColumnCount() - 3; col++) {
					if (dftm.getValueAt(row, col) != null) {
						line += "\"" + dftm.getValueAt(row, col).toString() + "\"";
					}
						
					if (col == dftm.getColumnCount() - 4) {
						line += "\n";
					}
					else line += ",";
				}
				tableData.write(line);
			}
			tableData.close();
			fileno++;
			System.out.println("Written to file.");
		} 
		catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
