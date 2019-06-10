package com.ant.Util;

import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;


public class SqliteConnection {
	static Connection conn = null;
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");  
             conn =  DriverManager.getConnection("jdbc:sqlite:C:\\Users\\admin\\Desktop\\App\\AppQuayThuong\\my_database.sqlite"); 
            JOptionPane.showMessageDialog(null, "Connection successful");
            return conn;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
}
