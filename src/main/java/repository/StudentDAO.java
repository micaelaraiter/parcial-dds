package repository;

import domain.Student;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public static void registerStudent(int userID, int courseID) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO Student (user_id, course_id) values (?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, userID);
        stm.setInt(2, courseID);
        stm.executeUpdate();
        connection.close();
    }

    public static Student selectStudentByUser(User user) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Student where user_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, (Integer) user.getId());
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            Student studentMapper = new Student();
            studentMapper.setId(resultSet.getInt("student_id"));
            studentMapper.setUser(user);
            return studentMapper;
        }

        connection.close();
        return null;
    }

    public static Student selectStudentByEmail(String mail) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select s.*, u.lastName, u.firstName from Student s " +
                "INNER JOIN User u on s.user_id = u.user_id" +
                "where u.email = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, mail);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            Student studentMapper = new Student();
            studentMapper.setId(resultSet.getInt("student_id"));

            User user = new User(mail, "");
            user.setName(resultSet.getString("firstName"));
            user.setName(resultSet.getString("lastName"));

            studentMapper.setUser(user);
            return studentMapper;
        }

        connection.close();
        return null;
    }



}
