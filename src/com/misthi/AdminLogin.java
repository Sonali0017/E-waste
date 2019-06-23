package com.misthi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 453);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminLoginPage = new JLabel("Admin Login page");
		lblAdminLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLoginPage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAdminLoginPage.setBounds(127, 60, 134, 26);
		contentPane.add(lblAdminLoginPage);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUsername.setBounds(56, 84, 103, 26);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(56, 110, 103, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(56, 154, 83, 20);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.RED);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String user,passw;
				 user=textField.getText();
				 passw=passwordField.getText().toString();
					if(user.equals(" ")||passw.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Enter complete details");
					}else {
							try {
								
						Connection con=Connect.ConnectDB();
					  Statement stmt=con.createStatement();
					  String sql="Select * from admin where Username='"+user+"' and Password='"+passw+"'";
				 ResultSet rs= stmt.executeQuery(sql);
				 if (rs.next())
				 { JOptionPane.showMessageDialog(null, "Login Succesfully");
				 dispose();
				 AdminWelcome ad= new AdminWelcome();
					ad.main(null);
				 }	 else
					 JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
					}
			}
		});
		btnLogin.setBounds(142, 228, 89, 23);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(56, 176, 103, 26);
		contentPane.add(passwordField);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage hm=new HomePage();
						hm.main(null);
				}
		});
		btnHome.setBackground(Color.RED);
		btnHome.setBounds(23, 11, 89, 23);
		contentPane.add(btnHome);
		JLabel lblNewLabel = new JLabel("New label");
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));

		lblNewLabel.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel);
	}
}

