package repository;

import domain.*;
import service.HomeworkService;
import service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeworkDAO {
    public static List<Homework> selectHomeworkByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select Homework.homework_id, Homework.title as HTitle, Homework.description as HDescription, Homework.dued_date , Homework.`order`, State.description as state, " +
                "Homework.tp_id, Tp.title as TTitle, Tp.description as TDescription " +
                "from HomeworkStudent  " +
                "INNER JOIN Homework ON HomeworkStudent.homework_id = Homework.homework_id " +
                "INNER JOIN Tp on Homework.tp_id = Tp.tp_id " +
                "INNER JOIN State on State.state_id = HomeworkStudent.state_id " +
                "where HomeworkStudent.student_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, studentId);
        ResultSet resultSet = stm.executeQuery();
        List<Homework> homeworkList = new ArrayList<Homework>();
        while (resultSet.next()) {
            SimpleHomework homework = new SimpleHomework(resultSet.getString("HTitle"), resultSet.getString("HDescription"), resultSet.getInt("homework_id"));
            homework.setState(HomeworkService.getHomeworkStateByDescription(resultSet.getString("state")));
            homework.setDuedDate(resultSet.getDate("dued_date"));
            homework.setOrder(resultSet.getInt("order"));
            Tp tp = new Tp(resultSet.getString("TTitle"), resultSet.getString("TDescription"), resultSet.getInt("tp_id"), new ArrayList<>());
            tp.addHomework(homework);

            homeworkList.add(homework);
        }

        connection.close();
        return homeworkList;
    }

    public static SimpleHomework selectHomeworkByTitle(String title) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select Homework.homework_id, Homework.description as HDescription, Homework.dued_date, Homework.`order`, State.description as state, " +
                "Homework.tp_id, Tp.title as TTitle, Tp.description as TDescription " +
                "from HomeworkStudent  " +
                "INNER JOIN Homework ON HomeworkStudent.homework_id = Homework.homework_id " +
                "INNER JOIN Tp on Homework.tp_id = Tp.tp_id " +
                "INNER JOIN State on State.state_id = HomeworkStudent.state_id " +
                "where Homework.title = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, title);
        ResultSet resultSet = stm.executeQuery();

        while (resultSet.next()) {
            SimpleHomework homework = new SimpleHomework(title, resultSet.getString("HDescription"), resultSet.getInt("homework_id"));
            homework.setState(HomeworkService.getHomeworkStateByDescription(resultSet.getString("state")));
            homework.setDuedDate(resultSet.getDate("dued_date"));
            homework.setOrder(resultSet.getInt("order"));
            Tp tp = new Tp(resultSet.getString("TTitle"), resultSet.getString("TDescription"), resultSet.getInt("tp_id"), new ArrayList<>());
            tp.addHomework(homework);

            System.out.println("TAREA: " + homework.getId().toString() + " \n TITULO: " + homework.getTitle() +
                    "\n FECHA: " + homework.getDuedDate().toString() + "\n ORDEN: " + homework.getOrder() + "\n ESTADO: " + homework.getState().getDescription());

            System.out.println("FORMA PARTE DEL TP: " + tp.getId());
            return homework;
        }

        connection.close();
        return null;
    }

    public static void updateHomeworkState(String state, Integer homeworkId, Integer studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        PreparedStatement stm;
        String sql;

        sql = "UPDATE HomeworkStudent" +
                "set state_id = (select state_id from State where description = ?)" +
                "where homework_id = ? and student_id = ?";

        stm = connection.prepareStatement(sql);
        stm.setString(1, state);
        stm.setInt(2, homeworkId);
        stm.setInt(3, studentId);

        stm.executeUpdate();

        System.out.println("Tarea " + homeworkId.toString() + " cambio de estado a " + state);

        connection.close();
    }
}
