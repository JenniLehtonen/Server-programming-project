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
/**
 * 
 * @author Jenni
 * This class is for adding new questions to the database. 
 * After adding the question to the database, a "success" page will be shown to the user.
 */
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
		
		/**
		 * Get the new question from addNewQuestions.jsp
		 */
		String kysymys = request.getParameter("kysymys");

		Question q = new Question(kysymys);

		/**
		 * Send the new question to dao where addQuestion method will add it to the database
		 */
		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.addQuestion(q);
		} else {
			System.out.println("No connection to database");
		}
		
		/**
		 * Show success-page after adding the new question
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editOkQuestions.jsp");
		dispatcher.forward(request, response);

	}
}

