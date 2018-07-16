package com.cg.mypaymentapp.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Factory for connection
public class DBUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		 Connection con=null;
		 
		 Class.forName("oracle.jdbc.driver.OracleDriver");
			
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		 
		 return con;
	}
}
