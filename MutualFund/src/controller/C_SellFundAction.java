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

		try {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			HttpSession session = request.getSession();
			CustomerBean c = (CustomerBean) session.getAttribute("customer");
			Transaction.begin();

			PositionBean positionBean;

			FundBean fundBean;

			SellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {

				request.setAttribute("price", price);

				fundBean = (FundBean) fundDAO.getFundByName(name);
				positionBean = (PositionBean) positionDAO.getPosition(
						c.getCustomer_id(), fundBean.getFund_id());

				request.setAttribute("position", positionBean);
				request.setAttribute("fund", fundBean);
				request.getSession().setAttribute("position2", positionBean);
				request.getSession().setAttribute("fund2", fundBean);
				request.getSession().setAttribute("fundname", name);
				return "c_sellFund.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				request.setAttribute("price", price);
				fundBean = (FundBean) fundDAO.getFundByName(name);
				positionBean = (PositionBean) positionDAO.getPosition(
						c.getCustomer_id(), fundBean.getFund_id());
				request.setAttribute("position", positionBean);
				request.setAttribute("fund", fundBean);
				request.getSession().setAttribute("position2", positionBean);
				request.getSession().setAttribute("fund2", fundBean);
				request.getSession().setAttribute("fundname", name);

				return "c_sellFund.jsp";
			}
			positionBean = (PositionBean) request.getSession().getAttribute(
					"position2");
			fundBean = (FundBean) request.getSession().getAttribute("fund2");
			long tmpShares = positionBean.getTempshares();
			long inputShares = dataConversion
					.convertFromStringToThreeDigitLong(form.getShare());
			if (inputShares > tmpShares) {
				errors.add("Number of shares should not be greater than "
						+ tmpShares);
				Transaction.commit();
				return "c_sellFund.jsp";
			}
			TransactionBean t = new TransactionBean();
			t.setShares(inputShares);
			t.setCustomer_id(c.getCustomer_id());
			// t.setCustomer_id(1);//test
			t.setTransaction_type("sell");
			t.setFund_id(fundBean.getFund_id());
			transactionDAO.createAutoIncrement(t);
			//PositionBean p = new PositionBean();
			// p.setCustomer_id(1);//test
			positionBean.setTempshares(tmpShares - inputShares);// should use ;
			// positionDAO.create(p);
			positionDAO.updataTempCash(positionBean);

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
