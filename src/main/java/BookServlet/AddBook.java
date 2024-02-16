package BookServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Dao.*;
import DaoImpl.*;
import Model.*;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook") // Add a proper URL mapping
public class AddBook extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Books> datalist = new ArrayList<>();
    private BookDao bookdao = new BookDaoImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String bookname = request.getParameter("addbookname");
        String category = request.getParameter("addbookcategory");
        String author = request.getParameter("addbookauthor");
        int isbn = Integer.parseInt(request.getParameter("addISBN"));
        int publishedyear = Integer.parseInt(request.getParameter("addpublishedyear"));

        boolean bookExists = false;
        for (Books b : datalist) {
            if (isbn == b.getISBN()) {
                bookExists = true;
                break;
            }
        }

        pw.write("hello world");

        if (!bookExists) {
            Books book = new Books();

            Category c = new Category();
            c.setCategoryname(category);

            Author a = new Author();
            a.setAuthorname(author);

            book.setTitle(bookname);
            book.setCategory(c);
            book.setAuthor(a);
            book.setISBN(isbn);
            book.setPublishedyear(publishedyear);

            datalist.add(book); // Add the book to the list
            bookdao.addBook(book);
            response.sendRedirect("book.jsp");
        } else {
            pw.write("Value already exists!");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
