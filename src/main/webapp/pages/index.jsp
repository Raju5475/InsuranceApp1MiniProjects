<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mini Project-1</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
     crossorigin="anonymous">
</head>
<body >
<div class="container">

<h3 class="pb-3 pt-3 fs-2 fw-bold text-decoration-underline ">Welocome To Report Insurance App</h3>

<form:form action="search" modelAttribute="search" method="POST">
<table>
 <tr>
 <td >Plan Name:</td>
 <td>
 <form:select path="plan_Name">
 
 <form:option value="">-Select</form:option>
  <form:options items="${status}"/>
 </form:select>
 
 
 </td>
 <td style="padding:50px;">Plan Status:</td>
 <td>
 <form:select path="plan_Status">
 
 <form:option value="">-Select</form:option>
  <form:options items="${names}"/>
 </form:select>
 </td>
 <td>Gender:</td>
 <td>
 <form:select path="gender">
 <form:option value="">-Select</form:option>
 <form:option value="Male">Male</form:option>
 <form:option value="FeMale">Fe-Male</form:option>
 
 </form:select>
 
 </td>
 </tr>
 <tr>
   <td>Start Date:</td>
   <td>
   <form:input path="start_Date"  type="date"/>
   </td>
   <td style="padding-left:50px" >End Date:</td>
   <td>
   <form:input path="end_Date" type="date"  />
   </td>
   </tr>
   <tr>
   <td style="padding-top:10px">
   <input type="submit" value="search" class="btn btn-primary">
   </td>
   
   </tr>
</table>

</form:form>
<hr/>
<table class="table table-striped table-hover">

<thead>
<tr>
<th class="text-decoration-underline">Sno</th>
<th class="text-decoration-underline" >Holder Name</th>
<th class="text-decoration-underline" >Gender</th>
<th class="text-decoration-underline" >Plan Name</th>
<th class="text-decoration-underline" >Plan Status</th>
<th class="text-decoration-underline" >Benifit Amount</th>
<th class="text-decoration-underline" >Denial Reason</th>
<th class="text-decoration-underline" >Terminated Reason</th>
<th class="text-decoration-underline" >Start Date</th>
<th class="text-decoration-underline" >End Date</th>
</tr>

</thead>
<tbody>

<c:forEach items="${plans}" var="plan" varStatus="index">
<tr>
<td>${index.count}</td>
<td>${plan.citizenName}</td>
<td>${plan.gender}</td>
<td>${plan.planName}</td>
<td>${plan.planStatus}</td>
<td>${plan.benefitAmount}</td>
<td>${plan.denialReason}</td>
<td>${plan.terminatedReason}</td>
<td>${plan.planStartDate}</td>
<td>${plan.planStartDate}</td>


</tr>

</c:forEach>
<tr>

<c:if test="${empty plans}">
<td colspan="10" style="text-align:center">No Records Found</td>
</c:if>        
</tr>

</tbody>


</table>


<hr/>

Export:<a href="excel" class="btn btn-secondary">EXCEL</a> <a href="pdf" class="btn btn-danger" >PDF</a>
 

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
 crossorigin="anonymous"></script>

</body>
</html>