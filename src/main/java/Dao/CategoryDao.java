package Dao;

import java.util.List;

import Model.Category;


public interface CategoryDao {

	final String INSERT_CATEGORY_SQL = "INSERT INTO `library_ms`.`category` (`category_name`, `status`) VALUES (?,?)";
	final String UPDATE_CATEGORY_SQL = "UPDATE `category` SET `category_name`=?,`status`=?  WHERE `categoryid`=?";
	final String GET_CATEGORYBYID_SQL = "SELECT * FROM `category` WHERE `categoryid` = ?";
	final String GET_CATEGORYBYNAME_SQL = "SELECT * FROM `category` WHERE `category_name` = ?";
	final String SHOW_CATEGORY_SQL = "SELECT * FROM `category`";
	final String DELETE_CATEGORY_SQL = "DELETE FROM `category` WHERE categoryid = ?";
	
	void addCategory(Category category);
	void updateCategory(Category category);
	Category getCategoryById(int id);
	Category getCategoryByName(String name);
	List<Category> getAllCategory();
	void deleteCategory(int id);
}
