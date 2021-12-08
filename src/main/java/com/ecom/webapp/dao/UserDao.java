package com.ecom.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.webapp.connection.JdbcConnection;
import com.ecom.webapp.model.User;

public class UserDao {

	String addUser = "insert into users(username,useremail,password,cnfPassword)" + "values (?,?,?,?)";
	String loginUser = "select * from users where useremail=? and password=?";
	PreparedStatement pstm = null;

	// create user
	public boolean createUser(User user) {

		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(addUser);
			// set parameters
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getUseremail());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getCnfPassword());
			// step 4: Execute Query
			int rowAfedted = pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// login user
	public boolean loginUser(User user) {
		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(loginUser);
			// set parameters
			pstm.setString(1, user.getUseremail());
			pstm.setString(2, user.getPassword());
			// step 4: Execute Query
			ResultSet rst = pstm.executeQuery();
			return display(rst);			
		} catch (SQLException e) {
			return false;
		}
	}

	// print details
	private boolean display(ResultSet rst) {
		try {
			while (rst.next()) {
				System.out.println(rst.getInt("userId"));
				System.out.println(rst.getString("username"));
				System.out.println(rst.getString("password"));
				System.out.println(rst.getDate("createdAt"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// update user
	// delete user
	// read one user by id
	// read all users

}
