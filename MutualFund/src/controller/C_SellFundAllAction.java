package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.FundBean;
import databeans.FundPriceHistoryBean;
import databeans.LastFundBean;
import databeans.PositionBean;

public class C_SellFundAllAction extends Action {
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	// ini DAOs
	public C_SellFundAllAction(Model model) {
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "c_sellFundAll.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			CustomerBean cb = (CustomerBean) request.getSession().getAttribute(
					"customer");
			request.setAttribute("user", cb);
			PositionBean[] pBean = positionDAO
					.getAllPositionsByCustomerIdBeans(cb.getCustomer_id());
			ArrayList<LastFundBean> list = new ArrayList<LastFundBean>();
			if (pBean != null && pBean.length != 0) {
				for (int i = 0; i < pBean.length; i++) {
					LastFundBean lfb = new LastFundBean();
					int fundID = pBean[i].getFund_id();
					FundPriceHistoryBean fphBean = fundPriceHistoryDAO
							.getLastDateBeanByFundId(fundID);
					if (fphBean != null) {
						FundBean fundBean = fundDAO.getFundByFundId(fundID);
						lfb.setName(fundBean.getName());
						lfb.setFund_id(fundBean.getFund_id());
						lfb.setSymbol(fundBean.getSymbol());
						lfb.setShares(pBean[i].getShares());
						lfb.setPrice_date(fphBean.getDate());
						lfb.setPrice(fphBean.getPrice());
						list.add(lfb);
					}
				}
			}
			request.setAttribute("userFundList", list);
			System.out.print("return");
			return "c_sellFundAll.jsp";
		} catch (RollbackException e) {
			System.out.print("" + e.toString());
			errors.add(e.toString());
			return "error-list.jsp";
		} catch (ParseException e) {
			System.out.print("" + e.toString());
			e.printStackTrace();
			errors.add(e.toString());
			return "error-list.jsp";
		}
	}
}
