package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Dao.AdminDao;
import DaoImpl.AdminDaoImpl;
import Model.Admin;

/**
 * Servlet implementation class LoginAdminServlet
 */
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminDao admindao = new AdminDaoImpl();   
    List<Admin> datalist = new ArrayList<>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		datalist = admindao.getAllAdmin();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = null;
		String lastname = null;
		boolean flag = true;
		
		for(Admin a : datalist) {
			if(!username.equals(a.getUsername()) && !password.equals(a.getPassword())) {
				flag = false;
			}
			firstname = a.getFirstname();
			lastname = a.getLastname();
		}
		
		if(flag) {
			HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("firstname", firstname);
            session.setAttribute("lastname", lastname);;
            
			response.sendRedirect("dashboard.jsp");
		}else {
			response.sendRedirect("adminlogin.jsp");
			request.setAttribute("flag", flag);
		}
			
		

//		pw.write(username + " " + password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
