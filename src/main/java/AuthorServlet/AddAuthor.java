package AuthorServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;

import Dao.AuthorDao;
import DaoImpl.AuthorDaoImpl;
import Model.Author;
import Model.Category;

/**
 * Servlet implementation class AddAuthor
 */
public class AddAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Author> datalist = new ArrayList<>();
	AuthorDao authordao = new AuthorDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAuthor() {
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

		String author = request.getParameter("addauthorname");
		String status = request.getParameter("addauthorstatus");
		Timestamp addedtime = new Timestamp(new Date().getTime());
		Timestamp updatedtime = new Timestamp(new Date().getTime());
				
		boolean authorExists = false;
	    for (Author c : datalist) {
	        if (author.equals(c.getAuthorname())) {
	        	authorExists = true;
	            break;
	        }
	    }
	    
	    if(!authorExists) {
	    	Author addauthor = new Author();
		    
		    if(status.equals("Active")) {
			    addauthor.setAuthorname(author);
			    addauthor.setStatus(true);
			    addauthor.setAdded(addedtime);
			    addauthor.setUpdated(updatedtime);
			    authordao.addAuthor(addauthor);
			    response.sendRedirect("author.jsp");
			    
		    }else if(status.equals("In Active")) {
			    addauthor.setAuthorname(author);
		    	addauthor.setStatus(false);
			    addauthor.setAdded(addedtime);
			    addauthor.setUpdated(updatedtime);
			    authordao.addAuthor(addauthor);
			    response.sendRedirect("author.jsp");
		    }else {
		    	response.sendRedirect("author.jsp");
		    }
	    }else {
	    	pw.write("sorry the value already exists!");
	    }
	    	    
//	    if (!authorExists) {
//	    	Author addauthor = new Author();
//	    	addauthor.setAuthorname(author);
//            addauthor.setAdded(addedtime);
//            addauthor.setUpdated(updatedtime);
//	    	if (status.equals("Active")) {
//	        	addauthor.setStatus(true);
//	        	
//	            addauthor.getAuthorname();
//	            addauthor.getAdded();
//	            addauthor.getUpdated();
//	            addauthor.getStatus();
//	            authordao.addAuthor(addauthor);
//		        response.sendRedirect("author.jsp");
//	        } else if (status.equals("In Active")) {
//	        	addauthor.setStatus(false);
//	        	
//	        	addauthor.getAuthorname();
//	        	addauthor.getAdded();
//	        	addauthor.getUpdated();
//	        	addauthor.getStatus();
//	        	
//	            authordao.addAuthor(addauthor);
//		        response.sendRedirect("author.jsp");
//	        }else {
//		        response.sendRedirect("author.jsp");
//	        }
//	        
//	    } else {
//	        // Redirect to the same page if the category already exists
//	        response.sendRedirect("author.jsp?error=authorAlreadyExists");
//	    }
//	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
