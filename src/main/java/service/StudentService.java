package service;

import domain.Homework;
import domain.SimpleHomework;
import repository.HomeworkDAO;
import domain.Student;
import domain.User;
import repository.StudentDAO;
import repository.UsersDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentService {

    public static List<Homework> getAllStudentHomework(int studentId) throws SQLException {
        return HomeworkDAO.selectHomeworkByStudentId(studentId);
    }

    public static Student getStudent(User user) throws SQLException {
        return StudentDAO.selectStudentByUser(user);
    }

    public static Student getStudentByEmail(String email) throws SQLException {
        return StudentDAO.selectStudentByEmail(email);
    }

    public static List<Student> getAllStudentsFromCourse(String courseCode) throws SQLException {
        return UsersDao.getAllStudentsFromCourse(courseCode);
    }

    public static void handHomework(Student student, String courseCode) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese el titulo de la tarea a entregar: ");
        String titleee = console.nextLine();
        SimpleHomework homework = HomeworkService.getSimpleHomeworkByTitle(titleee);
        student.handHomework(homework, courseCode);
    }

}
