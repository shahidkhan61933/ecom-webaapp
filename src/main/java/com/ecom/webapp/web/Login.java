package com.ecom.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.webapp.dao.UserDao;
import com.ecom.webapp.helper.ValidationHelper;
import com.ecom.webapp.model.User;

/**
 * Servlet implementation class Login.
 * Login is used to validate user.
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Load login page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// response.sendRedirect("login.html");
		request.getRequestDispatcher("index.html").include(request, response);
		request.getRequestDispatcher("login.html").include(request, response);
	}

	/**
	 * Process login.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		 
		// collect login params
		String useremail = request.getParameter("useremail");
		String password = request.getParameter("password");
		
		boolean emptyFields = ValidationHelper.isValidUser(useremail,password);
		if(!emptyFields) {
				//create user object
				User user = new User(useremail,password);
				// create object for user dao
				UserDao dao = new UserDao();
				boolean result = dao.loginUser(user);
				System.out.println(result);
				if(result) {
					//create a http session
					HttpSession session = request.getSession(true);
					session.setAttribute("useremail", useremail);
					
					out.print("<h2 style='color:green'>Login Successfull</h2>");
				} else {
					out.print("<h2 style='color:red'>Login Failed, Invalid user credentials</h2>");
				}
				
		} else {	
			out.print("<h2 style='color:red'>Login Failed, Required fields are missing !</h2>");
		}
		out.close();

	}

}
