package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import model.Model;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		Action.add(new C_LoginAction(model));
		Action.add(new E_LoginAction(model));
		Action.add(new C_LogoutAction(model));
		Action.add(new E_LogoutAction(model));
		Action.add(new E_DepositCheckAction(model));
		Action.add(new E_CreateCustomerAction(model));
		Action.add(new E_CreateEmployeeAction(model));
		Action.add(new E_TransitionAction(model));
		Action.add(new E_CreateFundAction(model));
		Action.add(new C_BuyFundAction(model));
		Action.add(new C_SellFundAction(model));
		Action.add(new C_RequestCheckAction(model));
		Action.add(new C_ViewAccountAction(model));
		Action.add(new E_ResetPfcAction(model));
		Action.add(new E_ResetPwdAction(model));
		Action.add(new C_ChangePwdAction(model));
		Action.add(new E_ChangePwdAction(model));
		Action.add(new C_ResearchFundAction(model));
		Action.add(new C_ViewTransaction(model));
		Action.add(new E_ViewCustomerAction(model));
		Action.add(new E_ViewTransaction(model));
		Action.add(new E_ViewAllAccountAction(model));
		Action.add(new E_ViewAccountAction(model));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		 CustomerBean customerBean = (CustomerBean) session.getAttribute("customer");
		 EmployeeBean employeeBean =  (EmployeeBean) session.getAttribute("employee");
		String action = getActionName(servletPath);

		// System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

		 if (action.equals("c_login.do")||action.equals("admin_login.do")) {
//		 Allow these actions without logging in
		 return Action.perform(action,request);
		 }

		 if (customerBean == null) {
		// If the user hasn't logged in, direct him to the login page
			if(employeeBean==null)	 
			 return Action.perform("c_login.do",request);
			else return Action.perform(action, request);	 
		 }
		// Let the logged in user run his chosen action
		return Action.perform(action, request);
		}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		if (nextPage.equals("image")) {
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
			return;
		}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
