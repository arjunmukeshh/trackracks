import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewDiet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = (String)request.getSession().getAttribute("username");
        // out.println(username);
        String isAdmin = (String)request.getSession().getAttribute("isAdmin");
        if(isAdmin != null && isAdmin.equals("1")) {
            response.sendRedirect("ViewDietAdmin");
        } 
        try{
        
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
    
            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(),LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());
    
            PreparedStatement ps=con.prepareStatement("select * from diet where uname = ?");
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            out.println("<head><link rel='stylesheet' href='css/mycss/viewdietstyle.css'></head>");
            out.println("<table id=viewDiet border=1>");
            out.println("<tr><th>Diet date</th><th>Description</th></tr>");
            while(rs.next()) {
                String outCode = "";
                outCode += "<tr>";
                outCode += "<td>"+ rs.getString(3) +"</td>";
                outCode += "<td>"+ rs.getString(4) +"</td>";
                outCode += "<form action=ViewFood method=post>";
                outCode += "<td><input type=submit value='View Food'></td>";
                outCode += "<input name=dietID value="+rs.getString(1)+" type=hidden>";
                outCode += "</form>";
                outCode += "<form action=DeleteDiet method=post>";
                outCode += "<td><input type=submit value='Delete Diet'></td>";
                outCode += "<input name=dietID value="+rs.getString(1)+" type=hidden>";
                outCode += "</form>";
                outCode += "</tr>";
                out.println(outCode);
            }
            out.println("</table>");
            String outCode = "<table><tr>";
            outCode += "<td><form action=dashboard.jsp method=post>";
            outCode += "<input type=submit value='Dashboard'>";
            outCode += "</form></td>";
            outCode += "<td><form action=modifyDailyCalories.jsp method=post>";
            outCode += "<input type=submit value='Change Daily Calories Goal'>";
            outCode += "</form></td></tr></table>";
            out.println(outCode);
        } catch(Exception se) {
            out.println(se.getMessage());
        }
        
    }
}