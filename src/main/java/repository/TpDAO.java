package repository;

import domain.Tp;

import java.sql.*;

public class TpDAO {
    public static int createTp(Tp tp) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "INSERT INTO TP (title) values (?)";
        PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, tp.getTitle());
        stm.executeUpdate();

        // obtener último id generado
        ResultSet generatedKeys = stm.getGeneratedKeys();
        Integer id = -1;
        if (generatedKeys.next()){
            id = generatedKeys.getInt(1);
            System.out.println("tp " + id.toString() + " creado con exito");
        }
        else {
            id = 0;
            System.out.println("error al crear tp");
        }
        connection.close();
        return id;
    }

    public static Tp selectTpById(int id) throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select * from Tp where tp_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            Tp tp = new Tp(resultSet.getString("title"));
            //TODO: el chiste es hacer un join para que devuelva las tareas que tiene
            return tp;
        }

        connection.close();
        return null;
    }

}
