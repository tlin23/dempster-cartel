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

public class addictInterface{
	
	// Window Frames in Addict UI
	private JFrame mainFrame;
	private Connection clientCon;
	private String user;
	

	public addictInterface(Connection con, String user){
		System.out.println("\nLogged in as Addict");
		clientCon = con;
		this.user = user;
		
		mainFrame = new JFrame("Addict View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Log Out");
		JButton viewAddicts = new JButton("View Addicts");
		JButton viewDealers = new JButton("View Dealers");
		JLabel addictView = new JLabel ("Addict View Menu");
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
				
		//view Addicts button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewAddicts, c);
		contentPane.add(viewAddicts);
			
		//view Dealers button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewDealers, c);
		contentPane.add(viewDealers);
		
		//Logout button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(exitButton, c);
		contentPane.add(exitButton);
		
		viewAddicts.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showAddicts();
				//mainFrame.dispose(); //not yet
			}
		});
		
		viewDealers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDealers();
				//mainFrame.dispose(); //not yet
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Login();
				mainFrame.dispose();
			}
		});
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Resize window
		mainFrame.pack();
	    Dimension d1 = mainFrame.getToolkit().getScreenSize();
	    Rectangle r1 = mainFrame.getBounds();
	    mainFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
	    
	    //other default window features
	    mainFrame.setVisible(true);	
	}
	
	private void showDealers(){
		final JFrame dataFrame = new JFrame("Dealers");
		JPanel contentPane = new JPanel();
		dataFrame.setContentPane(contentPane);
		
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		String[] colNames = { "DID","Name","Rating" };
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};
		
		final JTable jt = new JTable();
		jt.setModel(model);
		model.setColumnIdentifiers(colNames);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setRowHeight(50);
		jt.setMinimumSize(new Dimension(200,50));
		dataFrame.setMinimumSize(new Dimension(300, 100));
		contentPane.add(new JScrollPane(jt));
		
		try {
			List<DealerData> dealerData = DataQueries.getDealers();
			for (DealerData d : dealerData) {
				Object[] o = new Object[3];
				o[0] = d.DID;
				o[1] = d.name;
				o[2] = d.rating;
				model.addRow(o);
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Login.showErrorConnecting(mainFrame);
		}

		jt.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount()== 2){
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					
					//System.out.println(row);
					//System.out.println(column);
					
					int selectedDID = (int) jt.getModel().getValueAt(row,0);
					//System.out.println(selectedName);
					
					String selectedName = (String) jt.getModel().getValueAt(row, 1);
					
					int selectedRating = (int) jt.getModel().getValueAt(row,2);
					//System.out.println(selectedName.toString());
					
					//System.out.println(selectedRating);
					if(column == 0){
						
						rateAddict(selectedDID,selectedName, selectedRating);
						dataFrame.dispose();
						
					}
				}
			}
		});
		
		JPanel bot = new JPanel(new FlowLayout());
		JLabel rateLabel = new JLabel("Want to Rate a Dealer? Double Click the Dealer's DID!");
		
		
		bot.add(rateLabel);
		dataFrame.add(bot,BorderLayout.SOUTH);
		
		
		dataFrame.pack();
		dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataFrame.setVisible(true);
		
	    Dimension d1 = dataFrame.getToolkit().getScreenSize();
	    Rectangle r1 = dataFrame.getBounds();
	    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
	}
	
	private void rateAddict(final int DID,final String name, int rating){
		final JTextField rateField;
		final JFrame dataFrame = new JFrame("Rate Dealer");		
		JLabel titleLabel = new JLabel("Rate This Dealer!");
		JButton rateButton = new JButton("Done");
		JLabel nameLabel = new JLabel("Dealer Name: ");
		JLabel dLabel = new JLabel(name);
		JLabel rateLabel = new JLabel("Rate from 0 to 5: ");
		rateField = new JTextField(10);
		
		JPanel contentPane = new JPanel();
		dataFrame.setContentPane(contentPane);
		
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(titleLabel,c);
		contentPane.add(titleLabel);
		
		// name Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(nameLabel, c);
		contentPane.add(nameLabel);
				
		// dealer name
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(dLabel, c);
		contentPane.add(dLabel);
				
		// Password Label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(rateLabel, c);
		contentPane.add(rateLabel);
				
		// Password Field 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(rateField, c);
		contentPane.add(rateField);
		
		//rate button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(rateButton, c);
		contentPane.add(rateButton);
		
		ActionListener userListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String rateSt = rateField.getText();
				String checkQuery = "select rating from dealer where DID = ?";
				
				try{
					PreparedStatement st = clientCon.prepareStatement(checkQuery);
					st.setString(1,Integer.toString(DID));
					ResultSet rs = st.executeQuery();
					
					if(rs.next()){
						String oldRating = rs.getString("rating");
						if(rs.wasNull()) oldRating = "";
						System.out.println(oldRating);
					
					
						int newRating;
						if (oldRating.equals("")){
							newRating = Integer.parseInt(rateSt);
						}else{
							newRating = Integer.parseInt(rateSt) + Integer.parseInt(oldRating);
							newRating = newRating / 2;
						}
						
						System.out.println(newRating);
						
						
						String reQuery = "UPDATE dealer SET rating = ? WHERE DID = ?";
						st = clientCon.prepareStatement(reQuery);
						st.setString(1, Integer.toString(newRating));
						st.setString(2, Integer.toString(DID));
						rs = st.executeQuery();
						
						showDealers();
						dataFrame.dispose();
						
						
					}
					
				}catch(SQLException e1){
					System.out.println("Error: " + e1.getMessage());
				}
			
			}
		};
		
		rateField.addActionListener(userListener);
		rateButton.addActionListener(userListener);
		
		
		dataFrame.pack();
		dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataFrame.setVisible(true);
		
	    Dimension d1 = dataFrame.getToolkit().getScreenSize();
	    Rectangle r1 = dataFrame.getBounds();
	    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
	}
	
	private void showAddicts(){
		final JFrame dataFrame = new JFrame("Addict");
		JPanel contentPane = new JPanel();
		dataFrame.setContentPane(contentPane);
		
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		String[] colNames = { "Name" };
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};
		
		JTable jt = new JTable();
		jt.setModel(model);
		model.setColumnIdentifiers(colNames);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setRowHeight(50);
		jt.setMinimumSize(new Dimension(200,50));
		dataFrame.setMinimumSize(new Dimension(300, 100));
		contentPane.add(new JScrollPane(jt));
		
		try {
			List<AddictData> addictData = DataQueries.getAddicts();
			for (AddictData a : addictData) {
				Object[] o = new Object[1];
				o[0] = a.name;
				model.addRow(o);
			}
		}
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Login.showErrorConnecting(mainFrame);
		}
		
		/*JPanel bot = new JPanel(new FlowLayout());
		JButton addAddictButton = new JButton("Join the Community, Add Addict");
		
		addAddictButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAddict();
				dataFrame.dispose();
			}
		});
		bot.add(addAddictButton);
		dataFrame.add(bot,BorderLayout.SOUTH);*/
		
		dataFrame.pack();
		dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataFrame.setVisible(true);
		
	    Dimension d1 = dataFrame.getToolkit().getScreenSize();
	    Rectangle r1 = dataFrame.getBounds();
	    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
	}
	
	private void addAddict(){
		final JTextField nameField;
		final JTextField cashField;
		final JFrame dataFrame = new JFrame("Add Addict");		
		JLabel regLabel = new JLabel("Register Here!");
		JButton regButton = new JButton("Login");
		JLabel nameLabel = new JLabel("Enter name: ");
		JLabel cashLabel = new JLabel("Enter cash: ");
		nameField = new JTextField(10);
		cashField = new JTextField(10);
		
		JPanel contentPane = new JPanel();
		dataFrame.setContentPane(contentPane);
		
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(regLabel,c);
		contentPane.add(regLabel);
		
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
		
		ActionListener userListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String cashst = cashField.getText();
				String checkQuery = "select name from addict where name = ?";
				
				try{
					PreparedStatement st = clientCon.prepareStatement(checkQuery);
					st.setString(1, name);
					ResultSet rs = st.executeQuery();
					
					if(rs.next()){
						nameField.setText("");
						cashField.setText("");
						errorMsg.setText("name already exists");
					}else{
						String regQuery = "insert into addict values(0,?,?)";
						st = clientCon.prepareStatement(regQuery);
						System.out.println(regQuery);
						st.setString(1, cashst);
						st.setString(2, name);
						rs = st.executeQuery();
						
						showAddicts();
						dataFrame.dispose();
	
					}
					
				}catch(SQLException e1){
					System.out.println("Error: " + e1.getMessage());
				}
			
			}
		};
				
		nameField.addActionListener(userListener);
		regButton.addActionListener(userListener);
		
		
		dataFrame.pack();
		dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataFrame.setVisible(true);
		
	    Dimension d1 = dataFrame.getToolkit().getScreenSize();
	    Rectangle r1 = dataFrame.getBounds();
	    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
	}
}	