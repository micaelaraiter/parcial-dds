package controller;

import domain.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private ConnectionToDB connectionToDB;

    public Repository() {
        this.connectionToDB = new ConnectionToDB();
    }

    public static void registerUser(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO User (firstName,lastName,email,password) values (?, ?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user.getName());
        stm.setString(2, user.getLastName());
        stm.setString(3, user.getEmail());
        stm.setString(4, user.getPassword());
        stm.executeUpdate();
        connection.close();
    }

    public static User selectUserByEmail(String email) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from User where email = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, email);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
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
        stm.setInt(1, userID);
        stm.executeUpdate();
        connection.close();
    }

    public static Student selectStudentByUser(User user) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Student where user_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, (Integer) user.getId());
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            Student studentMapper = new Student();
            studentMapper.setId(resultSet.getInt("student_id"));
            studentMapper.setUser(user);
            return studentMapper;
        }

        connection.close();
        return null;
    }

    public static void selectHomeworkByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select Homework.homework_id, Homework.title,Homework.description, Homework.dued_date ,Homework.`order`,HomeworkStudent.state_id,Homework.tp_id from HomeworkStudent  INNER JOIN Homework ON HomeworkStudent.homework_id = Homework.homework_id where HomeworkStudent.student_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, studentId);
        ResultSet resultSet = stm.executeQuery();
        List<Homework> homeworkList = new ArrayList<Homework>();
        while (resultSet.next()) {
            SimpleHomework homework = new SimpleHomework();
            homework.setId(resultSet.getInt("homework_id"));
            homework.setTitle(resultSet.getString("title"));
            homework.setDuedDate(resultSet.getDate("dued_date"));
            homework.setOrder(resultSet.getInt("order"));
            homework.setState(HomeworkStateEnum.fromInteger(resultSet.getInt("state_id")));
            homework.setTpId(resultSet.getInt("tp_id")); // TODO: deberiamos tener un objeto TP, no un tp_id en las clases
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

    public static List<Homework> getHomeworksByStudentId(int studentId) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select t.* from Tarea t join AlumnoTarea aT on t.tarea_id = a.tarea_id where a.alumno_id = $(studentId)";
        Statement statement = connection.createStatement();
        // TODO: crear lo que subio el tipo las clases "DAO", los mappers entre las "DAO" y las de dominio, etc
        //boolean rows = statement.execute(sql);
        return null;
    }

    public static void createTp(Tp tp) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO TP (title) values (?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, tp.getTitle());
        stm.executeUpdate();
        connection.close();
    }


    public static void createHomework(SimpleHomework simpleHomework) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        if (simpleHomework.getTpId() == 0) {
            String sql = "INSERT INTO Homework (title,dued_date) values (?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, simpleHomework.getTitle());
            stm.setDate(2, (Date) simpleHomework.getDuedDate());
            stm.executeUpdate();
        } else {
            String sql = "INSERT INTO Homework (title,dued_date,tp_id,`order`) values (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, simpleHomework.getTitle());
            stm.setDate(2, (Date) simpleHomework.getDuedDate());
            stm.setInt(3, simpleHomework.getTpId());
            stm.setInt(4, simpleHomework.getOrder());
            stm.executeUpdate();
        }

        connection.close();
    }

    public static SimpleHomework selectHomeworkById(int id) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Homework where homework_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            SimpleHomework homeworkMapper = new SimpleHomework();
            homeworkMapper.setId(id);
            homeworkMapper.setTitle(resultSet.getString("title"));
            homeworkMapper.setOrder(resultSet.getInt("`order`"));
            homeworkMapper.setTpId(resultSet.getInt("tp_id"));
            //homeworkMapper.setDuedDate(resultSet.getDate("dued_date"));
            homeworkMapper.setGrade(resultSet.getInt("grade"));
            //homeworkMapper.setState(resultSet.getInt("state_id"));
            return homeworkMapper;
        }

        connection.close();
        return null;
    }

}
