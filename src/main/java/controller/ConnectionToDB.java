package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDB {

    public static Connection initDb() throws SQLException {
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



   }
