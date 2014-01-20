package model;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
/*
 * General Model
 * YD Jan 19 Version 1.0
 */
public class Model {
	private CustomerDAO cDAO;
	private EmployeeDAO eDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	
	public Model(ServletConfig config) throws ServletException {
		try{
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			cDAO  = new CustomerDAO("customer", pool);
			eDAO = new EmployeeDAO("employee",pool);
			positionDAO = new PositionDAO("position",pool);
			transactionDAO  = new TransactionDAO("transaction", pool);
			fundDAO = new FundDAO("fund",pool);
			fundPriceHistoryDAO = new FundPriceHistoryDAO("fundPriceHistory",pool);
			//transactionDAO,fundDAO,fund_priceDAO,positionDAO,
		}catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	public CustomerDAO getFavoriteDAO() { return cDAO; }
	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
	
	public FundDAO getFundDAO() {
		return fundDAO;
	}
	
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {
		return fundPriceHistoryDAO;
	}
	
	public PositionDAO getPositionDAO() {
		return positionDAO;
	}
	
	public EmployeeDAO  getUserDAO()  { return eDAO;  }
	//transactionDAO,fundDAO,fund_priceDAO,positionDAO,
}
