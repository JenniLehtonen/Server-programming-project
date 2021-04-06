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
import data.Answers;
import data.Candidates;

@WebServlet("/compareUserAnswersToCandidateAnswers")
public class compareUserAnswersToCandidateAnswers extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	 Dao dao = null;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public compareUserAnswersToCandidateAnswers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Answers> list=null;
		if (dao.getConnection()) {
			list=dao.readAllAnswers();
		}
		else {
			System.out.println("No connection to database");
		}
		
		ArrayList<Candidates> list1=null;
		if (dao.getConnection()) {
			list1=dao.readAllCandidates();
		}
		else {
			System.out.println("No connection to database");
		}
	
		request.setAttribute("answerslist", list);
		request.setAttribute("candidateslist", list1);

		RequestDispatcher rd=request.getRequestDispatcher("/jsp/compareUserAnswersToCandidateAnswers.jsp");
		rd.forward(request, response); 
		
	}	

}