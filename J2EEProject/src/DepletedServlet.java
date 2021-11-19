

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
 * Servlet implementation class DepletedServlet
 */
@WebServlet("/DepletedServlet")
public class DepletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepletedServlet() {
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
 		PreparedStatement checkId = con .prepareStatement("SELECT * from Depleted_product");
 		
 		List<List<String>> Depleted_product = new ArrayList<List<String>>();
 		ResultSet ret =  checkId.executeQuery();
 		while(ret.next()) {
 			List<String> temp = new ArrayList<String>();
 			temp.add(Integer.toString(ret.getInt("ProductID")));
 			temp.add(Integer.toString(ret.getInt("Quantity")));
 			Depleted_product.add(temp);
 		}
 		request.setAttribute("Depleted_product", Depleted_product);
 		RequestDispatcher rd = request.getRequestDispatcher("./Depleted.jsp");
		rd.forward(request, response);
		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}

}
