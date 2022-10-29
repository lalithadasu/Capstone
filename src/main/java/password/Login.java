package password;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/loginform")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname=request.getParameter("Username");
		String pwd= request.getParameter("Password");
		
		RequestDispatcher dispatcher=null;
		
		try {
			Connection c;
            Statement s;
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL","root","Lalitha@1");
            s = c.createStatement();
            
            String x="select * homeAuthDetails where username= " + uname + "and password=" + pwd + ";";
            
            ResultSet rs = s.executeQuery(x);
            
            if(rs.next())
            {
            	request.setAttribute("status", "success");
            	dispatcher= request.getRequestDispatcher("Login.jsp");
            }
            
            else
            {
            	request.setAttribute("status", "failed");
            	dispatcher= request.getRequestDispatcher("Login.jsp");
            }
            
            dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
