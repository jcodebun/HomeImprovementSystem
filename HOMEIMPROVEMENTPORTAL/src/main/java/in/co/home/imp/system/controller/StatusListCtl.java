package in.co.home.imp.system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.BaseBean;
import in.co.home.imp.system.bean.ProjectBean;
import in.co.home.imp.system.bean.StatusBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.model.ProjectModel;
import in.co.home.imp.system.model.StatusModel;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.PropertyReader;
import in.co.home.imp.system.util.ServletUtility;

/**
 * Servlet implementation class StatusListCtl
 */
@WebServlet(name = "StatusListCtl", urlPatterns = { "/ctl/StatusListCtl" })
public class StatusListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
private static Logger log = Logger.getLogger(StatusListCtl.class);
    
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("StatusListCtl populateBean method start");
		StatusBean bean = new StatusBean();
		bean.setProjectId(DataUtility.getLong(request.getParameter("proId")));
		bean.setApplicationId(DataUtility.getLong(request.getParameter("appId")));
		log.debug("StatusListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("StatusListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		StatusModel model = new StatusModel();
		StatusBean bean = (StatusBean) populateBean(request);
		try {
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("StatusListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("StatusListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		StatusBean bean = (StatusBean) populateBean(request);

		StatusModel model = new StatusModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {

				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HISView.STATUS_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				StatusBean deletebean = new StatusBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HISView.STATUS_LIST_CTL, request, response);
			return;

		}

		try {

			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("StatusListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HISView.STATUS_LIST_VIEW;
	}

}
