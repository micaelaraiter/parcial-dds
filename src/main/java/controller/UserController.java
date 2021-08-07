package controller;

import java.sql.*;

public class UserController {
    private ConnectionToDB connectionToDB;

    public UserController() {
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
        String sql = "select * Usuario where mail = $(email)";
        Statement statement = connection.createStatement();
        boolean rows = statement.execute(sql);
        return true;
    }

}
