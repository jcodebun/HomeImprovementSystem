package in.co.home.imp.system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.ApplicationBean;
import in.co.home.imp.system.bean.BaseBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.model.ApplicationModel;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.DataValidator;
import in.co.home.imp.system.util.PropertyReader;
import in.co.home.imp.system.util.ServletUtility;

/**
 * Servlet implementation class ApplicationCtl
 */
@WebServlet(name="ApplicationCtl",urlPatterns={"/ctl/ApplicationCtl"})
public class ApplicationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(ApplicationCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("ApplicationCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("appName"))) {
            request.setAttribute("appName",
                    PropertyReader.getValue("error.require", "Application Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("appName"))) {
			request.setAttribute("appName",
					PropertyReader.getValue("error.name", "Application Name"));
			pass = false;
		}

        if (DataValidator.isNull(request.getParameter("city"))) {
            request.setAttribute("city",
                    PropertyReader.getValue("error.require", "city"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("state"))) {
            request.setAttribute("state",
                    PropertyReader.getValue("error.require", "State"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("address1"))) {
            request.setAttribute("address1",
                    PropertyReader.getValue("error.require", "Address Line 1"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("address2"))) {
            request.setAttribute("address2",
                    PropertyReader.getValue("error.require", "Address Line 2"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("pinCode"))) {
            request.setAttribute("pinCode",
                    PropertyReader.getValue("error.require", "pinCode"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("fixIncome"))) {
            request.setAttribute("cfixIncome",
                    PropertyReader.getValue("error.require", "Fixed Income"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("anotherIn"))) {
            request.setAttribute("anotherIn",
                    PropertyReader.getValue("error.require", "Another Income"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("resident"))) {
            request.setAttribute("resident",
                    PropertyReader.getValue("error.require", "Resident Detail"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("genralCon"))) {
            request.setAttribute("genralCon",
                    PropertyReader.getValue("error.require", "Genral Condition"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("workDes"))) {
            request.setAttribute("workDes",
                    PropertyReader.getValue("error.require", "Work Description"));
            pass = false;
        }
        

        log.debug("ApplicationCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("ApplicationCtl populateBean method start");
		ApplicationBean bean=new ApplicationBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setApplicationName(DataUtility.getString(request.getParameter("appName")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setUserId(DataUtility.getLong(request.getParameter("userId")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setAddressline1(DataUtility.getString(request.getParameter("address1")));
		bean.setAddressline2(DataUtility.getString(request.getParameter("address2")));
		bean.setPincode(DataUtility.getString(request.getParameter("pinCode")));
		bean.setFixedIncome(DataUtility.getString(request.getParameter("fixIncome")));
		bean.setResidentDetail(DataUtility.getString(request.getParameter("resident")));
		bean.setAnotherIncome(DataUtility.getString(request.getParameter("anotherIn")));
		bean.setGenralCondition(DataUtility.getString(request.getParameter("genralCon")));
		bean.setWorkDescription(DataUtility.getString(request.getParameter("workDes")));
		populateDTO(bean, request);
		log.debug("ApplicationCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   ApplicationModel model = new ApplicationModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				ApplicationBean bean;
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
			log.debug("ApplicationCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		ApplicationModel model=new ApplicationModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			ApplicationBean bean=(ApplicationBean)populateBean(request);
				try {
					if(id>0){
						
					/*model.update(bean);*/
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
				ServletUtility.setErrorMessage("Application already exists",
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
			ApplicationBean bean=	(ApplicationBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(HISView.APPLICATION_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HISView.APPLICATION_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(HISView.APPLICATION_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("ApplicationCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HISView.APPLICATION_VIEW;
	}

}
