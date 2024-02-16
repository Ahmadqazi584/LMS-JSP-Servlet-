package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBconnection.dbconnection;
import Dao.CategoryDao;
import Model.Category;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORY_SQL);
			ps.setString(1, category.getCategoryname());
			ps.setBoolean(2, category.getStatus());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_CATEGORY_SQL);
			ps.setString(1, category.getCategoryname());
			ps.setBoolean(2, category.getStatus());
			ps.setInt(3, category.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_CATEGORYBYID_SQL);
			ps.setInt(1, id);
			ResultSet rstcategory = ps.executeQuery();
			while(rstcategory.next()) {
				Category c = new Category();
				c.setId(rstcategory.getInt("categoryid"));
				c.setCategoryname(rstcategory.getString("category_name"));
				c.setStatus(rstcategory.getBoolean("status"));
				if(c.getId() == id) {
					return c;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_CATEGORYBYNAME_SQL);
			ps.setString(1, name);
			ResultSet rstcategory = ps.executeQuery();
			while(rstcategory.next()) {
				Category c = new Category();
				c.setId(rstcategory.getInt("categoryid"));
				c.setCategoryname(rstcategory.getString("category_name"));
				c.setStatus(rstcategory.getBoolean("status"));
				if(c.getCategoryname() == name) {
					return c;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> datalist = new ArrayList<>();
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(SHOW_CATEGORY_SQL);
			ResultSet rstcategory = ps.executeQuery();
			while(rstcategory.next()) {
				Category c = new Category();
				c.setId(rstcategory.getInt("categoryid"));
				c.setCategoryname(rstcategory.getString("category_name"));
				c.setStatus(rstcategory.getBoolean("status"));
				datalist.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datalist;
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY_SQL);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
