

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
 * Servlet implementation class AddCustomerShipServlet
 */
@WebServlet("/AddCustomerShipServlet")
public class AddCustomerShipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerShipServlet() {
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
		String CustomerID = request.getParameter("cus_id");
		int Amount_paid = Integer.parseInt(request.getParameter("Amount_paid"));
		String Method = request.getParameter("Method");
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Transaction_date"));
		java.sql.Date Transaction_date = new java.sql.Date(date.getTime());


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/wms_system"; //MySQL URL and followed by the database name
 		String username = "WMS"; //MySQL username
 		String password = "Abcd@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM cus_trans_info WHERE TransactionID = ?;");
 		checkId.setInt(1 , Integer.parseInt(id));
 		
 		ResultSet ret =  checkId.executeQuery();
 		ret.next();
 		if(ret.getInt("COUNT(*)") != 0) {
 			System.out.println("Already Exists Transaction Id.");
 			RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
				rd.forward(request, response);
 		}
 		else {
 			PreparedStatement st = con .prepareStatement("insert into cus_trans_info values(?, ?);");
 	 		st.setInt(1,Integer.parseInt(id));
 			st.setInt(2,Integer.parseInt(CustomerID));
 			int result=st.executeUpdate();

 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 			if(result>0)
 			{
 				PreparedStatement sttemp = con .prepareStatement("insert into cus_payment values(?, ? ,?, ?)");
 				sttemp.setInt(1,Integer.parseInt(id));
 	 			sttemp.setString(3,Method);
 	 			sttemp.setInt(2,Amount_paid);
 	 			sttemp.setDate(4 ,  Transaction_date);
 	 			int resulttemp=sttemp.executeUpdate();
 	 			if(resulttemp > 0) {
 	 				System.out.println("all changes done in sup_payment and sup_trans_info");
 	 				RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
 	 				rd.forward(request, response);
 	 			}
 	 			System.out.println("all changes not done in sup_payment but done in sup_trans_info");
 				RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
 				rd.forward(request, response);
 			}else {
 				RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
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
