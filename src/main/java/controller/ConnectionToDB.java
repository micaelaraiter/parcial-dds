package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDB {

    public  Connection initDb() throws SQLException {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/parcial";
            String username = "root";
            String password = "elef";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            return connection;
        } catch (Error error) {
            System.out.println(error);
        }
        return null;
    }

    public void createCourseTable() throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS student (\n" +
                    "course_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "name VARCHAR(255),\n" +
                    "code VARCHAR(255),\n" +
                    "CONSTRAINT FK_Teacher FOREIGN KEY (teacher_id)  REFERENCES Teacher(teacher_id),\n" +
                    ")  ENGINE=INNODB;";
            Statement statement = this.connection.createStatement();
            boolean rows = statement.execute(sql);
        } catch (Error error) {
            System.out.println(error);
        }
    }


    public void createStudentTable() throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS student (\n" +
                    "student_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "CONSTRAINT FK_User FOREIGN KEY (user_id)  REFERENCES User(user_id),\n" +
                    "CONSTRAINT FK_Course FOREIGN KEY (course_id)  REFERENCES Course(course_id),\n" +
                    ")  ENGINE=INNODB;";
            Statement statement = this.connection.createStatement();
            boolean rows = statement.execute(sql);
        } catch (Error error) {
            System.out.println(error);
        }
    }

    //todo:esperando correcion

    public void createHomeworkTable() throws SQLException {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS homework (\n" +
                    "homework_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "title VARCHAR(255),\n" +
                    "description VARCHAR(255),\n" +
                    "due_date DATE,\n" +
                    "CONSTRAINT FK_User FOREIGN KEY (user_id)  REFERENCES User(user_id),\n" +
                    "CONSTRAINT FK_Course FOREIGN KEY (course_id)  REFERENCES Course(course_id),\n" +
                    ")  ENGINE=INNODB;";
            Statement statement = this.connection.createStatement();
            boolean rows = statement.execute(sql);
        } catch (Error error) {
            System.out.println(error);
        }
    }

    public void closeDb() throws SQLException {
        try {
            this.connection.close();
        } catch (Error error) {
            System.out.println(error);
        }
    }
}
