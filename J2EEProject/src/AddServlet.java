

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
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
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String author = request.getParameter("author");


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/library"; //MySQL URL and followed by the database name
 		String username = "universityDB0026"; //MySQL username
 		String password = "Dbms@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM book WHERE book_id = ?;");
 		checkId.setString(1 , id);
 		
 		ResultSet ret =  checkId.executeQuery();
 		ret.next();
 		if(ret.getInt("COUNT(*)") != 0) {
 			System.out.println("Already Exists Book Id.");
 			RequestDispatcher rd = request.getRequestDispatcher("Add.jsp");
				rd.forward(request, response);
 		}
 		else {
 			PreparedStatement st = con .prepareStatement("insert into book values(?, ?,?,?)");
 	 		st.setString(1,id);
 			st.setString(2,title);
 			st.setString(3,category);
 			st.setString(4,author);
 			int result=st.executeUpdate();

 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 			if(result>0)
 			{
 				
 				RequestDispatcher rd = request.getRequestDispatcher("AddResult.jsp");
 				rd.forward(request, response);
 			}
 		}
 		
		

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}

}
