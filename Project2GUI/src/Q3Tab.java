import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

public class Q3Tab {

    private JLayeredPane layeredUserPane;
    private JTable tableUser;
    private JButton btnSubmit;
	private JButton btnCloseConnUser;
	private JPanel panel;
	private JLabel label;
	private JLabel label_2;
	private JTextField text_state;
    private JTextField top_franchise_spread;
    private JComboBox lower_bound;
    private JComboBox upper_bound;
    private JTable table;
    private DefaultTableModel dftm;

    public Q3Tab() {
        initialize();
    }

    private void initialize() {
        layeredUserPane = new JLayeredPane();
		layeredUserPane.setOpaque(true);
		layeredUserPane.setBackground(SystemColor.control);
		
		panel = new JPanel();
		panel.setBounds(20, 10, 402, 48);
		layeredUserPane.add(panel);
		
		label = new JLabel("Group 15 Database GUI");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 68, 402, 440);
		layeredUserPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("State");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 43, 64, 24);
		panel_1.add(lblNewLabel);
		
		text_state = new JTextField();
		text_state.setFont(new Font("Tahoma", Font.PLAIN, 14));
		text_state.setBounds(23, 68, 78, 30);
		panel_1.add(text_state);
		text_state.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Average Stars");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(25, 130, 91, 24);
		panel_1.add(lblNewLabel_1);
		
		lower_bound = new JComboBox();
		lower_bound.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lower_bound.setModel(new DefaultComboBoxModel(new String[] {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5"}));
		lower_bound.setBounds(23, 164, 50, 35);
		panel_1.add(lower_bound);
		
		JLabel lblNewLabel_2 = new JLabel("to");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(83, 170, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		upper_bound = new JComboBox();
		upper_bound.setFont(new Font("Tahoma", Font.PLAIN, 14));
		upper_bound.setModel(new DefaultComboBoxModel(new String[] {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5"}));
		upper_bound.setBounds(115, 164, 50, 35);
		panel_1.add(upper_bound);
		
		JLabel lblNewLabel_3 = new JLabel("Location Spread");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(23, 236, 105, 24);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("top:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(23, 270, 37, 24);
		panel_1.add(lblNewLabel_4);
		
		top_franchise_spread = new JTextField();
		top_franchise_spread.setBounds(56, 270, 96, 30);
		panel_1.add(top_franchise_spread);
		top_franchise_spread.setColumns(10);
		label_2 = new JLabel("Query Results");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(432, 37, 106, 21);
		layeredUserPane.add(label_2);
		
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

    public JLayeredPane getQ3Tab() {
        return layeredUserPane;
    }

    public void configureTableResults() {
        double s_min, s_max;
        int filter;
        String state;

        s_min =  Double.parseDouble((String) lower_bound.getSelectedItem());
        s_max = Double.parseDouble((String) upper_bound.getSelectedItem());
        try {
            state = text_state.getText();
        } catch (NullPointerException e) { state = null; }
        try {
            filter = Integer.parseInt(top_franchise_spread.getText());
        } catch (NullPointerException e) { filter = 0; }

        Q3 search = new Q3(state);
        search.filterByRating(s_min, s_max);
        search.filterLocSpread(filter);

        Object columnNames[] =  {"Name", "Average Rating", "Standard Distance"};
        dftm = new DefaultTableModel(columnNames, 0);

        search.franchiseLocations.forEach((fname, locations) -> {
        	Object[] row = new Object[] {fname, search.avgFranchiseRating(fname), search.standDist(fname) };
        	dftm.addRow(row);
        });

        table.setModel(dftm);
        dftm.fireTableDataChanged();
    }
}
