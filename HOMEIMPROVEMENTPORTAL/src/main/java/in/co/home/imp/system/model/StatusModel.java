package in.co.home.imp.system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.ApplicationBean;
import in.co.home.imp.system.bean.ProjectBean;
import in.co.home.imp.system.bean.StatusBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DatabaseException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.JDBCDataSource;

public class StatusModel {
	
	private static Logger log = Logger.getLogger(StatusModel.class);

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
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Status");
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
	
	public StatusBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_status WHERE ID=?");
		StatusBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StatusBean();
				bean.setId(rs.getLong(1));
				bean.setProjectId(rs.getLong(2));
				bean.setProjectName(rs.getString(3));
				bean.setApplicationId(rs.getLong(4));
				bean.setApplicationName(rs.getString(5));
				bean.setDate(rs.getTimestamp(6));
				bean.setDescription(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	public long add(StatusBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		
		ApplicationModel aModel=new ApplicationModel();
		ApplicationBean aBean=aModel.findByAppID(bean.getApplicationId());
		
		ProjectModel pModel=new ProjectModel();
		ProjectBean pBean= pModel.findByProID(bean.getProjectId());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_status VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getProjectId());
			pstmt.setString(3, pBean.getName());
			pstmt.setLong(4, bean.getApplicationId());
			pstmt.setString(5, aBean.getApplicationName());
			pstmt.setTimestamp(6,DataUtility.getCurrentTimestamp());
			pstmt.setString(7, bean.getDescription());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
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
	
	public void delete(StatusBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_status WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	
	public void update(StatusBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		
		ApplicationModel aModel=new ApplicationModel();
		ApplicationBean aBean=aModel.findByAppID(bean.getApplicationId());
		
		
		ProjectModel pModel=new ProjectModel();
		ProjectBean pBean= pModel.findByProID(bean.getProjectId());
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE H_Status SET projectId=?,projectName=?,applicationId=?,applicationName=?,Date=?,DESCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getProjectId());
			pstmt.setString(2, pBean.getName());
			pstmt.setLong(3, bean.getApplicationId());
			pstmt.setString(4, aBean.getApplicationName());
			pstmt.setTimestamp(5,DataUtility.getCurrentTimestamp());
			pstmt.setString(6, bean.getDescription());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	 public List search(StatusBean bean) throws ApplicationException {
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
	    public List search(StatusBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_Status WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            
	            if (bean.getProjectId() > 0) {
	                sql.append(" AND projectid = " + bean.getProjectId());
	            }
	            
	            if (bean.getApplicationId() > 0) {
	                sql.append(" AND applicationid = " + bean.getApplicationId());
	            }
	            if (bean.getProjectName() != null && bean.getProjectName().length() > 0) {
					sql.append(" AND ProjectNAME LIKE '" + bean.getProjectName() + "%'");
	            }
	            if (bean.getApplicationName() != null && bean.getApplicationName().length() > 0) {
					sql.append(" AND ApplicationNAME LIKE '" + bean.getApplicationName() + "%'");
	            }
	            if (bean.getDescription() != null
	                    && bean.getDescription().length() > 0) {
					sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription()
	                        + "%'");
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
	                bean = new StatusBean();
	                bean.setId(rs.getLong(1));
					bean.setProjectId(rs.getLong(2));
					bean.setProjectName(rs.getString(3));
					bean.setApplicationId(rs.getLong(4));
					bean.setApplicationName(rs.getString(5));
					bean.setDate(rs.getTimestamp(6));
					bean.setDescription(rs.getString(7));
					bean.setCreatedBy(rs.getString(8));
					bean.setModifiedBy(rs.getString(9));
					bean.setCreatedDatetime(rs.getTimestamp(10));
					bean.setModifiedDatetime(rs.getTimestamp(11));
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
	        StringBuffer sql = new StringBuffer("select * from H_Status");
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
	            	StatusBean bean = new StatusBean();
	            	 bean.setId(rs.getLong(1));
						bean.setProjectId(rs.getLong(2));
						bean.setProjectName(rs.getString(3));
						bean.setApplicationId(rs.getLong(4));
						bean.setApplicationName(rs.getString(5));
						bean.setDate(rs.getTimestamp(6));
						bean.setDescription(rs.getString(7));
						bean.setCreatedBy(rs.getString(8));
						bean.setModifiedBy(rs.getString(9));
						bean.setCreatedDatetime(rs.getTimestamp(10));
						bean.setModifiedDatetime(rs.getTimestamp(11));
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
