package service;

import repository.Repository;
import domain.Student;
import domain.User;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    public static void getAllStudentHomework(int studentId) throws SQLException {
        Repository.selectHomeworkByStudentId(studentId);
    }

    public static Student getStudent(User user) throws SQLException {
        return Repository.selectStudentByUser(user);
    }

    public static List<Student> getAllStudentsFromCourse(String courseCode) throws SQLException {
        return Repository.getAllStudentsFromCourse(courseCode);
    }
}
