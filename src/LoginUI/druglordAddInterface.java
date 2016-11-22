package LoginUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;
import querySet.*;

public class druglordAddInterface {
	
	// Window Frames in Druglord UI
	private JFrame mainFrame;
	private Connection clientcon;
	
	public druglordAddInterface(final Connection con, final String user){
		clientcon = con;
		
		mainFrame = new JFrame("Druglord Add View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton addDruglordButton = new JButton("Add Druglord");
		JLabel addictView = new JLabel ("Druglord View Menu");
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		//add Druglord button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(addDruglordButton, c);
		contentPane.add(addDruglordButton);

		//Logout button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(exitButton, c);
		contentPane.add(exitButton);
		
		
		
		addDruglordButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDrulordRegInterface(con);
				//mainFrame.dispose(); //not yet
			}

			private void showDrulordRegInterface(final Connection con) {
				final JTextField usernameField;
				final JPasswordField passwordField;
				final JTextField nameField;
				final JTextField cashField;
				final JTextField cocaineField;
				JFrame dataFrame = new JFrame("DrugLord Registration");
				
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				JLabel regLabel = new JLabel("DrugLord Registration");
				JLabel usernameLabel = new JLabel("Enter UserName: ");
				JLabel passwordLabel = new JLabel("Enter Password: ");
				JLabel nameLabel = new JLabel("Enter name: ");
				JLabel cashLabel = new JLabel("Enter cash: ");
				JLabel cocaineLabel = new JLabel("Enter cocaine (in kg): ");
				
				usernameField = new JTextField(10);
				passwordField = new JPasswordField(10);
				nameField = new JTextField(10);
				cashField = new JTextField(10);
				cocaineField = new JTextField(10);
				passwordField.setEchoChar('*');
				JButton regButton = new JButton("Register");
				
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
								
								errorMsg.setText("Success!");
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
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new drugLordInterface(con,user);
				mainFrame.dispose();
			}
		});
		mainFrame.setMinimumSize(new Dimension(400, 200));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Resize window
		mainFrame.pack();
	    Dimension d1 = mainFrame.getToolkit().getScreenSize();
	    Rectangle r1 = mainFrame.getBounds();
	    mainFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
	    
	    //other default window features
	    mainFrame.setVisible(true);	
	}
}
