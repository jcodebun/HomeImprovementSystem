package in.co.home.imp.system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.BaseBean;
import in.co.home.imp.system.bean.StatusBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.model.StatusModel;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.DataValidator;
import in.co.home.imp.system.util.PropertyReader;
import in.co.home.imp.system.util.ServletUtility;

/**
 * Servlet implementation class StatusCtl
 */
@WebServlet(name="StatusCtl",urlPatterns={"/ctl/StatusCtl"})
public class StatusCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(StatusCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("StatusCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("proId"))) {
            request.setAttribute("proId",
                    PropertyReader.getValue("error.require", "Project Id"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("appId"))) {
            request.setAttribute("appId",
                    PropertyReader.getValue("error.require", "Application Id"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Project Status"));
            pass = false;
        }

        log.debug("StatusCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("StatusCtl populateBean method start");
		StatusBean bean=new StatusBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setProjectId(DataUtility.getLong(request.getParameter("proId")));
		bean.setApplicationId(DataUtility.getLong(request.getParameter("appId")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("StatusCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("StatusCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   StatusModel model = new StatusModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				StatusBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("StatusCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("StatusCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		StatusModel model=new StatusModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			StatusBean bean=(StatusBean)populateBean(request);
				try {
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(HISView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists",
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
			StatusBean bean=	(StatusBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(HISView.STATUS_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HISView.STATUS_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(HISView.STATUS_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("StatusCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HISView.STATUS_VIEW;
	}

}
