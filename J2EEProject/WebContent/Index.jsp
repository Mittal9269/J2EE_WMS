<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>WMS</title>
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
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

.column {
  float: left;
  width: 33%;
  padding: 0 10px;
  margin : 0 auto !important;
}

/* Remove extra left and right margins, due to padding */
.row {margin: 0 -5px;}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive columns */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  text-align: center;
  background-color: #515452;
  margin-top : 30px !important;
}
</style>
<body class="page-body login-page login-form-fall">
<form action="./Logout.jsp" method="post">
		<input class="button" type="submit" value="Logout"/>
	</form>
	<div class="row">
	<div class="column">
    <div class="card">
    <img  src="./Image/cart.jpg" alt="nothing">
	<form action="CategoryServlet" method="post">
		<input class="button" type="submit" value="Search Product"/>
	</form>
	</div></div>
	<div class="column">
    <div class="card">
    <img  src="./Image/cart.jpg" alt="nothing">
	<form action="SupplierServlet" method="post">
		<input class="button" type="submit" value="Search Supplier"/>
	</form>
	</div>
	</div>
	<div class="column">
    <div class="card">
    <img  src="./Image/cart.jpg" alt="nothing">
	<form action="CustomerServlet" method="post">
		<input class="button" type="submit" value="All Customers"/>
	</form>
	</div>
	</div>
	<div class="column">
    <div class="card">
    <img  src="./Image/cart.jpg" alt="nothing">
	<form action="./Payment.jsp" method="post">
		<input class="button" type="submit" value="Payment Detaills"/>
	</form>
	</div>
	</div>
	<div class="column">
    <div class="card">
    <img  src="./Image/cart.jpg" alt="nothing">
	<form action="./Shipment.jsp" method="post">
		<input class="button" type="submit" value="Shipment Details"/>
	</form>
	</div>
	</div>
	</div>
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