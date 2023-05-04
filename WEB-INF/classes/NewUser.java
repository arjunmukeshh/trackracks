import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class NewUser extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("name");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String age = request.getParameter("age");
        String weight = request.getParameter("weight");
        String height = request.getParameter("height");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String dailyCalories = request.getParameter("dailyCalories");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());

        PreparedStatement ps=con.prepareStatement
                  ("insert into user(name,age,weight,height,gender,uname,upwd,email, dailyCalories) values(?,?,?,?,?,?,?,?,?)");

        ps.setString(1, name);
        ps.setString(2, age);
        ps.setString(3, weight);
        ps.setString(4, height);
        ps.setString(5, gender);
        ps.setString(6, uname);
        ps.setString(7, upwd);
        ps.setString(8,email);
        ps.setString(9,dailyCalories);
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            request.getSession().setAttribute("username",uname);
            response.sendRedirect("./");
            out.println("You are sucessfully registered");
          }
        
        }
        catch(Exception se)
        {
            out.println(se.getMessage());
        }
	
      }
    }
