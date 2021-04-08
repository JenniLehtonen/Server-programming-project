package app;

import java.io.IOException;
import java.sql.SQLException;
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

	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		     throws IOException
		     {
				response.sendRedirect("index.html");
		     }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		String ehdokas_id=request.getParameter("ehdokas_id");
		String sukunimi=request.getParameter("sukunimi");
		String etunimi=request.getParameter("etunimi");
		String puolue=request.getParameter("puolue");
		String kotipaikkakunta=request.getParameter("kotipaikkakunta");
		String ika=request.getParameter("ika");
		String miksi_eduskuntaan=request.getParameter("miksi_eduskuntaan");
		String mita_asioita_haluat_edistaa=request.getParameter("mita_asioita_haluat_edistaa");
		String ammatti=request.getParameter("ammatti");
		
		Candidates candidate=new Candidates(ehdokas_id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan, mita_asioita_haluat_edistaa, ammatti);
		
		ArrayList<Candidates> list=null;
		if (dao.getConnection()) {
			try {
				list=dao.updateCandidate(candidate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Connections fails");
		}
		
		request.setAttribute("candidateslist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/editOk.jsp");
		rd.forward(request, response);
	}
}
