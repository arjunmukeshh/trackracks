import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class NewWorkout extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String workoutDate = request.getParameter("workoutDate");
        String description = request.getParameter("description");
        String uname = request.getParameter("uname");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());

        PreparedStatement ps=con.prepareStatement("insert into workout(uname,workoutDate,description) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, uname);
        ps.setString(2, workoutDate);
        ps.setString(3, description);
        
        int i=ps.executeUpdate();
        ResultSet res = ps.getGeneratedKeys();
          if(i>0)
          {
            while(res.next()){
              request.getSession().setAttribute("workoutID",res.getString(1));
            }
           response.sendRedirect("addExercise.jsp");
            out.println("Workout added."+workoutDate);
          }
          else{

          }
        
        }
        catch(Exception se)
        {
            out.println(se.getMessage());
        }
	
      }
    }
