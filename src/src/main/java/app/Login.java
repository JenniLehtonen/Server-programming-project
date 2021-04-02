package app;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import data.LoginData;
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
    
    String username = request.getParameter("username"); //Get the username from the login.jsp
	String password = request.getParameter("password"); //Get the password from the login.jsp
    
    
    String dbUrl = getServletContext().getInitParameter("DBUrl");
    String dbUsername = getServletContext().getInitParameter("username");
    String dbPassword = getServletContext().getInitParameter("password");
    Connection conn = null;
    ResultSet result = null;
    Statement statement = null;
    String adminUsername = null;
    String adminPassword;
    String MD5Password = null;
    
    try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    
    try {
	    conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	 
	    if (conn != null) {
	        System.out.println("Connected");
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    System.out.println("Could not connect to the database");
	}

    String sql = "SELECT * FROM admin";
	 
	
	try {
		statement = conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		result = statement.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	try {
		while (result.next()){
		    adminUsername = result.getString(1); //get admin's username and password from the database
		    adminPassword = result.getString(2);
		    MD5Password = data.LoginData.crypt(adminPassword); //crypt admin's password
		    password = data.LoginData.crypt(password); //crypt the password that the user has provided
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch(IllegalArgumentException e) {
		e.printStackTrace();
	}
	request.setAttribute("userProvidedUsername", username); //send variables to login.jsp
	request.setAttribute("username", adminUsername);
	request.setAttribute("password", password); 
	request.setAttribute("MD5Password", MD5Password);
	request.getRequestDispatcher("/jsp/login.jsp").forward(request, response); 
  }
}