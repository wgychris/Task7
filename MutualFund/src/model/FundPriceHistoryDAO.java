package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;
/*
 * FundPriceHistory DAO
 * Daisy Jan 19 Version 1.0
*/
public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean>{

	public FundPriceHistoryDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}
	
	/*
	 * void createNewFundPriceHistory
	 * @param FundPriceHistoryBean
	 * @return void
	 */
	public void createNewFundPriceHistory(FundPriceHistoryBean bean) throws RollbackException{
		try{
			Transaction.begin();
			create(bean);
			Transaction.commit();
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	
	/*
	 * FundBean[] getAllFundPriceHistory
	 * @description Get all FundPriceHistory 
	 * @return FundPriceHistoryBean[] 
	 */
	public FundPriceHistoryBean[] getAllFundPriceHistory() throws RollbackException{
		try{
			Transaction.begin();
			FundPriceHistoryBean[] newBeanArray = match();
			return newBeanArray;
		}finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	/*
	 * FundBean[] getFundPriceHistoryByFundId
	 * @description Get all FundPriceHistory 
	 * @return FundPriceHistoryBean[] 
	 */
	public FundPriceHistoryBean[] getFundPriceHistoryByFundId(int id) throws RollbackException{
		try{
			Transaction.begin();
			FundPriceHistoryBean[] newBeanArray = match(MatchArg.equals("fund_price_history_id",id));
			return newBeanArray;
		}finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	/*
	 * FundBean[] getFundPriceHistoryByDate
	 * @description Get all FundPriceHistory 
	 * @return FundPriceHistoryBean[] 
	 */
	public FundPriceHistoryBean[] getFundPriceHistoryByDate(String  date) throws RollbackException{
		try{
			Transaction.begin();
			FundPriceHistoryBean[] newBeanArray = match(MatchArg.equals("date", date));
			return newBeanArray;
		}finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	

}
