package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidates;
import data.Question;

@WebServlet("/updatecandidateanswer")

/**
 * 
 * @author Sanna Nieminen-Vuorio
 *
 */
public class updateCandidateAnswer extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		     throws IOException
		     {
				response.sendRedirect("index.html");
		     }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		
		String id = request.getParameter("ehdokas_id");
		int ehdokas_id = Integer.valueOf(id);
		ArrayList<Question> questionlist = null;
		//ArrayList<Integer> candidatesIds = new ArrayList<Integer>();
		
		if (dao.getConnection()) {
			questionlist = dao.readAllQuestion();
		} else {
			System.out.println("No connection to database");
		}
		
		/*
		ArrayList<Candidates> can = new ArrayList<Candidates>();
		can = dao.readAllCandidates();
		
		for(Candidates c : can)
		{
			String s = "" + c;
			int i = Integer.parseInt(s);
			candidatesIds.add(i);
		} */
			
		ArrayList<Integer> candidateanswerlist = new ArrayList<>();
		String answer_string = null;
		int answer = 0;

		for (int i = 0; i < questionlist.size(); i++) {
			answer_string = request.getParameter("" + (i + 1));
			answer = Integer.valueOf(answer_string);
			candidateanswerlist.add(answer);
		}
		
		dao.updateCandidateAnswer(candidateanswerlist, ehdokas_id);
		//dao.addCandidateAnswers(candidateanswerlist, ehdokas_id);
		/*
		boolean b = candidatesIds.contains(ehdokas_id);
		if(b)
		{
			dao.updateCandidateAnswer(candidateanswerlist, ehdokas_id);
		}
		else
		{
			dao.addCandidateAnswers(candidateanswerlist, ehdokas_id);
		} */

		request.setAttribute("answerlist", candidateanswerlist);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/editOk.jsp");
		rd.forward(request, response);
	}

}
