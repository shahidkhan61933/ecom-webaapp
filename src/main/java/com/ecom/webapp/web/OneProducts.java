package com.ecom.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.webapp.dao.ProductDao;
import com.ecom.webapp.helper.ValidationHelper;

/**
 * Servlet implementation class Products
 */
@WebServlet("/one-product")
public class OneProducts extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Get all products
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		// validate user session.
		boolean result = ValidationHelper.validateSession(request);
			if(result) {
				request.getRequestDispatcher("one-product.html").include(request, response);
			} else {
				out.print("<h2 style='color:red'>Unauthoised user, Please login first.</h2>");
				out.println("<a href='login'> Login </a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		// collect add product params
		int prodId = Integer.parseInt(request.getParameter("productId"));
		// validate user session.
		boolean result = ValidationHelper.validateSession(request);
			if(result) {
				try {
					ProductDao productDao = new ProductDao();
					ResultSet rst = productDao.getOneProduct(prodId);
					out.println("<table border=1 width=50% height=50%>");
					out.println("<tr>" + "<th>Product Id</th>" + "<th>Product Name</th>" + "<th>Price</th>"
							+ "<th>Description</th>" + "<th>Created at</th>" + "<tr>");

					while (rst.next()) {
						int productId = rst.getInt("productId");
						String productName = rst.getString("name");
						double productPrice = rst.getDouble("price");
						String productDesc = rst.getString("description");
						Date createdAt = rst.getDate("createdAt");
						out.println("<tr>" + "<td>" + productId + "</td>" + "<td>" + productName + "</td>" + "<td>"
								+ productPrice + "</td>" + "<td>" + productDesc + "</td>" + "<td>" + createdAt + "</td>"
								+ "</tr>");
					}
					out.println("</table>");
					out.println("<a href='logout'> Logout </a>");
					out.println("</html></body>");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				out.print("<h2 style='color:red'>Unauthoised user, Please login first.</h2>");
				out.println("<a href='login'> Login </a>");
			}
	}

}
