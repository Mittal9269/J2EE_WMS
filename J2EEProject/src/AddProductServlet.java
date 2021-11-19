

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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
		String catid = request.getParameter("catid");
		String PName = request.getParameter("PName");
		int Quantity = Integer.parseInt(request.getParameter("Quantity"));
		int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
		int ReorderIndex = Integer.parseInt(request.getParameter("ReorderIndex"));

		System.out.println(id + " " + catid + " " + PName + " "+ Quantity + " " + unitPrice + " " + ReorderIndex);
		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/wms_system"; //MySQL URL and followed by the database name
 		String username = "WMS"; //MySQL username
 		String password = "Abcd@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM Product WHERE ProductID = ?;");
 		checkId.setInt(1 , Integer.parseInt(id));
 		
 		ResultSet ret =  checkId.executeQuery();
 		ret.next();
 		if(ret.getInt("COUNT(*)") != 0) {
 			System.out.println("Already Exists Product Id.");
 			RequestDispatcher rd = request.getRequestDispatcher("./AddProduct.jsp");
				rd.forward(request, response);
 		}
 		else {
 			//PreparedStatement checknewId = con .prepareStatement("SELECT COUNT(*) FROM Category WHERE categoryID = ?;");
 	 		//checknewId.setInt(1 , Integer.parseInt(catid));
 	 		
 	 		//System.out.println(checknewId);
 	 		//ResultSet retnew =  checkId.executeQuery();
 	 		//retnew.next();
 	 		//System.out.println(retnew);
 	 		
 	 			PreparedStatement st = con .prepareStatement("insert into Product values(?, ?, ? ,?, ? , ?)");
 	 	 		st.setInt(1,Integer.parseInt(id));
 	 	 		st.setInt(3,Integer.parseInt(catid));
 	 			st.setString(2,PName);
 	 			st.setInt(4,Quantity);
 	 			st.setInt(5,unitPrice);
 	 			st.setInt(6,ReorderIndex);
 	 			int result=st.executeUpdate();

 	 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 	 			if(result>0)
 	 			{
 	 				if(ReorderIndex > Quantity) {
 	 					PreparedStatement dep = con .prepareStatement("insert into Depleted_product values(?, ?)");
 	 	 	 	 		dep.setInt(1,Integer.parseInt(id));
 	 	 	 			dep.setInt(2,Quantity);
 	 	 	 			int resulttemp=dep.executeUpdate();
 	 	 	 			if(resulttemp > 0 ) {
 	 	 	 				RequestDispatcher rd = request.getRequestDispatcher("./AddProduct.jsp");
 	 	 	 				rd.forward(request, response);
 	 	 	 			}
 	 	 	 			else {
 	 	 	 				RequestDispatcher rd = request.getRequestDispatcher("./AddProduct.jsp");
	 	 	 				rd.forward(request, response);
 	 	 	 			}
 	 				}
 	 				
 	 			}
 			
 		}
 		
		

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}

}
