package com.ant.Util;

import java.sql.*;
import javax.swing.JOptionPane;

public class SqliteConnection {
	static Connection conn = null;

	public static void connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:C:/Sqlite/my_database.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);
			conn = SqliteConnection.dbConnector();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		connect();
	}

	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:C:/Sqlite/my_database.sqlite");

			return conn;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}
}
