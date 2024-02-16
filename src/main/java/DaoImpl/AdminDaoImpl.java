package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBconnection.dbconnection;
import Dao.AdminDao;
import Model.Admin;

public class AdminDaoImpl implements AdminDao {

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_ADMIN_SQL);
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getFirstname());
			ps.setString(4, admin.getLastname());
			ps.setString(5, admin.getEmail());
			ps.setString(6, admin.getPhone());
			ps.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_ADMIN_SQL);
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getFirstname());
			ps.setString(4, admin.getLastname());
			ps.setString(5, admin.getEmail());
			ps.setString(6, admin.getPhone());
			ps.setInt(7, admin.getId());
			ps.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Admin getAdminById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(GETADMINBYID_SQL);
			ps.setInt(1, id);
			ResultSet rstadmin = ps.executeQuery();
			while(rstadmin.next()) {
				Admin a = new Admin();
				a.setId(rstadmin.getInt("adminid"));
				a.setUsername(rstadmin.getString("username"));
				a.setPassword(rstadmin.getString("password"));
				a.setFirstname(rstadmin.getString("first_name"));
				a.setLastname(rstadmin.getString("last_name"));
				a.setEmail(rstadmin.getString("email"));
				a.setPhone(rstadmin.getString("phone"));
				
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
	public List<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
		List<Admin> datalist = new ArrayList<>();
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(SHOW_ADMIN_SQL);
			ResultSet rstadmin = ps.executeQuery();
			while(rstadmin.next()) {
				Admin a = new Admin();
				a.setId(rstadmin.getInt("adminid"));
				a.setUsername(rstadmin.getString("username"));
				a.setPassword(rstadmin.getString("password"));
				a.setFirstname(rstadmin.getString("firstname"));
				a.setLastname(rstadmin.getString("lastname"));
				a.setEmail(rstadmin.getString("email"));
				a.setPhone(rstadmin.getString("phone"));
				datalist.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return datalist;
	}
	
//	public static void main(String [] args) {
//		AdminDao admindao = new AdminDaoImpl();
//		List<Admin> datalist = admindao.getAllAdmin();
//		for(Admin a : datalist) {
//			System.out.print(a.getFirstname());
//			System.out.print(a.getEmail());
//		}
//	}

	@Override
	public void deleteAdmin(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbconnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_ADMIN_SQL);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
