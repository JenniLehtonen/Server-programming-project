package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import data.CandidatesAndAnswers;
import data.Points;
import data.Candidates;
import data.Question;
import data.*;

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

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * Get the question list
		 */
		ArrayList<Question> questionlist = null;
		if (dao.getConnection()) {
			questionlist = dao.readAllQuestion();
		} else {
			System.out.println("No connection to database");
		}

		/**
		 * Get candidate list
		 */
		ArrayList<Candidates> candidatelist = null;
		if (dao.getConnection()) {
			candidatelist = dao.readAllCandidates();
			for (int i = 0; i < candidatelist.size(); i++) {
				Candidates x = candidatelist.get(i);
			}
		} else {
			System.out.println("No connection to database");
		}

		/**
		 * Construct a list out of user's answers
		 */
		ArrayList<Integer> useranswerlist = new ArrayList<>();
		String answer_string = null;
		int answer = 0;

		for (int i = 0; i < questionlist.size(); i++) {
			answer_string = request.getParameter("" + (i + 1));
			if (answer_string != null) {

				answer = Integer.valueOf(answer_string);
				useranswerlist.add(answer);

				/**
				 * If user didn't answer a question, default to zero
				 */
			} else {
				answer = 0;
				useranswerlist.add(answer);
			}

		}

		

		int difference = 0;
		int differenceSum = 0;
		CandidatesAndAnswers candidateAnswer = null;
		ArrayList<CandidatesAndAnswers> candidatesAnswers = null;

		ArrayList<Points> pointsAndCandidates = new ArrayList<>();

		/**
		 * Read each particular candidate's answers and put them in a list
		 */
		for (int i = 0; i < candidatelist.size(); i++) {
			Points points = new Points();
			differenceSum = 0;
			if (dao.getConnection()) {
				Candidates c = candidatelist.get(i);
				candidatesAnswers = dao.readCandidatesAnswers(c.getEhdokas_id());
			} else {
				System.out.println("No connection to database");
			}

			/**
			 * Check if a candidate has any answers
			 */
			if (candidatesAnswers.size() != 0) {

				/**
				 * If so, go through them and subtract them from the user's answers to get a score
				 */
				for (int j = 0; j < questionlist.size(); j++) {

					/**
					 * Check if the user has answered a particular question. If not, skip.
					 */
					if (useranswerlist.get(j) != 0) {
						candidateAnswer = candidatesAnswers.get(j);
						difference = useranswerlist.get(j) - candidateAnswer.getVastaus();
						differenceSum = differenceSum + Math.abs(difference);

						System.out.println("ID: " + candidatelist.get(i).getEhdokas_id() + ", vastaus: "
								+ candidatesAnswers.get(j).getVastaus());
					}

				}
				points.setCandidate_id(candidatelist.get(i).getEhdokas_id());
				points.setPointAmount(differenceSum);
				points.setCandidateFirstname(candidatelist.get(i).getEtunimi());
				points.setCandidateSurname(candidatelist.get(i).getSukunimi());
				pointsAndCandidates.add(points);
				System.out.println("ID: " + points.getCandidate_id() + ", points: " + points.getPointAmount());


				/**
				 * Lajitellaan ehdokaslista pisteiden perusteella. /Sanna
				 */
				Collections.sort(pointsAndCandidates);


			}

		}

		/**
		 * Sort the list of points so best candidates are on top
		 */
		Collections.sort(pointsAndCandidates);

		/**
		 * Remove everyone beyond the fifth slot
		 */
		pointsAndCandidates.subList(5, pointsAndCandidates.size()).clear();

		request.setAttribute("useranswers", useranswerlist);
		request.setAttribute("pointsAndCandidates", pointsAndCandidates);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bestCandidates.jsp");

		dispatcher.forward(request, response);

	}

}
