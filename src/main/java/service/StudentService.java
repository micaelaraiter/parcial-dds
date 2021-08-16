package service;

import controller.Repository;
import domain.Student;
import domain.User;

import java.sql.SQLException;

public class StudentService {

    public static void getAllStudentHomework(int studentId) throws SQLException {
        Repository.selectHomeworkByStudentId(studentId);
    }

    public static Student getStudent(User user) throws SQLException {
        return Repository.selectStudentByUser(user);
    }
}
