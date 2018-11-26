package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import models.validators.ListsValidator;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


	    String _token = (String)request.getParameter("_token");
	    if(_token != null && _token.equals(request.getSession().getId())) {
	        EntityManager em = DBUtil.createEntityManager();

	        Tasks m = em.find(Tasks.class, (Integer)(request.getSession().getAttribute("task_id")));

	        String title = request.getParameter("title");
	        m.setTitle(title);

	        String content = request.getParameter("content");
	        m.setContent(content);

	        Integer done = Integer.parseInt(request.getParameter("done"));
	        m.setDone(done);

/*修正前
            Date deadline = Date.valueOf("1900-01-01");
            String dl_str = request.getParameter("deadline");
            if(dl_str != null && !dl_str.equals("")) {
                 deadline = Date.valueOf(request.getParameter("deadline"));
            }
            m.setDeadline(deadline);


	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        m.setUpdated_at(currentTime);
*/
	        Date deadline;
            try {
                deadline = Date.valueOf(request.getParameter("deadline"));
            } catch(IllegalArgumentException e) {
                deadline = null;
            }
            m.setDeadline(deadline);



	        List<String> errors = ListsValidator.validate(m);
            if(errors.size() > 0) {
                em.close();
/*修正前
                deadline = new Date(System.currentTimeMillis());
                m.setDeadline(deadline);
*/
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "変更が完了しました。");
                em.close();

                request.getSession().removeAttribute("task_id");

                response.sendRedirect(request.getContextPath() + "/index");

            }
	    }



	}

}
