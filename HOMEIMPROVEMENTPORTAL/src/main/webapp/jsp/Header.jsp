<%@page import="in.co.home.imp.system.controller.LoginCtl"%>
<%@page import="in.co.home.imp.system.bean.RoleBean"%>
<%@page import="in.co.home.imp.system.bean.UserBean"%>
<%@page import="in.co.home.imp.system.controller.HISView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="img/fav.png">
		<!-- Author Meta -->
		<meta name="author" content="colorlib">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- meta character set -->
		<meta charset="UTF-8">
		<!-- Site Title -->
		<title>Interior</title>

		<link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet"> 
			<!--
			CSS
			============================================= -->
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/linearicons.css">
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/font-awesome.min.css">
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/bootstrap.css">
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/magnific-popup.css">
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/nice-select.css">							
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/animate.min.css">
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/owl.carousel.css">
			<link rel="stylesheet" href="/HOMEIMPROVEMENTPORTAL/css/main.css">
</head>
<body>
 <header id="header" id="home">
		  		<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>
		  		
			    <div class="container main-menu">
			    	<div class="row align-items-center justify-content-between d-flex">
				      <div id="logo">
				       <b><a href="<%=HISView.WELCOME_CTL%>" style="font-size:20px; color: white; font-family: inherit;">Home Improvement Portal</a></b>
				      </div>
				      <nav id="nav-menu-container">
				        <ul class="nav-menu">
				        
				        <%
        			if (userLoggedIn) {
    					%>
    					<%if(userBean.getRoleId()==RoleBean.ADMIN){ %>
				          
				          <li><a href="<%=HISView.WELCOME_CTL%>">Home</a></li>
				          <li><a href="<%=HISView.USER_CTL%>">Add Employee</a></li>
				          <li><a href="<%=HISView.APPLICATION_LIST_CTL%>">Application Report</a></li>
				          <li><a href="<%=HISView.PROJECT_CTL%>">Add Project</a></li>
				          <li><a href="<%=HISView.PROJECT_LIST_CTL%>">Project Report</a></li>
				          <li><a href="<%=HISView.STATUS_LIST_CTL%>">Status Report</a></li>
				          
				          <%}else if(userBean.getRoleId()==RoleBean.EMPLOYEE){%>
				          
				          <li><a href="<%=HISView.WELCOME_CTL%>">Home</a></li>
				          <li><a href="<%=HISView.PROJECT_LIST_CTL%>">Project Report</a></li>
				          <li><a href="<%=HISView.STATUS_CTL%>">Add Status</a></li>
				          <li><a href="<%=HISView.STATUS_LIST_CTL%>">Status Report</a></li>
				          
				          <%}else if(userBean.getRoleId()==RoleBean.CUSTOMER){%>
				         
				          <li><a href="<%=HISView.WELCOME_CTL%>">Home</a></li>
				          <li><a href="<%=HISView.APPLICATION_CTL%>">Add Application</a></li>
				          <li><a href="<%=HISView.APPLICATION_LIST_CTL%>">Application Report</a></li>
				         <li><a href="<%=HISView.PROJECT_LIST_CTL%>">Project Report</a></li>
				         <li><a href="<%=HISView.STATUS_LIST_CTL%>">Status Report</a></li>
				          <%} %>
				          <%}else{ %>
				          <li><a href="<%=HISView.DESIGN_CTL%>">Design</a></li>
				          <li><a href="<%=HISView.LOGIN_CTL%>">Login</a></li>
				          <li><a href="<%=HISView.USER_REGISTRATION_CTL%>">SignIn</a></li>
				           <%} %>
				          
				          <%if(userLoggedIn){%>
				           <li class="menu-has-children"><a href=""><%=welcomeMsg%></a>
				            <ul>
				            	<li><a href="<%=HISView.MY_PROFILE_CTL%>">My Profile</a></li>
				            	<li><a href="<%=HISView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
				              <li><a href="<%=HISView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a></li>
				            </ul>
				          </li>
				          <%} %>
				        </ul>
				      </nav><!-- #nav-menu-container -->		    		
			    	</div>
			    </div>
			  </header><!-- #header -->
        
</body>
</html>