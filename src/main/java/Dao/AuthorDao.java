package Dao;

import java.util.List;

import Model.Author;

public interface AuthorDao {

	final String INSERT_AUTHOR_SQL = "INSERT INTO `library_ms`.`author` (`author_name`, `added`, `updated`, `status`) VALUES (?, ?, ?, ?)";
	final String UPDATE_AUTHOR_SQL = "UPDATE `author` SET `author_name`=?, `status`=?, `added`=?, `updated`=? WHERE `authorid`=?";
	final String GET_AUTHORBYID_SQL = "SELECT * FROM `author` WHERE `authorid` = ?";
	final String SHOW_AUTHOR_SQL = "SELECT * FROM `author`";
	final String DELETE_AUTHOR_SQL = "DELETE FROM `author` WHERE authorid = ?";
	
	void addAuthor(Author author);
	void updateAuthor(Author author);
	Author getAuthorById(int id);
	List<Author> getAllAuthor();
	void deleteAuthor(int id);

}
