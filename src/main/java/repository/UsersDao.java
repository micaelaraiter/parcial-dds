package repository;

import domain.Course;
import domain.Student;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    public static List<Student> getAllStudentsFromCourse(String courseCode) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select s.student_id, u.firstName, u.lastName, c.name, u.email, u.password from Student s" +
                " join Course c on s.course_id = c.course_id" +
                " join User u on s.user_id = u.user_id" +
                " where c.code = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, courseCode);
        ResultSet resultSet = stm.executeQuery();

        // Recorrer y usar cada línea retornada
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student obj = new Student();
            obj.setId(resultSet.getInt("student_id"));
            Course course = new Course(resultSet.getString("name"), courseCode);
            User user = new User(resultSet.getString("email"), resultSet.getString("password"));
            user.setName(resultSet.getString("firstName"));;
            user.setLastName(resultSet.getString("lastName"));

            obj.setCourse(course);
            obj.setUser(user);

            System.out.println("student " + resultSet.getString("firstName"));
            students.add(obj);
        }
        connection.close();
        return students;
    }


}
