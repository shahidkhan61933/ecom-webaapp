package com.ecom.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.webapp.dao.ProductDao;
import com.ecom.webapp.helper.ValidationHelper;
import com.ecom.webapp.model.Product;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * load create product webpage.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		// validate user session.
		boolean result = ValidationHelper.validateSession(request);
		if (result) {
			request.getRequestDispatcher("add-product.html").include(request, response);
		} else {
			out.print("<h3 style='color:red'>Unauthoised user, Please login first.</h3>");
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
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		String description = request.getParameter("description");
		
		// validate user session.
		boolean result = ValidationHelper.validateSession(request);
		if (result) {
			// create product 
			Product product = new Product(name, price, description);
			ProductDao dao = new ProductDao();
			boolean status = dao.createProduct(product);
			if(status) {
				out.print("<h2 style='color:green'>Product is created Successfully !</h2>");
			}
		} else {
			out.print("<h3 style='color:red'>Unauthoised user, Please login first.</h3>");
			out.println("<a href='login'> Login </a>");
		}

	}

}
