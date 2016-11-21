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
import querySet.TerritoryData;

public class makeDistTransInterface {
	// Window Frames in Druglord UI
	private JFrame mainFrame;
	private Connection clientcon;
	
	public makeDistTransInterface(final Connection con){
		clientcon = con;
		
		mainFrame = new JFrame("Dealer Make Distribution Transaction View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton executeButton = new JButton("Execute");
		JLabel addictView = new JLabel ("Distribution Transaction");
		final JTextField dealerField = new JTextField(10);
		final JTextField territoryField = new JTextField(10);
		final JTextField addictField = new JTextField(10);
		final JTextField cashAmountField = new JTextField(10);
		final JTextField cocaineAmountField = new JTextField(10);
		final JTextField addictAmountPayField = new JTextField(10);
		
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
		
		// territory Label
		JLabel territoryLabel = new JLabel("Territory: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(territoryLabel, c);
		contentPane.add(territoryLabel);
		
		// territory Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(territoryField, c);
		contentPane.add(territoryField);
		
		// addict Label
		JLabel addictLabel = new JLabel("Addict Username: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictLabel, c);
		contentPane.add(addictLabel);
		
		// addict Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(addictField, c);
		contentPane.add(addictField);
		
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
		
		// addictAmountPay Label
		JLabel addictAmountPayLabel = new JLabel("Amount Addict Actually Pays: ");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictAmountPayLabel, c);
		contentPane.add(addictAmountPayLabel);
		
		// addictAmountPay Text Field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		c.weightx=1.;
		c.fill = GridBagConstraints.NONE;
		gb.setConstraints(addictAmountPayField, c);
		contentPane.add(addictAmountPayField);
		
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
				String territory = territoryField.getText();
				String addict = addictField.getText();
				String cashAmount = cashAmountField.getText();
				String cocaineAmount = cocaineAmountField.getText();
				String addictAmountPay = addictAmountPayField.getText();
				
				executeTrans(dealer, territory, addict, cashAmount, cocaineAmount, addictAmountPay);
			}
			
			private void executeTrans(String dealer, String territory, String addict, String cashAmount, String cocaineAmount, String addictAmountPay) {
//				System.out.println("Dealer: " + dealer + " Territory: " + territory + " Addict: " + addict + " cashAmount: " + cashAmount + " cocaineAmount: " + cocaineAmount + " Addict Payment: " + addictAmountPay);
				int did = -1;
				int tid = -1;
				int aid = -1;
				int dlCocaine = -1;
				int payment = Integer.parseInt(addictAmountPay);
				try {
					List<DealerData> dealerData = DataQueries.findDealersByExactName(dealer);
					System.out.println(dealerData);
					if (dealerData.size() == 1) {
						did = dealerData.get(0).DID;
						dlCocaine = dealerData.get(0).cocaine;
					} else {
						errorMsg.setText("Invalid Dealer username");
						return;
					}
					
					List<TerritoryData> territoryData = DataQueries.findTerritoriesByName(territory);
					if (territoryData.size() == 1) {
						tid = territoryData.get(0).TID;
					} else {
						errorMsg.setText("Invalid Territory name");
						return;
					}
					
					List<AddictData> addictData = DataQueries.findAddictsByExactName(addict);
					if (addictData.size() == 1) {
						aid = addictData.get(0).AID;
					} else {
						errorMsg.setText("Invalid Addict username");
						return;
					}
					
					if (dlCocaine - Integer.parseInt(cocaineAmount) < 0) {
						errorMsg.setText("Not enough cocaine");
						return;
					} else if(payment < 0) {
						errorMsg.setText("Non-sensical Addict Payment (must be non-negative)");
						return;
					} else if(payment > Integer.parseInt(cashAmount)) {
						errorMsg.setText("Non-sensical Addict Payment (can't be more than Cash Amount)");
						return;
					} else {
						if (DataQueries.makeDistTrans(Integer.toString(did), Integer.toString(tid), Integer.toString(aid), cashAmount, cocaineAmount, payment)){
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
				new dealerInterface(con);
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
