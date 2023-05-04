import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String calories = "";
        String isAdmin = "";
        try{
        
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection  con=DriverManager.getConnection(LoginInfo.USER1.getConnectionIP(), LoginInfo.USER1.getUsername(), LoginInfo.USER1.getPassword());

            PreparedStatement ps=con.prepareStatement("select * from user where uname = '"+username+"' and upwd = '"+password+"'");
           //  ps.setString(1, username);
           // ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                request.getSession().setAttribute("loginSuccess", 0);
                response.sendRedirect("./");
                out.println("<html>Login Failed.</html>");
            }

            else{
                isAdmin = rs.getString("isAdmin");
                calories = rs.getString("dailyCalories");
                request.getSession().setAttribute("username",username);
                request.getSession().setAttribute("isAdmin",isAdmin);
                request.getSession().setAttribute("dailyCalories", calories);
                request.getSession().setAttribute("loginSuccess", "1");
                response.sendRedirect("dashboard.jsp");
                out.println("<html> <h1> Welcome </h1> </html>");
            }
            
            
        }
        catch(SQLException se) {
            out.println(se.getMessage());
        } catch (ClassNotFoundException ce) {
            out.println(ce.getMessage());
        } catch (Exception ce) {
            out.println(ce.getMessage());
        }
	
    }
}
