package LoginUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//DrugLord registration
public class dlRegInterface {
	Connection con;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField cashField;
	private JTextField cocaineField;
	private JFrame mainFrame;
	
	public dlRegInterface(final Connection con){
		
		this.con = con;
		mainFrame = new JFrame("DrugLord Registration");
		JLabel regLabel = new JLabel("DrugLord Registration");
		JLabel usernameLabel = new JLabel("Enter UserName: ");
		JLabel passwordLabel = new JLabel("Enter Password: ");
		JLabel nameLabel = new JLabel("Enter name: ");
		JLabel cashLabel = new JLabel("Enter cash: ");
		JLabel cocaineLabel = new JLabel("Enter cocaine (in kg): ");
		
		/* drug lord has cash, cocaine, name*/
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		nameField = new JTextField(10);
		cashField = new JTextField(10);
		cocaineField = new JTextField(10);
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
				
		// cocaine Label
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
		
		//Error message
		final JLabel errorMsg = new JLabel("");
		errorMsg.setForeground(Color.red);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(errorMsg, c);
		contentPane.add(errorMsg);
		
		//Register button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(regButton, c);
		contentPane.add(regButton);
			
		ActionListener regListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String userName = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String name = nameField.getText();
				String cashst = cashField.getText();
				String cocaine = cocaineField.getText();
				
				//Construct query to see if DrugLord already in DL table
				String loginQuery = "select dlUserName from drugLordUser where dlUserName = ?";
				
				//Attempt to see if druglord in table
				try{
					PreparedStatement st = con.prepareStatement(loginQuery);
					st.setString(1, userName);
					ResultSet rs = st.executeQuery();
					
					if(rs.next()){
						passwordField.setText("");
						usernameField.setText("");
						nameField.setText("");
						cashField.setText("");
						cocaineField.setText("");
						errorMsg.setText("Username already exists");
					}else{
						String regQuery = "insert into drugLordUser values(?,?)";
						st = con.prepareStatement(regQuery);
						st.setString(1, userName);
						st.setString(2, password);
						rs = st.executeQuery();
						
						String addQuery = "insert into DrugLord values(0,?,?,?,NULL,?)";
						st = con.prepareStatement(addQuery);
						st.setString(1, cashst);
						st.setString(2, cocaine);
						st.setString(3, name);
						st.setString(4, userName);
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
		passwordField.addActionListener(regListener);
		regButton.addActionListener(regListener);
		
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
