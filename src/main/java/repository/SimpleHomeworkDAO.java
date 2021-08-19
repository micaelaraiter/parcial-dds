package repository;

import domain.SimpleHomework;

import java.sql.*;

public class SimpleHomeworkDAO {
    public static SimpleHomework selectHomeworkById(int id) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Homework where homework_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            SimpleHomework homeworkMapper = new SimpleHomework(resultSet.getString("title"), resultSet.getDate("dued_date"), resultSet.getInt("order"), resultSet.getInt("tp_id"));
            homeworkMapper.setId(id);
            //homeworkMapper.setState(HomeworkStateEnum.fromInteger(resultSet.getInt("state_id"))); --> homework no tiene state_id, es homeworkStudent
            return homeworkMapper;
        }

        connection.close();
        return null;
    }

    public static int createHomework(SimpleHomework simpleHomework) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        PreparedStatement stm;
        String sql;

        if (simpleHomework.getTpId() == 0) {
            sql = "INSERT INTO Homework (title,dued_date) values (?,?)";
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, simpleHomework.getTitle());
            stm.setDate(2, new java.sql.Date(simpleHomework.getDuedDate().getTime()));
        } else {
            sql = "INSERT INTO Homework (title,dued_date,tp_id,`order`) values (?,?,?,?)";
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, simpleHomework.getTitle());
            stm.setDate(2, (Date) simpleHomework.getDuedDate());
            stm.setInt(3, simpleHomework.getTpId());
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
