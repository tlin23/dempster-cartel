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
	private Connection clientcon;
	private String user;
	

	public addictInterface(Connection con, String user){
		System.out.println("\nLogged in as Addict");
		clientcon = con;
		this.user = user;
		
		mainFrame = new JFrame("Addict View");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30 , 30 ,30));
		
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
				mainFrame.dispose();
			}
		});
	}
	
	private void showDealers(){
		
	}
	
	private void showAddicts(){
		
	}
}
