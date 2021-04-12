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
 * Luokka hakee dao- luokasta metodin, mikä lukee ja listaa kysymykset tietokannasta.
 * lista lähetetään showquestion2foredit.jsp tiedostolle, missä ylläpitäjä voi halutessaan muokata kysymyksiä
 *
 */

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
			System.out.println("Toimii");
		}
		
		request.setAttribute("questionlist", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestion2foredit.jsp");
		rd.forward(request, response);
	}

	
	  
}