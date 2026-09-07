<%@page import="in.co.home.imp.system.controller.UserCtl"%>
<%@page import="in.co.home.imp.system.util.DataUtility"%>
<%@page import="in.co.home.imp.system.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp" %>

		<section class="banner-area relative" id="home">	
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Employee				
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
                
                <form method="post" action="<%=HISView.USER_CTL%>">
                
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
                        <div class="form-group col-md-6">
                         <label>First Name</label>
                          <input id="Full Name" name="firstName" placeholder="First Name" class="form-control" type="text" 
                          value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                         <label>Last Name</label>
                          <input type="text" class="form-control" id="inputEmail4" placeholder="Last Name" name="lastName" 
                          value="<%=DataUtility.getStringData(bean.getLastName())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                        </div>
                      </div>
                      
                  <div class="form-row">
                        
                        <div class="form-group col-md-6">
                         <label>Email Id</label>
                          <input id="Full Name" name="login" placeholder="Email Id" class="form-control" type="text"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>">
                        
                        <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                    	</div>
                    	
                    	<div class="form-group col-md-6">
                    	 <label>Mobile No.</label>
                            <input id="Mobile No." name="mobile" placeholder="Mobile No." class="form-control"  type="text"
                            value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                            <font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
                        </div>
                        
                  </div>
                  
                   <div class="form-row">
                        <div class="form-group col-md-6">
                         <label>Password</label>
                          <input id="Full Name" name="password" placeholder="Password" class="form-control" type="password"
                          value="<%=DataUtility.getStringData(bean.getPassword())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                         <label>Confirm Password</label>
                          <input type="password" class="form-control" id="inputEmail4" placeholder="Confirm Password" name="confirmPassword"
                          value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                        </div>
                   </div>
                      
                    <div class="form-row">
                        <div class="form-group col-md-12">
                         <label>Address</label>
                                  <textarea id="comment" name="address" cols="40" rows="5" placeholder="Enter Address" class="form-control">
                                  <%=DataUtility.getStringData(bean.getAddress())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=UserCtl.OP_SAVE%>">
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=UserCtl.OP_RESET%>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="Footer.jsp" %>
</body>
</html>