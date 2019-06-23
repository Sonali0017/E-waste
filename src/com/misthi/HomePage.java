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
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminLogin al=new AdminLogin();
				al.main(null);
			}
		});
		
		JLabel lblConsideredAsEwaste = new JLabel(" considered as e-waste.");
		lblConsideredAsEwaste.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblConsideredAsEwaste.setBounds(32, 160, 193, 14);
		contentPane.add(lblConsideredAsEwaste);
		
		JLabel lblReuseresalesalvageOrRecycling = new JLabel(" reuse,resale,salvage or recycling are");
		lblReuseresalesalvageOrRecycling.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblReuseresalesalvageOrRecycling.setBounds(32, 145, 203, 14);
		contentPane.add(lblReuseresalesalvageOrRecycling);
		
		JLabel lblUsedElectronicsWhich = new JLabel("Used electronics which are destined for");
		lblUsedElectronicsWhich.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblUsedElectronicsWhich.setBounds(32, 130, 210, 14);
		contentPane.add(lblUsedElectronicsWhich);
		
		JLabel lblDiscardedElectricalOr = new JLabel("discarded electrical or electronic devices.");
		lblDiscardedElectricalOr.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDiscardedElectricalOr.setBounds(32, 117, 210, 14);
		contentPane.add(lblDiscardedElectricalOr);
		
		JLabel lblElectronicWasteOr = new JLabel("Electronic waste or e-waste describes");
		lblElectronicWasteOr.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblElectronicWasteOr.setBounds(32, 96, 193, 23);
		contentPane.add(lblElectronicWasteOr);
		
		
		btnAdmin.setForeground(new Color(240, 248, 255));
		btnAdmin.setBackground(new Color(238, 130, 238));
		btnAdmin.setBounds(10, 11, 89, 23);
		contentPane.add(btnAdmin);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			Login lg=new Login();
			lg.main(null);
			}
		});
		btnUser.setBackground(new Color(123, 104, 238));
		btnUser.setForeground(new Color(240, 248, 255));
		btnUser.setBounds(120, 11, 89, 23);
		contentPane.add(btnUser);
		
		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel);
	}
}
