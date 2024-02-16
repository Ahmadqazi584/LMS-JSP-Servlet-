package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DBconnection.dbconnection;
import Dao.StudentDao;
import Model.Students;

public class StudentDaoImpl implements StudentDao{

	@Override
	public void addStudent(Students student) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_STUDENT_SQL);
			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPhone());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateStudent(Students student) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT_SQL);
			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPhone());
			ps.setInt(5, student.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Students getStudentById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_STUDENTBYID_SQL);
			ps.setInt(1, id);
			ResultSet rststudent = ps.executeQuery();
			while(rststudent.next()) {
				Students s = new Students();
				s.setId(rststudent.getInt("studenid"));
				s.setFirstname(rststudent.getString("first_name"));
				s.setLastname(rststudent.getString("last_name"));
				s.setEmail(rststudent.getString("email"));
				s.setPassword(rststudent.getString("password"));
				s.setPhone(rststudent.getString("phone"));
				
				if(s.getId() == id) {
					return s;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Students> getAllStudents() {
		// TODO Auto-generated method stub
		List<Students> datalist = new ArrayList<>();
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(SHOW_STUDENT_SQL);
			ResultSet rststudent = ps.executeQuery();
			while(rststudent.next()) {
				Students s = new Students();
				s.setId(rststudent.getInt("studenid"));
				s.setFirstname(rststudent.getString("first_name"));
				s.setLastname(rststudent.getString("last_name"));
				s.setEmail(rststudent.getString("email"));
				s.setPassword(rststudent.getString("password"));
				s.setPhone(rststudent.getString("phone"));
				
				datalist.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datalist;
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_STUDENT_SQL);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
