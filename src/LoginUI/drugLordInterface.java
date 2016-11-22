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

public class drugLordInterface {
	
	// Window Frames in Druglord UI
	private JFrame mainFrame;
	private Connection clientcon;
	
	public drugLordInterface(final Connection con, final String user){
		System.out.println("\nLogged in as Druglord");
		clientcon = con;
		
		mainFrame = new JFrame("Druglord Menu");
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
		
		JButton exitButton = new JButton("Log Out");
		JButton viewsButton = new JButton("Views");
		JButton addButton = new JButton("Add People");
		JButton makeTransButton = new JButton("Transactions");
		JButton removeButton = new JButton("Remove people");
		

		JLabel druglordView = new JLabel ("Druglord Menu");
		
		// Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(druglordView,c);
		contentPane.add(druglordView);
		
		//views button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(viewsButton, c);
		contentPane.add(viewsButton);
		
		//add button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(addButton, c);
		contentPane.add(addButton);
		
		//make trans button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(makeTransButton, c);
		contentPane.add(makeTransButton);
		
		//remove people button
//		c.gridwidth = GridBagConstraints.REMAINDER;
//		c.insets = new Insets(5, 10, 10, 10);
//		c.weightx= 0;
//		c.fill = GridBagConstraints.NONE;
//		c.anchor = GridBagConstraints.CENTER;
//		gb.setConstraints(removeButton, c);
//		contentPane.add(removeButton);

		//Logout button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(exitButton, c);
		contentPane.add(exitButton);
		
		
		viewsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new druglordViewInterface(con,user);
				mainFrame.dispose();
			}
		});
		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new druglordAddInterface(con,user);
				mainFrame.dispose();
			}
		});
		
		makeTransButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new druglordTransInterface(con,user);
				mainFrame.dispose();
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
