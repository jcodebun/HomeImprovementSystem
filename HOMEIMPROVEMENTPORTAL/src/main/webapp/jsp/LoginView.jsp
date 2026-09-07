<%@page import="in.co.home.imp.system.controller.LoginCtl"%>
<%@page import="in.co.home.imp.system.util.ServletUtility"%>
<%@page import="in.co.home.imp.system.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp" %>

		<section class="banner-area relative" id="home">	
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Login				
							</h1>	
						</div>	
					</div>
				</div>
			</section>
<section class="testimonial py-5" id="testimonial">
    <div class="container">
        <div class="row ">
            
            <div class="col-md-8 py-5 border">
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                
                <form action="<%=HISView.LOGIN_CTL%>" method="post">
                <jsp:useBean id="bean" class="in.co.home.imp.system.bean.UserBean"
            scope="request"></jsp:useBean>
            
            <% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
               <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
              
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Email Id</label>
                          <input id="Full Name" name="login" placeholder="Enter Email ID" class="form-control" type="text"
                          value="<%=DataUtility.getStringData(bean.getLogin())%>">
                          <font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Password</label>
                          <input type="password" class="form-control" name="password" id="inputEmail4" placeholder="Enter Password"
                          value="<%=DataUtility.getStringData(bean.getPassword()) %>">
                          <font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
                          
                        </div>
                      </div>
                      
                      
                    <div class="form-row">
                        <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN %>" class="btn btn-danger">
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" class="btn btn-danger">
                         &nbsp;&nbsp;&nbsp;
                        
                    </div>
                    <br>
                
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="Footer.jsp" %>
</body>
</html>