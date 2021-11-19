<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("name");    
    String pwd = request.getParameter("password");
    //System.out.println(userid +" " +  pwd);
    if (userid.equals("WMS") && pwd.equals("Abcd@123")) {
        session.setAttribute("userid", userid);
        //out.println("welcome " + userid);
        //out.println("<a href='logout.jsp'>Log out</a>");
        response.sendRedirect("Index.jsp");
    } else {
        out.println("Invalid password <a href='Login.jsp'>try again</a>");
    }
%>