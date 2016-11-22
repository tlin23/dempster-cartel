package LoginUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import querySet.DataQueries;
import querySet.DealerData;
import querySet.SupplyTransData;

public class totalCashDealersInterface {
	// Window Frames in totalCashDealers UI
	private JFrame mainFrame;
	private Connection clientcon;

	
	public totalCashDealersInterface(final Connection con,final String user){
		clientcon = con;
		
		mainFrame = new JFrame("View Total Money To Collect From Dealers (and their Addicts)");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		
		JButton exitButton = new JButton("Back");
		JButton searchByNameButton = new JButton("Search By Name");
		JLabel addictView = new JLabel ("Find $$$$$$ To Collect");
		final JTextField nameField = new JTextField(10);
		
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		// find by name Label
		JLabel nameLabel = new JLabel("Enter your druglord name: ");
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
				showTotalCashDealers(name);
			}

			private void showTotalCashDealers(String name) {
				JFrame dataFrame = new JFrame("View Total Money To Collect From Dealers (and their Addicts)");
				JPanel contentPane = new JPanel();
				dataFrame.setContentPane(contentPane);
				
				BorderLayout layout = new BorderLayout();
				contentPane.setLayout(layout);
				contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
				
				String[] colNames = { "Name", "Total Cash To Collect"};
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
					List<DealerData> dealerData = DataQueries.getTotalCashDealers(name);
					for (DealerData d : dealerData) {
						Object[] o = new Object[2];
						o[0] = d.name;
						o[1] = d.cash;
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
