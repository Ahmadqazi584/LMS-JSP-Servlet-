package Dao;

import java.util.List;

import Model.Books;

public interface BookDao {

	final String INSERT_BOOK_SQL = "INSERT INTO `library_ms`.`books` (`title`, `ISBN`, `publishedyear`, `authorid`, `categoryid`) VALUES (?,?,?,?,?)";
	final String UPDATE_BOOK_SQL = "UPDATE `books` SET `title`=?, `ISBN`=?, `publishedyear`=?, `authorid`=?, `categoryid`=? WHERE `bookid`=?";
	final String SHOW_BOOK_SQL = "SELECT * FROM `books`";
	final String GET_BOOKBYID_SQL = "SELECT * FROM `books` WHERE `bookid` = ?";
	final String DELETE_BOOK_SQL = "DELETE FROM `books` WHERE bookid = ?";
	
	void addBook(Books book);
	void updateBook(Books book);
	List<Books> getAllBooks();
	Books getBookById(int id);
	void deleteBook(int id);

}
