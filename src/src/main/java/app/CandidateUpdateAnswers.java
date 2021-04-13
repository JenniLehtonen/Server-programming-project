package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;

@WebServlet("/candidateupdateanswer")

/**
 * 
 * @author Sanna Nieminen-Vuorio
 *
 *This class will fetch all the questions from database and redirect them to CandidateUpdateAnswer.jsp,
 * where candidate can answer all the questions and the answers will be updated to database
 */

public class CandidateUpdateAnswers extends HttpServlet{
	
	public CandidateUpdateAnswers() {

    }
	
	private static final long serialVersionUID = 1L;
	private Dao dao=null;

	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		ArrayList<Question> list=null;
		if (dao.getConnection()) {
			list=dao.readAllQuestion();
			System.out.println("connected");
		}
		else {
			System.out.println("No connection to database");
		}

		request.setAttribute("questionlist", list);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/candidateUpdateAnswers.jsp");
		rd.forward(request, response);
	}

}
