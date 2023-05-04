import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewWorkoutAdmin extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = (String)request.getSession().getAttribute("username");
        String isAdmin = (String)request.getSession().getAttribute("isAdmin");
        if(!isAdmin.equals("1")) {
            response.sendRedirect("dashboard.jsp");
        } 
        try{
        
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("select * from workout");
            
            ResultSet rs = ps.executeQuery();
            out.println("<head><link rel='stylesheet' href='css/mycss/viewworkoutstyle.css'></head>");
            out.println("<table id=viewWorkout border=1>");
            out.println("<tr><th>User</th><th>Workout date</th><th>Description</th></tr>");
            while(rs.next()) {
                String outCode = "";
                outCode += "<tr>";
                outCode += "<td>"+ rs.getString(2) +"</td>";
                outCode += "<td>"+ rs.getString(3) +"</td>";
                outCode += "<td>"+ rs.getString(4) +"</td>";
                outCode += "<form action=ViewExercise method=post>";
                outCode += "<td><input type=submit value='View exercise'></td>";
                outCode += "<input name=workoutID value="+rs.getString(1)+" type=hidden>";
                outCode += "</form>";
                outCode += "<form action=DeleteWorkout method=post>";
                outCode += "<td><input type=submit value='Delete workout'></td>";
                outCode += "<input name=workoutID value="+rs.getString(1)+" type=hidden>";
                outCode += "</form>";
                outCode += "</tr>";
                out.println(outCode);
            }
            out.println("</table>");
            String outCode = "";
            outCode += "<form action=dashboard.jsp method=post>";
            outCode += "<input type=submit value='Dashboard'>";
            outCode += "</form>";
            out.println(outCode);
        } catch(Exception se) {
            out.println(se.getMessage());
        }
    }
}