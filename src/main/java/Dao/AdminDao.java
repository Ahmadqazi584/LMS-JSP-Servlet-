package Dao;

import java.util.List;

import Model.Admin;

public interface AdminDao {
	
	final String INSERT_ADMIN_SQL = "INSERT INTO `library_ms`.`admin` (`username`, `password`, `firstname`, `lastname`, `email`, `phone`) VALUES (?,?,?,?,?,?)";
	final String UPDATE_ADMIN_SQL = "UPDATE `admin` SET `username`=?, `password`=?, `firstname`=?, `lastname`=?, `email`=?, `phone`=? WHERE `adminid` =?";
	final String GETADMINBYID_SQL = "SELECT * FROM `admin` WHERE adminid = ?";
	final String SHOW_ADMIN_SQL = "SELECT * FROM `admin`";
	final String DELETE_ADMIN_SQL = "DELETE FROM `admin` WHERE adminid = ?";
	
	void addAdmin(Admin admin);
	void updateAdmin(Admin admin);
	Admin getAdminById(int id);
	List<Admin> getAllAdmin();
	void deleteAdmin(int id);
	
}
