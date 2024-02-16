package Dao;

import java.util.List;

import Model.Students;


public interface StudentDao {
	
	final String INSERT_STUDENT_SQL = "INSERT INTO `library_ms`.`students` (`first_name`, `last_name`, `email`, `phone`) VALUES (?,?,?,?)";
	final String UPDATE_STUDENT_SQL = "UPDATE `students` SET `first_name`=?, `last_name`=?, `email`=?, `phone`=? WHERE `studentid`=?";
	final String GET_STUDENTBYID_SQL = "SELECT * FROM `students` WHERE `studentid` = ?";
	final String SHOW_STUDENT_SQL = "SELECT * FROM `students`";
	final String DELETE_STUDENT_SQL = "DELETE FROM `students` WHERE studentid = ?";
	
	void addStudent(Students student);
	void updateStudent(Students student);
	Students getStudentById(int id);
	List<Students> getAllStudents();
	void deleteStudent(int id);
	
}
