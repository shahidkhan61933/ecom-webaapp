package com.ecom.webapp.web;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.webapp.dao.UserDao;
import com.ecom.webapp.helper.ValidationHelper;
import com.ecom.webapp.model.User;

/**
 * Servlet implementation class Registration. Registration is used to register a
 * user.
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Load a registration page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// response.sendRedirect("registration.html");
		request.getRequestDispatcher("index.html").include(request, response);
		request.getRequestDispatcher("registration.html").include(request, response);
	}

	/**
	 * process the registration.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);

		// collect registration params
		String username = request.getParameter("username");
		String useremail = request.getParameter("useremail");
		String password = request.getParameter("password");
		String cnfPassword = request.getParameter("cnfPassword");

		boolean emptyFields = ValidationHelper.isValidUser(username, useremail, password, cnfPassword);
		if (!emptyFields) {
			boolean match = ValidationHelper.isPasswordMatch(password, cnfPassword);
			if (match) {
				// create user object
				User user = new User(username, useremail, password, cnfPassword);
				// create user dao object
				UserDao userDao = new UserDao();
				userDao.createUser(user);
				out.print("<h2 style='color:green'>Registration Successfull !</h2>");
			} else {
				out.print("<h2 style='color:red'>Registration Failed, Password mismatch !</h2>");
			}
		} else {
			out.print("<h2 style='color:red'>Registration Failed, Required fields are missing !</h2>");
			// request.getRequestDispatcher("failure.html").include(request, response);
		}
		out.close();
	}

}
