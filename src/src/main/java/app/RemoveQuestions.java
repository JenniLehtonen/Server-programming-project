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

@WebServlet(
    name = "RemoveQuestions",
    urlPatterns = {"/removeQuestions"}
)
public class RemoveQuestions extends HttpServlet {
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		ArrayList<Question> list=null;
		if (dao.getConnection()) {
			list=dao.readAllQuestion();
			System.out.println("connected");
			System.out.println(list);
		}
		else {
			System.out.println("No connection to database");
		}
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/removeQuestions.jsp");
		rd.forward(request, response);
	}
}