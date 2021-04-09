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

@WebServlet(name = "updateQuestion", urlPatterns = { "/updateQuestion" })
public class Update extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Dao dao;

	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.readAllQuestion();
		} else {
			System.out.println("jotain muuta");
		}
		//response.getWriter().println(dao.readAllQuestion());
		request.setAttribute("questionlist", list);
		//response.sendRedirect("/jsp/showquestion2foredit.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestion2foredit.jsp");
		rd.forward(request, response);
	}

	/***
	 * public void doGet(HttpServletRequest request, HttpServletResponse response)
	 * throws IOException {
	 * response.sendRedirect("\"/jsp/showquestiontoedit.jsp\""); }
	 ***/
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
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestion2foredit.jsp");
		rd.forward(request, response);
	}
}