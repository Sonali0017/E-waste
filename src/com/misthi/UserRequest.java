package com.misthi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UserRequest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRequest frame = new UserRequest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
static  int uid;
public static void setUserID(int u) {uid=u;}
	public UserRequest() {
		
		//JOptionPane.showMessageDialog(null, useri);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 453);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogout = new JButton("View Status");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				StatusView.main(null);
				StatusView.setUserID(uid);
			}
		});
		
		JLabel lblUsername = new JLabel("  Username");
		lblUsername.setBounds(10, 82, 77, 14);
		contentPane.add(lblUsername);
		btnLogout.setForeground(new Color(240, 248, 255));
		btnLogout.setBackground(new Color(255, 0, 0));
		btnLogout.setBounds(10, 11, 105, 23);
		contentPane.add(btnLogout);
		
		JLabel lblSendRequest = new JLabel("Send Request");
		lblSendRequest.setHorizontalAlignment(SwingConstants.CENTER);
		lblSendRequest.setBounds(104, 45, 85, 23);
		contentPane.add(lblSendRequest);
		
		JLabel lblFlatNumber = new JLabel("Flat number");
		lblFlatNumber.setBounds(10, 113, 64, 14);
		contentPane.add(lblFlatNumber);
		
		textField = new JTextField();
		textField.setBounds(124, 110, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 138, 77, 14);
		contentPane.add(lblDescription);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 141, 103, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(10, 184, 46, 14);
		contentPane.add(lblContact);
		
		textField_2 = new JTextField();
		textField_2.setBounds(124, 181, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(124, 79, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.setForeground(new Color(240, 248, 255));
		btnSendRequest.setBackground(new Color(0, 0, 205));
		btnSendRequest.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
			
					String add,desc,cont,name,d,status;
					int userid;
					userid=uid;
					add=textField.getText();
					desc=textField_1.getText();
					cont=textField_2.getText();
					name=textField_3.getText();
					d=formatter.format(date);
					status="To be pickedup";
					
					
					
					if(name.equals("")||add.equals(" ")||desc.equals("")||cont.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Enter complete details");
					}else {
						try {
						Connection con=Connect.ConnectDB();
						
						
						PreparedStatement ps1= con.prepareStatement("select * from request where Address=? and Description=? and Name=?");
						ps1.setString(1,add);
						ps1.setString(2,desc);
						ps1.setString(3,name);
						ResultSet rs1=ps1.executeQuery();
						if(rs1.next())
						{
							JOptionPane.showMessageDialog(null, "request already exist...");
						}
	    					else
	    					{
	    						
	{
						
						
						String query="insert into request (Name,Address,Description,Contact,Date,Status,UserId) values(?,?,?,?,?,?,?)";
						PreparedStatement pst=con.prepareStatement(query);
						if (add.equals(""))
						{
							pst.setString(1,null);
						}
						else
						{
							pst=con.prepareStatement(query);
							pst.setString(1,name);
								}
						pst.setString(2,add);
								pst.setString(3,desc);
								pst.setString(4,cont);
								pst.setString(5,d);
								pst.setString(6,status);
								pst.setInt(7, userid);
								pst.execute();
								pst.close();
								JOptionPane.showMessageDialog(null, "Request Accepted");}
						}}
								
					catch(Exception e1) {
						e1.printStackTrace();
					}}}
				
			
		});
		btnSendRequest.setBounds(110, 228, 117, 23);
		contentPane.add(btnSendRequest);
		
		JButton btnLogout_1 = new JButton("Logout");
		btnLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage hl=new HomePage();
	     		hl.main(null);
			}
		});
		btnLogout_1.setForeground(new Color(240, 248, 255));
		btnLogout_1.setBackground(new Color(255, 0, 0));
		btnLogout_1.setBounds(125, 11, 89, 23);
		contentPane.add(btnLogout_1);
		
		JLabel lblNewLabel2 = new JLabel("New label");
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		lblNewLabel2.setIcon(new ImageIcon(img));

		lblNewLabel2.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel2);		
	}

	

	

	}

