<%@page import="in.co.home.imp.system.controller.ApplicationCtl"%>
<%@page import="in.co.home.imp.system.util.DataUtility"%>
<%@page import="in.co.home.imp.system.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application</title>
</head>
<body>
<%@include file="Header.jsp" %>

		<section class="banner-area relative" id="home">	
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Application				
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
                
                <form method="post" action="<%=HISView.APPLICATION_CTL%>">
                
                <jsp:useBean id="bean" class="in.co.home.imp.system.bean.ApplicationBean"
			scope="request"></jsp:useBean>
			
			<% UserBean ubean=(UserBean)session.getAttribute("user");%>
			
			<input type="hidden" name="userId" value="<%=ubean.getId()%>">
			<input type="hidden" name="userName" value="<%=ubean.getFirstName()+" "+ubean.getLastName()%>">
			
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
				 <label>Application Name</label>
                          <input id="Full Name" name="appName" placeholder="Application Name" class="form-control" type="text"
                        value="<%=DataUtility.getStringData(bean.getApplicationName())%>">
                        <font color="red"> <%=ServletUtility.getErrorMessage("appName", request)%></font>
                    	</div>
                    	
                    	<div class="form-group col-md-6">
                    	<label>Pin Code</label>
                            <input id="Mobile No." name="pinCode" placeholder="Pin Code" class="form-control"  type="text"
                            value="<%=DataUtility.getStringData(bean.getPincode())%>">
                            <font color="red"> <%=ServletUtility.getErrorMessage("pinCode", request)%></font>
                        </div>
				 </div>
			
                  <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>City</label>
                          <input id="Full Name" name="city" placeholder="City Name" class="form-control" type="text" 
                          value="<%=DataUtility.getStringData(bean.getCity())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                        <label>State</label>
                          <input type="text" class="form-control" id="inputEmail4" placeholder="State Name" name="state" 
                          value="<%=DataUtility.getStringData(bean.getState())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font>
                        </div>
                      </div>
                      
                 
                  
                   <div class="form-row">
                        <div class="form-group col-md-6">
                        <label>Fixed Income</label>
                          <input id="Full Name" name="fixIncome" placeholder="Fixed Income" class="form-control" type="text"
                          value="<%=DataUtility.getStringData(bean.getFixedIncome())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("fixIncome", request)%></font>
                        </div>
                        <div class="form-group col-md-6">
                        <label>Another Income</label>
                          <input type="text" class="form-control" id="inputEmail4" placeholder="Another Income" name="anotherIn"
                          value="<%=DataUtility.getStringData(bean.getAnotherIncome())%>">
                          <font color="red"> <%=ServletUtility.getErrorMessage("anotherIn", request)%></font>
                        </div>
                   </div>
                      
                       <div class="form-row">
                        <div class="form-group col-md-12">
                        <label>Address Line 1</label>
                                  <textarea id="comment" name="address1" cols="20" rows="4" placeholder="Enter Address Line 1" class="form-control">
                                  <%=DataUtility.getStringData(bean.getAddressline1())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("address1", request)%></font>
                        </div>
                    </div>
                    
                     <div class="form-row">
                        <div class="form-group col-md-12">
                        <label>Address Line 2</label>
                                  <textarea id="comment" name="address2" cols="20" rows="4" placeholder="Enter Address Line 2" class="form-control">
                                  <%=DataUtility.getStringData(bean.getResidentDetail())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("address2", request)%></font>
                        </div>
                    </div>
                      
                    <div class="form-row">
                        <div class="form-group col-md-12">
                        <label>Resident Detail</label>
                                  <textarea id="comment" name="resident" cols="20" rows="4" placeholder="Enter Resident Detail" class="form-control">
                                  <%=DataUtility.getStringData(bean.getResidentDetail())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("resident", request)%></font>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-12">
                        <label>Genral Condition</label>
                                  <textarea id="comment" cols="20" rows="4" name="genralCon" placeholder="Enter Genral Condition" class="form-control">
                                  <%=DataUtility.getStringData(bean.getGenralCondition())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("genralCon", request)%></font>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                        <label>Work Description</label>
                                  <textarea  name="workDes" cols="20" rows="4" placeholder="Enter Work Description" class="form-control">
                                  <%=DataUtility.getStringData(bean.getWorkDescription())%></textarea>
                                  <font color="red"> <%=ServletUtility.getErrorMessage("workDes", request)%></font>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=ApplicationCtl.OP_SAVE%>">
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" class="btn btn-danger" name="operation" value="<%=ApplicationCtl.OP_RESET%>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="Footer.jsp" %>
</body>
</html>