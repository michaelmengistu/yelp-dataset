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
import javax.swing.JLayeredPane;
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

public class UserQueryTab {

	private JLayeredPane layeredUserPane;
	private JTable tableUser;
	private JButton btnCloseConnUser;
	private JPanel panel;
	private JLabel label;
	private JTextField userNameField;
	private JLabel lblNewLabel_10;
	private JLabel label_2;
	private DefaultTableModel dftm;
	private JComboBox orderByComboBox;
	private JSpinner spnrReviewCount;

	/**
	 * Create the application.
	 */
	public UserQueryTab() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		layeredUserPane = new JLayeredPane();
		layeredUserPane.setOpaque(true);
		layeredUserPane.setBounds(100, 100, 1400, 624);
		layeredUserPane.setBackground(SystemColor.control);
		
		panel = new JPanel();
		panel.setBounds(20, 10, 402, 48);
		layeredUserPane.add(panel);
		
		label = new JLabel("Group 15 Database GUI");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 68, 402, 140);
		layeredUserPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("User name");
		label_1.setBounds(10, 10, 67, 17);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(label_1);
		
		userNameField = new JTextField();
		userNameField.setBounds(10, 37, 284, 27);
		panel_1.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Min. Reviews");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(304, 12, 88, 13);
		panel_1.add(lblNewLabel_8);
		
		spnrReviewCount = new JSpinner();
		spnrReviewCount.setModel(new SpinnerNumberModel(new Integer(5), null, null, new Integer(1)));
		spnrReviewCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrReviewCount.setBounds(304, 37, 58, 27);
		panel_1.add(spnrReviewCount);
		
		orderByComboBox = new JComboBox();
		orderByComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Increasing review count", "Decreasing review count"}));
		orderByComboBox.setBounds(10, 97, 165, 21);
		panel_1.add(orderByComboBox);
		
		lblNewLabel_10 = new JLabel("Order By");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(10, 74, 96, 13);
		panel_1.add(lblNewLabel_10);
		
		JButton btnSubmitUser = new JButton("Submit");
		btnSubmitUser.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        submitBtnClicked();
		    }
		});
		btnSubmitUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmitUser.setBounds(277, 95, 85, 21);
		panel_1.add(btnSubmitUser);
		
		label_2 = new JLabel("Query Results");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(438, 37, 106, 21);
		layeredUserPane.add(label_2);
		
		JPanel tablePanelUser = new JPanel();
		tablePanelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tablePanelUser.setBounds(432, 68, 944, 440);
		//frmDatabaseQueryFilter.getContentPane().add(tablePanel);
		layeredUserPane.add(tablePanelUser);
		tablePanelUser.setLayout(new BoxLayout(tablePanelUser, BoxLayout.X_AXIS));
		
		tableUser = new JTable();
		tableUser.setBackground(Color.WHITE);
		tableUser.setBorder(new CompoundBorder());
		tableUser.setBounds(917, 336, -901, -322);
		
		JScrollPane scrollPaneUser = new JScrollPane(tableUser);
		// Force the scrollbars to always be displayed
		scrollPaneUser.setHorizontalScrollBarPolicy(
		    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneUser.setVerticalScrollBarPolicy(
		    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		tablePanelUser.add(scrollPaneUser);
		
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
	
	public JLayeredPane getUserTab() {
		return layeredUserPane;
	}
	
	private void submitBtnClicked() {
		String selectedOrder = (String) orderByComboBox.getSelectedItem();
		
		String sqlOrder = "";
		if (selectedOrder.equals("Increasing review count"))
			sqlOrder = "ASC";
		else if(selectedOrder.equals("Decreasing review count"))
			sqlOrder = "DESC";
		
		
		String query = "SELECT name, review_count, average_stars, useful, funny, cool, yelping_since FROM users WHERE review_count >= " 
				+ spnrReviewCount.getValue() + " AND name LIKE \'%" + userNameField.getText() + "%\'";
		
		if (!sqlOrder.equals(""))
			query += " ORDER BY review_count " + sqlOrder;
		
		ResultSet result = jdbcpostgreSQLGUI.queryDatabaseTable(query);
		
		configureTableResults(result);
	}
	

	/**
	 * Configures and populates the query table in the GUI
	 * @param result - the ResultSet needed to populate the table
	 */
	private void configureTableResults(ResultSet result)
	{
		Object columnNames[] = { "Name", "Review Count", "Average Stars", "Useful", "Funny", "Cool", "Yelping Since" };
		dftm = new DefaultTableModel(columnNames, 0);
		if ( result != null ) {  
			//OUTPUT
			try {
				while (result.next()) {
					//JButton btnReviews = configureSummaryButton(result.getString(8));
					
					Object[] row = { result.getString(1), result.getInt(2), result.getDouble(3), 
							result.getInt(4), result.getInt(5), result.getInt(6), 
							result.getDate(7)};
					
					dftm.addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}    
        }
		tableUser.setModel(dftm);
	}
}
