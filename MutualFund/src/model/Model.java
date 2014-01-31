package model;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databeans.EmployeeBean;
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
	
	public Model(ServletConfig config) throws ServletException{
		try{
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			//+"?user=yusiz&password=leoryzu521"
			cDAO  = new CustomerDAO("customer", pool);
			eDAO = new EmployeeDAO("employee",pool);
			positionDAO = new PositionDAO("position",pool);
			transactionDAO  = new TransactionDAO("transaction", pool);
			fundDAO = new FundDAO("fund",pool);
			fundPriceHistoryDAO = new FundPriceHistoryDAO("fundPriceHistory",pool);
			//transactionDAO,fundDAO,fund_priceDAO,positionDAO,
			int count=eDAO.getEmployeeCount();
			if(count==0){
				EmployeeBean eb=new EmployeeBean();
				eb.setFirstname("xc");
				eb.setLastname("l");
				eb.setPassword("001");
				eb.setUsername("001");
				eDAO.create(eb);
			}
		}catch (DAOException e) {
			throw new ServletException(e);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CustomerDAO getCustomerDAO() { return cDAO; }
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
	
	public EmployeeDAO  getEmployeeDAO()  { return eDAO;  }
	//transactionDAO,fundDAO,fund_priceDAO,positionDAO,
}
