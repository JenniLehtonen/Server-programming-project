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
/***
 * 
 * @author liisa
 * 
 * The class retrieves a method from the dao class, which reads and lists the questions from the database.
 * the list is sent to the showquestion.jsp file where the user can answer questions
 * 
 *
 */

/**
 * Servlet implementation class ShowQuestion
 */
@WebServlet("/ShowQuestion")
public class ShowQuestion extends HttpServlet {
	
	public ShowQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	
  
	
	private static final long serialVersionUID = 1L;
	private Dao dao=null;
	
	
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
		
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
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showquestion.jsp");
		rd.forward(request, response);
	}
}

