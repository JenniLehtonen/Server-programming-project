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
import data.Question;

/**
 * Servlet implementation class ShowQuestion
 */
@WebServlet("/SaveAnswers")
public class SaveAnswers extends HttpServlet {
	
	public SaveAnswers() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String q1 = request.getParameter("Q1");
	    String q2 = request.getParameter("Q2");
	   
		}
	    // more processing code...
		
	
}

