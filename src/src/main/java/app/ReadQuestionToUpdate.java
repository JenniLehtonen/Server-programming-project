package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;
/***
 * 
 *  @Liisa
 *  
 *  The class reads the questions for editing from the showquestionforedit2.jsp file and sends them to the showquestiontoedit.jsp file.
 *   where the selected question can be edited
 * 
 */
/**
 * Servlet implementation class ReadToUpdate
 */
@WebServlet("/readquestiontoupdate")
public class ReadQuestionToUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadQuestionToUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		Question f=null;
		if (dao.getConnection()) {
			f=dao.readQuestion(id);
		}
		
		request.setAttribute("question", f);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showquestiontoedit.jsp");
		rd.forward(request, response);
	}
}