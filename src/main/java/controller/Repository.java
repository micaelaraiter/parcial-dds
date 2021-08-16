package controller;

import domain.Homework;
import domain.Student;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private ConnectionToDB connectionToDB;

    public Repository() {
        this.connectionToDB = new ConnectionToDB();
    }

    public static  void registerUser(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO User (firstName,lastName,email,password) values (?, ?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1,user.getName());
        stm.setString(2,user.getLastName());
        stm.setString(3,user.getEmail());
        stm.setString(4,user.getPassword());
        stm.executeUpdate();
        connection.close();
    }

    public static User selectUserByEmail(String email) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from User where email = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, email);
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            User userMapper = new User();
            userMapper.setId(resultSet.getInt("user_id"));
            userMapper.setEmail(resultSet.getString("email"));
            userMapper.setName(resultSet.getString("firstName"));
            System.out.println("respuesta de la db" + userMapper.getEmail());
            return userMapper;
        }

        connection.close();
        return null;
    }

    public static void registerStudent(int userID) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO Student (user_id) values (?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1,userID);
        stm.executeUpdate();
        connection.close();
    }

    public static Student selectStudentByUser(User user) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Student where user_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, (Integer) user.getId());
        ResultSet resultSet = stm.executeQuery();
        if(resultSet.next()){
            Student studentMapper = new Student();
            studentMapper.setId(resultSet.getInt("student_id"));
            studentMapper.setUser(user);
            return studentMapper;
        }

        connection.close();
        return null;
    }

    public static  void selectHomeworkByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select Homework.homework_id, Homework.title,Homework.description, Homework.date_dued,Homework.orden,Homework.state_id,Homework.tp_id from HomeworkStudent  INNER JOIN Homework ON HomeworkStudent.homework_id = Homework.homework_id where HomeworkStudent.student_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, studentId);
        ResultSet resultSet = stm.executeQuery();
        List<Homework> homeworkList= new ArrayList<Homework>();
        while (resultSet.next()){
            Homework homework = new Homework();
            homework.setId(resultSet.getInt("homework_id"));
            homework.setTitle(resultSet.getString("title"));
            System.out.println("respuesta de la db");

        }

        connection.close();

    }

    public static List<Homework> getHomeworksByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select t.* from Tarea t join AlumnoTarea aT on t.tarea_id = a.tarea_id where a.alumno_id = $(studentId)";
        Statement statement = connection.createStatement();
        // TODO: crear lo que subio el tipo las clases "DAO", los mappers entre las "DAO" y las de dominio, etc
        //boolean rows = statement.execute(sql);
        return null;
    }
}
