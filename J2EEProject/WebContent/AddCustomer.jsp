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
<title>Insert title here</title>
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
* {
  box-sizing: border-box;
}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>
<body class="page-body login-page login-form-fall">
<form action="./Index.jsp" method="post">
		<button type="submit" class="btn btn-primary btn-block btn-login">
                              Go Home
                            </button>
	</form>
<form action="./Logout.jsp" method="post">
<button style = "margin : 0px 20px;" type="submit" class="btn btn-primary btn-block btn-login">
                              Logout
                            </button>
	</form>
<div class="login-form">

               <div class="login-content">
<form action="AddCustomerServlet" method="post">
	<div class="form-group">
	<div class="input-group">
	<div class="input-group-addon">
                                    <i class="entypo-user"></i>
                                </div>
	 <input type="text" id="id" name="id" placeholder="id">
	</div>
	</div>
	
	<div class="form-group">
	<div class="input-group">
	<div class="input-group-addon">
                                    <i class="entypo-user"></i>
                                </div>
	 <input type="text" id="PName" name="PName" placeholder="PName">
	</div>
	</div>
	<div class="form-group">
	<div class="input-group">
	<div class="input-group-addon">
                                    <i class="entypo-user"></i>
                                </div>
	 <input type="text" id="Address" name="Address" placeholder="Address">
	</div>
	</div>
	<div class="form-group">
	<div class="input-group">
	<div class="input-group-addon">
                                    <i class="entypo-user"></i>
                                </div>
	 <input type="text" id="Phone" name="Phone" placeholder="Phone">
	</div>
	</div>

			
			<div class="form-group">
		<button type="submit" class="btn btn-primary btn-block btn-login">
                                <i class="entypo-login"></i>
                                Submit

                            </button>
	</div>
</form></div>
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