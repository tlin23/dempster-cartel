package LoginUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class registrationInterface {
	Connection con;
	
	private JFrame mainFrame;
	
	public registrationInterface(final Connection con){
		
		this.con = con;
		mainFrame = new JFrame("Register an account");
		JLabel regLabel = new JLabel("Register an account as a");
		JButton dlButton = new JButton("DrugLord");
		JButton drButton = new JButton("Dealer");
		JButton aButton = new JButton("Addict");
		
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
		gb.setConstraints(regLabel, c);
		contentPane.add(regLabel);
		
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
		ActionListener dlListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new dlRegInterface(con);
				mainFrame.dispose();
			}
		};
		//Drug Lord button event handler
		dlButton.addActionListener(dlListener);
		
		ActionListener dListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new dRegInterface(con);
				mainFrame.dispose();
			}
		};
		//Dealer button event handler
		drButton.addActionListener(dListener);
		
		ActionListener aListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new aRegInterface(con);
				mainFrame.dispose();
			}
		};
		//Dealer button event handler
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

