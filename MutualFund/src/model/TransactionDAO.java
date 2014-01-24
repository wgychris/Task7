package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

/*
 * TransactionDAO DAO
 * Daisy 19 Version 1.0
 */
public class TransactionDAO extends GenericDAO<TransactionBean> {

	public TransactionDAO(String tableName, ConnectionPool cp)
			throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}

	/*
	 * void createNewTransaction
	 * 
	 * @param transactionBean
	 * 
	 * @return void
	 */
	public void createNewTransaction(TransactionBean bean)
			throws RollbackException {
		try {
			Transaction.begin();
			createAutoIncrement(bean);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/*
	 * void updateTransactionDate
	 * 
	 * @param TransactionBean
	 * 
	 * @return void
	 */
	public void updateTransactionDate(TransactionBean bean, String date)
			throws RollbackException {
		try {
			Transaction.begin();
			TransactionBean newBean = read(bean.getTransaction_id());

			if (newBean == null) {
				throw new RollbackException("Transaction no longer exists");
			}

			newBean.setExecute_date(date);
			;

			update(newBean);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/*
	 * TransactionBean[] getAllTransactions
	 * 
	 * @description Get all Transactions
	 * 
	 * @return TransactionBean[]
	 */
	public TransactionBean[] getAllTransactions() throws RollbackException {
		try {
			Transaction.begin();
			TransactionBean[] newBeanArray = match();
			return newBeanArray;
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/*
	 * TransactionBean[] getTransactionByType
	 * 
	 * @description Get all Transactions of a certain type
	 * 
	 * @param transaction_type
	 * 
	 * @return TransactionBean[]
	 */
	public TransactionBean[] getTransactionByType(String type)
			throws RollbackException {
		try {
			Transaction.begin();
			TransactionBean[] newBeanArray = match(MatchArg.equals(
					"transaction_type", type));

			return newBeanArray;
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/*
	 * TransactionBean[] getTransactionByDate
	 * 
	 * @description Get all Transactions of a certain execute_date
	 * 
	 * @param execute_date
	 * 
	 * @return TransactionBean[]
	 */
	public TransactionBean[] getTransactionByDate(String execute_date)
			throws RollbackException {
		try {
			Transaction.begin();
			TransactionBean[] newBeanArray = match(MatchArg.equals(
					"execute_date", execute_date));

			return newBeanArray;
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/*
	 * TransactionBean[] getTransactionByDate
	 * 
	 * @description Get all Transactions of a certain execute_date
	 * 
	 * @param execute_date
	 * 
	 * @return TransactionBean[]
	 */
	public TransactionBean[] getTransactionByCustomerId(int customer_id) throws RollbackException{
		
			System.out.print("@@");
			
			System.out.print("##");
//			TransactionBean[] newBeanArray = match(MatchArg.equals("execute_date",execute_date));
			TransactionBean[] newBeanArray = match(MatchArg.equals("customer_id", customer_id));
			System.out.print("??");
			if(newBeanArray != null && newBeanArray.length > 0)
			return newBeanArray;
			else
				return null;
		
	}

}
