package Dao;

import java.util.List;

import Model.IssuedBook;

public interface IssuedBookDao {
	
	final String INSERT_IB_SQL = "INSERT INTO `library_ms`.`issuedbookdetails` (`bookid`, `studentid`, `issuedate`, `duedate`, `returndate`) VALUES (?,?,?,?,?)";
	final String UPDATE_IB_SQL = "UPDATE `issuedbookdetails` SET `bookid`=?,`studentid`=?,`issuedate`=?,`duedate`=?,`returndate`=? WHERE `issuedid`=?";
	final String SHOW_IB_SQL = "SELECT * FROM `issuedbookdetails`";
	final String GET_IBBYID_SQL = "SELECT * FROM `issuedbookdetails` WHERE `issuedid` = ?";
	final String DELETE_IB_SQL = "DELETE FROM `issuedbookdetails` WHERE issuedid = ?";
	
	void addIssuedBook(IssuedBook issuedbook);
	void updateIssuedBook(IssuedBook issuedbook);
	List<IssuedBook> getAllIssuedBook();
	IssuedBook getIssuedBookById(int id);
	void deleteIssuedBook(int id);
	
}
