<%@page import="in.co.home.imp.system.controller.ChangePasswordCtl"%>
<%@page import="in.co.home.imp.system.util.DataUtility"%>
<%@page import="in.co.home.imp.system.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<%@include file="Header.jsp" %>

		<section class="banner-area relative" id="home">	
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Change Password				
							</h1>	
							
						</div>	
					</div>
				</div>
			</section>
<section class="testimonial py-5" id="testimonial">
    <div class="container">
        <div class="row ">
            
            <div class="col-md-8 py-5 border">
                <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
                <b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                
                <form method="post" action="<%=HISView.CHANGE_PASSWORD_CTL%>">
                
                <jsp:useBean id="bean" class="in.co.home.imp.system.bean.UserBean"
			scope="request"></jsp:useBean>
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
			  <div class="form-row">
					  <div class="form-group col-md-12">
                         <label>Old Password</label>
                          <input id="Full Name" name="oldPassword" placeholder="Old Password" class="form-control" type="password"
                          value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
                          <font color="red"> <%=ServletUtility.getErrorMessage("old Password", request)%></font>
                        </div>
			  </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                         <label>New Password</label>
                          <input id="Full Name" name="newPassword" placeholder="New Password" class="form-control" type="password"
                         value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>>
                          <font color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                         <label>Confirm Password</label>
                          <input type="password" class="form-control" id="inputEmail4" placeholder="Confirm Password" name="confirmPassword"
                          value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
                          <font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                        </div>
                   </div>
                   
                  
                    
                    <div class="form-row">
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="Footer.jsp" %>
</body>
</html>