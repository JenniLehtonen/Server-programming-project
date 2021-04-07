package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidates;

@WebServlet(
	    name = "FindCandidates",
	    urlPatterns = {"/findcandidates"})

public class FindCandidates extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap<Integer, Integer> list=null;
		//String id = request.getParameter("ehdokas_id");
		//int ehdokas_id = Integer.parseInt(id);
		if (dao.getConnection()) {
			list=dao.candidatesAnswers();
		}
		else {
			System.out.println("No connection to database");
		}
	
		//request.setAttribute("candidateslist", list);
		
		response.getWriter().println(list);
		//RequestDispatcher rd=request.getRequestDispatcher("/jsp/showcandidatesbyparty.jsp");
		//rd.forward(request, response); 
		
	}


}