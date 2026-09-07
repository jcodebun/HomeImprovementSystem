package in.co.home.imp.system.controller;

import java.io.IOException;
import java.util.List;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.home.imp.system.bean.BaseBean;
import in.co.home.imp.system.bean.RoleBean;
import in.co.home.imp.system.bean.UserBean;
import in.co.home.imp.system.exception.ApplicationException;
import in.co.home.imp.system.exception.DuplicateRecordException;
import in.co.home.imp.system.model.UserModel;
import in.co.home.imp.system.util.DataUtility;
import in.co.home.imp.system.util.DataValidator;
import in.co.home.imp.system.util.PropertyReader;
import in.co.home.imp.system.util.ServletUtility;



/**
 * Servlet implementation class UserCtl
 */

/**
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@ WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		

		

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} 

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require","Address"));
			pass = false;
		}

		log.debug("UserCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(RoleBean.EMPLOYEE);

		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
	
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
	
		bean.setConfirmPassword(DataUtility.getString(request
					.getParameter("confirmPassword")));
	
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
			
		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		populateDTO(bean, request);

		log.debug("UserCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		UserModel model = new UserModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			UserBean bean;
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
        log.debug("UserCtl Method doGet Ended");
	}

	

/**
 * Contains Submit logics
 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		if (OP_SAVE.equalsIgnoreCase(op)) {
			
			UserBean bean = (UserBean) populateBean(request);
			try {
			//	System.out.println("in try sign up");
				long pk = model.registerUser(bean);
				//System.out.println("register");
				bean.setId(pk);
				request.getSession(true).setAttribute("userId",pk);
				ServletUtility.redirect(HISView.EMPLOYEE_CTL, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(HISView.USER_CTL, request, response);
			return;
		}
		
        log.debug("UserCtl Method doPostEnded");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return HISView.USER_VIEW;
	}

}
