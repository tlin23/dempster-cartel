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

public class dealerInterface {
	// Window Frames in Dealer UI
	private JFrame mainFrame;
	private Connection clientcon;
	private String user;
	
	public dealerInterface(final Connection con){
		System.out.println("\nLogged in as Dealer");
		clientcon = con;
		this.user = user;
		
		mainFrame = new JFrame("Dealer View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Log Out");
		JButton viewAddicts = new JButton("View All Addicts");
		JButton viewDealers = new JButton("View All Dealers");
		JButton viewDruglords = new JButton("View All Druglords");
		JButton viewTerritories = new JButton("View All Territories");
		JButton viewDistTrans = new JButton("View All Distribution Transactions");
		JButton viewAddictDebt = new JButton("View Total Addict Debt");
		JButton makeDistTrans = new JButton("Make Distribution Transaction");
		JLabel addictView = new JLabel ("Dealer View Menu");
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		//view DistTrans button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewDistTrans, c);
		contentPane.add(viewDistTrans);
				
		
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
		
		//view Druglords button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewDruglords, c);
		contentPane.add(viewDruglords);
		
		//view Territories button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewTerritories, c);
		contentPane.add(viewTerritories);
		
		//view viewAddictDebt button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewAddictDebt, c);   	
		contentPane.add(viewAddictDebt);	
		
		//view DistTrans button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(makeDistTrans, c);
		contentPane.add(makeDistTrans);
		
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
			
			private void showAddicts(){
				final JFrame dataFrame = new JFrame("View All Addicts");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "AID","Name" };
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
					List<AddictData> addictData = DataQueries.getAddicts();
					for (AddictData a : addictData) {
						Object[] o = new Object[2];
						o[0] = a.AID;
						o[1] = a.name;
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
							
							int selectedAID = (int) jt.getModel().getValueAt(row,0);
							
							if(column == 0){
					
								editAddict(selectedAID);
								dataFrame.dispose();
								
							}
						}
					}
					
					private void editAddict(final int selectedAID) {
						final JFrame dataFrame = new JFrame("Edit Addict");
						JButton uButton = new JButton("Update");
						JButton dButton = new JButton("Delete");
						
						JPanel contentPane = new JPanel();
						dataFrame.setContentPane(contentPane);
						
						GridBagLayout gb = new GridBagLayout();
						GridBagConstraints c = new GridBagConstraints();
						contentPane.setLayout(gb);
						contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
						
						//Update Button
						c.gridwidth = GridBagConstraints.REMAINDER;
						c.insets = new Insets(5, 10, 10, 10);
						c.weightx= 0;
						c.fill = GridBagConstraints.NONE;
						c.anchor = GridBagConstraints.CENTER;
						gb.setConstraints(uButton, c);
						contentPane.add(uButton);

						//Delete Button
						c.gridwidth = GridBagConstraints.REMAINDER;
						c.insets = new Insets(5, 10, 10, 10);
						c.weightx= 0;
						c.fill = GridBagConstraints.NONE;
						c.anchor = GridBagConstraints.CENTER;
						gb.setConstraints(dButton, c);
						contentPane.add(dButton);
						
						ActionListener uListener = new ActionListener(){
							public void actionPerformed(ActionEvent e){
								updateAddict(selectedAID);
								dataFrame.dispose();
							}
						};
						//Drug Lord button event handler
						uButton.addActionListener(uListener);
						
						ActionListener dListener = new ActionListener(){
							public void actionPerformed(ActionEvent e){
								deleteAddict(selectedAID);
								dataFrame.dispose();
							}
						};
						
						dButton.addActionListener(dListener);
						
						dataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						//Resize window
						dataFrame.pack();
						Dimension d = dataFrame.getToolkit().getScreenSize();
						Rectangle r = dataFrame.getBounds();
						dataFrame.setLocation((d.width - r.width)/2, (d.height - r.height)/2);
							    
						//other default window features
						dataFrame.setVisible(true);
						
					}
						
					private void updateAddict(final int updateAID){
						final JTextField  nameField;
						final JTextField cashField;
						final JFrame dataFrame = new JFrame("Update Addict");		
						JLabel titleLabel = new JLabel("Update Addict: "+ updateAID);
						JButton updateButton = new JButton("Update");
						JLabel nameLabel = new JLabel("Addict New Name: ");
						JLabel cashLabel = new JLabel("Cash: ");
						nameField = new JTextField(10);
						cashField = new JTextField (10);
						
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
								
						// name Field 
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
						
						//rate button
						c.gridwidth = GridBagConstraints.REMAINDER;
						c.insets = new Insets(5, 10, 10, 10);
						c.weightx= 0;
						c.fill = GridBagConstraints.NONE;
						c.anchor = GridBagConstraints.CENTER;
						gb.setConstraints(updateButton, c);
						contentPane.add(updateButton);
						
						//Error message
						final JLabel errorMsg = new JLabel("");
						errorMsg.setForeground(Color.red);
						c.gridwidth = GridBagConstraints.REMAINDER;
						c.insets = new Insets(10, 10, 5, 0);
						gb.setConstraints(errorMsg, c);
						contentPane.add(errorMsg);
						
						ActionListener userListener = new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								
								String newNameSt = nameField.getText();
								String cashSt = cashField.getText();
		
								
								try{
									PreparedStatement st;
									ResultSet rs;														
									String reQuery = "UPDATE addict SET cash = ?,name = ? WHERE AID = ?";
									st = con.prepareStatement(reQuery);
									st.setString(1, cashSt);
									st.setString(2, newNameSt);
									st.setString(3, Integer.toString(updateAID));
									rs = st.executeQuery();
										
									showAddicts();
									dataFrame.dispose();								
								}catch(SQLException e1){
									System.out.println("Error: " + e1.getMessage());
									errorMsg.setText("Invalid Input");
								}
								
	
							}
						};
						
						nameField.addActionListener(userListener);
						updateButton.addActionListener(userListener);
						
						
						dataFrame.pack();
						dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						dataFrame.setVisible(true);
						
					    Dimension d1 = dataFrame.getToolkit().getScreenSize();
					    Rectangle r1 = dataFrame.getBounds();
					    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
					
					}
						
					private void deleteAddict(final int deleteAID){
						final JFrame dataFrame = new JFrame("Delete Addict");
						JButton yButton = new JButton("Yes");
						JButton nButton = new JButton("No");
						JLabel titleLabel = new JLabel("Are you sure you want to remove: "+ deleteAID);
					
						JPanel contentPane = new JPanel();
						dataFrame.setContentPane(contentPane);
						
						GridBagLayout gb = new GridBagLayout();
						GridBagConstraints c = new GridBagConstraints();
						
						//title
						c.gridwidth = GridBagConstraints.REMAINDER;
						c.insets = new Insets(10, 10, 5, 0);
						gb.setConstraints(titleLabel,c);
						contentPane.add(titleLabel);
						
						// name Label
						c.gridwidth = GridBagConstraints.RELATIVE;
						c.insets = new Insets(10, 10, 5, 0);
						gb.setConstraints(yButton, c);
						contentPane.add(yButton);
								
						// name Field 
						c.gridwidth = GridBagConstraints.REMAINDER;
						c.insets = new Insets(10, 0, 5, 10);
						c.weightx=1.;
						c.fill = GridBagConstraints.NONE;
						gb.setConstraints(nButton, c);
						contentPane.add(nButton);
						
						
						ActionListener yesListener = new ActionListener(){
							public void actionPerformed(ActionEvent e){	
								try{
									PreparedStatement st;						
									String reQuery = "DELETE FROM addict WHERE AID = ?";
									st = con.prepareStatement(reQuery);
									st.setString(1,Integer.toString(deleteAID));
									ResultSet rs = st.executeQuery();
										
									showAddicts();
									dataFrame.dispose();								
								}catch(SQLException e1){
									System.out.println("Error: " + e1.getMessage());
								}								
							}
						};
						yButton.addActionListener(yesListener);
						
						ActionListener noListener = new ActionListener(){
							public void actionPerformed(ActionEvent e){
								showAddicts();
								dataFrame.dispose();
							}
						};
						nButton.addActionListener(noListener);
						
						dataFrame.pack();
						dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						dataFrame.setVisible(true);
						
					    Dimension d1 = dataFrame.getToolkit().getScreenSize();
					    Rectangle r1 = dataFrame.getBounds();
					    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
					}
				});
				
				JPanel bot = new JPanel(new FlowLayout());
				JLabel rateLabel = new JLabel("Want to Update or Delete an Addict? Double Click the Addict's AID!");
				
				
				bot.add(rateLabel);
				dataFrame.add(bot,BorderLayout.SOUTH);
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		viewDealers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDealers();
				//mainFrame.dispose(); //not yet
			}
			
			private void showDealers(){
				JFrame dataFrame = new JFrame("View All Dealers");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "Name", "Cash", "Cocaine" };
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
					List<DealerData> dealerData = DataQueries.getDealers();
					for (DealerData d : dealerData) {
						Object[] o = new Object[3];
						o[0] = d.name;
						o[1] = d.cash;
						o[2] = d.cocaine;
						model.addRow(o);
					}
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage());
					Login.showErrorConnecting(mainFrame);
				}
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		viewDruglords.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDruglords();
				//mainFrame.dispose(); //not yet
			}
			
			private void showDruglords(){
				
				JFrame dataFrame = new JFrame("View All Druglords");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "Name", "Cash", "Cocaine" };
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
					List<DrugLordData> druglordData = DataQueries.getDruglords();
					for (DrugLordData dl : druglordData) {
						Object[] o = new Object[3];
						o[0] = dl.name;
						o[1] = dl.cash;
						o[2] = dl.cocaine;
						model.addRow(o);
					}
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage());
					Login.showErrorConnecting(mainFrame);
				}
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		viewDistTrans.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDistTrans();
				//mainFrame.dispose(); //not yet
			}

			private void showDistTrans() {
				JFrame dataFrame = new JFrame("View All Distribution Transactions");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "DTID", "Cash", "Cocaine", "Date", "DID", "DLID", "TID", "AID" };
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
					List<DistTransData> distTransData = DataQueries.getDistTrans();
					for (DistTransData d : distTransData) {
						Object[] o = new Object[8];
						o[0] = d.DTID;
						o[1] = d.cash;
						o[2] = d.cocaine;
						o[3] = d.transDate;
						o[4] = d.DID;
						o[5] = d.DLID;
						o[6] = d.TID;
						o[7] = d.AID;
						model.addRow(o);
					}
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage());
					Login.showErrorConnecting(mainFrame);
				}
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		viewTerritories.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showTerritories();
				//mainFrame.dispose(); //not yet
			}

			private void showTerritories() {
				JFrame dataFrame = new JFrame("View All Territories");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
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
					List<TerritoryData> territoryData = DataQueries.getTerritories();
					for (TerritoryData d : territoryData) {
						Object[] o = new Object[3];
						o[0] = d.name;
						model.addRow(o);
					}
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage());
					Login.showErrorConnecting(mainFrame);
				}
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		viewAddictDebt.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				showAddictDebt();							
				//mainFrame.dispose(); //not yet
			}
			
			private void showAddictDebt(){									
				JFrame dataFrame = new JFrame("View Total Addict Debt");	
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "Cash"};					
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
					List<AddictData> addictData = DataQueries.getAddictDebt();	
					for (AddictData a : addictData) {
						Object[] o = new Object[1];
						o[0] = a.cash;
						model.addRow(o);
					}
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage());
					Login.showErrorConnecting(mainFrame);
				}
				
				dataFrame.pack();
				dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dataFrame.setVisible(true);
				
			    Dimension d1 = dataFrame.getToolkit().getScreenSize();
			    Rectangle r1 = dataFrame.getBounds();
			    dataFrame.setLocation((d1.width - r1.width)/2, (d1.height - r1.height)/2);
			    
			    //other default window features
			}
		});
		
		makeDistTrans.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				showTotalCashDealers();
				new makeDistTransInterface(con);
				mainFrame.dispose(); //not yet
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Login();
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
