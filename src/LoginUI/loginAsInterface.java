package LoginUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class loginAsInterface {	

	private JFrame mainFrame;
	
	public loginAsInterface(final Connection con){		
		mainFrame = new JFrame("Login As");
		JLabel loginAsLabel = new JLabel("Login as:");
		JButton dlButton = new JButton("DrugLord");
		JButton drButton = new JButton("Dealer");
		JButton aButton = new JButton("Addict");
		JButton backButton = new JButton("Back");
		
		//Create and populate the window
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		
		//Layout componenets
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Title
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10,10,5,0);
		gb.setConstraints(loginAsLabel, c);
		contentPane.add(loginAsLabel);
		
		//DrugLord Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(dlButton, c);
		contentPane.add(dlButton);
		
		//Dealer Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(drButton, c);
		contentPane.add(drButton);
		
		//Addict Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(aButton, c);
		contentPane.add(aButton);
		
		//Back Button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.weightx= 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(backButton, c);
		contentPane.add(backButton);
		
		ActionListener backListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Login();
				mainFrame.dispose();
			}
		};
		//Drug Lord button event handler
		backButton.addActionListener(backListener);
		
		ActionListener dlListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new dlLoginInterface(con);
				mainFrame.dispose();
			}
		};
		//Drug Lord button event handler
		dlButton.addActionListener(dlListener);
		
		ActionListener dListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new dLoginInterface(con);
				mainFrame.dispose();
			}
		};
		//Dealer button event handler
		drButton.addActionListener(dListener);
		
		ActionListener aListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new aLoginInterface(con);
				mainFrame.dispose();
			}
		};
		//Addict button event handler
		aButton.addActionListener(aListener);
	
		// closing window
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
					System.exit(0);
			}
		});
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Resize window
		mainFrame.pack();
		Dimension d = mainFrame.getToolkit().getScreenSize();
		Rectangle r = mainFrame.getBounds();
		mainFrame.setLocation((d.width - r.width)/2, (d.height - r.height)/2);
			    
		//other default window features
		mainFrame.setVisible(true);
		}
}

