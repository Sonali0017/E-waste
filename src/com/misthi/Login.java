package com.misthi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.misthi.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Canvas;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 453);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Username");
		lblLogin.setBackground(new Color(255, 0, 0));
		lblLogin.setBounds(23, 47, 104, 14);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(23, 105, 71, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setBounds(23, 66, 206, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(23, 124, 206, 28);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 0, 0));
		btnLogin.setForeground(new Color(240, 248, 255));
		btnLogin.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 String user,passw;
				 int userid ;
				 user=textField.getText();
				 passw=passwordField.getText();
					if(user.equals(" ")||passw.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Enter complete details");
					}else {
							try {
						
						Connection con=Connect.ConnectDB();
					  Statement stmt=con.createStatement();
					  String sql="Select * from details where Username='"+textField.getText()+"' and Password='"+passwordField.getText().toString()+"' and Status='active'";
				 ResultSet rs= stmt.executeQuery(sql);
				 if (rs.next())
				 { JOptionPane.showMessageDialog(null, "Login Succesfully");
				 dispose();
				 Statement smt=con.createStatement();
                 smt.executeQuery("Select Id from details where Username='"+textField.getText()+"'");
                
                userid =rs.getInt(1);
               UserRequest.main(null);
				UserRequest.setUserID(userid);
				 }
				 else
					 JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				 
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
					}}});
		btnLogin.setBounds(23, 163, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setBackground(new Color(0, 0, 0));
		lblLoginPage.setForeground(new Color(0, 0, 0));
		lblLoginPage.setBounds(81, 22, 71, 14);
		contentPane.add(lblLoginPage);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(240, 248, 255));
		btnReset.setBackground(new Color(0, 0, 255));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			textField.setText("");
				passwordField.setText("");
			}
		});
		btnReset.setBounds(139, 163, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(240, 248, 255));
		btnSignUp.setBackground(new Color(238, 130, 238));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Register nw=new Register();
				nw.NewScreen();
			}
		});
		btnSignUp.setBounds(81, 197, 89, 23);
		contentPane.add(btnSignUp);
		
		JLabel lblForgotPassword = new JLabel("Forgot password?");
		lblForgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				ForgetPassword fb= new ForgetPassword();
				fb.setVisible(true);
				
				fb.setLocationRelativeTo(null);
				fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
					
		});
		
		
		lblForgotPassword.setBounds(45, 231, 125, 28);
		contentPane.add(lblForgotPassword);
		
		JLabel lblNewLabel = new JLabel("New label");
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));

		lblNewLabel.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel);
	}
}