

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueServlet
 */
@WebServlet("/IssueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
		//getting input values from jsp page 
		String id = request.getParameter("id");
		String st_id = request.getParameter("st_id");
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("issue_time"));
		java.sql.Date issueDate = new java.sql.Date(date.getTime()) ;
		String rt_tm = request.getParameter("return_time");
		java.util.Date date2 = null;
		java.sql.Date ReturnDate = null;
		if(rt_tm.length() > 0) {
			 date2 = new SimpleDateFormat("yyyy-MM-dd").parse(rt_tm);
			 ReturnDate = new java.sql.Date(date2.getTime()) ;
		}
		
		//java.sql.Date ReturnDate = new java.sql.Date(date.getTime()) ;


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/library"; //MySQL URL and followed by the database name
 		String username = "universityDB0026"; //MySQL username
 		String password = "Dbms@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM student WHERE student_id = ?;");
 		checkId.setString(1 , st_id);
 		
 		ResultSet ret =  checkId.executeQuery();
 		ret.next();
 		if(ret.getInt("COUNT(*)") != 0) {
 			PreparedStatement st = null;
 			if(rt_tm.length() > 0) {
 				st = con .prepareStatement("insert into issue values(?, ?,?, ?)");
 	 	 		st.setString(1,id);
 	 			st.setString(2,st_id);
 	 			st.setDate(3,issueDate);
 	 			st.setDate(4,ReturnDate);
 			}
 			else {
 				st = con .prepareStatement("insert into issue values(?, ?,?, NULL)");
 	 	 		st.setString(1,id);
 	 			st.setString(2,st_id);
 	 			st.setDate(3,issueDate);
 	 			//st.setDate(4,ReturnDate);
 			}
 			int result=st.executeUpdate();

 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 			if(result>0)
 			{
 				
 				RequestDispatcher rd = request.getRequestDispatcher("IssueResult.jsp");
 				rd.forward(request, response);
 			}
 		}
 		else {
 			
 			System.out.println("student Id don't exist");
 			RequestDispatcher rd = request.getRequestDispatcher("Issue.jsp");
				rd.forward(request, response);
 		}
 		
		

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}

}
