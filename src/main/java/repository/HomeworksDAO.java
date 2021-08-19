package repository;

import domain.Homework;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HomeworksDAO {
    public static List<Homework> getHomeworksByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select t.* from Tarea t join AlumnoTarea aT on t.tarea_id = a.tarea_id where a.alumno_id = $(studentId)";
        Statement statement = connection.createStatement();
        // TODO: crear lo que subio el tipo las clases "DAO", los mappers entre las "DAO" y las de dominio, etc
        //boolean rows = statement.execute(sql);
        return null;
    }
}
