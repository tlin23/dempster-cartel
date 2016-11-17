package LoginUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import querySet.DataQueries;

public class aLoginInterface {
	Connection con;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JFrame mainFrame;
	
	public aLoginInterface(Connection con){
		this.con = con;
		mainFrame = new JFrame("Addict Login");
		
		JLabel regLabel = new JLabel("Addict Login");
		JLabel usernameLabel = new JLabel("Enter UserName: ");
		JLabel passwordLabel = new JLabel("Enter Password: ");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		JButton loginButton = new JButton("Login");
		
		//Populate window
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout compenents
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(regLabel,c);
		contentPane.add(regLabel);
		
		// Username Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(usernameLabel, c);
		contentPane.add(usernameLabel);
		
		// Username Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(usernameField, c);
		contentPane.add(usernameField);
		
		// Password Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(passwordLabel, c);
		contentPane.add(passwordLabel);
		
		// Password Field 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(passwordField, c);
		contentPane.add(passwordField);
		
		//Login button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(loginButton, c);
		contentPane.add(loginButton);
		
		//Error message
		final JLabel errorMsg = new JLabel("");
		errorMsg.setForeground(Color.red);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(errorMsg, c);
		contentPane.add(errorMsg);
		
		ActionListener userListener = new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(connect()){
					String user = usernameField.getText();
					String pass = String.valueOf(passwordField.getPassword());
					
					String loginQuery = "select aUserName,password from addictUser where aUserName = '";
					loginQuery = loginQuery.concat(user.concat("'"));
					
					//search addict table
					try{
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery(loginQuery);
						
						if(rs.next()){
							String dBPass = rs.getString("password");
							if(dBPass.equals(pass)){
								Connection con1 = con;
								new addictInterface(con1);
								mainFrame.dispose();
							}else{
								errorMsg.setText("Invalid Username");
								passwordField.setText("");
								usernameField.setText("");
							}
						}
						
						
					}catch(SQLException e1){
						System.out.println("Error: "+ e1.getMessage());
						errorMsg.setText("Invalid input");
					}
				}
			}
		};
				
		//populate password field and login button with event handler
		passwordField.addActionListener(userListener);
		loginButton.addActionListener(userListener);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Resize window
		mainFrame.pack();
	    Dimension d = mainFrame.getToolkit().getScreenSize();
	    Rectangle r = mainFrame.getBounds();
	    mainFrame.setLocation((d.width - r.width)/2, (d.height - r.height)/2);
	    
	    //other default window features
	    mainFrame.setVisible(true);
	    usernameField.requestFocus();	
	}
	
	private boolean connect(){
	try{
		String connectURL ="jdbc:oracle:thin:@localhost:1522:ug";
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection(connectURL, "ora_h3b0b", "a14558143");
		DataQueries.con = con;
		return true;
	}catch(SQLException e){
		System.out.println("Message: " + e.getMessage());
	}
	JOptionPane.showMessageDialog(mainFrame, "Cannot connect to Oracle","Error",JOptionPane.ERROR_MESSAGE);
	return false;
    }

}
