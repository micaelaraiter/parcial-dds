package repository;

import domain.Homework;
import domain.HomeworkStateEnum;
import domain.SimpleHomework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeworkDAO {
    public static void selectHomeworkByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select Homework.homework_id, Homework.title,Homework.description, Homework.dued_date ,Homework.`order`,HomeworkStudent.state_id,Homework.tp_id from HomeworkStudent  INNER JOIN Homework ON HomeworkStudent.homework_id = Homework.homework_id where HomeworkStudent.student_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, studentId);
        ResultSet resultSet = stm.executeQuery();
        List<Homework> homeworkList = new ArrayList<Homework>();
        while (resultSet.next()) {
            SimpleHomework homework = new SimpleHomework(resultSet.getString("title"), resultSet.getDate("dued_date"), resultSet.getInt("order"), resultSet.getInt("tp_id"));
            homework.setId(resultSet.getInt("homework_id"));
            homework.setState(HomeworkStateEnum.fromInteger(resultSet.getInt("state_id")));
            // TODO: deberiamos tener un objeto TP, no un tp_id en las clases
            // opcion por las dudas: ...select DATE_FORMAT(Homework.dued_date, '%d-%m-%y') from Homework...
            //SimpleHomework simpleHomework = new SimpleHomework();
            //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //java.util.Date birthday = formatter.parse(resultSet.getString("dued_date"));

            System.out.println("TAREA: " + homework.getId().toString() + " \n TITULO: " + homework.getTitle() +
                    "\n FECHA: " + homework.getDuedDate().toString() + "\n ORDEN: " + homework.getOrder() + "\n ESTADO: " + homework.getState().toString());

            if (homework.getTpId() != 0) {
                System.out.println("FORMA PARTE DEL TP: " + homework.getTpId());
            } else {
                System.out.println("NO FORMA PARTE DE NINGUN TP");
            }

        }

        connection.close();

    }
}
