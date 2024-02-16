package AuthorServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.AuthorDao;
import DaoImpl.AuthorDaoImpl;
import Model.Author;
import Model.Category;

/**
 * Servlet implementation class UpdateAuthor
 */
public class UpdateAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Author> datalist = new ArrayList<>();
	AuthorDao authordao = new AuthorDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		datalist = authordao.getAllAuthor();
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String action = request.getParameter("action");
        if("DELETE".equals(action)){
        	
            int authorid = Integer.parseInt(request.getParameter("category_id").toString());                
            authordao.deleteAuthor(authorid);
            response.sendRedirect("author.jsp");
            
        }else {
        	
        	int authorid = Integer.parseInt(request.getParameter("authorid"));
            String authorname = request.getParameter("updateauthorname");
            String authorstatus = request.getParameter("updateauthorstatus");
            Timestamp updatedtime = new Timestamp(new Date().getTime());
            Timestamp addedtime = null;

            for (Author author : datalist) {
                if (authorid == author.getId()) {
                    addedtime = author.getAdded();
                }
            }

            Author a = new Author();
            a.setAuthorname(authorname);
            a.setId(authorid);
            a.setAdded(addedtime);
            a.setUpdated(updatedtime);
            a.setStatus(true);  
    		
    		if (authorstatus.equals("Active")) {
                a.setStatus(true);
                authordao.updateAuthor(a);
    	        response.sendRedirect("author.jsp");
            } else if (authorstatus.equals("In Active")) {
                a.setStatus(false);
                authordao.updateAuthor(a);
    	        response.sendRedirect("author.jsp");
            }else {
    	        response.sendRedirect("author.jsp");
            }
    		
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
