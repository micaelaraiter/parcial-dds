package repository;

import domain.SimpleHomework;
import domain.Student;
import domain.Tp;
import service.StudentService;

import java.sql.*;
import java.util.List;

public class SimpleHomeworkDAO {
    public static SimpleHomework selectHomeworkById(int id) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select title, description, tp_id, dued_date, `order` from Homework where homework_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            SimpleHomework homework = new SimpleHomework(resultSet.getString("title"), resultSet.getString("description"), resultSet.getInt("tp_id"));
            homework.setDuedDate(resultSet.getDate("dued_date"));
            homework.setOrder(resultSet.getInt("order"));
            homework.setId(id);
            //homeworkMapper.setState(HomeworkStateEnum.fromInteger(resultSet.getInt("state_id"))); --> homework no tiene state_id, es homeworkStudent
            return homework;
        }

        connection.close();
        return null;
    }

    public static int createHomework(SimpleHomework simpleHomework, Tp tp, String courseCode) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        PreparedStatement stm;
        String sql;

        if (tp == null) {
            sql = "INSERT INTO Homework (title, description, dued_date) values (?,?,?)";
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, simpleHomework.getTitle());
            stm.setString(2, simpleHomework.getDescription());
            stm.setDate(3, new java.sql.Date(simpleHomework.getDuedDate().getTime()));
        } else {
            sql = "INSERT INTO Homework (title, description,dued_date,tp_id,`order`) values (?,?,?,?,?)";
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, simpleHomework.getTitle());
            stm.setString(2, simpleHomework.getDescription());
            java.sql.Date duedDate = new java.sql.Date(simpleHomework.getDuedDate().getTime());
            stm.setDate(3, duedDate);
            stm.setInt(4, tp.getId());
            stm.setInt(5, simpleHomework.getOrder());
        }
        stm.executeUpdate();

        // obtener último id generado
        ResultSet generatedKeys = stm.getGeneratedKeys();
        Integer id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
            System.out.println("tarea " + id.toString() + " creada con exito");
        }
        else {
            id = 0;
            System.out.println("error al crear tarea");
        }

        List<Student> students = StudentService.getAllStudentsFromCourse(courseCode);
        String query = "INSERT INTO HomeworkStudent (student_id, homework_id, state_id, grade) values (?,?,?,?)";
        Integer finalId = id;
        students.forEach(student -> {
            try {
                student.addHomework(simpleHomework);
                PreparedStatement stm2 = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stm2.setInt(1, student.getId().intValue());
                stm2.setInt(2, finalId);
                stm2.setInt(3, 1);
                stm2.setInt(4, 0);
                stm2.executeUpdate();
                System.out.println("tarea " + finalId.toString() + " asignada al estudiante " + student.getId().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        connection.close();
        return id;
    }
}
