package Servlet;

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
 * Servlet implementation class AddCategoryServlet
 */
public class AddCategoryyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Category> datalist = new ArrayList<>();
	CategoryDao categorydao = new CategoryDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryyServlet() {
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
		String category = request.getParameter("addcategory");
		String status = request.getParameter("addstatus");

    	boolean categoryExists = false;
	    for (Category c : datalist) {
	        if (category.equals(c.getCategoryname())) {
	            categoryExists = true;
	            break;
	        }
	    }
	    
	    if (!categoryExists) {
	        Category newCategory = new Category();
	        newCategory.setCategoryname(category);
	        if (status.equals("Active")) {
	            newCategory.setStatus(true);
	            categorydao.addCategory(newCategory);
		        response.sendRedirect("category.jsp");
	        } else if (status.equals("In Active")) {
	            newCategory.setStatus(false);
	            categorydao.addCategory(newCategory);
		        response.sendRedirect("category.jsp");
	        }else {
		        response.sendRedirect("category.jsp");
	        }
	        
	    } else {
	        // Redirect to the same page if the category already exists
	        response.sendRedirect("category.jsp?error=CategoryAlreadyExists");
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
