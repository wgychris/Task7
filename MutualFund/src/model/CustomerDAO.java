package model;
/*
 * CustomerDAO
 * Yusi Jan 19 Version 1.0
 * co-author
 */

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.CustomerBean;



public class CustomerDAO extends GenericDAO<CustomerBean>{

	public CustomerDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}

}
