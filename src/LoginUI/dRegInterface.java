package LoginUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class dRegInterface {
	Connection con;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField cashField;
	private JTextField cocaineField;
	private JTextField drugLordField;
	private JFrame mainFrame;
	
	public dRegInterface(final Connection con){
		this.con = con;
		mainFrame = new JFrame("Dealer Registration");
		JLabel regLabel = new JLabel("Dealer Registration");
		JLabel usernameLabel = new JLabel("Enter UserName: ");
		JLabel passwordLabel = new JLabel("Enter Password: ");
		JLabel nameLabel = new JLabel("Enter name: ");
		JLabel cashLabel = new JLabel("Enter cash: ");
		JLabel cocaineLabel = new JLabel("Enter cocaine (in kg): ");
		JLabel drugLordLabel = new JLabel("Enter Associated DrugLord:");
		
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		nameField = new JTextField(10);
		cashField = new JTextField(10);
		cocaineField = new JTextField(10);
		drugLordField = new JTextField(10);
		passwordField.setEchoChar('*');
		
		JButton regButton = new JButton("Register");
		
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
		
		// name Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(nameLabel, c);
		contentPane.add(nameLabel);
					
		// name Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(nameField, c);
		contentPane.add(nameField);
					
		// cash Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cashLabel, c);
		contentPane.add(cashLabel);
								
		// cash Field 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cashField, c);
		contentPane.add(cashField);
						
		// cash Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cocaineLabel, c);
		contentPane.add(cocaineLabel);
										
		// cocaine Field 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cocaineField, c);
		contentPane.add(cocaineField);
		
		// cocaine Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(drugLordLabel, c);
		contentPane.add(drugLordLabel);
												
		// cash Field 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(drugLordField, c);
		contentPane.add(drugLordField);
		
		//Register button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(regButton, c);
		contentPane.add(regButton);
		
		//Error message
		final JLabel errorMsg = new JLabel("");
		errorMsg.setForeground(Color.red);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(errorMsg, c);
		contentPane.add(errorMsg);
		
		ActionListener regListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String userName = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String name = nameField.getText();
				String cashst = cashField.getText();
				String cocaine = cocaineField.getText();
				String drugName = drugLordField.getText();
				
				//Construct query to see if Dealer already in DL table
				String loginQuery = "select dUserName from dealerUser where dUserName = ?";
				String testQuery = "select  DLID from drugLord where Name = ?";
				//Attempt to see if Dealer in table
				try{
					PreparedStatement st = con.prepareStatement(loginQuery);
					PreparedStatement st2 = con.prepareStatement(testQuery);
					st.setString(1, userName);
					st2.setString(1, drugName);
					ResultSet rs = st.executeQuery();
					ResultSet rs2 = st2.executeQuery();
					
					if(rs.next()){
						passwordField.setText("");
						usernameField.setText("");
						nameField.setText("");
						cashField.setText("");
						cocaineField.setText("");
						drugLordField.setText("");
						errorMsg.setText("Username already exists");
					} else if (!rs2.next()){
						passwordField.setText("");
						usernameField.setText("");
						nameField.setText("");
						cashField.setText("");
						cocaineField.setText("");
						drugLordField.setText("");
						errorMsg.setText("DrugLord doesn't exists");
					}else{
						String drugLordID = rs2.getString("DLID");
						System.out.println(drugLordID);
						String regQuery = "insert into dealerUser values(?,?)";
						st = con.prepareStatement(regQuery);
						st.setString(1, userName);
						st.setString(2, password);
						rs = st.executeQuery();
						
						String addQuery = "insert into Dealer values(0,?,?,?,?,NULL,?)";
						st = con.prepareStatement(addQuery);
						st.setString(1, cashst);
						st.setString(2, cocaine);
						st.setString(3, name);
						st.setString(4, drugLordID);
						st.setString(5, userName);
						rs = st.executeQuery();
						
						new Login();
						mainFrame.dispose();
					}
					
				}catch(SQLException e1){
					System.out.println("Error: " + e1.getMessage());
					errorMsg.setText("Invalid input");
				}
			}
		};
		
		nameField.addActionListener(regListener);
		drugLordField.addActionListener(regListener);
		passwordField.addActionListener(regListener);
		regButton.addActionListener(regListener);
		
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		
		mainFrame.setMinimumSize(new Dimension(600, 400));
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
