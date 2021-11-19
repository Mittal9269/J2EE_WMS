

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
 * Servlet implementation class AddCusTransDetailServlet
 */
@WebServlet("/AddCusTransDetailServlet")
public class AddCusTransDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCusTransDetailServlet() {
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
 		PreparedStatement checkIdstart = con .prepareStatement("SELECT Quanty_avl FROM Product WHERE ProductID = ?;");
 		checkIdstart.setInt(1, Integer.parseInt(ProductID));
 		ResultSet rets =  checkIdstart.executeQuery();
 		rets.next();
 		if(rets.getInt("Quanty_avl") <= Quantity) {
 			System.out.println("Quantity don't exist ");
 			RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
				rd.forward(request, response);
 		}else {
 			PreparedStatement checkId = con .prepareStatement("SELECT COUNT(*) FROM cus_trans_detail WHERE TransactionID = ? and ProductID=?;");
 	 		checkId.setInt(1 , Integer.parseInt(id));
 	 		checkId.setInt(2 , Integer.parseInt(ProductID));
 	 		
 	 		ResultSet ret =  checkId.executeQuery();
 	 		ret.next();
 	 		if(ret.getInt("COUNT(*)") != 0) {
 	 			System.out.println("Already Exists Transaction ");
 	 			RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
 					rd.forward(request, response);
 	 		}
 	 		else {
 	 			PreparedStatement st = con .prepareStatement("insert into cus_trans_detail values(?, ?, ? , ? ,? ,?);");
 	 	 		st.setInt(1,Integer.parseInt(id));
 	 			st.setInt(2,Integer.parseInt(ProductID));
 	 			st.setInt(3,Quantity);
 	 			st.setInt(4,Discount);
 	 			st.setInt(5,Total_amount);
 	 			st.setDate(6 ,  Trans_init_date);
 	 			int result=st.executeUpdate();

 	 			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
 	 			if(result>0)
 	 			{
 	 				PreparedStatement stt = con .prepareStatement("update Product SET Quanty_avl = Quanty_avl - ?  where ProductID=?");
 	 				stt.setInt(1,Quantity);
 	 	 			stt.setInt(2,Integer.parseInt(ProductID));
 	 	 			int res=stt.executeUpdate();
 	 	 			if(res > 0) {
 	 	 				PreparedStatement checkIdt = con .prepareStatement("SELECT Quanty_avl,ReorderIndex FROM Product where ProductID=?;");
 	 	 	 	 		checkIdt.setInt(1 , Integer.parseInt(ProductID));
 	 	 	 	 		ResultSet rettemp =  checkIdt.executeQuery();
 	 	 	 	 		
 	 	 	 	 		rettemp.next();
 	 	 	 	 		if(rettemp.getInt("Quanty_avl") < rettemp.getInt("ReorderIndex")) {
 	 	 	 	 			int Qun = rettemp.getInt("Quanty_avl");
	 	 	 	 	 		PreparedStatement checkIdt2 = con .prepareStatement("insert into Depleted_product values(?, ?);");
	 	 	 	 	 		checkIdt2.setInt(1 , Integer.parseInt(ProductID));
	 	 	 	 	 		checkIdt2.setInt(2 , Qun);
	 	 	 	 	 		ResultSet rettemp2 =  checkIdt.executeQuery();
	 	 	 	 	 		
	 	 	 	 	 		rettemp2.next();
 	 	 	 	 		}
 	 	 				System.out.println("change in cus_trans_detail and product");
 	 	 				RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
 	 	 				rd.forward(request, response);
 	 	 			}else {
 	 	 				System.out.println(" change in cus_trans_detail but not in  product");
 	 	 				RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
 	 	 				rd.forward(request, response);
 	 	 			}
 	 				
 	 			}else {
 	 				System.out.println("nothing change in cus_trans_detail and product");
 	 				RequestDispatcher rd = request.getRequestDispatcher("CumstomerShipServlet");
 		 				rd.forward(request, response);
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
