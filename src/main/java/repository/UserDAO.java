package repository;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

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
            User userMapper = new User(resultSet.getString("email"), "fakepass");
            userMapper.setId(resultSet.getInt("user_id"));
            userMapper.setName(resultSet.getString("firstName"));
            System.out.println("respuesta de la db: " + userMapper.getEmail());
            return userMapper;
        }

        connection.close();
        return null;
    }

}
