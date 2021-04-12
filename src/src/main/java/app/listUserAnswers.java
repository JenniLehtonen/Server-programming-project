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
import data.*;

@WebServlet(name = "listuseranswers", urlPatterns = { "/listanswersofuser" })
public class listUserAnswers extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	
	public listUserAnswers () {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		String answers=request.getParameter("answers");
		ArrayList<CandidatesAndAnswers> list=null;
		 
			
		
		request.setAttribute("useranswerlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/compareUserAnswerToCandidateAnswer.jsp");
		rd.forward(request, response);
	}
	
	
}		    

