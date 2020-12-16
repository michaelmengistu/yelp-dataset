import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dbLogin {

	private JFrame frame;
	private String user;
	private char[] pswd;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public void setPswd(char[] cs)
	{
		this.pswd = cs;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public String getPswd() 
	{
		return new String(pswd);
	}

	/**
	 * Create the application.
	 */
	public dbLogin() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 38);
		frame.getContentPane().add(panel);
		
		JLabel loginHeader = new JLabel("Login To Database db911_group15_project2");
		loginHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(loginHeader);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 57, 416, 196);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(10, 34, 74, 13);
		panel_1.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(92, 27, 314, 32);
		panel_1.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 86, 74, 13);
		panel_1.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(92, 77, 314, 32);
		panel_1.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setUser(usernameField.getText());
				setPswd(passwordField.getPassword());
				frame.dispose();
				jdbcpostgreSQLGUI.connectToDatabase(getUser(), getPswd());
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(169, 150, 85, 21);
		panel_1.add(btnLogin);
	}
}
