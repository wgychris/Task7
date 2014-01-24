package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import databeans.FundBean;
import databeans.FundPriceHistoryBean;

import formbeans.ResearchFundForm;


public class C_ResearchFundAction extends Action {
	private FormBeanFactory<ResearchFundForm> formBeanFactory = FormBeanFactory
			.getInstance(ResearchFundForm.class);

	private FundDAO fundDAO;

	private FundPriceHistoryDAO fphDAO;

	public C_ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fphDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "c_researchFund.do";// ??
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ResearchFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "c_researchFund.jsp";
			}

			// Any validation errors?
			if (!fundDAO.checkFundByTicker(form.getFundTicker())) {
				errors.add("No such fund exists");
			}
			
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "c_researchFund.jsp";
			}
			
			String ticker = form.getFundTicker();
			FundBean fb  = fundDAO.getFundByTicker(ticker);
			FundPriceHistoryBean[] fphBean = fphDAO.getFundPriceHistoryByFundId(fb.getFund_id());
			request.setAttribute("fundInfo", fphBean);
			
			return "c_researchFund.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error-list.jsp";
		} catch (RollbackException e) {
			return "error-list.jsp";
		}
	}
}
