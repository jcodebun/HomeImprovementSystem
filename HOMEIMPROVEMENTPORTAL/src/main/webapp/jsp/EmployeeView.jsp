<%@page import="in.co.home.imp.system.controller.EmployeeCtl"%>
<%@page import="in.co.home.imp.system.bean.EmployeeBean"%>
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
                
                <form method="post" action="<%=HISView.EMPLOYEE_CTL%>">
                
                <jsp:useBean id="bean" class="in.co.home.imp.system.bean.EmployeeBean"
			scope="request"></jsp:useBean>
			
			<%		long userId=(long)session.getAttribute("userId"); %>
			<input type="hidden" name="userId" value="<%=userId%>">
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
                        <label>Skills</label>
                          <input id="Full Name" name="skills" placeholder="Enter Skills" class="form-control" type="text" 
                          value="<%=DataUtility.getStringData(bean.getSkills())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("skills", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Date Of Birth</label>
                          <input type="text" class="form-control" id="inputEmail4" placeholder="Date Of Birth" name="dob" 
                          value="<%=DataUtility.getDateString(bean.getDob())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
                        </div>
                      </div>
                      
                  
                      
                    <div class="form-row">
                        <div class="form-group col-md-12">
                        <label>About Your Self</label>
                                  <textarea id="comment" name="description" cols="40" rows="5" placeholder="Enter About Your Self" class="form-control">
                                  <%=DataUtility.getStringData(bean.getDescription())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=EmployeeCtl.OP_SAVE%>">
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=EmployeeCtl.OP_RESET%>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="Footer.jsp" %>
</body>
</html>