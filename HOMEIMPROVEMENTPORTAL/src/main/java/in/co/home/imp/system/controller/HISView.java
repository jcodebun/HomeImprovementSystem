package in.co.home.imp.system.controller;

public interface HISView {
	
	public String APP_CONTEXT = "/HOMEIMPROVEMENTPORTAL";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String STATUS_VIEW = PAGE_FOLDER + "/StatusView.jsp";	
	public String STATUS_LIST_VIEW = PAGE_FOLDER + "/StatusListView.jsp";

	public String EMPLOYEE_VIEW = PAGE_FOLDER + "/EmployeeView.jsp";	
	public String EMPLOYEE_LIST_VIEW = PAGE_FOLDER + "/EmployeeListView.jsp";
	
	public String PROJECT_VIEW = PAGE_FOLDER + "/ProjectView.jsp";	
	public String PROJECT_LIST_VIEW = PAGE_FOLDER + "/ProjectListView.jsp";

	public String APPLICATION_VIEW = PAGE_FOLDER + "/ApplicationView.jsp";	
	public String APPLICATION_LIST_VIEW = PAGE_FOLDER + "/ApplicationListView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	public String DESIGN_VIEW = PAGE_FOLDER + "/Design.jsp";	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String DESIGN_CTL = APP_CONTEXT + "/DesignCtl";
	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String APPLICATION_CTL = APP_CONTEXT + "/ctl/ApplicationCtl";
	public String APPLICATION_LIST_CTL = APP_CONTEXT + "/ctl/ApplicationListCtl";
	
	public String EMPLOYEE_CTL = APP_CONTEXT + "/ctl/EmployeeCtl";
	public String EMPLOYEE_LIST_CTL = APP_CONTEXT + "/ctl/EmployeeListCtl";
	
	public String PROJECT_CTL = APP_CONTEXT + "/ctl/ProjectCtl";
	public String PROJECT_LIST_CTL = APP_CONTEXT + "/ctl/ProjectListCtl";
	
	public String STATUS_CTL = APP_CONTEXT + "/ctl/StatusCtl";
	public String STATUS_LIST_CTL = APP_CONTEXT + "/ctl/StatusListCtl";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
