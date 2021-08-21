package repository;

import domain.Student;
import domain.Teacher;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {

    public static void registerTeacher(int userID, int courseID) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO Teacher (user_id, course_id) values (?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, userID);
        stm.setInt(2, courseID);
        stm.executeUpdate();
        connection.close();
    }

    public static Teacher selectTeacherByUser(User user) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Teacher where user_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, (Integer) user.getId());
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            Teacher teacher = new Teacher();
            //teacher.setId(resultSet.getInt("teacher_id"));
            teacher.setUser(user);
            return teacher;
        }

        connection.close();
        return null;
    }
}
