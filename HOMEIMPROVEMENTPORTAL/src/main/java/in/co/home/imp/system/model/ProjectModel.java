package in.co.home.imp.system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.ApplicationBean;
import in.co.home.imp.system.bean.ProjectBean;
import in.co.home.imp.system.bean.RoleBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DatabaseException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.util.JDBCDataSource;

public class ProjectModel {

	private static Logger log = Logger.getLogger(ProjectModel.class);

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
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Project");
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

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextProId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ProjectId) FROM H_Project");
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

	public ProjectBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_project WHERE NAME=?");
		ProjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setProjectId(rs.getLong(3));
				bean.setApplicationId(rs.getLong(4));
				bean.setApplicationName(rs.getString(5));
				bean.setEmployeeQuantity(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setToDate(rs.getDate(8));
				bean.setFromDate(rs.getDate(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	public ProjectBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Project WHERE ID=?");
		ProjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setProjectId(rs.getLong(3));
				bean.setApplicationId(rs.getLong(4));
				bean.setApplicationName(rs.getString(5));
				bean.setEmployeeQuantity(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setToDate(rs.getDate(8));
				bean.setFromDate(rs.getDate(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
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

	public ProjectBean findByProID(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Project WHERE ProjectID=?");
		ProjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setProjectId(rs.getLong(3));
				bean.setApplicationId(rs.getLong(4));
				bean.setApplicationName(rs.getString(5));
				bean.setEmployeeQuantity(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setToDate(rs.getDate(8));
				bean.setFromDate(rs.getDate(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
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

	public long add(ProjectBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		ProjectBean duplicataRole = findByName(bean.getName());

		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Project already exists");
		}
		
		ApplicationModel aModel=new ApplicationModel();
		ApplicationBean aBean=aModel.findByAppID(bean.getApplicationId());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_Project VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, nextProId());
			pstmt.setLong(4, bean.getApplicationId());
			pstmt.setString(5, aBean.getApplicationName());
			pstmt.setString(6, bean.getEmployeeQuantity());
			pstmt.setString(7, bean.getDescription());
			pstmt.setDate(8, new java.sql.Date(bean.getToDate().getTime()));
			pstmt.setDate(9, new java.sql.Date(bean.getFromDate().getTime()));
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
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
	public void delete(ProjectBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Project WHERE ID=?");
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

	public void update(ProjectBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		ProjectBean duplicataRole = findByName(bean.getName());

		// Check if updated Role already exist
		if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
			throw new DuplicateRecordException("Role already exists");
		}
		
		ApplicationModel aModel=new ApplicationModel();
		ApplicationBean aBean=aModel.findByAppID(bean.getApplicationId());
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE H_Project SET NAME=?,projectId=?,applicationId=?,applicationName=?,employeequantity=?,DESCRIPTION=?,toDate=?,fromDate=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setLong(2, nextProId());
			pstmt.setLong(3, bean.getApplicationId());
			pstmt.setString(4, aBean.getApplicationName());
			pstmt.setString(5, bean.getEmployeeQuantity());
			pstmt.setString(6, bean.getDescription());
			pstmt.setDate(7, new java.sql.Date(bean.getToDate().getTime()));
			pstmt.setDate(8, new java.sql.Date(bean.getFromDate().getTime()));
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
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
	
	 public List search(ProjectBean bean) throws ApplicationException {
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
	    public List search(ProjectBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_Project WHERE 1=1");
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
	            if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
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
	                bean = new ProjectBean();
	                bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setProjectId(rs.getLong(3));
					bean.setApplicationId(rs.getLong(4));
					bean.setApplicationName(rs.getString(5));
					bean.setEmployeeQuantity(rs.getString(6));
					bean.setDescription(rs.getString(7));
					bean.setToDate(rs.getDate(8));
					bean.setFromDate(rs.getDate(9));
					bean.setCreatedBy(rs.getString(10));
					bean.setModifiedBy(rs.getString(11));
					bean.setCreatedDatetime(rs.getTimestamp(12));
					bean.setModifiedDatetime(rs.getTimestamp(13));
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
	        StringBuffer sql = new StringBuffer("select * from H_Project");
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
	            	ProjectBean bean = new ProjectBean();
	            	bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setProjectId(rs.getLong(3));
					bean.setApplicationId(rs.getLong(4));
					bean.setApplicationName(rs.getString(5));
					bean.setEmployeeQuantity(rs.getString(6));
					bean.setDescription(rs.getString(7));
					bean.setToDate(rs.getDate(8));
					bean.setFromDate(rs.getDate(9));
					bean.setCreatedBy(rs.getString(10));
					bean.setModifiedBy(rs.getString(11));
					bean.setCreatedDatetime(rs.getTimestamp(12));
					bean.setModifiedDatetime(rs.getTimestamp(13));
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
