package model;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
/*
 * General Model
 * Yusi Jan 19 Version 1.0
 */
public class Model {
	private CustomerDAO cDAO;
	private EmployeeDAO eDAO;
	
	public Model(ServletConfig config) throws ServletException {
		try{
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			cDAO  = new CustomerDAO("customer", pool);
			eDAO = new EmployeeDAO("employee",pool);
			//transactionDAO,fundDAO,fund_priceDAO,positionDAO,
		}catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	public CustomerDAO getFavoriteDAO() { return cDAO; }
	public EmployeeDAO  getUserDAO()  { return eDAO;  }
	//transactionDAO,fundDAO,fund_priceDAO,positionDAO,
}
