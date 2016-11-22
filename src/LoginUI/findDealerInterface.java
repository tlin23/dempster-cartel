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

public class findDealerInterface {
	
	// Window Frames in Druglord UI
	private JFrame mainFrame;
	private Connection clientcon;
	private String user;
	
	public findDealerInterface(final Connection con, final String user){
		clientcon = con;
		this.user = user;
		
		mainFrame = new JFrame("Druglord Find Dealer View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton searchByNameButton = new JButton("Search By Name");
		JButton searchByCashGreaterButton = new JButton("Search By Cash Greater");
		JButton searchByCashLesserButton = new JButton("Search By Cash Lesser");
		JButton searchByCocaineGreaterButton = new JButton("Search By Cocaine Greater");
		JButton searchByCocaineLesserButton = new JButton("Search By Cocaine Lesser");
		JLabel addictView = new JLabel ("Find Dealer");
		final JTextField nameField = new JTextField(10);
		final JTextField cashGreaterField = new JTextField(10);
		final JTextField cashLesserField = new JTextField(10);
		final JTextField cocaineGreaterField = new JTextField(10);
		final JTextField cocaineLesserField = new JTextField(10);
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		// find by name Label
		JLabel nameLabel = new JLabel("Find dealer with name: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(nameLabel, c);
		contentPane.add(nameLabel);
		
		// find by name Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(nameField, c);
		contentPane.add(nameField);
		
		//searchByName button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(searchByNameButton, c);
		contentPane.add(searchByNameButton);
		
		// find by cashGreater Label
		JLabel cashGreaterLabel = new JLabel("Find dealer with cash >: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cashGreaterLabel, c);
		contentPane.add(cashGreaterLabel);
		
		// find by cashGreater Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cashGreaterField, c);
		contentPane.add(cashGreaterField);
		
		//find by cashGreater Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(searchByCashGreaterButton, c);
		contentPane.add(searchByCashGreaterButton);
		
		// find by cashGreater Label
		JLabel cashLesserLabel = new JLabel("Find dealer with cash <: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cashLesserLabel, c);
		contentPane.add(cashLesserLabel);
		
		// find by cashLesser Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cashLesserField, c);
		contentPane.add(cashLesserField);
		
		//find by cashLesser Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(searchByCashLesserButton, c);
		contentPane.add(searchByCashLesserButton);
		
		// find by cocaineGreater Label
		JLabel cocaineGreaterLabel = new JLabel("Find dealer with cocaine >: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cocaineGreaterLabel, c);
		contentPane.add(cocaineGreaterLabel);
		
		// find by cocaineGreater Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cocaineGreaterField, c);
		contentPane.add(cocaineGreaterField);
		
		//find by cocaineGreater Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(searchByCocaineGreaterButton, c);
		contentPane.add(searchByCocaineGreaterButton);
		
		// find by cocaineLesser Label
		JLabel cocaineLesserLabel = new JLabel("Find dealer with cocaine <: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cocaineLesserLabel, c);
		contentPane.add(cocaineLesserLabel);
		
		// find by cocaineLesser Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cocaineLesserField, c);
		contentPane.add(cocaineLesserField);
		
		//find by cocaineLesser Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(searchByCocaineLesserButton, c);
		contentPane.add(searchByCocaineLesserButton);
		
		//Logout button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(exitButton, c);
		contentPane.add(exitButton);
		
		
		searchByNameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = nameField.getText();
				showSearchResult(name);
			}
			
			private void showSearchResult(String name) {
				JFrame dataFrame = new JFrame("Find Dealer");
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
					List<DealerData> dealerData = DataQueries.findDealersByName(name);
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
		
		searchByCashGreaterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cashGreater = cashGreaterField.getText();
				showSearchResult(cashGreater);
			}
			
			private void showSearchResult(String cashGreater) {
				JFrame dataFrame = new JFrame("Find Dealer");
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
					List<DealerData> dealerData = DataQueries.findDealersByCashGreater(cashGreater);
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
		
		searchByCashLesserButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cashLesser = cashLesserField.getText();
				showSearchResult(cashLesser);
			}
			
			private void showSearchResult(String cashLesser) {
				JFrame dataFrame = new JFrame("Find Dealer");
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
					List<DealerData> dealerData = DataQueries.findDealersByCashLesser(cashLesser);
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
		
		searchByCocaineGreaterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cocaineGreater = cocaineGreaterField.getText();
				showSearchResult(cocaineGreater);
			}
			
			private void showSearchResult(String cocaineGreater) {
				JFrame dataFrame = new JFrame("Find Dealer");
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
					List<DealerData> dealerData = DataQueries.findDealersByCocaineGreater(cocaineGreater);
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
		
		searchByCocaineLesserButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String cocaineLesser = cocaineLesserField.getText();
				showSearchResult(cocaineLesser);
			}
			
			private void showSearchResult(String cocaineLesser) {
				JFrame dataFrame = new JFrame("Find Dealer");
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
					List<DealerData> dealerData = DataQueries.findDealersByCocaineLesser(cocaineLesser);
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
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new druglordViewInterface(con,user);
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
