package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBconnection.dbconnection;
import Dao.IssuedBookDao;
import Model.Books;
import Model.IssuedBook;
import Model.Students;

public class IssuedBookDaoImpl implements IssuedBookDao{

	@Override
	public void addIssuedBook(IssuedBook issuedbook) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_IB_SQL);
			ps.setInt(1, issuedbook.getBook().getId());
			ps.setInt(2, issuedbook.getBook().getId());
			ps.setDate(3, issuedbook.getIssuedate());
			ps.setDate(4, issuedbook.getDuedate());
			ps.setDate(5, issuedbook.getReturndate());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateIssuedBook(IssuedBook issuedbook) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_IB_SQL);
			ps.setInt(1, issuedbook.getBook().getId());
			ps.setInt(2, issuedbook.getBook().getId());
			ps.setDate(3, issuedbook.getIssuedate());
			ps.setDate(4, issuedbook.getDuedate());
			ps.setDate(5, issuedbook.getReturndate());
			ps.setInt(6, issuedbook.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<IssuedBook> getAllIssuedBook() {
		// TODO Auto-generated method stub
		List<IssuedBook> datalist = new ArrayList<>();
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(SHOW_IB_SQL);
			ResultSet rstib = ps.executeQuery();
			while(rstib.next()) {
				IssuedBook ib = new IssuedBook();
				ib.setId(rstib.getInt("issuedid"));
				Books b = new Books();
				b.setId(rstib.getInt("bookid"));
				ib.setBook(b);
				Students s = new Students();
				s.setId(rstib.getInt("studentid"));
				ib.setStudent(s);
				ib.setIssuedate(rstib.getDate("issueddate"));
				ib.setDuedate(rstib.getDate("duedate"));
				ib.setReturndate(rstib.getDate("returndate"));
				
				datalist.add(ib);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datalist;
	}

	@Override
	public IssuedBook getIssuedBookById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_IBBYID_SQL);
			ps.setInt(1, id);
			ResultSet rstib = ps.executeQuery();
			while(rstib.next()) {
				IssuedBook ib = new IssuedBook();
				ib.setId(rstib.getInt("issuedid"));
				Books b = new Books();
				b.setId(rstib.getInt("bookid"));
				ib.setBook(b);
				Students s = new Students();
				s.setId(rstib.getInt("studentid"));
				ib.setStudent(s);
				ib.setIssuedate(rstib.getDate("issueddate"));
				ib.setDuedate(rstib.getDate("duedate"));
				ib.setReturndate(rstib.getDate("returndate"));
				
				if(ib.getId() == id) {
					return ib;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteIssuedBook(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_IB_SQL);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
