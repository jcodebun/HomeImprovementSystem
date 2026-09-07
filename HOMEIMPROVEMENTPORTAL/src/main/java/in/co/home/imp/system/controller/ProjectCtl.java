package in.co.home.imp.system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.BaseBean;
import in.co.home.imp.system.bean.ProjectBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.model.ProjectModel;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.DataValidator;
import in.co.home.imp.system.util.PropertyReader;
import in.co.home.imp.system.util.ServletUtility;

/**
 * Servlet implementation class ProjectCtl
 */
@WebServlet(name="ProjectCtl",urlPatterns={"/ctl/ProjectCtl"})
public class ProjectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(ProjectCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("ProjectCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.name", "Name"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("appId"))) {
            request.setAttribute("appId",
                    PropertyReader.getValue("error.require", "Application ID"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("empQuantity"))) {
            request.setAttribute("empQuantity",
                    PropertyReader.getValue("error.require", "Employee Quantity"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("toDate"))) {
            request.setAttribute("toDate",
                    PropertyReader.getValue("error.require", "To Date"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("fromDate"))) {
            request.setAttribute("fromDate",
                    PropertyReader.getValue("error.require", "From Date"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }

        log.debug("ProjectCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("ProjectCtl populateBean method start");
		ProjectBean bean=new ProjectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setApplicationId(DataUtility.getLong(request.getParameter("appId")));
		bean.setEmployeeQuantity(DataUtility.getString(request.getParameter("empQuantity")));
		bean.setToDate(DataUtility.getDate(request.getParameter("toDate")));
		bean.setFromDate(DataUtility.getDate(request.getParameter("fromDate")));
		populateDTO(bean, request);
		log.debug("ProjectCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ProjectCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   ProjectModel model = new ProjectModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				ProjectBean bean;
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
			log.debug("ProjectCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("ProjectCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			ProjectModel model=new ProjectModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				ProjectBean bean=(ProjectBean)populateBean(request);
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
					ServletUtility.setErrorMessage("Project  already exists",
							request);
				}
				
			}else if (OP_DELETE.equalsIgnoreCase(op)) {
				ProjectBean bean=	(ProjectBean)populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(HISView.PROJECT_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
			}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(HISView.PROJECT_LIST_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HISView.PROJECT_CTL, request, response);
			return;
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("ProjectCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HISView.PROJECT_VIEW;
	}

}
