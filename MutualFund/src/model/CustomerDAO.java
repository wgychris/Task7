package model;

/*
 * CustomerDAO
 * Yusi Jan 19 Version 1.0
 * co-author
 */

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean> {

	public CustomerDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}

	/*
	 * int getCustomerId
	 * 
	 * @description Get customer id
	 * 
	 * @param username
	 * 
	 * @return userid or -1 if the username not existed.
	 */

	public int getCustomerId(String username) throws RollbackException {

		CustomerBean[] dbCustomer = match(MatchArg.equals("username", username));
		if (dbCustomer.length > 0)
			return dbCustomer[0].getCustomer_id();
		else
			return -1;
	}

	/*
	 * void changePassword
	 * 
	 * @description Change password
	 * 
	 * @param customer_id, password
	 * 
	 * @return void
	 */
	public void changePassword(int customer_id, String password)
			throws RollbackException {
	//	try {
		//	Transaction.begin();
			CustomerBean dbCustomer = read(customer_id);

			if (dbCustomer == null) {
				throw new RollbackException("User no longer exists");
			}

			dbCustomer.setPassword(password);

			update(dbCustomer);
	/*		Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}

	/*
	 * boolean checkPassword
	 * 
	 * @description Check Password
	 * 
	 * @param customer_id, password
	 * 
	 * @return true if the password is correct otherwise false
	 */
	public boolean checkPassword(int customer_id, String password)
			throws RollbackException {

		CustomerBean db = read(customer_id);
		if (db == null) {
			// throw new
			// RollbackException("User "+db.getUsername()+" no longer exists");
			return false;
		}
		if (db.getPassword().equals(password))
			return true;
		else
			return false;

	}

	/*
	 * CustomerBean getCustomerInfo
	 * 
	 * @description Get all information of a certain customer
	 * 
	 * @param customer id
	 * 
	 * @return CustomerBean or null if customer_id not exist
	 */
	public CustomerBean getCustomerInfo(int customer_id)
			throws RollbackException {
		CustomerBean db = read(customer_id);
		if (db == null)
			return null;
		return db;
	}

	/*
	 * CustomerBean[] getAllCustomers
	 * 
	 * @description Get all customers beans
	 * 
	 * @param none
	 * 
	 * @return CustomerBean[]
	 */
	public CustomerBean[] getAllCustomers() throws RollbackException {
		CustomerBean[] customers = match();
		return customers;
	}

	/*
	 * void updataCash
	 * 
	 * @description Update cash
	 * 
	 * @param customer_id, cash(long)
	 * 
	 * @return void
	 */
	public void updataCash(int customer_id, long cash) throws RollbackException {
	//	try {
		//	Transaction.begin();
			CustomerBean dbCustomer = read(customer_id);

			if (dbCustomer == null) {
				throw new RollbackException("User no longer exists");
			}

			dbCustomer.setCash(cash);

			update(dbCustomer);
	/*		Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}

	/*
	 * void createNewCustomer
	 * 
	 * @description Create New Customer Account (used by employee)
	 * 
	 * @param customerbean
	 * 
	 * @return void
	 */
	public void createNewCustomer(CustomerBean cb) throws RollbackException {
	//	try {
		//	Transaction.begin();
			create(cb);
	/*		Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}

	public CustomerBean login(String userName, String password)
			throws RollbackException {
//		try {
	//		Transaction.begin();
			CustomerBean[] beans = match(new MatchArg[] {
					MatchArg.equals("username", userName),
					MatchArg.equals("password", password) });
			if (beans.length == 0)
				return null;
//			Transaction.commit();
			return beans[0];
/*		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}
	
	/*
	 * void updataCash
	 * 
	 * @description Update cash
	 * 
	 * @param customer_id, cash(long)
	 * 
	 * @return void
	 */
	public void updataTempCash(int customer_id, long tempcash) throws RollbackException {
	//	try {
		//	Transaction.begin();
			CustomerBean dbCustomer = read(customer_id);

			if (dbCustomer == null) {
				throw new RollbackException("User no longer exists");
			}

			dbCustomer.setTempcash(tempcash);

			update(dbCustomer);
/*			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		*/
	}

	public CustomerBean[] getRelateCustomers(String username) throws RollbackException {
		// TODO Auto-generated method stub
		CustomerBean[] customers = match(MatchArg.contains("username", username));
		//System.out.println(customers.length);
		return customers;
	}

}
