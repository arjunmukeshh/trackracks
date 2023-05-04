import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewFood extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = (String)request.getSession().getAttribute("username");
        String dietID = (String)request.getParameter("dietID");
        String calories = (String)request.getSession().getAttribute("dailyCalories");
        // out.println(username+" "+dietID);
        try{
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("select * from food where dietID = ?");
            ps.setString(1, dietID);
            
            ResultSet rs = ps.executeQuery();
            out.println("<head><link rel='stylesheet' href='css/mycss/viewfoodstyle.css'></head>");
            out.println("<table id=viewFood border=1>");
            out.println("<tr><th>Name</th><th>Calories</th></tr>");
            double total = 0;
            double calorieGoal = Double.parseDouble(calories);
            while(rs.next()) {
                String outCode = "";
                outCode += "<tr>";
                outCode += "<td>"+ rs.getString(3)+"</td>";
                outCode += "<td>"+ rs.getString(4)+"</td>";
                outCode += "</tr>";
                out.println(outCode);
                total += Double.parseDouble(rs.getString(4));
            }
            out.println("</table>");
            String outCode = "";
            outCode += "<p>Total Calories Consumed: "+total+"/"+calorieGoal+"</p>";
            if(total>calorieGoal) {
                outCode += "<p>You have reached your daily calories goal with an excess of " + (total - calorieGoal)+"</p>";
            } else {
                outCode += String.format("<p>You need %f to reach your daily calories goal</p>", (calorieGoal - total));
            }
            outCode += "<table><tr>";
            outCode += "<td><form action=ViewDiet method=get>";
            outCode += "<input type=submit value='Return to Diet'>";
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