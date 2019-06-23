package com.misthi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class ForgetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassword frame = new ForgetPassword();
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
	public ForgetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 453);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGenerateOtp_1 = new JButton("Generate OTP");
		btnGenerateOtp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email;
				email=textField_1.getText();
				 if(email.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Enter complete details");}
				 else
			try {
Connection con=Connect.ConnectDB();
				
				PreparedStatement ps1= con.prepareStatement("select * from details where EmailId='"+email+"'");
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next())
				{
					
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
	 
	  String sub="OTP";
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
					}catch (MessagingException e1)
					{isSent=false;
					throw new RuntimeException(e1);
						}
					try {
						
						
						String query="update details set Otp='"+otp+"' where EmailId='"+email+"' ";
						
						PreparedStatement pst=con.prepareStatement(query);
								pst.execute();
								pst.close();}
					
		catch(Exception e1) {
			e1.printStackTrace();
		}
				}else {
			
					JOptionPane.showMessageDialog(null, "User does not exist...");
			
				}
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}}}
		);
		btnGenerateOtp_1.setBounds(256, 159, 132, 23);
		contentPane.add(btnGenerateOtp_1);
		
		JLabel lblNewLabel = new JLabel("RESET PASSWORD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(48, 48, 180, 31);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(25, 123, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("OTP");
		lblNewLabel_3.setBounds(25, 163, 58, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New Password");
		lblNewLabel_4.setBounds(25, 199, 95, 17);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 120, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(142, 160, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 197, 86, 19);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email,np,otp1;
				otp1=textField_3.getText();
				email=textField_1.getText();
				np=passwordField.getText().toString();
				
				
				
				if(otp1.equals("")||email.equals("")||np.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter complete details");
				}else{
				
					try {
					Connection con=Connect.ConnectDB();
					int otp=Integer.parseInt(otp1);
					 String sql="Select * from details where EmailId='"+email+"' and Otp="+otp+"";
					PreparedStatement ps1= con.prepareStatement(sql);
				 ResultSet rs= ps1.executeQuery(sql);
				 if (rs.next()) {
					 PreparedStatement psmt=con.prepareStatement("update details set Password='"+np+"' where EmailId='"+email+"'");
					psmt.execute();
					psmt.close();
					 JOptionPane.showMessageDialog(null, "Password Reset Succesfully");
					 dispose();
						Login ne=new Login();
						ne.main(null);
				 }else
					 JOptionPane.showMessageDialog(null, "Incorrect User Details");
				
}catch(Exception e1) {
	e1.printStackTrace();
}
				}}
		});
		
		
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setForeground(new Color(240, 248, 255));
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Login fb=new Login();
				fb.main(null);
			}
		});
		btnNewButton_1.setBounds(142, 255, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton.setBounds(25, 255, 89, 23);  
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel_5);
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(img));

		lblNewLabel_5.setBounds(0, 0, 694, 421);
		
		
		
	}
}
