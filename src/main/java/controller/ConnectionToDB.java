package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    private Connection connection;

    public static Connection initDb() throws SQLException {
        //cuando saquemos el static, deberia hacer que el conection sea una property
        try{
            String jdbcURL ="jdbc:mysql://localhost:3306/parcial";
            String username="root";
            String password ="elef";
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);
            return connection;
        } catch (Error error){
            System.out.println(error);
        }

        return null;
    }

    public void closeDb(){

    }
}
