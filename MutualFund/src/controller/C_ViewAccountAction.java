/**
 * 
 */
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
import databeans.PositionBean;
import databeans.LastFundBean;

import org.genericdao.*;

public class C_ViewAccountAction extends Action {

	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	// ini DAOs
	public C_ViewAccountAction(Model model) {
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "c_viewAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {

			CustomerBean cb = (CustomerBean) request.getSession().getAttribute(
					"customer");
			request.setAttribute("user", cb);
			Transaction.begin();
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
						// lfb.setFund_id(fundID);
						lfb.setName(fundBean.getName());
						lfb.setSymbol(fundBean.getSymbol());
						lfb.setShares(pBean[i].getShares());
						lfb.setPrice_date(fphBean.getDate());
						lfb.setPrice(fphBean.getPrice());
						list.add(lfb);
					}
				}
			}
			request.setAttribute("day", list.size() == 0 ? "No trading day"
					: list.get(0).getPrice_date());
			request.setAttribute("userFundList", list);
			Transaction.commit();
			return "c_viewAccount.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "c_viewAccount.jsp";
		} catch (ParseException e) {
			e.printStackTrace();
			errors.add(e.toString());
			return "c_viewAccount.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "c_viewAccount.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

}
