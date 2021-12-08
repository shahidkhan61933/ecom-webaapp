package com.ecom.webapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	// datasource properties
	static String url = "jdbc:mysql://localhost:3306/ecom_webapp_db";
	static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	static String username = "root";
	static String password = "root";

	static Connection conn = null;

	public static Connection getConn() {
		
		try {
			// step1 : Register Driver
			Class.forName(jdbcDriver);
			// step2 : Get Connection
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Connection Failed "+e);
		} catch (SQLException e) {
			System.out.println("Driver Manager class cannot be loaded. ");
		}
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
