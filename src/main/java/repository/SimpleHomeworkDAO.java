package repository;

import domain.SimpleHomework;
import domain.Tp;

import java.sql.*;

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

    public static int createHomework(SimpleHomework simpleHomework, Tp tp) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        PreparedStatement stm;
        String sql;

        if (tp == null) {
            sql = "INSERT INTO Homework (title,dued_date) values (?,?)";
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, simpleHomework.getTitle());
            stm.setDate(2, new java.sql.Date(simpleHomework.getDuedDate().getTime()));
        } else {
            sql = "INSERT INTO Homework (title,dued_date,tp_id,`order`) values (?,?,?,?)";
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, simpleHomework.getTitle());
            stm.setDate(2, (Date) simpleHomework.getDuedDate());
            stm.setInt(3, tp.getId());
            stm.setInt(4, simpleHomework.getOrder());
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
        connection.close();
        return id;
    }
}
