package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanFactory;

import utils.dataConversion;
import databeans.CustomerBean;
import databeans.FundBean;
import databeans.FundPriceHistoryBean;
import databeans.FundWithLastPriceBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import formbeans.TransitionForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class E_TransitionAction extends Action {
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory
			.getInstance(TransitionForm.class);

	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private CustomerDAO customerDAO;

	public E_TransitionAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "e_transitionDay.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			TransitionForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			Transaction.begin();
			FundBean[] funds = fundDAO.getAllFunds();

			if (funds.length == 0) {
				errors.add("No funds created yet");
				Transaction.commit();
				return "e_transitionDay.jsp";
			}

			FundWithLastPriceBean[] fundsWithPrice = new FundWithLastPriceBean[funds.length];
			for (int i = 0; i < fundsWithPrice.length; i++) {
				FundWithLastPriceBean fw = new FundWithLastPriceBean();
				fw.setFund_id(funds[i].getFund_id());
				fw.setName(funds[i].getName());
				FundPriceHistoryBean fb = fundPriceHistoryDAO
						.getLastDateBeanByFundId(funds[i].getFund_id());
				long price = 0;
				if (fb != null) {
					price = fb.getPrice();
				}
				fw.setPrice(price);
				fw.setSymbol(funds[i].getSymbol());
				fundsWithPrice[i] = fw;
			}
			request.setAttribute("funds", fundsWithPrice);
//			TransactionBean[] temp = transactionDAO.getAllTransactions();
//			int count;
//			for (count = 0; count < transactionDAO.getCount(); count++) {
//				if (temp[count].getExecute_date() == null) {
//					break;
//				}
//			}
//			String lastdate;
//			if (count == 0) {
//				lastdate = "no last trading day";
//			} else {
//				lastdate = temp[count - 1].getExecute_date();
//			}
			
			//check number of items in the fundpricehistory table, return the last indext.
			//its date is the last trading day.
			//becuse each transition day the pricehistory table will chang, transaction table may not.
			int itemcount = fundPriceHistoryDAO.getCount();
			if(itemcount==0){
				request.setAttribute("lastdate", "");
			}else{
				String lastpricedate = fundPriceHistoryDAO.getAllFundPriceHistory()[itemcount-1].getDate();
				request.setAttribute("lastdate", lastpricedate);
			}
			
			
			// If no params were passed, return with no errors so that the form
			// will be presented (we assume for the first time).
			if (!form.isPresent()) {
				Transaction.commit();
				return "e_transitionDay.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				Transaction.commit();
				return "e_transitionDay.jsp";
			}
			// validate the date here
			java.text.SimpleDateFormat sFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			Date newDate = sFormat.parse(form.getTransitionDay());
			int randomId = Integer.parseInt(form.getFund_id()[0]);
			FundPriceHistoryBean d = fundPriceHistoryDAO
					.getLastDateBeanByFundId(randomId);
			if (d != null) {
				Date lastDate = sFormat.parse(d.getDate());
				if (lastDate != null && !newDate.after(lastDate)) {
					errors.add("The date is not valid.New date should be after "
							+ sFormat.format(lastDate));
					Transaction.commit();
					return "e_transitionDay.jsp";
				}
			}
			String[] price = form.getPrice();
			String[] fund_id = form.getFund_id();
			// here,we have to control the update price

			HashMap<String, String> map = new HashMap<String, String>();
			// First, we update the fund_price_history according to price info
			for (int i = 0; i < price.length; i++) {
				FundPriceHistoryBean historyBean = new FundPriceHistoryBean();
				historyBean.setFund_id(Integer.parseInt(fund_id[i]));
				historyBean.setDate(form.getTransitionDay());
				long updatePrice = dataConversion
						.convertFromStringToTwoDigitLong(price[i]);
				historyBean.setPrice(updatePrice);
				fundPriceHistoryDAO.createNewFundPriceHistory(historyBean);
				map.put(fund_id[i], price[i]);
			}

			// Second, we update all the transaction in queue
			// For the deposit and request transaction, we need to update the
			// transaction date and customer cash
			// Then for the buy fund, we need to update the transaction date and
			// share
			// if the position'table doesn't have this record, we need to add
			// one,or we need to update the share
			// finally we still have to update the cash of customer
			TransactionBean[] tbs = transactionDAO.getTransactionByDate(null);
			for (int i = 0; i < tbs.length; i++) {
				long currentCash = customerDAO.getCustomerInfo(
						tbs[i].getCustomer_id()).getCash();
				if (tbs[i].getTransaction_type().equals("deposit")) {
					transactionDAO.updateTransactionDate(tbs[i],
							form.getTransitionDay());
					long depositCash = tbs[i].getAmount();
					customerDAO.updataCash(tbs[i].getCustomer_id(), currentCash
							+ depositCash);
				} else if (tbs[i].getTransaction_type().equals("request")) {
					transactionDAO.updateTransactionDate(tbs[i],
							form.getTransitionDay());
					long requestCash = tbs[i].getAmount();
					customerDAO.updataCash(tbs[i].getCustomer_id(), currentCash
							- requestCash);
				} else if (tbs[i].getTransaction_type().equals("buy")) {
					int fundid = tbs[i].getFund_id();
					int cusid = tbs[i].getCustomer_id();
					double updatePrice = dataConversion
							.convertFromStringToTwoDigitLong(map.get(""
									+ fundid));
					// Because the updatePrice has two digit as long integer, so
					// the share is correct
					double share = tbs[i].getAmount() / updatePrice;
					long sh = dataConversion
							.convertFromDoubleToThreeDigitLong(share);
					tbs[i].setShares(sh);
					tbs[i].setExecute_date(form.getTransitionDay());
					transactionDAO.update(tbs[i]);

					if (positionDAO.getPosition(cusid, fundid) == null) {
						PositionBean pb = new PositionBean();
						pb.setCustomer_id(cusid);
						pb.setFund_id(fundid);
						pb.setShares(sh);
						pb.setTempshares(sh);
						positionDAO.create(pb);
					} else {
						PositionBean pb = positionDAO
								.getPosition(cusid, fundid);
						pb.setShares(pb.getShares() + sh);
						pb.setTempshares(pb.getShares());
						positionDAO.update(pb);
					}
					customerDAO.updataCash(tbs[i].getCustomer_id(),currentCash - tbs[i].getAmount());
					//CustomerBean cb = customerDAO.getCustomerInfo(tbs[i]
							//.getCustomer_id());
					//cb.setCash(currentCash - tbs[i].getAmount());
				} else if (tbs[i].getTransaction_type().equals("sell")) {
					int fundid = tbs[i].getFund_id();
					int cusid = tbs[i].getCustomer_id();
					double updatePrice = dataConversion
							.convertFromStringToTwoDigitLong(map.get(""
									+ fundid));
					double amount = tbs[i].getShares() * updatePrice / 1000;
					long am = dataConversion
							.convertFromDoubleToTwoDigitLong(amount / 100);
					tbs[i].setAmount(am);
					tbs[i].setExecute_date(form.getTransitionDay());
					transactionDAO.update(tbs[i]);

					PositionBean pb=positionDAO.getPosition(cusid, fundid);
					if ( pb== null) {
						throw new RollbackException(
								"system error with sell fund");
					} else {
						pb.setShares(pb.getShares() - tbs[i].getShares());
						pb.setTempshares(pb.getShares());
						positionDAO.update(pb);
					}
					
					customerDAO.updataCash(tbs[i].getCustomer_id(),
					  currentCash + am);
					 
					//CustomerBean cb = customerDAO.getCustomerInfo(tbs[i]
							//.getCustomer_id());
					//cb.setCash(currentCash + am);
					//customerDAO.update(cb);
				}
			}
			// finally, we have to update the temp cash for every customer
			CustomerBean[] customers = customerDAO.getAllCustomers();
			for (int i = 0; i < customers.length; i++) {
				customerDAO.updataTempCash(customers[i].getCustomer_id(),
						customers[i].getCash());
			}
			request.setAttribute("message",
					"the transitionDay is updated successfully");
			Transaction.commit();
			return "e_success.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "e_transitionDay.jsp";
		} catch (NumberFormatException e) {
			System.out.print("catched");
			errors.add("Input Amount is too large");
			return "e_transitionDay.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "e_transitionDay.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
