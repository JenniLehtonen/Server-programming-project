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
/**
 * 
 * @author Jenni
 * This class is for logging in to admin's page. Here the database's correct username and password are searched from servlet context saved to
 * variables. The database connection is created using those variables.
 * Admin's username and password are searched from the database and sent to Data package's LoginData.java file and they are crypted in a method there.
 * Also the the password provided by the user in the UI is sent to the same method and crypted. The crypted passwords are then compared
 * login.jsp and if the password and the username are correct, log in has succeeded.
 */
public class Login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
	    
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    /**
     * Get the username and the password that the user has provided. The information comes from login.jsp
     */
    String username = request.getParameter("username");
	String password = request.getParameter("password");
    
    /**
     * Get the database url, username and password from the servlet context
     */
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
    
    /**
     * Create database connection using the variables that get the information from servlet context
     */
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
	
	/**
	 * Excecute the sql sentence that was introduced in the line 73
	 */
	try {
		result = statement.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	
	try {
		while (result.next()){
			/**
			 * Go through the results of the SQL sentence and save the admin's username and password to variables
			 */
		    adminUsername = result.getString(1);
		    adminPassword = result.getString(2);
		    /**
		     * Crypt admin's password that came from the database
		     */
		    MD5Password = data.LoginData.crypt(adminPassword);
		    /**
		     * Crypt the password that the user had provided
		     */
		    password = data.LoginData.crypt(password);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch(IllegalArgumentException e) {
		e.printStackTrace();
	}
	/**
	 * Send variables to login.jsp that checks if the username and password that the user has provided are correct or not
	 */
	request.setAttribute("userProvidedUsername", username);
	request.setAttribute("username", adminUsername);
	request.setAttribute("password", password); 
	request.setAttribute("MD5Password", MD5Password);
	request.getRequestDispatcher("/jsp/login.jsp").forward(request, response); 
  }
}