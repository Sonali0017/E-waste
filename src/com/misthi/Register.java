package com.misthi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;

import java.awt.Color;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistrationForm.setBounds(99, 23, 193, 17);
		contentPane.add(lblRegistrationForm);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(38, 75, 68, 17);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(38, 113, 68, 17);
		contentPane.add(lblPassword);
		
		JLabel lblEmailid = new JLabel("EmailId");
		lblEmailid.setBounds(38, 146, 46, 14);
		contentPane.add(lblEmailid);
		
		JLabel lblContactNo = new JLabel("Contact ");
		lblContactNo.setBounds(38, 181, 57, 17);
		contentPane.add(lblContactNo);
		
		textField = new JTextField();
		textField.setBounds(178, 73, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(178, 143, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(178, 179, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(240, 248, 255));
		btnSubmit.setBackground(new Color(255, 0, 0));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Uname,pass,email,cont;
				Uname=textField.getText();
				pass=passwordField.getText();
				email=textField_2.getText();
				cont=textField_3.getText();
				String ot=textField_1.getText();
				
				
				
				
				if(Uname.equals("")||pass.equals("")||email.equals("")||cont.equals("")||ot.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter complete details");
				}else {
				try 
				 
{
					Connection con=Connect.ConnectDB();
					
					int ott=Integer.parseInt(ot);
					PreparedStatement ps1=con.prepareStatement("select * from details where Otp=?");
					ps1.setInt(1,ott);
					ResultSet rs =ps1.executeQuery();
					if(rs.next())
					{	PreparedStatement ps2=con.prepareStatement("update details set Status=? where Otp=?");
						ps2.setString(1,"active");
						ps2.setInt(2,ott);
						int i=ps2.executeUpdate();
							JOptionPane.showMessageDialog(null, "SignUp Successfully");
							dispose();
							Login ne=new Login();
							ne.main(null);
					}
					}
							
				catch(Exception e1) {
					e1.printStackTrace();
				}}
				
			}
		});
		btnSubmit.setBounds(27, 296, 89, 23);
		contentPane.add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 110, 87, 22);
		contentPane.add(passwordField);
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(240, 248, 255));
		btnLogin.setBackground(new Color(0, 0, 205));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Login ne=new Login();
				ne.main(null);
			}
		});
		btnLogin.setBounds(203, 296, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel1 = new JLabel("New label");
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		
		JLabel lblOtp = new JLabel("Otp");
		lblOtp.setBounds(38, 209, 109, 23);
		contentPane.add(lblOtp);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(178, 210, 86, 20);
		contentPane.add(textField_1);
		
		JButton btnGenerateOtp = new JButton("Generate Otp");
		btnGenerateOtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Uname,pass,email,cont,st;
				Uname=textField.getText();
				pass=passwordField.getText();
				email=textField_2.getText();
				cont=textField_3.getText();
				 if(Uname.equals("")||pass.equals("")||email.equals("")||cont.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Enter complete details");}
				 else
			try {
Connection con=Connect.ConnectDB();
				
				PreparedStatement ps1= con.prepareStatement("select * from details where EmailId=? and status='active'");
				ps1.setString(1,textField_2.getText());
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next())
				{
					JOptionPane.showMessageDialog(null, "User already exist...");
				}else {
			
					int otp=new Random().nextInt(99999);
				
				boolean isSent=false;
				Properties props=new Properties();
				props.put("mail.smtp.host","smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port","465");
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSlSocketFactory");
				props.put("mail.smtp.auth","true");
				props.put("mail.smtp.port","465");
				props.put("mail.smtp.ssl.enable", "true");
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("neelesh32008@gmail.com","Cbtrigger03@")	;
				}
				});
 
  String sub="ME";
  String msg=Integer.toString(otp);
				try {
					Message message=new MimeMessage(session);
				    message.setFrom(new InternetAddress("neelesh32008@gmail.com"));
				    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				    message.setSubject(sub);
				    message.setText(msg);
				    Transport.send(message);
				    isSent=true;
				    JOptionPane.showMessageDialog(null, "Mail sent");
				}catch (MessagingException e)
				{isSent=false;
				throw new RuntimeException(e);
					}
				try {
					
					
				String query="insert into details (Username,Password,EmailId,Contact,Status,Otp) values(?,?,?,?,?,?)";
				PreparedStatement pst=con.prepareStatement(query);
				if (Uname.equals(""))
				{
					pst.setString(1,null);
				}
				else
				{
					pst=con.prepareStatement(query);
					pst.setString(1,Uname);
						}
						pst.setString(2,pass);
						pst.setString(3,email);
						pst.setString(4,cont);
						pst.setString(5,"inactive");
						pst.setInt(6,otp);
						pst.execute();
						pst.close();}
			
catch(Exception e1) {
	e1.printStackTrace();
}
				}
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}}}
		);
		btnGenerateOtp.setBounds(284, 209, 99, 23);
		contentPane.add(btnGenerateOtp);
		lblNewLabel1.setIcon(new ImageIcon(img));

		lblNewLabel1.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel1);
		
		
	}
}
