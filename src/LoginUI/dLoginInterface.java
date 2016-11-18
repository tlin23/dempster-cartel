package LoginUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import querySet.DataQueries;

public class dLoginInterface {
	Connection con;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JFrame mainFrame;
	
	public dLoginInterface(final Connection con){
	
		this.con = con;
		mainFrame = new JFrame("Dealer Login");
		
		JLabel regLabel = new JLabel("Dealer Login");
		JLabel usernameLabel = new JLabel("Enter UserName: ");
		JLabel passwordLabel = new JLabel("Enter Password: ");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		JButton loginButton = new JButton("Login");
		JButton backButton = new JButton("Back");
		
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
		
		//Back button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(backButton, c);
		contentPane.add(backButton);
			
		//Error message
		final JLabel errorMsg = new JLabel("");
		errorMsg.setForeground(Color.red);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(errorMsg, c);
		contentPane.add(errorMsg);
		
		ActionListener backListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new loginAsInterface(con);
				mainFrame.dispose();
			}
		};
		//Back button event handler
		backButton.addActionListener(backListener);
		
		ActionListener userListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String user = usernameField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				
				String loginQuery = "select dUserName,password from dealerUser where dUserName = '";
				loginQuery = loginQuery.concat(user.concat("'"));
				
				//search drug lord table
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(loginQuery);
					
					if(rs.next()){
						String dBPass = rs.getString("password");
						if(dBPass.equals(pass)){
							Connection con1 = con;
							new dealerInterface(con1);
							mainFrame.dispose();
						}else{
							errorMsg.setText("Invalid Username");
							passwordField.setText("");
							usernameField.setText("");
						}
					} else {
						errorMsg.setText("Invalid Login. Please try again");
						passwordField.setText("");
						usernameField.setText("");
					}
				}catch(SQLException e1){
					System.out.println("Error: "+ e1.getMessage());
					errorMsg.setText("Invalid input");
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
		
		mainFrame.setMinimumSize(new Dimension(400, 200));
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
}
