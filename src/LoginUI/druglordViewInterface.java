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

public class druglordViewInterface {
	
	// Window Frames in Drug lord UI
	private JFrame mainFrame;
	
	public druglordViewInterface(final Connection con, final String user){
		
		mainFrame = new JFrame("Druglord View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton viewAddicts = new JButton("View All Addicts");
		JButton viewDealers = new JButton("View All Dealers");
		JButton viewDruglords = new JButton("View All Druglords");
		JButton viewSuppliers = new JButton("View All Suppliers");
		JButton viewTerritories = new JButton("View All Territories");
		JButton viewDistTrans = new JButton("View All Distribution Transactions");
		JButton viewSupplyTrans = new JButton("View All Supply Transactions");
		JButton viewSummaryDealers = new JButton("View Summary of Dealer Sales");
		JButton viewDealerMostCash = new JButton("View Dealer with Most Owed Cash");
		JButton viewTotalCashDealers = new JButton("View Total Money To Collect From Dealers (and their Addicts)");
		JButton findDruglord = new JButton("Find a Druglord");
		JButton findDealer = new JButton("Find a Dealer");
		JButton findSupplier = new JButton("Find a Supplier");
		JButton findAddict = new JButton("Find a Addict");
		JButton findSketchyDealers = new JButton("Find Sketchy Dealer(s)");
		JLabel addictView = new JLabel ("Druglord View Menu");
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);		
		
		//Find Druglord button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(findDruglord, c);
		contentPane.add(findDruglord);
		
		//Find Dealer button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(findDealer, c);
		contentPane.add(findDealer);
		
		//Find Supplier button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(findSupplier, c);
		contentPane.add(findSupplier);
		
		//Find Supplier button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(findAddict, c);
		contentPane.add(findAddict);
		
		//view Supply button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewSupplyTrans, c);
		contentPane.add(viewSupplyTrans);
		
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
		
		//view Suppliers button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewSuppliers, c);
		contentPane.add(viewSuppliers);
		
		//view Territories button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewTerritories, c);
		contentPane.add(viewTerritories);
		
		//viewDealerMostCash button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewDealerMostCash, c);
		contentPane.add(viewDealerMostCash);
		
		//viewTotalCashDealers button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewTotalCashDealers, c);
		contentPane.add(viewTotalCashDealers);
		
		//view Dealer Summary button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewSummaryDealers, c);
		contentPane.add(viewSummaryDealers);

		//Find sketchy dealer(s) button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(findSketchyDealers, c);
		contentPane.add(findSketchyDealers);
		
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
				JFrame dataFrame = new JFrame("View All Addicts");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "Name", "Debt" };
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
						Object[] o = new Object[2];
						o[0] = a.name;
						o[1] = a.cash;
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
		
		viewSuppliers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showSuppliers();
				//mainFrame.dispose(); //not yet
			}

			private void showSuppliers() {
				JFrame dataFrame = new JFrame("View All Suppliers");
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
					List<SupplierData> supplierData = DataQueries.getSuppliers();
					for (SupplierData d : supplierData) {
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
		
		viewSupplyTrans.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showSupplyTrans();
				//mainFrame.dispose(); //not yet
			}

			private void showSupplyTrans() {
				JFrame dataFrame = new JFrame("View All Supply Transactions");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "STID", "Cash", "Cocaine", "TransDate", "SID", "DLID" };
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
					List<SupplyTransData> supplyTransData = DataQueries.getSupplyTrans();
					for (SupplyTransData d : supplyTransData) {
						Object[] o = new Object[6];
						o[0] = d.STID;
						o[1] = d.cash;
						o[2] = d.cocaine;
						o[3] = d.transDate;
						o[4] = d.SID;
						o[5] = d.DLID;
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
				
		viewTotalCashDealers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				showTotalCashDealers();
				new totalCashDealersInterface(con,user);
				mainFrame.dispose(); //not yet
			}
		});
	
		viewDealerMostCash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDealerMostCash();
				//mainFrame.dispose(); //not yet
			}
			private void showDealerMostCash(){
				JFrame dataFrame = new JFrame("View Dealer with Most Owed Cash");
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
					List<DealerData> dealerData = DataQueries.getDealerMostCash();
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
				
		viewSummaryDealers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showSummaryDealers();
				//mainFrame.dispose(); //not yet
			}
			private void showSummaryDealers(){
				JFrame dataFrame = new JFrame("View Summary of Dealer Sales");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "Name", "Total Sales", "Total Cocaine" };
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
					List<DealerData> dealerData = DataQueries.getSummaryDealers();
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
		

		findSketchyDealers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showSketchyDealers();
				//mainFrame.dispose(); //not yet
			}
			private void showSketchyDealers(){
				JFrame dataFrame = new JFrame("Find Sketchy Dealer(s)");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);

				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

				String[] colNames = { "Territory Name", "Dealer Name" };
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
					List<DealerData> dealerData = DataQueries.getSketchyDealers();
					for (DealerData d : dealerData) {
						Object[] o = new Object[2];
						o[0] = d.name;
						o[1] = d.dUserName;
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
		
		findDruglord.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new findDruglordInterface(con,user);
				mainFrame.dispose();
			}
		});
		
		findDealer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new findDealerInterface(con, user);
				mainFrame.dispose();
			}
		});
		
		findSupplier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new findSupplierInterface(con, user);
				mainFrame.dispose();
			}
		});

		findAddict.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new findAddictInterface(con, user);
				mainFrame.dispose();
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new drugLordInterface(con, user);
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
