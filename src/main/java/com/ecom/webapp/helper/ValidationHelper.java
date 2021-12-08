package com.ecom.webapp.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ValidationHelper {

	/**
	 * True is for empty fileds False for not empty fields.
	 */
	public static boolean isValidUser(String username, String useremail, String password, String cnfPassword) {
		if (username != null && !username.isEmpty() && useremail != null && !useremail.isEmpty() && password != null
				&& !password.isEmpty() && cnfPassword != null && !cnfPassword.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Is password match. password match return true.
	 */

	public static boolean isPasswordMatch(String password, String cnfPassword) {
		if (password.equals(cnfPassword)) {
			return true;
		}
		return false;
	}

	public static boolean isValidUser(String useremail, String password) {
		if (useremail != null && !useremail.isEmpty() && password != null && !password.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean validateSession(HttpServletRequest request) {
		// fetch created session
		HttpSession session = request.getSession(false);
		if (session != null) {
			String useremail = (String) session.getAttribute("useremail");
			if (useremail != null && !useremail.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
}

}
