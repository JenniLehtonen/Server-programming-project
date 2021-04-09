package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.api.services.discovery.Discovery.Apis.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.Dao;
import data.CandidatesAndAnswers;
import data.Points;
import data.Candidates;
import data.Question;

@WebServlet(name = "listanswersofuser", urlPatterns = { "/listanswersofuser" })
public class listUserAnswers {

	private static final long serialVersionUID = 1L;
	Dao dao = null;

	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {

	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
	    
	    
	    RequestDispatcher reqdisp = request.getRequestDispatcher("/bestCandidates");
	    try {
	    	reqdisp.forward(request, response);
	    }catch (Exception e) {
	    	
	    }
	  }
	 /*** request.setAttribute("useranswerlist", useranswerlist);
	  
	  RequestDispatcher dispatcher2 = request.getRequestDispatcher("/jsp/compareUserAnswersToCandidateAnswers.jsp");
	  dispatcher.forward(request, response);***/
}

