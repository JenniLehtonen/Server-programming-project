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
	    name = "ReadParty",
	    urlPatterns = {"/readparty"})
	    
/**
 * 
 * @author Sanna Nieminen-Vuorio
 * 
 * This class is used for reading candidates based on their party. 
 * Party is given to the class as a parameter.
 *
 */
public class ReadParty extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Candidates> list=null;
		String party = request.getParameter("party");
		if (dao.getConnection()) {
			list=dao.readByParty(party);
		}
		else {
			System.out.println("No connection to database");
		}
	
		request.setAttribute("candidateslist", list);

		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showcandidatesbyparty.jsp");
		rd.forward(request, response); 
		
	}

}
