import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TeamChoiceTab {

    private JLayeredPane layeredUserPane;
    private JButton btnSubmit;
	private JButton btnCloseConnUser;
	private JPanel panel;
	private JLabel lblRadiusChecker;
	private JLabel label_2;
    private JTable table;
    private DefaultTableModel dftm;
    private JLabel lblNewLabel;
    private JTextField textField_Latitude;
    private JTextField textField_Longitude;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JTextField textFieldFranchise;

    public TeamChoiceTab() {
        initialize();
    }

    private void initialize() {
        layeredUserPane = new JLayeredPane();
		layeredUserPane.setOpaque(true);
		layeredUserPane.setBackground(SystemColor.control);
		
		panel = new JPanel();
		panel.setBounds(20, 10, 402, 48);
		layeredUserPane.add(panel);
		
		lblRadiusChecker = new JLabel("Radius Checker");
		lblRadiusChecker.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblRadiusChecker);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 68, 402, 440);
		layeredUserPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel tablePanelUser = new JPanel();
		tablePanelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tablePanelUser.setBounds(432, 68, 944, 440);
		//frmDatabaseQueryFilter.getContentPane().add(tablePanel);
		layeredUserPane.add(tablePanelUser);
		tablePanelUser.setLayout(new BoxLayout(tablePanelUser, BoxLayout.X_AXIS));
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBorder(new CompoundBorder());
		table.setBounds(917, 336, -901, -322);
		
		JScrollPane scrollPaneUser = new JScrollPane(table);
		// Force the scrollbars to always be displayed
		scrollPaneUser.setHorizontalScrollBarPolicy(
		    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneUser.setVerticalScrollBarPolicy(
		    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		tablePanelUser.add(scrollPaneUser);
        
        btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSubmit.setBounds(23, 327, 85, 21);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
		    public void actionPerformed(ActionEvent e) {
		        configureTableResults();
		    }
        });

		panel_1.add(btnSubmit);
		
		lblNewLabel = new JLabel("Enter Franchise Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 73, 157, 21);
		panel_1.add(lblNewLabel);
		
		textField_Latitude = new JTextField();
		textField_Latitude.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_Latitude.setBounds(23, 212, 173, 34);
		panel_1.add(textField_Latitude);
		textField_Latitude.setColumns(10);
		
		textField_Longitude = new JTextField();
		textField_Longitude.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_Longitude.setColumns(10);
		textField_Longitude.setBounds(206, 212, 173, 34);
		panel_1.add(textField_Longitude);
		
		lblNewLabel_1 = new JLabel("Your Latitude");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 191, 85, 21);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Your Longitude (Neg #)");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(206, 191, 182, 21);
		panel_1.add(lblNewLabel_2);
		
		textFieldFranchise = new JTextField();
		textFieldFranchise.setBounds(23, 94, 356, 34);
		panel_1.add(textFieldFranchise);
		textFieldFranchise.setColumns(10);
		label_2 = new JLabel("Query Results");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(432, 37, 106, 21);
		layeredUserPane.add(label_2);

		btnCloseConnUser = new JButton("Close");
		btnCloseConnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdbcpostgreSQLGUI.closeConnection();
				QueryGUI.disposeFrame();
			}
		});
		btnCloseConnUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCloseConnUser.setBounds(1291, 518, 85, 21);
		layeredUserPane.add(btnCloseConnUser);		
    }
    
	/**
	 * Configures and populates the query table in the GUI
	 * @param result - the ResultSet needed to populate the table
	 */
	public void configureTableResults()
	{
		String franchise = textFieldFranchise.getText();
		String latitude = textField_Latitude.getText();
		String longitude = textField_Longitude.getText();

		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);
		
		double rightSide;
		double leftSide;
		double topSide;
		double bottomSide;
		double margin = 0.05;
		
		rightSide = lat + margin;
		leftSide = lat - margin;
		topSide = lon + margin;
		bottomSide = lon - margin;
		
		System.out.println(latitude);
		System.out.println(longitude);
		System.out.println(franchise);
		// Query
		String query = "SELECT name, address, city, state, latitude, longitude FROM business WHERE latitude > " + leftSide + " AND latitude < " + 
				rightSide + " AND longitude > " + bottomSide + " AND longitude < " + topSide + " AND name like \'%" + franchise + "%\'";
		System.out.println(query);
		ResultSet result = jdbcpostgreSQLGUI.queryDatabaseTable(query);
		
		Object columnNames[] = {"Business Name", "Address", "City", "State", "Latitude", "Longitude" };
		dftm = new DefaultTableModel(columnNames, 0);
		if ( result != null ) {  
			//OUTPUT
			try {
				while (result.next()) {
					
					Object[] row = { result.getString(1), result.getString(2), result.getString(3),
							result.getString(4), result.getDouble(5), result.getDouble(6)};
					
					dftm.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}    
        }
		table.setModel(dftm);
	}
	public JLayeredPane getTeamChoiceTab() {
		return layeredUserPane;
	}
}