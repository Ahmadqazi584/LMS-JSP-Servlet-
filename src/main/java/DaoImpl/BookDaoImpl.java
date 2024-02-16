package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBconnection.dbconnection;
import Dao.BookDao;
import Model.Author;
import Model.Books;
import Model.Category;

public class BookDaoImpl implements BookDao{

	@Override
	public void addBook(Books book) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_SQL);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getISBN());
			ps.setInt(3, book.getPublishedyear());
			ps.setInt(4, book.getAuthor().getId());
			ps.setInt(5, book.getCategory().getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateBook(Books book) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_SQL);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getISBN());
			ps.setInt(3, book.getPublishedyear());
			ps.setInt(4, book.getAuthor().getId());
			ps.setInt(5, book.getCategory().getId());
			ps.setInt(6, book.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
		List<Books> datalist = new ArrayList<>();
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(SHOW_BOOK_SQL);
			ResultSet rstbook = ps.executeQuery();
			while(rstbook.next()) {
				Books b = new Books();
				b.setId(rstbook.getInt("bookid"));
				b.setTitle(rstbook.getString("title"));
				b.setISBN(rstbook.getInt("ISBN"));
				b.setPublishedyear(rstbook.getInt("publishedyear"));
				
				Author a = new Author();
				a.setId(rstbook.getInt("authorid"));
				b.setAuthor(a);
				
				Category c = new Category();
				c.setId(rstbook.getInt("categoryid"));
				b.setCategory(c);
				
				datalist.add(b);
			}
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datalist;
	}

	@Override
	public Books getBookById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_BOOKBYID_SQL);
			ps.setInt(1, id);
			ResultSet rstbook = ps.executeQuery();
			while(rstbook.next()) {
				Books b = new Books();
				b.setId(rstbook.getInt("bookid"));
				b.setTitle(rstbook.getString("title"));
				b.setISBN(rstbook.getInt("ISBN"));
				b.setPublishedyear(rstbook.getInt("publishedyear"));
				
				Author a = new Author();
				a.setId(rstbook.getInt("authorid"));
				b.setAuthor(a);
				
				Category c = new Category();
				c.setId(rstbook.getInt("categoryid"));
				b.setCategory(c);
				
				if(b.getId() == id) {
					return b;
				}
			}
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_SQL);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
