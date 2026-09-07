package in.co.home.imp.system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.ApplicationBean;
import in.co.home.imp.system.bean.RoleBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DatabaseException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.JDBCDataSource;

public class ApplicationModel {

	private static Logger log = Logger.getLogger(ApplicationModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Application");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public Integer nextAppId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ApplicationId) FROM H_Application");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public long add(ApplicationBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_Application VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getUserId());
			pstmt.setString(3, bean.getUserName());
			pstmt.setString(4, bean.getApplicationName());
			pstmt.setLong(5,nextAppId());
			pstmt.setString(6, bean.getCity());
			pstmt.setString(7, bean.getState());
			pstmt.setString(8, bean.getAddressline1());
			pstmt.setString(9, bean.getAddressline2());
			pstmt.setString(10, bean.getPincode());
			pstmt.setTimestamp(11,DataUtility.getCurrentTimestamp());
			pstmt.setString(12, bean.getFixedIncome());
			pstmt.setString(13, bean.getAnotherIncome());
			pstmt.setString(14, bean.getResidentDetail());
			pstmt.setString(15, bean.getGenralCondition());
			pstmt.setString(16, bean.getWorkDescription());
			pstmt.setString(17, bean.getCreatedBy());
			pstmt.setString(18, bean.getModifiedBy());
			pstmt.setTimestamp(19, bean.getCreatedDatetime());
			pstmt.setTimestamp(20, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	 /**
     * Delete a Role
     * 
     * @param bean
     * @throws DatabaseException
     * @throws ApplicationException
     */
	    public void delete(ApplicationBean bean) throws ApplicationException {
	        log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM H_Application WHERE ID=?");
	            pstmt.setLong(1, bean.getId());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException(
	                    "Exception : Exception in delete Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model delete Started");
	    }
	    
	    
	    /**
	     * Find User by Role
	     * 
	     * @param name
	     *            : get parameter
	     * @return bean
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public ApplicationBean findByName(String name) throws ApplicationException {
	        log.debug("Model findBy EmailId Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM H_application WHERE ApplicationNAME=?");
	        ApplicationBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, name);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by emailId");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findBy EmailId End");
	        return bean;
	    }
	    
	    
	    public ApplicationBean findByUserName(String name) throws ApplicationException {
	        log.debug("Model findBy EmailId Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM H_application WHERE UserNAME=?");
	        ApplicationBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, name);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by emailId");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findBy EmailId End");
	        return bean;
	    }
	    
	    public ApplicationBean findByPK(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE ID=?");
	        ApplicationBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	            }
	            rs.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }
	    
	    public ApplicationBean findByAppID(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE applicationID=?");
	        ApplicationBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	            }
	            rs.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }
	    
	    
	    public ApplicationBean findByUserID(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE userID=?");
	        ApplicationBean bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	            }
	            rs.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }
	    
	    public List search(ApplicationBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

	    /**
	     * Search Role with pagination
	     * 
	     * @return list : List of Roles
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * 
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List search(ApplicationBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getUserId() > 0) {
	                sql.append(" AND userid = " + bean.getUserId());
	            }
	            
	            if (bean.getApplicationId() > 0) {
	                sql.append(" AND Applicationid = " + bean.getApplicationId());
	            }
	            if (bean.getUserName() != null && bean.getUserName().length() > 0) {
					sql.append(" AND UserNAME LIKE '" + bean.getUserName() + "%'");
	            }
	            if (bean.getApplicationName() != null && bean.getApplicationName().length() > 0) {
					sql.append(" AND ApplicationNAME LIKE '" + bean.getApplicationName() + "%'");
	            }
	        }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" Limit " + pageNo + ", " + pageSize);
	            // sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model search End");
	        return list;
	    }
	    
	    public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	    /**
	     * Get List of Role with pagination
	     * 
	     * @return list : List of Role
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * @throws DatabaseException
	     *  @throws ApplicationException
	     */
	    public List list(int pageNo, int pageSize) throws ApplicationException {
	        log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from H_Application");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                ApplicationBean bean = new ApplicationBean();
	                bean.setId(rs.getLong(1));
	                bean.setUserId(rs.getLong(2));
	                bean.setUserName(rs.getString(3));
	                bean.setApplicationName(rs.getString(4));
	                bean.setApplicationId(rs.getLong(5));
	                bean.setCity(rs.getString(6));
	                bean.setState(rs.getString(7));
	                bean.setAddressline1(rs.getString(8));
	                bean.setAddressline2(rs.getString(9));
	                bean.setPincode(rs.getString(10));
	                bean.setDate(rs.getTimestamp(11));
	                bean.setFixedIncome(rs.getString(12));
	                bean.setAnotherIncome(rs.getString(13));
	                bean.setResidentDetail(rs.getString(14));
	                bean.setGenralCondition(rs.getString(15));
	                bean.setWorkDescription(rs.getString(16));
	                bean.setCreatedBy(rs.getString(17));
	                bean.setModifiedBy(rs.getString(18));
	                bean.setCreatedDatetime(rs.getTimestamp(19));
	                bean.setModifiedDatetime(rs.getTimestamp(20));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model list End");
	        return list;

	    }

}
