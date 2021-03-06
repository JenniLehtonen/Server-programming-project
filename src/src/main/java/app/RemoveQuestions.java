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

@WebServlet(
    name = "RemoveQuestions",
    urlPatterns = {"/removeQuestions"}
)
/**
 * 
 * @author Jenni
 *This class is for removing the questions from the database. The question will be removed by the id that the program gets from the UI when
 *the user clicks button under the question that they want to remove.
 */
public class RemoveQuestions extends HttpServlet {
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		/**
		 * Get the question id from removeQuestions.jsp
		 */
		String id=request.getParameter("id");
		ArrayList<Question> list=null;
		if (dao.getConnection()) {
			/**
			 * send the question id to method that will remove the question from the database
			 */
			list=dao.removeQuestion(id);
			System.out.println("connected");
			System.out.println(list);
		}
		else {
			System.out.println("No connection to database");
		}
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/removeQuestions.jsp");
		rd.forward(request, response);
	}
}