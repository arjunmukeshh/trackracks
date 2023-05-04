import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewExercise extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = (String)request.getSession().getAttribute("username");
        String workoutID = (String)request.getParameter("workoutID");


        try{
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("select * from exercise where workoutID = ?");
            ps.setString(1, workoutID);
            
            ResultSet rs = ps.executeQuery();
            out.println("<head><link rel='stylesheet' href='css/mycss/viewexercisestyle.css'></head>");
            out.println("<table id=viewWorkout border=1>");
            out.println("<tr><th>Name</th><th>Sets</th><th>Reps</th><th>Weight</th><th>Tiredness Factor</th></tr>");
            while(rs.next()) {
                String outCode = "";
                outCode += "<tr>";
                outCode += "<td>"+ rs.getString(3)+"</td>";
                outCode += "<td>"+ rs.getString(4)+"</td>";
                outCode += "<td>"+ rs.getString(5)+"</td>";
                outCode += "<td>"+ rs.getString(6)+"</td>";
                outCode += "<td>"+ rs.getString(7)+"</td>";
                outCode += "</tr>";
                out.println(outCode);
            }
            out.println("</table>");
            String outCode = "";
            outCode += "<table><tr>";
            outCode += "<td><form action=ViewWorkout method=get>";
            outCode += "<input type=submit value='Return to workout'>";
            outCode += "</form></td>";
            outCode += "<td><form action=dashboard.jsp method=post>";
            outCode += "<input type=submit value='Dashboard'>";
            outCode += "</form></td>";
            outCode += "</tr></table>";
            out.println(outCode);
        } catch(Exception se) {
            out.println(se.getMessage());
        }
    }
}