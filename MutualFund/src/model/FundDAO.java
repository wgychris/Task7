package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;
/*
 * Fund DAO
 * Daisy Jan 19 Version 1.0
*/
public class FundDAO extends GenericDAO<FundBean>{

	public FundDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	
	/*
	 * void createNewFund 
	 * @param FundBean
	 * @return void
	 */
	public void createNewFund(FundBean bean) throws RollbackException{
		try{
			Transaction.begin();
			create(bean);
			Transaction.commit();
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	/*
	 * void checkFundByName
	 * @param fundName
	 * @return false if fund already exists
	 */
	public boolean checkFundByName(String fundName) throws RollbackException{
		try{
			Transaction.begin();
			FundBean[] newBeanArray = match(MatchArg.equals("name",fundName));
			if(newBeanArray.length > 0 ) 
				return true;
			return false; 
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	/*
	 * void checkFundByTicker
	 * @param fundTicker
	 * @return false if fund already exists
	 */
	public boolean checkFundByTicker(String ticker) throws RollbackException{
		try{
			Transaction.begin();
			FundBean[] newBeanArray = match(MatchArg.equals("symbol",ticker));

			if(newBeanArray.length > 0 ) 
				return true;
			return false; 

		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	
	/*
	 * FundBean[] getAllFunds
	 * @description Get all Funds 
	 * @return FundBean[] 
	 */
	public FundBean[] getAllFunds() throws RollbackException{
		try{
			Transaction.begin();
			FundBean[] newBeanArray = match();
			return newBeanArray;
		}finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
	}

	/*
	 * void getFundByName
	 * @param fundName
	 * @return FundBean
	 */
	public FundBean getFundByName(String fundName) throws RollbackException{
		try{
			Transaction.begin();
			FundBean[] newBeanArray = match(MatchArg.equals("name",fundName));
			if(newBeanArray.length > 0 ) 
				return newBeanArray[0];
			return null; 
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	/*
	 * void getFundByTicker
	 * @param ticker
	 * @return FundBean
	 */
	public FundBean getFundByTicker(String ticker) throws RollbackException{
		try{
			Transaction.begin();
			FundBean[] newBeanArray = match(MatchArg.equals("symbol",ticker));
			if(newBeanArray.length > 0 ) 
				return newBeanArray[0];
			return null; 
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	/*
	 * Get position
	 * @param customer_id and fund_id
	 * @return position_bean
	 */
	public FundBean getFundByFundId(int fund_id) throws RollbackException {
		FundBean fb = read(fund_id);
//		
		return fb;
	}
	

}
