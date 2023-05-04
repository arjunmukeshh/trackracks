import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ModifyDailyCalories extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String calories = request.getParameter("caloriesGoalInput");
        String username = (String)request.getSession().getAttribute("username");
        
        try{
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("update user set dailyCalories = ? where uname = ?");
            //ps.setString(1, username);
            ps.setString(1, calories);
            ps.setString(2, username);
            Boolean isSuccess = ps.execute();
            request.getSession().setAttribute("dailyCalories", calories);
            response.sendRedirect("ViewDiet");
        } catch(Exception se) {
            out.println(se.getMessage());
        }
    }
}