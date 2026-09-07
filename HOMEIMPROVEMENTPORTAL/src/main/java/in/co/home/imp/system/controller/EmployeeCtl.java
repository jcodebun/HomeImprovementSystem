package in.co.home.imp.system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.BaseBean;
import in.co.home.imp.system.bean.EmployeeBean;
import in.co.home.imp.system.bean.RoleBean;
import in.co.home.imp.system.bean.UserBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.model.EmployeeModel;
import in.co.home.imp.system.model.UserModel;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.DataValidator;
import in.co.home.imp.system.util.PropertyReader;
import in.co.home.imp.system.util.ServletUtility;

/**
 * Servlet implementation class EmployeeCtl
 */
@WebServlet(name="EmployeeCtl",urlPatterns={"/ctl/EmployeeCtl"})
public class EmployeeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(EmployeeCtl.class);
   
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("EmployeeCtl Method validate Started");

		boolean pass = true;

		
		
		if (DataValidator.isNull(request.getParameter("skills"))) {
			request.setAttribute("skills", PropertyReader.getValue("error.require","Skills"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require","Description"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require","Date Of Birth"));
			pass = false;
		}

		log.debug("EmployeeCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("EmployeeCtl Method populatebean Started");

		EmployeeBean bean = new EmployeeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setUserId(DataUtility.getLong(request.getParameter("userId")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		bean.setSkills(DataUtility.getString(request.getParameter("skills")));
			
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.debug("EmployeeCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("EmployeeCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		EmployeeModel model = new EmployeeModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			EmployeeBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("EmployeeCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("EmployeeCtl Method doPost Started");
      
		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        EmployeeModel model = new EmployeeModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
        	EmployeeBean bean = (EmployeeBean) populateBean(request);
            
            try {
                if (id > 0) {
                    model.update(bean);
                
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                    long pk = model.add(bean);
                   // bean.setId(pk);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }
              
               
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Login id already exists", request);
            }
            ServletUtility.forward(getView(), request, response);
        } else if (OP_DELETE.equalsIgnoreCase(op)) {

        	EmployeeBean bean = (EmployeeBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HISView.EMPLOYEE_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HISView.EMPLOYEE_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HISView.EMPLOYEE_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("EmployeeCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HISView.EMPLOYEE_VIEW;
	}

}
