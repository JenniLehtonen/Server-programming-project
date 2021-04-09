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
public class ReadForMore extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadForMore() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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