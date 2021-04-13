package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidates;

@WebServlet("/readcandidate")

/**
 * 
 * @author Sanna Nieminen-Vuorio
 * 
 * This class gets parameter ehdokas_id (candidate's id) and reads one candidate from database based on id.
 * Candidate's information if given for updatecandidate.jsp where admin can update candidate's informations. 
 *
 */
public class ReadCandidate extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       

    public ReadCandidate() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("ehdokas_id");
		Candidates candidate=null;
		if (dao.getConnection()) {
			candidate=dao.readCandidate(id);
		}
		request.setAttribute("candidate", candidate);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/updatecandidate.jsp");
		rd.forward(request, response);
	}

}

