import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class NewMeal extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String dietID = request.getParameter("dietID");
        String name = request.getParameter("name");
        String calories = request.getParameter("calories");

        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());

        PreparedStatement ps=con.prepareStatement("insert into food(dietID,name,calories) values(?,?,?)");

        ps.setString(1, dietID);
        ps.setString(2, name);
        ps.setString(3, calories);

        
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            response.sendRedirect("addFood.jsp");
            out.println("Exercise added.");
          }
        
        }
        catch(Exception se)
        {
            out.println(se.getMessage());
        }
	
      }
    }
