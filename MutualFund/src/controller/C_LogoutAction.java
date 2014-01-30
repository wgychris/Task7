package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import org.genericdao.*;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class C_LogoutAction extends Action {

	public C_LogoutAction(Model model) {
	}

	public String getName() {
		return "c_logout.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute("customer", null);

		request.setAttribute(
				"message",
				"You are now logged out <br></br><a type=\"button\" class=\"btn btn-primary btn-lg\" href=\"entry.do\">Homepage</a>");
		return "c_success.jsp";
	}
}
