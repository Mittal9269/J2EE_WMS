

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
 * Servlet implementation class AddSupplierServlet
 */
@WebServlet("/AddSupplierServlet")
public class AddSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSupplierServlet() {
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
		String SName = request.getParameter("SName");
		String Address = request.getParameter("Address");
		String Phone = request.getParameter("Phone");


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/wms_system"; //MySQL URL and followed by the database name
 		String username = "WMS"; //MySQL username
 		String password = "Abcd@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM Supplier_info WHERE SupplierID = ?;");
 		checkId.setInt(1 , Integer.parseInt(id));
 		
 		ResultSet ret =  checkId.executeQuery();
 		ret.next();
 		if(ret.getInt("COUNT(*)") != 0) {
 			System.out.println("Already Exists Supplier Id.");
 			RequestDispatcher rd = request.getRequestDispatcher("SupplierServlet");
				rd.forward(request, response);
 		}
 		else {
 			PreparedStatement st = con .prepareStatement("insert into Supplier_info values(?, ?, ? , ?)");
 	 		st.setInt(1,Integer.parseInt(id));
 			st.setString(2,SName);
 			st.setString(3,Address);
 			st.setString(4,Phone);
 			int result=st.executeUpdate();

 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 			if(result>0)
 			{
 				
 				RequestDispatcher rd = request.getRequestDispatcher("SupplierServlet");
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
