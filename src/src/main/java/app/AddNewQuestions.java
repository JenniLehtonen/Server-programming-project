package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.Dao;
import data.Candidates;
import data.Question;

@WebServlet(name = "addNewQuestions", urlPatterns = { "/addNewQuestions" })
public class AddNewQuestions extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewQuestions() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addNewQuestions.jsp");
		dispatcher.forward(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String kysymys = request.getParameter("kysymys");

		Question q = new Question(kysymys);

		dao.addQuestion(q);

		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.readAllQuestion();
		} else {
			System.out.println("No connection to database");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editOkQuestions.jsp");
		dispatcher.forward(request, response);

	}
}

