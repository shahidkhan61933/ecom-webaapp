package com.ecom.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.webapp.connection.JdbcConnection;
import com.ecom.webapp.model.Product;

public class ProductDao {

	String readProducts = "select * from products";
	String createProduct = "insert into products(name,price,description) values (?,?,?)";
	String readOneProduct = "select * from products where productId=?";
	String updateProducts = "update products set name=?, price=?, description=? where productId=?";
	String deleteProducts = "delete from products where productId=?";

	PreparedStatement pstm = null;

	// read all products
	public ResultSet getProducts() {
		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(readProducts);
			ResultSet rst = pstm.executeQuery();
			return rst;
		} catch (SQLException e) {
			return null;
		}
	}

	// create products
	public boolean createProduct(Product product) {
		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(createProduct);
			// set parameters
			pstm.setString(1, product.getName());
			pstm.setDouble(2, product.getPrice());
			pstm.setString(3, product.getDescription());
			// step 4: Execute Query
			int rowAffected = pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// update product
	public boolean updateProduct(Product product) {
		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(updateProducts);
			// set parameters
			pstm.setString(1, product.getName());
			pstm.setDouble(2, product.getPrice());
			pstm.setString(3, product.getDescription());
			pstm.setInt(4, product.getProductId());
			// step 4: Execute Query
			int rowAffected = pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// get one product
	public ResultSet getOneProduct(int productId) {
		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(readOneProduct);
			pstm.setInt(1, productId);
			ResultSet rst = pstm.executeQuery();
			return rst;
		} catch (SQLException e) {
			return null;
		}
	}
	
	// delete product
	public boolean deleteProduct(int productId) {
		Connection conn = JdbcConnection.getConn();
		// create statement
		try {
			pstm = conn.prepareStatement(deleteProducts);
			pstm.setInt(1, productId);
			int rowAffected = pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
