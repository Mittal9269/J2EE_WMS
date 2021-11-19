

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TotalProfitServlet
 */
@WebServlet("/TotalProfitServlet")
public class TotalProfitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalProfitServlet() {
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
 		LocalDate today = LocalDate.now();
 		int month = today.getMonthValue();
 		//System.out.println(month);
 		PreparedStatement checkId = con .prepareStatement("select T.total from (select sum(Amount_paid) as total  ,Month(Transaction_date) as month  from sup_payment group by Month(Transaction_date)) as T where T.month = ?;");
 		checkId.setInt(1, month);
 		
 		int TotalFromSupplier = 0;
 		ResultSet ret =  checkId.executeQuery();
 		while(ret.next()) {
 			TotalFromSupplier = ret.getInt("total");
 		}
 		PreparedStatement checkId2 = con .prepareStatement("select T.total from (select sum(Amount_paid) as total  ,Month(Transaction_date) as month  from cus_payment group by Month(Transaction_date)) as T where T.month = ?;");
 		checkId2.setInt(1, month);

 		int TotalFromCustomer = 0;
 		ResultSet ret2 =  checkId2.executeQuery();
 		while(ret2.next()) {
 			TotalFromCustomer = ret2.getInt("total");
 		}
 		PreparedStatement checkId3 = con .prepareStatement("select T.total from (select sum(Total_amount) as total  ,Month(Trans_init_date) as month  from cus_trans_detail group by Month(Trans_init_date)) as T where T.month = ?;");
 		checkId3.setInt(1, month);

 		int TotalFromCustomerInTrans = 0;
 		ResultSet ret3 =  checkId3.executeQuery();
 		while(ret3.next()) {
 			TotalFromCustomerInTrans = ret3.getInt("total");
 		}
 		PreparedStatement checkId4 = con .prepareStatement("select T.total from (select sum(Total_amount) as total  ,Month(Trans_init_date) as month  from sup_trans_detail group by Month(Trans_init_date)) as T where T.month = ?;");
 		checkId4.setInt(1, month);

 		int TotalFromSupplierInTrans = 0;
 		ResultSet ret4 =  checkId4.executeQuery();
 		while(ret4.next()) {
 			TotalFromSupplierInTrans = ret4.getInt("total");
 		}
 		//System.out.println(TotalFromCustomer);
 		//System.out.println(TotalFromSupplier);
 		request.setAttribute("TotalFromSupplier",  TotalFromSupplier);
 		request.setAttribute("TotalFromCustomer", TotalFromCustomer );
 		request.setAttribute("TotalFromCustomerInTrans", TotalFromCustomerInTrans);
 		request.setAttribute("TotalFromSupplierInTrans", TotalFromSupplierInTrans);
 		request.setAttribute("Month", month);
 		
 		RequestDispatcher rd = request.getRequestDispatcher("ResultProfit.jsp");
		rd.forward(request, response);
		}
		 catch (Exception e)
 		{
 			e.printStackTrace();
 		}

	
	}

}
