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

@WebServlet(name = "AddCandidate", urlPatterns = { "/addCandidate" })
public class AddCandidate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCandidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Candidates> list = null;
		if (dao.getConnection()) {
			list = dao.readAllCandidates();
		} else {
			System.out.println("No connection to database");
		}

		request.setAttribute("candidateslist", list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addCandidate.jsp");
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String etunimi = request.getParameter("etunimi");
		String sukunimi = request.getParameter("sukunimi");
		String puolue = request.getParameter("puolue");
		String kotipaikkakunta = request.getParameter("kotipaikkakunta");
		String ika_string = request.getParameter("ika");
		String miksi_eduskuntaan = request.getParameter("miksi_eduskuntaan");
		String mita_asioita_haluat_edistaa = request.getParameter("mita_asioita_haluat_edistaa");
		String ammatti = request.getParameter("ammatti");

		int ika = Integer.parseInt(ika_string);

		Candidates c = new Candidates(etunimi, sukunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan,
				mita_asioita_haluat_edistaa, ammatti);

		dao.addCandidate(c);

		ArrayList<Candidates> list = null;
		if (dao.getConnection()) {
			list = dao.readAllCandidates();
		} else {
			System.out.println("No connection to database");
		}

		request.setAttribute("candidateslist", list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addCandidate.jsp");
		dispatcher.forward(request, response);

	}
}
