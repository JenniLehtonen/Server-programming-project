package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.google.api.services.discovery.Discovery.Apis.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.Dao;
import data.Answers;
import data.Candidates;
import data.Question;

@WebServlet(name = "bestCandidates", urlPatterns = { "/bestCandidates" })
public class bestCandidates extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public bestCandidates() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Question> questionlist = null;
		if (dao.getConnection()) {
			questionlist = dao.readAllQuestion();
		} else {
			System.out.println("No connection to database");
		}

		ArrayList<Candidates> candidatelist = null;
		if (dao.getConnection()) {
			candidatelist = dao.readAllCandidates();
		} else {
			System.out.println("No connection to database");
		}

		ArrayList<Integer> useranswerlist = new ArrayList();
		String answer_string = null;
		int answer = 0;

		for (int i = 0; i < questionlist.size(); i++) {
			answer_string = request.getParameter("" + (i + 1));
			answer = Integer.valueOf(answer_string);
			useranswerlist.add(answer);
		}

		int difference = 0;
		int differenceSum = 0;
		Answers candidateAnswer = null;
		HashMap<Integer, Integer> points = new HashMap<Integer, Integer>();
		for (int i = 0; i < candidatelist.size(); i++) {
			differenceSum = 0;
			ArrayList<Answers> candidatesAnswers = null;
			if (dao.getConnection()) {
				candidatesAnswers = dao.readCandidatesAnswers(i);
			} else {
				System.out.println("No connection to database");
			}

			if (candidatesAnswers.size() != 0) {
				for (int j = 0; j < questionlist.size(); j++) {
					candidateAnswer = candidatesAnswers.get(j);
					difference = useranswerlist.get(j) - candidateAnswer.getVastaus();
					differenceSum = differenceSum + Math.abs(difference);
					points.put(candidateAnswer.getEhdokas_id(), differenceSum);

				}
			}
			
		}

		request.setAttribute("points", points);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bestCandidates.jsp");
		dispatcher.forward(request, response);

	}
}