package app;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidates;
import data.CandidatesAndAnswers;

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
	 * @param list1 
	 * @param list 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


    	@Override
    	public void doGet(HttpServletRequest request, HttpServletResponse response) 
    	     throws IOException, ServletException {
    		String answers_string=request.getParameter("answers");
    		ArrayList<Integer> useranswers = new ArrayList<>();
    		    		
    		for (int i=0; i< answers_string.length(); i++) {
    			  int digit = Integer.valueOf(answers_string.charAt(i));
    			  useranswers.add(digit);
    		}
    		
    		ArrayList<CandidatesAndAnswers> list=null;
    		if (dao.getConnection()) {
    			list=dao.readAllAnswers();
    			System.out.println(list);
    		}
    		else {
    			System.out.println("No connection to database");
    		}
    		request.setCharacterEncoding("utf-8");
    		request.setAttribute("candidatesAndAnswersList", list);
    		
    		
    		request.setAttribute("useranswers", useranswers);
    		RequestDispatcher rd=request.getRequestDispatcher("/jsp/compareUserAnswersToCandidateAnswers.jsp");
    		rd.forward(request, response);
    	}

}