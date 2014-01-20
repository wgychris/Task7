package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean>{

	public EmployeeDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}

}
