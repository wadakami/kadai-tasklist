package controllers;

import java.io.IOException;
//1個目import java.sql.Timestamp;
import java.sql.Date;

//1個目import javax.persistence.EntityManager;

import javax.servlet.RequestDispatcher;//2個目
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
//1個目import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    request.setAttribute("_token", request.getSession().getId());//CSRF：セキュリティ対策
	    request.setAttribute("task", new Tasks());

	    Tasks r = new Tasks();
	    r.setDeadline(new Date(System.currentTimeMillis()));
	    request.setAttribute("deadline", r);


	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
	    rd.forward(request, response);
	    /*1個目
	    EntityManager em = DBUtil.createEntityManager();
		em.getTransaction().begin();

		Tasks m = new Tasks();

		String title = "taro";
		m.setTitle(title);

		String content = "hello";
		m.setContent(content);

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		m.setCreated_at(currentTime);
		m.setUpdated_at(currentTime);

		em.persist(m);
		em.getTransaction().commit();

		response.getWriter().append(Integer.valueOf(m.getId()).toString());

		em.close();
		*/

	}

}
