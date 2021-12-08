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
@WebServlet("/delete-product")
public class DeleteProduct extends HttpServlet {
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
			request.getRequestDispatcher("delete-product.html").include(request, response);
		} else {
			out.print("<h3 style='color:red'>Unauthoised user, Please login first.</h3>");
			out.println("<a href='login'> Login </a>");
		}
	}

	/**
	 * update product processing
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);

		// collect add product params
		int productId = Integer.parseInt(request.getParameter("productId"));		
		// validate user session.
		boolean result = ValidationHelper.validateSession(request);
		if (result) {
			ProductDao dao = new ProductDao();
			boolean status = dao.deleteProduct(productId);
			if(status) {
				out.print("<h2 style='color:green'>Product is deleted Successfully !</h2>");
			}
		} else {
			out.print("<h3 style='color:red'>Unauthoised user, Please login first.</h3>");
			out.println("<a href='login'> Login </a>");
		}

	}

}
