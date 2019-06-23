package com.misthi; 

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect { 
	
	public static Connection ConnectDB() {
	try {

	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro?useSSL=false","root","1234");
	  
	  return con;
}
	catch(Exception e) {
	 System.out.print(e);
}
	return null;
	}
}


