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
import data.Candidates;

@WebServlet(
	    name = "Update",
	    urlPatterns = {"/updatecandidate"}
	)

public class UpdateCandidate extends HttpServlet{

	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		String id=request.getParameter("id");
		String breed=request.getParameter("breed");
		
		Candidates c=new Candidates();
		
		ArrayList<Candidates> list=null;
		if (dao.getConnection()) {
			list=dao.updateCandidate(c);
		}
		
		request.setAttribute("fishlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/updateCandidate.jsp");
		rd.forward(request, response);
	}
}
