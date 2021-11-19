

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet("/SupplierServlet")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/wms_system"; //MySQL URL and followed by the database name
 		String username = "WMS"; //MySQL username
 		String password = "Abcd@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT * from Supplier_info");
 		
 		List<List<String>> Supplier_info = new ArrayList<List<String>>();
 		ResultSet ret =  checkId.executeQuery();
 		while(ret.next()) {
 			List<String> temp = new ArrayList<String>();
 			temp.add(Integer.toString(ret.getInt("SupplierID")));
 			temp.add(ret.getString("Sname"));
 			temp.add(ret.getString("Address"));
 			temp.add(ret.getString("Phone"));
 			Supplier_info.add(temp);
 		}
 		request.setAttribute("Suppler_info", Supplier_info);
 		RequestDispatcher rd = request.getRequestDispatcher("Supplier.jsp");
		rd.forward(request, response);
		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}

}
