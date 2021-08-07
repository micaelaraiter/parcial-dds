package controller;

import java.sql.*;

public class UserController {

    public static void main(String args[]) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ConnectionToDB.initDb();
        Connection connection = ConnectionToDB.initDb();
        String sql ="INSERT INTO Usuario (usuario_id,nombre) values (1,'mica')";
        Statement statement = connection.createStatement();
        boolean rows = statement.execute(sql);
    }

    public static boolean selectUserByEmail(String email) throws SQLException {
        ConnectionToDB.initDb();
        Connection connection = ConnectionToDB.initDb();
        String sql ="select * Usuario where mail = $(email)";
        Statement statement = connection.createStatement();
        boolean rows = statement.execute(sql);
        return true;
    }

}
