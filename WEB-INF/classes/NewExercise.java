import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class NewExercise extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String workoutID = request.getParameter("workoutID");
        String name = request.getParameter("name");
        String sets = request.getParameter("sets");
        String reps = request.getParameter("reps");
        String weight = request.getParameter("weight");
        String tirednessfactor = request.getParameter("tirednessfactor");

        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());

        PreparedStatement ps=con.prepareStatement("insert into exercise(workoutID,name,sets,reps,weight,tirednessfactor) values(?,?,?,?,?,?)");

        ps.setString(1, workoutID);
        ps.setString(2, name);
        ps.setString(3, sets);
        ps.setString(4, reps);
        ps.setString(5, weight);
        ps.setString(6, tirednessfactor);
        
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            response.sendRedirect("addExercise.jsp");
            out.println("Exercise added.");
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
    }
