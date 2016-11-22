package LoginUI;

import java.awt.Color;
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
import javax.swing.JTextField;

import querySet.AddictData;
import querySet.DataQueries;
import querySet.DealerData;
import querySet.DrugLordData;
import querySet.TerritoryData;

public class giveDealersDrugsInteface {
	private JFrame mainFrame;
	private Connection clientcon;
	
	public giveDealersDrugsInteface(final Connection con,final String user){
		clientcon = con;
		
		mainFrame = new JFrame("Give Dealer Drugs");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton executeButton = new JButton("Execute");
		JLabel addictView = new JLabel ("Give Dealer Drugs");
		final JTextField dealerField = new JTextField(10);
		final JTextField cashAmountField = new JTextField(10);
		final JTextField cocaineAmountField = new JTextField(10);

		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		// dealer Label
		JLabel dealerLabel = new JLabel("Dealer Username: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(dealerLabel, c);
		contentPane.add(dealerLabel);
		
		// dealer Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(dealerField, c);
		contentPane.add(dealerField);
		
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
				String dealer = dealerField.getText();
				String cashAmount = cashAmountField.getText();
				String cocaineAmount = cocaineAmountField.getText();
				
				executeTrans(dealer,cashAmount, cocaineAmount);
			}
			
			private void executeTrans(String dealer, String cashAmount, String cocaineAmount) {
				int did = -1;
				int dlid = -1;
				int tid = -1;
				int aid = -1;
				int druglordCocaine = -1;
				int dealerCashLeft = -1;
				try {
					List<DrugLordData> druglordData = DataQueries.findDruglordsByExactUsername(user);
					if (druglordData.size() == 1) {
						dlid = druglordData.get(0).DLID;
						druglordCocaine = druglordData.get(0).cocaine;
					} else {
						errorMsg.setText("Invalid Druglord username");
						return;
					}
					
					List<DealerData> dealerData = DataQueries.findDealersByExactNameAndDLID(dealer,Integer.toString(dlid));
					if (dealerData.size() == 1) {
						did = dealerData.get(0).DID;
						dealerCashLeft = dealerData.get(0).cash;
					} else {
						errorMsg.setText("Invalid Dealer username (Reminder: must be your dealer)");
						return;
					}
					
					
					
					if (druglordCocaine - Integer.parseInt(cocaineAmount) < 0) {
						errorMsg.setText("Druglord does not have enough cocaine");
						return;
					} else if (dealerCashLeft - Integer.parseInt(cashAmount) < 0) {
						errorMsg.setText("Dealer does not have enough cash");
						return;
					} else {
						if (DataQueries.giveDealerDrugs(Integer.toString(did), Integer.toString(dlid), cashAmount, cocaineAmount)){
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
