package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDB {
    public Connection connection;

    public void initDb() throws SQLException {
        //cuando saquemos el static, deberia hacer que el conection sea una property
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/parcial";
            String username = "root";
            String password = "elef";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            this.connection = connection;
            this.createUserTable();
        } catch (Error error) {
            System.out.println(error);
        }
    }

    public void createUserTable() throws SQLException {
        try{
            String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "    user_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    firstName VARCHAR(255),\n" +
                    "    lastName VARCHAR(255),\n" +
                    "    email VARCHAR(255),\n" +
                    "    password VARCHAR(255),\n" +
                    "    priority TINYINT NOT NULL,\n" +
                    ")  ENGINE=INNODB;";
            Statement statement = this.connection.createStatement();
            boolean rows = statement.execute(sql);
        } catch (Error error){
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
