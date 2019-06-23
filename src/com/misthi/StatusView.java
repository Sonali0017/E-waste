package com.misthi;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;


class Use{
    
    private String Name;
    private String Address;
    private String Description;
    private String Contact;
    private String Date;
    private String Status;
	private int ID;
    
    public Use(int id,String name,String address,String description,String contact,String date,String status){
        this.ID=id;
    	this.Name = name;
        this.Address = address;
        this.Description = description;
        this.Contact = contact;
        this.Date = date;
        this.Status = status;
    }

	public String getName() {
		return this.Name;
	}

	public String getAddress() {
		return this.Address;
	}

	public String getDescription() {
		return this.Description;
	}

	public String getContact() {
		return this.Contact;
	}

	public String getDate() {
		return this.Date;
	}

	public String getStatus() {
		return this.Status;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.ID;
	}
}
public class StatusView extends JFrame {
	
	public StatusView()  {
		initComponents();
		Show_Users_In_JTable();
		}
		

		static  int uid;
		public static void setUserID(int u) {uid=u;}
		static ArrayList<Use> getUserList(){
	        
	        ArrayList<Use> user = new ArrayList<Use>();
	        
			Connection con=Connect.ConnectDB();
	        
	        Statement st;
	        
	        ResultSet rs;
	        
	        Use u;
	      
	        try {
	            
	            st = con.createStatement();
	            rs = st.executeQuery("SELECT * FROM request where UserId='"+uid+"'");
	            
	            while(rs.next()){
	                
	                u = new Use(
	                		rs.getInt("id"),
	                        rs.getString("name"),
	                        rs.getString("address"),
	                        rs.getString("description"),
	                        rs.getString("contact"),
	                        rs.getString("date"),
	                        rs.getString("status")
	                );
	                
	                user.add(u);
	            }
	             
	        } catch (SQLException ex) {
	            Logger.getLogger(StatusView.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return user;
	    }
		
		public void Show_Users_In_JTable()
		   {
		       ArrayList<Use> list = getUserList();
		       DefaultTableModel model=new DefaultTableModel()
		    		   {};
			  //ASSIGN THE MODEL TO TABLE
			    
			    table.setModel(model);
			    Object[] columnsName = new Object[7];
			    columnsName[0] = "Request NO.";
			    columnsName[1] = "Name";
			    columnsName[2] = "Address";
			    columnsName[3] = "Description";
			    columnsName[4] = "Contact";
			    columnsName[5] = "Date";
			    columnsName[6] = "Status";
			    model.setColumnIdentifiers(columnsName);
			     
			            Object[] rowData = new Object[7];
			            
			            for(int i = 0; i < getUserList().size(); i++){
			            	   rowData[0] = getUserList().get(i).getId();	
			                rowData[1] = getUserList().get(i).getName();
			                 rowData[2] = getUserList().get(i).getAddress();
			                  rowData[3] = getUserList().get(i).getDescription();
			                   rowData[4] = getUserList().get(i).getContact();
			                   rowData[5] = getUserList().get(i).getDate();
			                   rowData[6] = getUserList().get(i).getStatus();
			                  model.addRow(rowData);
			                  
			      } 
		    }
		       
		 
		   
			private JPanel contentPane;
			private JTable table;
			private JLabel lblNewLabel;
			private JButton btnLogout;
  //MAIN METHOD
  public static void main(String[] args)
  {

       EventQueue.invokeLater(new Runnable()
       {
           public void run()
           {
               //INITIALIZE JFRAME FORM
               StatusView form=new StatusView();
              form.setVisible(true);;
           }
       });

  }

  //CONSTRUCTOR
 void initComponents()
{ 

	//THE ROW
		
		//the form
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100,100,704,453);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(154, 205, 50));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    getContentPane().setLayout(null);
	    
	     JButton btnLogout_1 = new JButton("Logout");
	     btnLogout_1.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		dispose();
	     		HomePage hl=new HomePage();
	     		hl.main(null);
	     	}
	     });
	     
	     JButton btnBack = new JButton("Back");
	     btnBack.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		dispose();
	     		UserRequest ur=new UserRequest();
	     		ur.main(null);
	     	}
	     });
	     btnBack.setForeground(Color.WHITE);
	     btnBack.setBackground(Color.BLUE);
	     btnBack.setBounds(32, 30, 89, 23);
	     contentPane.add(btnBack);
	     btnLogout_1.setForeground(new Color(211, 211, 211));
	     btnLogout_1.setBackground(new Color(0, 0, 255));
	     btnLogout_1.setBounds(125, 29, 100, 25);
	     contentPane.add(btnLogout_1);

	    //ADD SCROLLPANE
	    JScrollPane scroll=new JScrollPane();
	    scroll.setBounds(68,265,599,90);
	    getContentPane().add(scroll);

	    //THE TABLE
	    table=new JTable();
	    scroll.setViewportView(table);
	    table.setBackground(new Color(0,0,0,0));
	    ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
	    table.setGridColor(Color.WHITE);
	    table.setForeground(Color.WHITE);
	    
	    scroll.setBackground(new Color(0,0,0,0));
	    scroll.setOpaque(false);
	    table.setOpaque(false);
	    ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
        scroll.getViewport().setOpaque(false);
        table.setShowGrid(true);
	    
	    JLabel lblNewLabel1 = new JLabel("New label");
	    lblNewLabel1.setBackground(Color.BLUE);
		Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
		lblNewLabel1.setIcon(new ImageIcon(img));

		lblNewLabel1.setBounds(0, 0, 694, 421);
		contentPane.add(lblNewLabel1);
		
	    
}}
