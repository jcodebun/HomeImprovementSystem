<%@page import="in.co.home.imp.system.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.home.imp.system.bean.ProjectBean"%>
<%@page import="in.co.home.imp.system.model.ProjectModel"%>
<%@page import="in.co.home.imp.system.controller.ProjectListCtl"%>
<%@page import="in.co.home.imp.system.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project List</title>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
<style type="text/css">
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>
<%@ include file="Header.jsp" %>
		<section class="banner-area relative" id="home">	
				<div class="overlay overlay-bg"></div>
				<div class="container">				
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Project List				
							</h1>	
						</div>	
					</div>
				</div>
			</section>
			
<form action="<%=HISView.PROJECT_LIST_CTL%>" method="post">			
<section class="testimonial py-5" id="testimonial">
    <div class="container">
        <div class="row ">
                    <div class="form-row">
                        <div class="form-group col-md-4">
                        <label>Application Id</label>
                          <input id="Full Name" name="appId" placeholder="Application Id" class="form-control" type="text"
                          value="<%=ServletUtility.getParameter("appId", request)%>">
                        
                        </div>
                        <div class="form-group col-md-4">
                        <label>Project Id</label>
                          <input type="text" class="form-control" name="proId" id="inputEmail4" placeholder="Application Name"
                          value="<%=ServletUtility.getParameter("proId", request)%>">
                        </div>
                        
                         <div class="form-group col-md-4">
                        
                          <input type="submit" name="operation" value="<%=ProjectListCtl.OP_SEARCH%>" class="btn btn-danger" style="margin-top: 30px">
                        </div>
                      </div>
            </div>
        </div>
</section>
<center><b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b></center>		
<table>
  <thead>
    <tr>
    
    <th><input type="checkbox" id="selectall">Select All</th>
      <th>S.No</th>
      <th>Project Id</th>
      <th>Project Name</th>
      <th>Application ID</th>
      <th>Application Name</th>
      <th>To Date</th>
      <th>From Date</th>
      <th>No.Of Employee</th>
      <th>Description</th>
    <% if(userBean.getRoleId()==1){%>  
      <th>Edit</th>
     <%} %>
    </tr>
  </thead>
  <tbody id="myTable">
  				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					ProjectBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<ProjectBean> iterator = list.iterator();
					while (iterator.hasNext()) {
						bean = iterator.next();
				%>
    					<tr>
    					<td align="center"><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"></td>
    					<td align="center"><%=index++%></td>
      						<td><%=bean.getProjectId()%></td>
      						<td><%=bean.getName()%></td>
      						<td><%=bean.getApplicationId()%></td>
      						<td><%=bean.getApplicationName()%></td>
      						<td><%=DataUtility.getDateString(bean.getToDate())%></td>
      						<td><%=DataUtility.getDateString(bean.getFromDate())%></td>
      						<td><%=bean.getEmployeeQuantity()%></td>
      						<td><%=bean.getDescription()%></td>
      						 <% if(userBean.getRoleId()==1){%> 
      						<td align="center"><a href="ProjectCtl?id=<%=bean.getId()%>" class="btn btn-danger">Edit</a></td>
  						 	<%} %>
  						 </tr>
   					<%} %>
  </tbody>
</table>
<br>
<table border="0">

	<tr>
				<td><input type="submit" name="operation" class="btn btn-danger"
						value="<%=ProjectListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						 <% if(userBean.getRoleId()==1){%> 
						<td><input type="submit" name="operation" class="btn btn-danger"
						value="<%=ProjectListCtl.OP_NEW%>"></td>
						
					<td><input type="submit" name="operation" class="btn btn-danger"
						value="<%=ProjectListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
						<%} %>
					<%
						ProjectModel model = new ProjectModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-danger"
						value="<%=ProjectListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>
	</tr>

</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>" > <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<%@ include file="Footer.jsp" %>
</body>
</html>