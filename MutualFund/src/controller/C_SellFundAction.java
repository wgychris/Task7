package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import databeans.FundBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import formbeans.SellFundForm;

public class C_SellFundAction extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(SellFundForm.class);

	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;

	public C_SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() {
		return "c_sellFund.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		String price = null;
		String name = null;

		try {
			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				name = request.getParameter("name");
				price = request.getParameter("price");
				if (name == null || name == "" || price == null || price == "") {
					errors.add("Please choose a fund to sell");
					return "c_sellFundAll.jsp";
				}
				Transaction.begin();
				FundBean fundBean = (FundBean) fundDAO.getFundByName(name);
				CustomerBean c = (CustomerBean) request.getSession().getAttribute("customer");
				PositionBean positionBean = (PositionBean) positionDAO.getPosition(
						c.getCustomer_id(), fundBean.getFund_id());
				//first time we get here, put into session immediatelly
				request.getSession().setAttribute("price", price);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("fund", fundBean);
				request.getSession().setAttribute("position", positionBean);
				Transaction.commit();
				return "c_sellFund.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "c_sellFund.jsp";
			}
			Transaction.begin();
			HttpSession session = request.getSession();
			name = (String) session.getAttribute("name");
			price = (String) session.getAttribute("price");
			FundBean fundBean = (FundBean) fundDAO.getFundByName(name);
			CustomerBean c = (CustomerBean) session.getAttribute("customer");
			PositionBean positionBean = (PositionBean) positionDAO.getPosition(
					c.getCustomer_id(), fundBean.getFund_id());
			
			long tmpShares = positionBean.getTempshares();
			long inputShares = dataConversion
					.convertFromStringToThreeDigitLong(form.getShare());
			
			if (inputShares > tmpShares) {
				errors.add("Number of shares should not be greater than available shares");
				Transaction.commit();
				return "c_sellFund.jsp";
			}
			TransactionBean t = new TransactionBean();
			t.setShares(inputShares);
			t.setCustomer_id(c.getCustomer_id());
			t.setTransaction_type("sell");
			t.setFund_id(fundBean.getFund_id());
			transactionDAO.createAutoIncrement(t);
			positionBean.setTempshares(tmpShares - inputShares);// should use ;
			positionDAO.update(positionBean);
			
			request.getSession().setAttribute("fund", fundBean);
			request.getSession().setAttribute("position", positionBean);
			request.setAttribute("message", "fund has been sold");
			Transaction.commit();
			return "c_success.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "c_sellFund.jsp";
		} catch (NumberFormatException e) {
			System.out.print("catched");
			errors.add("Input Amount is too large");
			return "c_sellFund.jsp";
		} catch (RollbackException e) {
			return "c_sellFund.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "c_sellFund.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
