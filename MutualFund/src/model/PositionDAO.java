package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.CustomerBean;
import databeans.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean>{
	
	public PositionDAO(String tableName, ConnectionPool cp) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
	
	/*
	 * Create New Position row (used by employee and customer)
	 * @param customerbean(both primary keys shall not be empty)
	 * @return void
	 */
	public void createNewPosition(PositionBean pb) throws RollbackException{
		try{
			Transaction.begin();
			create(pb);
			Transaction.commit();
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	
	/*
	 * Update position
	 * @param positionbean(must provide both primarykeys and new shares value)
	 * @return void
	 */
	public void updataCash(PositionBean pb) throws RollbackException{
		try{
			Transaction.begin();
			PositionBean newpb = read(pb.getFund_id(),pb.getFund_id());
			
			if(newpb==null) {
				throw new RollbackException("Position "+newpb.getFund_id()+" no longer exists");
			}
			
			update(newpb);
			Transaction.commit();
		}finally {
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	/*
	 * Get position
	 * @param customer_id and fund_id
	 * @return position_bean
	 */
	public PositionBean getPosition(int customer_id, int fund_id) throws RollbackException {
		PositionBean pb = read(customer_id,fund_id);
		return pb;
	}
	
	public PositionBean[] getAllPositionsByCustomerIdBeans (int customer_id) throws RollbackException {
		Transaction.begin();
		PositionBean[] newBeanArray = match(MatchArg.equals("customer_id", customer_id));
		System.out.print("In getAllPositionsByCustomerIdBeans:");
		if(newBeanArray.length > 0) {
			System.out.print("get position successfully \n");
			return newBeanArray;
		}
		System.out.print("get position failed \n");
		return null;
	}
}
