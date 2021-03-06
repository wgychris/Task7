package model;

import java.text.ParseException;
import java.util.*;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.*;

/*
 * FundPriceHistory DAO
 * Daisy Jan 19 Version 1.0
 */
public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {

	public FundPriceHistoryDAO(String tableName, ConnectionPool cp)
			throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}

	/*
	 * void createNewFundPriceHistory
	 * 
	 * @param FundPriceHistoryBean
	 * 
	 * @return void
	 */
	public void createNewFundPriceHistory(FundPriceHistoryBean bean)
			throws RollbackException {
	//	try {
		//	Transaction.begin();
			create(bean);
	/*		Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}

	/*
	 * FundBean[] getAllFundPriceHistory
	 * 
	 * @description Get all FundPriceHistory
	 * 
	 * @return FundPriceHistoryBean[]
	 */
	public FundPriceHistoryBean[] getAllFundPriceHistory()
			throws RollbackException {
	//	try {
		//	Transaction.begin();
			FundPriceHistoryBean[] newBeanArray = match();
			return newBeanArray;
	/*	} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}*/
	}

	/*
	 * String getLastDateByFundId
	 * 
	 * @description Get last date of fund given fundid
	 * 
	 * @return String
	 */
	public FundPriceHistoryBean getLastDateBeanByFundId(int id)
			throws RollbackException, ParseException {
		FundPriceHistoryBean[] newBeanArray = match(MatchArg.equals("fund_id",
				id));
		for(FundPriceHistoryBean f:newBeanArray){
			System.out.println(f.getDate());
		}
		java.text.SimpleDateFormat sFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		if (newBeanArray.length > 0) {
			Date lastDate = new Date(0000-00-00);
			//System.out.println(lastDate);
			int lastDayIndex = 0;
			for (int i = 0; i < newBeanArray.length; i++) {
				if (newBeanArray[i].getDate() != null) {
					Date nextDate = sFormat.parse(newBeanArray[i].getDate());
					if (nextDate.after(lastDate)) {
						lastDate = nextDate;
						lastDayIndex = i;
					}
					//System.out.println("cur day:"+nextDate+"    last day:"+ lastDate);
				}
			}
			return newBeanArray[lastDayIndex];
		}
		return null;

	}

	/*
	 * FundBean[] getFundPriceHistoryByFundId
	 * 
	 * @description Get all FundPriceHistory
	 * 
	 * @return FundPriceHistoryBean[]
	 */
	public FundPriceHistoryBean[] getFundPriceHistoryByFundId(int id)
			throws RollbackException {
		FundPriceHistoryBean[] newBeanArray = match(MatchArg.equals("fund_id",
				id));
		return newBeanArray;

	}

	/*
	 * FundBean[] getFundPriceHistoryByDate
	 * 
	 * @description Get all FundPriceHistory
	 * 
	 * @return FundPriceHistoryBean[]
	 */
	public FundPriceHistoryBean[] getFundPriceHistoryByDate(String date)
			throws RollbackException {
	//	try {
		//	Transaction.begin();
			FundPriceHistoryBean[] newBeanArray = match(MatchArg.equals("date",
					date));
			return newBeanArray;
	/*	} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}

}
