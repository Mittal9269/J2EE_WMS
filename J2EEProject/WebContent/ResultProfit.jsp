<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
   <%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
<h1>You are not logged in</h1><br/>
<a href="Login.jsp">Please Login</a>
<%} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WholeSale Maangement System</title>
<link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
        <link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css">
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/core.css">
        <link rel="stylesheet" href="assets/css/theme.css">
        <link rel="stylesheet" href="assets/css/forms.css">
        <link rel="stylesheet" href="assets/css/custom.css">

        <script src="assets/js/jquery-1.11.3.min.js"></script>

</head>
<style>
.center {
  margin: auto;
  width: 50%;
  border: 3px solid green;
  padding: 10px;
}
.styled-table {
    border-collapse: collapse;
    margin: 25px auto !important;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table thead tr {
    background-color: #009879;
    color: #ffffff;
    text-align: left;
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
.styled-table tbody tr {
    border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
    border-bottom: 2px solid #009879;
}
.styled-table tbody tr.active-row {
    font-weight: bold;
    color: #009879;
}
.button {
margin : 50px 50px !important;
  background-color: #e7e7e7; color: black;
  border: none;
  color: black;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
<body class="page-body login-page login-form-fall">
<div style="display: flex; flex-direction:row;">
	<form action="./Index.jsp" method="post">
		<input class="button" type="submit" value="Go Home"/>
	</form>
	<form action="./Logout.jsp" method="post">
		<input class="button" type="submit" value="Logout"/>
	</form>
</div>
	<h1 style="color : white;">Profit for the  ${Month}</h1>
	<h2 style="color : white;">The Total Money get from Customer is :- ${TotalFromCustomer}</h2>
	<h2 style="color : white;">The Total item sold  is in Money :- ${TotalFromCustomerInTrans}</h2>
	<h2 style="color : white;">The Total Money Given to Supplier is :- ${TotalFromSupplier}</h2>
	<h2 style="color : white;">The Money pending for the suppliers :- ${TotalFromSupplierInTrans}</h2>
	<h1 style="color : white;">The Total Profit is ${- TotalFromCustomerInTrans - TotalFromSupplier +  TotalFromSupplierInTrans + TotalFromCustomer}</h1>
 <script src="assets/js/gsap/TweenMax.min.js"></script>
        <script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
        <script src="assets/js/bootstrap.js"></script>
        <script src="assets/js/joinable.js"></script>
        <script src="assets/js/resizeable.js"></script>
        <script src="assets/js/sabuj-api.js"></script>
        <script src="assets/js/jquery.validate.min.js"></script>
        <script src="assets/js/neon-login.js"></script>


        <!-- JavaScripts initializations and stuff -->
        <script src="assets/js/custom.js"></script>


        <!-- Demo Settings -->
        <script src="assets/js/neon-demo.js"></script>
</body>
</html>
<%
    }
%>