package service;

import repository.HomeworkDAO;
import domain.Student;
import domain.User;
import repository.StudentDAO;
import repository.UsersDao;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    public static void getAllStudentHomework(int studentId) throws SQLException {
        HomeworkDAO.selectHomeworkByStudentId(studentId);
    }

    public static Student getStudent(User user) throws SQLException {
        return StudentDAO.selectStudentByUser(user);
    }

    public static List<Student> getAllStudentsFromCourse(String courseCode) throws SQLException {
        return UsersDao.getAllStudentsFromCourse(courseCode);
    }
}
