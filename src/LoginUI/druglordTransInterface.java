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

public class druglordTransInterface {
	
	// Window Frames in Druglord UI
	private JFrame mainFrame;
	private Connection clientcon;
	
	public druglordTransInterface(final Connection con, final String user){
		clientcon = con;
		
		mainFrame = new JFrame("Druglord Transaction View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Back");
		JButton makeSupplyTrans = new JButton("Make Supply Transaction");
		JButton giveDealersDrugs = new JButton("Give Dealer Drugs");
		JLabel addictView = new JLabel ("Druglord Transaction View");
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(addictView,c);
		contentPane.add(addictView);
		
		//makeSupplyTrans button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(makeSupplyTrans, c);
		contentPane.add(makeSupplyTrans);
	
		//giveDealerDrugs button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(giveDealersDrugs, c);
		contentPane.add(giveDealersDrugs);
		
		//exit button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(exitButton, c);
		contentPane.add(exitButton);
		
		makeSupplyTrans.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new makeSupplyTransInterface(con,user);
				mainFrame.dispose(); //not yet
			}
		});
		
		giveDealersDrugs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new giveDealersDrugsInteface(con,user);
				mainFrame.dispose(); //not yet
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
