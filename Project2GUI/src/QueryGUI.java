import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class QueryGUI {

	public static JFrame frmDatabaseQueryFilter;

	/**
	 * Create the application.
	 */
	public QueryGUI() {
		initialize();
		frmDatabaseQueryFilter.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * The JButton btnSubmit will call the submitBtnClicked() function on click.
	 */
	private void initialize() {
		frmDatabaseQueryFilter = new JFrame();
		frmDatabaseQueryFilter.setForeground(new Color(192, 192, 192));
		frmDatabaseQueryFilter.setBackground(Color.WHITE);
		frmDatabaseQueryFilter.setTitle("Database Query Filter");
		frmDatabaseQueryFilter.setAlwaysOnTop(true);
		frmDatabaseQueryFilter.setBounds(100, 100, 1400, 624);
		frmDatabaseQueryFilter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatabaseQueryFilter.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1386, 577);
		frmDatabaseQueryFilter.getContentPane().add(tabbedPane);
		
		DefaultQueryTab defTab = new DefaultQueryTab();
		UserQueryTab userTab = new UserQueryTab();
		TeamChoiceTab teamTab = new TeamChoiceTab();
		ShortestPathTab shortPathTab = new ShortestPathTab();
		Q3Tab Q3Tab = new Q3Tab();
		
		tabbedPane.addTab("Default", null, defTab.getDefaultTab(), null);
		tabbedPane.addTab("User", null, userTab.getUserTab(), null);
		tabbedPane.addTab("Shortest Path", null, shortPathTab.getShortestPathTab(), null);
		tabbedPane.addTab("Question 3", null, Q3Tab.getQ3Tab(), null);
		tabbedPane.addTab("TeamChoice", null, teamTab.getTeamChoiceTab(), null);
	}
	
	public static void disposeFrame() {
		frmDatabaseQueryFilter.dispose();
	}
	
	/**
	 * Is needed to make the buttons inside the table clickable.
	 */
	public static class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
            int row = e.getY()/table.getRowHeight(); //get the row of the button

                    /*Checking the row or column is valid or not*/
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    /*perform a click event*/
                    ((JButton)value).doClick();
                }
            }
        }
    }
	
	/**
	 * Renders the buttons so they are not text in the table.
	 */
	public static class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }
}
