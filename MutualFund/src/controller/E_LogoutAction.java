package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;


/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class E_LogoutAction extends Action {

	public E_LogoutAction(Model model) { }

	public String getName() { return "e_logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("employee",null);

		request.setAttribute("message","You are now logged out");
        return "e_success.jsp";
    }
}