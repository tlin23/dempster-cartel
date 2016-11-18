package LoginUI;

import java.awt.*;
import java.sql.*;

import javax.swing.*;

import querySet.DataQueries;

import java.awt.event.*;

public class Login{
	//Fields in the login window
	/*private JTextField usernameField;
	private JPasswordField passwordField;*/
	private JFrame mainFrame;
	public Connection con = null;
	
	public Login(){
		// Connect to database
		// to tunnel: ssh -l <your username> -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 lulu.ugrad.cs.ubc.ca
		// to tunnel: ssh -l h3b0b -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 lulu.ugrad.cs.ubc.ca
		try {
		   System.out.println("Loading driver ...");

		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		   System.out.println("Driver loaded.");
	    } catch (Exception e) {
		   System.out.println("Unable to load driver\n" + e);
		   System.exit(-1);
	    }
		
		try {  
		   System.out.println("Connecting to NetDB2 ...");
	 
		   con = DriverManager.getConnection(
					  "jdbc:oracle:thin:@localhost:1522:ug", "ora_h3b0b", "a14558143");
	 
		   System.out.println("Connection successful.");
		    DataQueries.con = con;
	    } catch( Exception e) {
		   System.out.println("Connection failed\n" + e);
	    }
		
		
		//Create components for the login window
		
		mainFrame = new JFrame("The Dempster Cartel");
		JLabel loginLabel = new JLabel("Dempster Cartel Database Login");
		JButton loginButton = new JButton("Login");
		JButton regButton = new JButton ("Don't have an Account? Click here to Register");
		
		//Create and populate the window
		
		JPanel contentPane= new JPanel();
		mainFrame.setContentPane(contentPane);
		
		// Layout components
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(loginLabel,c);
		contentPane.add(loginLabel);
		
		//Login button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(loginButton, c);
		contentPane.add(loginButton);
		
		//Register button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(regButton, c);
		contentPane.add(regButton);
		
		
		ActionListener loginAsListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new loginAsInterface(con);
				mainFrame.dispose();
			}
		};
		//login button event handler
		loginButton.addActionListener(loginAsListener);
		
		ActionListener regListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new registrationInterface(con);
				mainFrame.dispose();
			}
		};
		//reg button event handler
		regButton.addActionListener(regListener);
		
		// closing window
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
	}
	
 
	public static void main(String[] args){		
		new Login();
	}
	
	public static void showErrorConnecting(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
				"Error connecting to Oracle",
				"Error",
				JOptionPane.ERROR_MESSAGE);
	}

}
