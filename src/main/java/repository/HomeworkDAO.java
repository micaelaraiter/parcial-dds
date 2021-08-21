package repository;

import domain.Homework;
import domain.HomeworkStateEnum;
import domain.SimpleHomework;
import domain.Tp;
import service.HomeworkService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeworkDAO {
    public static void selectHomeworkByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select Homework.homework_id, Homework.title as HTitle,Homework.description as HDescription, Homework.dued_date ,Homework.`order`,State.description as state, " +
                "Homework.tp_id, Tp.title as TTitle, Tp.description as TDescription, " +
                "from HomeworkStudent  " +
                "INNER JOIN Homework ON HomeworkStudent.homework_id = Homework.homework_id " +
                "INNER JOIN Tp on Homework.tp_id = Tp.tp_id " +
                "INNER JOIN State on State.state_id = HomeworkStudent.state_id" +
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
            // opcion por las dudas: ...select DATE_FORMAT(Homework.dued_date, '%d-%m-%y') from Homework...
            //SimpleHomework simpleHomework = new SimpleHomework();
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //java.util.Date birthday = formatter.parse(resultSet.getString("dued_date"));

            System.out.println("TAREA: " + homework.getId().toString() + " \n TITULO: " + homework.getTitle() +
                    "\n FECHA: " + homework.getDuedDate().toString() + "\n ORDEN: " + homework.getOrder() + "\n ESTADO: " + homework.getState().toString());

            System.out.println("FORMA PARTE DEL TP: " + tp.getId());
        }

        connection.close();

    }
}
