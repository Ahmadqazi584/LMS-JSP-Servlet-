package DaoImpl;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import DBconnection.dbconnection;
import Dao.AuthorDao;
import Model.Author;

public class AuthorDaoImpl implements AuthorDao{

	@Override
	public void addAuthor(Author author) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR_SQL);
			ps.setString(1, author.getAuthorname());
			ps.setTimestamp(2, author.getAdded());
			ps.setTimestamp(3, author.getUpdated());
			ps.setBoolean(4, author.getStatus());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAuthor(Author author) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_AUTHOR_SQL);
			ps.setString(1, author.getAuthorname());
		    ps.setBoolean(2, author.getStatus());
		    ps.setTimestamp(3, author.getAdded());
		    ps.setTimestamp(4, author.getUpdated());
			ps.setInt(5, author.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Author getAuthorById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_AUTHORBYID_SQL);
			ps.setInt(1, id);
			ResultSet rstauthor = ps.executeQuery();
			while(rstauthor.next()) {
				Author a = new Author();
				a.setId(rstauthor.getInt("authorid"));
				a.setAuthorname(rstauthor.getString("author_name"));
				a.setStatus(rstauthor.getBoolean("status"));
				if(a.getId() == id) {
					return a;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Author> getAllAuthor() {
		// TODO Auto-generated method stub
		List<Author> datalist = new ArrayList<>();
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(SHOW_AUTHOR_SQL);
			ResultSet rstauthor = ps.executeQuery();
			while(rstauthor.next()) {
				Author a = new Author();
				a.setId(rstauthor.getInt("authorid"));
				a.setAuthorname(rstauthor.getString("author_name"));
				a.setAdded(rstauthor.getTimestamp("added"));
				a.setUpdated(rstauthor.getTimestamp("updated"));
				a.setStatus(rstauthor.getBoolean("status"));
				datalist.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datalist;
	}

	@Override
	public void deleteAuthor(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_AUTHOR_SQL);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
