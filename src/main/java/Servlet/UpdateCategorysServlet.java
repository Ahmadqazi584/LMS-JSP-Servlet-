package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Dao.CategoryDao;
import DaoImpl.CategoryDaoImpl;
import Model.Category;

/**
 * Servlet implementation class UpdateCategorysServlet
 */
public class UpdateCategorysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  List<Category> datalist = new ArrayList<>();
	  CategoryDao categorydao = new CategoryDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategorysServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		datalist = categorydao.getAllCategory();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String action = request.getParameter("action");
		if ("DELETE".equals(action)) {
			int categoryId = Integer.parseInt(request.getParameter("category_id"));
		    categorydao.deleteCategory(categoryId);  
		    response.sendRedirect("category.jsp");
		}else {
			
		String category = request.getParameter("updatecategory");
		String status = request.getParameter("updatestatus");
		int categoryid = Integer.parseInt(request.getParameter("categoryid"));
		
		Category c = new Category();
		c.setCategoryname(category);
		c.setId(categoryid);
		
		if (status.equals("Active")) {
            c.setStatus(true);
            categorydao.updateCategory(c);
	        response.sendRedirect("category.jsp");
        } else if (status.equals("In Active")) {
            c.setStatus(false);
            categorydao.updateCategory(c);
	        response.sendRedirect("category.jsp");
        }else {
	        response.sendRedirect("category.jsp");
        }
		}
		
		
//		pw.write( " " + category + " " + status);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
