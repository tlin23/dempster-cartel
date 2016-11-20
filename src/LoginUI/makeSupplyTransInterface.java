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

public class makeSupplyTransInterface {
	
	// Window Frames in Druglord UI
	private JFrame mainFrame;
	private Connection clientcon;
	private String user;
	
	public makeSupplyTransInterface(final Connection con){
		clientcon = con;
		this.user = user;
		
		mainFrame = new JFrame("Druglord Make Supply Transaction View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton executeButton = new JButton("Execute");
		JLabel addictView = new JLabel ("Supply Transaction");
		final JTextField supplierField = new JTextField(10);
		final JTextField druglordField = new JTextField(10);
		final JTextField cashAmountField = new JTextField(10);
		final JTextField cocaineAmountField = new JTextField(10);
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		// supplier Label
		JLabel supplierLabel = new JLabel("Supplier Name: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(supplierLabel, c);
		contentPane.add(supplierLabel);
		
		// supplier Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(supplierField, c);
		contentPane.add(supplierField);
		
		// druglord Label
		JLabel druglordLabel = new JLabel("Druglord Username: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(druglordLabel, c);
		contentPane.add(druglordLabel);
		
		// druglord Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(druglordField, c);
		contentPane.add(druglordField);
		
		// cash Label
		JLabel cashLabel = new JLabel("Cash Amount: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cashLabel, c);
		contentPane.add(cashLabel);
		
		// cash Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cashAmountField, c);
		contentPane.add(cashAmountField);
		
		// cocaine Label
		JLabel cocaineLabel = new JLabel("Cocaine Amount: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(cocaineLabel, c);
		contentPane.add(cocaineLabel);
		
		// cocaine Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(cocaineAmountField, c);
		contentPane.add(cocaineAmountField);
		
		//execute Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(executeButton, c);
		contentPane.add(executeButton);
		
		//Logout button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(exitButton, c);
		contentPane.add(exitButton);
		
		//Error message
		final JLabel errorMsg = new JLabel("");
		errorMsg.setForeground(Color.red);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(errorMsg, c);
		contentPane.add(errorMsg);
		
		
		executeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String supplier = supplierField.getText();
				String druglord = druglordField.getText();
				String cashAmount = cashAmountField.getText();
				String cocaineAmount = cocaineAmountField.getText();
				
				executeTrans(supplier, druglord, cashAmount, cocaineAmount);
			}
			
			private void executeTrans(String supplier, String druglord, String cashAmount, String cocaineAmount) {
				System.out.println("Supplier: " + supplier + " Druglord: " + druglord + " cashAmount: " + cashAmount + " cocaineAmount: " + cocaineAmount);
				int sid = -1;
				int dlid = -1;
				int dlCash = -1;
				try {
					List<SupplierData> supplierData = DataQueries.findSuppliersByExactName(supplier);
					if (supplierData.size() == 1) {
						sid = supplierData.get(0).SID;
					} else {
						errorMsg.setText("Invalid supplier name");
						return;
					}
					
					List<DrugLordData> druglordData = DataQueries.findDruglordsByExactUsername(druglord);
					if (druglordData.size() == 1) {
						dlid = druglordData.get(0).DLID;
						dlCash = druglordData.get(0).cash;
					} else {
						errorMsg.setText("Invalid Druglord username");
						return;
					}
					if (dlCash - Integer.parseInt(cashAmount) < 0) {
						errorMsg.setText("Not enough cash");
						return;
					} else {
						if (DataQueries.makeSupplyTrans(Integer.toString(sid), Integer.toString(dlid), cashAmount, cocaineAmount)){
							errorMsg.setText("Success!");
						} else {
							errorMsg.setText("Oops! Something went wrong :(");
							return;
						}
					}
				}
				catch (SQLException ex) {
					System.out.println(ex.getMessage());
					Login.showErrorConnecting(mainFrame);
				}
			}
		});
				
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new druglordTransInterface(con);
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
