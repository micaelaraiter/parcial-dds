package repository;

import domain.Course;
import domain.Student;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public static Integer insertCourse(Course course, Integer schoolId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO Course (name, code, school_id) values (?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, course.getName());
        stm.setString(2, course.getCode());
        stm.setInt(3, schoolId);
        stm.executeUpdate();

        // obtener último id generado
        ResultSet generatedKeys = stm.getGeneratedKeys();
        Integer id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
            System.out.println("curso " + id.toString() + " creado con exito");
        }
        else {
            id = 0;
            System.out.println("error al crear curso");
        }
        connection.close();
        return id;
    }

    public static Course selectCourseByCode(String code) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select c.course_id, c.name from Course c " +
                "where c.code = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, code);
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            Course course = new Course(resultSet.getString("name"), code);
            course.setId(resultSet.getInt("course_id"));
            return course;
        }

        connection.close();
        return null;
    }
}
