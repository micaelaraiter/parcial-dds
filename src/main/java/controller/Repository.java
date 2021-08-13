package controller;

import domain.Homework;

import java.sql.*;
import java.util.List;

public class Repository {
    private ConnectionToDB connectionToDB;

    public Repository() {
        this.connectionToDB = new ConnectionToDB();
    }

    public void main(String args[]) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Connection connection = this.connectionToDB.connection;
        String sql = "INSERT INTO Usuario (usuario_id,nombre) values (1,'mica')";
        Statement statement = connection.createStatement();
        boolean rows = statement.execute(sql);
    }

    public static boolean selectUserByEmail(String email) throws SQLException {
        Connection connection = this.connectionToDB.connection;
        String sql = "select * from Usuario where mail = $(email)";
        Statement statement = connection.createStatement();
        boolean rows = statement.execute(sql);
        return true;
    }

    public static List<Homework> getHomeworksByStudentId(int studentId) throws SQLException {
        Connection connection = this.connectionToDB.connection;
        String sql = "select t.* from Tarea t join AlumnoTarea aT on t.tarea_id = a.tarea_id where a.alumno_id = $(studentId)";
        Statement statement = connection.createStatement();
        // TODO: crear lo que subio el tipo las clases "DAO", los mappers entre las "DAO" y las de dominio, etc
        //boolean rows = statement.execute(sql);
        return null;
    }
}
