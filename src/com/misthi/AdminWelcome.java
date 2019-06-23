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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;


class Users{
    
    private String Name;
   

	private String Address;
    private String Description;
    private String Contact;
    private String Date;
    private String Status;
    private int ID;
    
    public Users(int id, String name,String address,String description,String contact,String date,String status){
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
public class AdminWelcome extends JFrame {

	public AdminWelcome()  {
		initComponents();
		Show_Users_In_JTable();
		}
		static ArrayList<Users> getUserList(){
	        
	        ArrayList<Users> user = new ArrayList<Users>();
	        
			Connection con=Connect.ConnectDB();
	        
	        Statement st;
	        
	        ResultSet rs;
	        
	        Users u;
	        
	        try {
	            
	            st = con.createStatement();
	            rs = st.executeQuery("SELECT * FROM request");
	            
	            while(rs.next()){
	                
	                u = new Users(
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
	            Logger.getLogger(AdminWelcome.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return user;
	    }
		
		
		public void Show_Users_In_JTable()
		   {
		       ArrayList<Users> list = getUserList();
		       DefaultTableModel model=new DefaultTableModel()
			    {
			      public Class<?> getColumnClass(int column)
			      {
			        switch(column)
			        {
			        case 0:
			          return Boolean.class;
			        case 1:
			          return String.class;
			        case 2:
			          return String.class;
			        case 3:
			          return String.class;
			        case 4:
			          return String.class;

			          default:
			            return String.class;
			        }
			      }
			    };
			  //ASSIGN THE MODEL TO TABLE
			    
			    table.setModel(model);
			    Object[] columnsName = new Object[8];
			    columnsName[0] = "Select";
			    columnsName[1] = "Request NO.";
			    columnsName[2] = "Name";
			    columnsName[3] = "Address";
			    columnsName[4] = "Description";
			    columnsName[5] = "Contact";
			    columnsName[6] = "Date";
			    columnsName[7] = "Status";
			    model.setColumnIdentifiers(columnsName);
			     
			            Object[] rowData = new Object[8];
			            
			            for(int i = 0; i < getUserList().size(); i++){
			            	rowData[1] = getUserList().get(i).getId();
			                rowData[2] = getUserList().get(i).getName();
			                 rowData[3] = getUserList().get(i).getAddress();
			                  rowData[4] = getUserList().get(i).getDescription();
			                   rowData[5] = getUserList().get(i).getContact();
			                   rowData[6] = getUserList().get(i).getDate();
			                   rowData[7] = getUserList().get(i).getStatus();
			                  model.addRow(rowData);
			                  
			      } for(int i=0;i< getUserList().size();i++)
		            {
		              
		              model.setValueAt(false,i,0);
		            }
		    }
		       
		 // Execute The Insert Update And Delete Query
		   public void executeSQlQuery(String query, String message)
		   {
				Connection con=Connect.ConnectDB();
		       Statement st;
		       try{
		           st = con.createStatement();
		           if((st.executeUpdate(query)) == 1)
		           {
		               
		               DefaultTableModel model = (DefaultTableModel)table.getModel();
		               model.setRowCount(0);
		               Show_Users_In_JTable();
		               
		               JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
		           }else{
		               JOptionPane.showMessageDialog(null, "Data Not "+message);
		           }
		       }catch(Exception ex){
		           ex.printStackTrace();
		       }
		   }
		   
			private JPanel contentPane;
			private JTable table;
  //MAIN METHOD
  public static void main(String[] args)
  {

       EventQueue.invokeLater(new Runnable()
       {
           public void run()
           {
               //INITIALIZE JFRAME FORM
               AdminWelcome form=new AdminWelcome();
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

	    //ADD SCROLLPANE
	    JScrollPane scroll=new JScrollPane();
	    scroll.setBounds(34,141,600,200);
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
	    
	    
	    //THE MODEL OF OUR TABLE
	   
	    JButton btnLogout = new JButton("Logout");
	    btnLogout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		dispose();
	    		HomePage hp=new HomePage();
	    		hp.main(null);
	    	}
	    });
		btnLogout.setForeground(Color.LIGHT_GRAY);
		btnLogout.setBackground(new Color(0, 0, 255));
		btnLogout.setBounds(153, 30, 100, 25);
		contentPane.add(btnLogout);

  	  
	          //OBTAIN SELECTED ROW
			    JButton btn=new JButton("Get Selected");
			    btn.setForeground(Color.LIGHT_GRAY);
			    btn.setBackground(new Color(255, 0, 0));
			    btn.addActionListener(new ActionListener() {

			      @Override
			      public void actionPerformed(ActionEvent arg0) {
			        // TODO Auto-generated method stub

			        //GET SELECTED ROW

						 for(int i=0;i<table.getRowCount();i++)
					        {
					          Boolean checked=Boolean.valueOf(table.getValueAt(i, 0).toString());

					          int ad=getUserList().get(i).getId();
					          
					          //DISPLAY
					          if(checked)
					          {
					            ;
					            try
					            {
									Connection con=Connect.ConnectDB();
									String query="UPDATE request SET Status='Approved' WHERE ID='"+ad+"'";
					                PreparedStatement smt = con.prepareStatement(query);
					                smt.execute();
					                JOptionPane.showMessageDialog(null, "Request Approved");
					                  
					                
					                
					              }
					            catch (SQLException e) {
					            	e.printStackTrace();
					            }
					          }
					          
					        }
						 Show_Users_In_JTable();


			      }
			    });
			    btn.setBounds(21,30,122,27);
			    getContentPane().add(btn);
			    
				 

	
			    JLabel lblNewLabel = new JLabel("New label");
				Image img=new ImageIcon(this.getClass().getResource("/Wallpaper1.jpg")).getImage();
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.setForeground(Color.LIGHT_GRAY);
				btnDelete.setBackground(Color.RED);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { for(int i=0;i<table.getRowCount();i++)
			        {
				          Boolean checked=Boolean.valueOf(table.getValueAt(i, 0).toString());

				          int ad=getUserList().get(i).getId();
				          
				          //DISPLAY
				          if(checked)
				          {
				            ;
				            try
				            {
								Connection con=Connect.ConnectDB();
								String query="Delete from request WHERE ID='"+ad+"'";
				                PreparedStatement smt = con.prepareStatement(query);
				                smt.execute();
				                JOptionPane.showMessageDialog(null, "Successfully deleted");
				                  
				                
				                
				              }
				            catch (SQLException e1) {
				            	e1.printStackTrace();
				            }
				          }
				          
				        }
					 Show_Users_In_JTable();


					}
				});
				btnDelete.setBounds(22, 79, 121, 25);
				contentPane.add(btnDelete);
				lblNewLabel.setIcon(new ImageIcon(img));

				lblNewLabel.setBounds(0, 0, 694, 421);
				contentPane.add(lblNewLabel);
				
				
  
	}

        }