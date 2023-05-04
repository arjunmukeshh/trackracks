import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteDiet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = (String)request.getSession().getAttribute("username");
        String dietID = (String)request.getParameter("dietID");


        try{
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("delete from diet where dietID=?");
            //ps.setString(1, username);
            ps.setString(1, dietID);
            
            Boolean isSuccess = ps.execute();
            response.sendRedirect("ViewDiet");
        } catch(Exception se) {
            out.println(se.getMessage());
        }
    }
}