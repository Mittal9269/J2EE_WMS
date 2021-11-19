

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
 * Servlet implementation class AddTransDetailServlet
 */
@WebServlet("/AddTransDetailServlet")
public class AddTransDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTransDetailServlet() {
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
		String ProductID = request.getParameter("pro_id");
		int Quantity = Integer.parseInt(request.getParameter("Quantity"));
		int Discount = Integer.parseInt(request.getParameter("Discount"));
		int Total_amount = Integer.parseInt(request.getParameter("Total_amount"));
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Trans_init_date"));
		java.sql.Date Trans_init_date = new java.sql.Date(date.getTime());
		java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Trans_receive_date"));
		java.sql.Date Trans_receive_date = new java.sql.Date(date2.getTime());


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/wms_system"; //MySQL URL and followed by the database name
 		String username = "WMS"; //MySQL username
 		String password = "Abcd@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
 		PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM sup_trans_detail WHERE TransactionID = ? and ProductID=?;");
 		checkId.setInt(1 , Integer.parseInt(id));
 		checkId.setInt(2 , Integer.parseInt(ProductID));
 		
 		ResultSet ret =  checkId.executeQuery();
 		ret.next();
 		if(ret.getInt("COUNT(*)") != 0) {
 			System.out.println("Already Exists Transaction ");
 			RequestDispatcher rd = request.getRequestDispatcher("SupplierShipServlet");
				rd.forward(request, response);
 		}
 		else {
 			PreparedStatement st = con .prepareStatement("insert into sup_trans_detail values(?, ?, ? , ? ,? ,? ,?);");
 	 		st.setInt(1,Integer.parseInt(id));
 			st.setInt(2,Integer.parseInt(ProductID));
 			st.setInt(3,Quantity);
 			st.setInt(4,Discount);
 			st.setInt(5,Total_amount);
 			st.setDate(6 ,  Trans_init_date);
 			st.setDate(7 ,  Trans_receive_date);
 			int result=st.executeUpdate();

 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 			if(result>0)
 			{
 				PreparedStatement stt = con .prepareStatement("update Product SET Quanty_avl = Quanty_avl + ? where ProductID=?");
 				stt.setInt(1,Quantity);
 	 			stt.setInt(2,Integer.parseInt(ProductID));
 	 			int result1 = stt.executeUpdate();
 	 			if(result1 > 0) {
 	 				PreparedStatement st2 = con .prepareStatement("delete from Depleted_product where ProductID=?");
 	 	 			st2.setInt(1,Integer.parseInt(ProductID));
 	 	 			int res1 = st2.executeUpdate();
 	 				System.out.println("all changes done in Product and sup_trans_detail");
 	 				RequestDispatcher rd = request.getRequestDispatcher("SupplierShipServlet");
 	 				rd.forward(request, response);
 	 			}
 	 			else {
 	 				System.out.println("no not done in sup_payment  done in sup_trans_info");
 	 				RequestDispatcher rd = request.getRequestDispatcher("SupplierShipServlet");
 	 				rd.forward(request, response);
 	 			}
 			}else {
 				System.out.println("no not done in sup_payment  done in sup_trans_info");
 				RequestDispatcher rd = request.getRequestDispatcher("SupplierShipServlet");
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
