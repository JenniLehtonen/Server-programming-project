package app;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "login",
    urlPatterns = {"/login"}
)
public class Login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
	    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    //String driver = getServletContext().getInitParameter("driver");
    String dbUrl = getServletContext().getInitParameter("DBUrl");
    String username = getServletContext().getInitParameter("username");
    String password = getServletContext().getInitParameter("password");
    Connection conn = null;
    String adminUsername;
    String adminPassword;
    
    try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    
    try {
	    conn = DriverManager.getConnection(dbUrl, username, password);
	 
	    if (conn != null) {
	        System.out.println("Connected");
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    System.out.println("Could not connect to the database");
	}

    String sql = "SELECT * FROM admin";
	 
	Statement statement = null;
	try {
		statement = conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ResultSet result = null;
	try {
		result = statement.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	try {
		while (result.next()){
		    String name = result.getString(1);
		    String pass = result.getString(2);
		    System.out.println(name + pass);
		    adminUsername = String.format(name); //Tarviiko String.format?
		    adminPassword = String.format(pass);
		    System.out.println("Username:"+ adminUsername + "Password: " + adminPassword);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
    RequestDispatcher rd=request.getRequestDispatcher("/jsp/login.jsp");
	rd.forward(request, response);

  }
}