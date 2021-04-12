package app;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.Question;
import dao.Dao;

@WebServlet("/candidateaddanswer")

/**
 * 
 * @author Sanna
 *
 */
public class CandidateAddAnswer extends HttpServlet {
	
	public CandidateAddAnswer() {
        super();
        // TODO Auto-generated constructor stub
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
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/candidateAnswer.jsp");
		rd.forward(request, response);
	}
}
