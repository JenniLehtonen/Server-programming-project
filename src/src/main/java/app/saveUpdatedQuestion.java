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
/***
 * 
 * @author liisa
 * 
 * Luokka ottaa vastaan muokatun kysymyksen showquestiontoedit.jsp tiedostosta ja kutsuu dao- luokan metodia, millä muokattu kysymys
 * tallentuu tietokantaan
 *
 */
@WebServlet(name = "saveupdatedquestion", urlPatterns = { "/saveupdatedquestion" })
public class saveUpdatedQuestion extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Dao dao;

	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	
	
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		String whatquestion = request.getParameter("whatquestion");

		Question f = new Question(id, whatquestion);

		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.updateQuestion(f);
		} else {
			System.out.println("No connection to database");
		}
		response.getWriter().println(dao.readAllQuestion());
		request.setAttribute("questionlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/editOKQuestions.jsp");
		rd.forward(request, response);
	}
}