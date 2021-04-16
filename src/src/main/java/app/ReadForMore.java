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

@WebServlet("/readformore")

/**
 * 
 * @author Sanna Nieminen-Vuorio
 * 
 * This class will be used to read one candidate from database based on candidate's id, which is given as a parameter.
 * Candidate's information will be shown in showCandidateById.jsp. This is just for showing the information.
 *
 */
public class ReadForMore extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       
    public ReadForMore() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("ehdokas_id");
		Candidates candidate=null;
		if (dao.getConnection()) {
			candidate=dao.readCandidate(id);
			System.out.println("Haetaan ehdokasta");
		}
		request.setAttribute("candidate", candidate);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showCandidateById.jsp");
		rd.forward(request, response);
	}

}
