import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;


public class ViewWorkoutGraph extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = (String)request.getSession().getAttribute("username");
        String isAdmin = (String)request.getSession().getAttribute("isAdmin");
        if(isAdmin != null && isAdmin.equals("1")) {
            response.sendRedirect("dashboard.jsp");
        }
        String exerciseName = (String)request.getParameter("exerciseName");
        try{
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("SELECT * FROM workout INNER JOIN exercise ON workout.workoutID=exercise.workoutID where uname=? and name=? order by workoutDate;");
            ps.setString(1, username);
            ps.setString(2, exerciseName);
            ResultSet rs = ps.executeQuery();
            out.println("<head><link rel='stylesheet' href='css/mycss/viewworkoutgraphstyle.css'></head>");;
            out.println("<table id=viewWorkout border=1>");
            out.println("<tr><th>Workout date</th><th>Description</th></tr>");
            ArrayList<Double> sets = new ArrayList<>();
            ArrayList<Double> reps = new ArrayList<>();
            ArrayList<Double> weights = new ArrayList<>();
            ArrayList<String> dates = new ArrayList<>();

            while(rs.next()) {
                String outCode = "";
                outCode += "<tr>";
                outCode += "<td>"+ rs.getString("workoutDate") +"</td>";
                dates.add(rs.getString("workoutDate"));
                outCode += "<td>"+ rs.getString("name") +"</td>";
                outCode += "<td>"+ rs.getString("sets") +"</td>";

                sets.add(Double.parseDouble(rs.getString("sets")));
                outCode += "<td>"+ rs.getString("reps") +"</td>";
                
                reps.add(Double.parseDouble(rs.getString("reps")));
                outCode += "<td>"+ rs.getString("weight") +"</td>";
                
                weights.add(Double.parseDouble(rs.getString("weight")));
                outCode += "</tr>";

                out.println(outCode);
            }

            ArrayList<Double> intensities = new ArrayList<>();
            for(int i = 0; i < sets.size(); i++) {
                intensities.add(sets.get(i) * reps.get(i) * weights.get(i));
            }
            out.println("</table>");
            request.getSession().setAttribute("workoutIntensities", intensities.toString());
            request.getSession().setAttribute("workoutDates", dates.toString());
            request.getSession().setAttribute("exerciseName", exerciseName);
            
            response.sendRedirect("workoutGraph.jsp");
        } catch(Exception se) {
            out.println(se.getMessage());
        }

    }
}