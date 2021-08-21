package repository;

import domain.Course;
import domain.School;
import domain.Student;
import domain.User;

import java.sql.*;

public class SchoolDAO {

    public static Integer insertSchool(String name, String address) throws SQLException {
        School school = SchoolDAO.selectSchool();

        if(school == null){
            Connection connection = ConnectionToDB.initDb();
            String sql = "INSERT INTO School (name, address) values (?,?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, name);
            stm.setString(2, address);
            stm.executeUpdate();

            // obtener último id generado
            ResultSet generatedKeys = stm.getGeneratedKeys();
            Integer id = -1;
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                System.out.println("escuela " + id.toString() + " creada con exito");
            }
            else {
                id = 0;
                System.out.println("error al crear escuela");
            }
            connection.close();
            return id;
        }
        return 0;
    }

    public static School selectSchool() throws SQLException {
        Connection connection = ConnectionToDB.initDb();
        String sql = "select s.name, s.address s.school_id from School s ";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            School school = School.getInstance(resultSet.getInt("school_id"), resultSet.getString("name"), resultSet.getString("address"));
            return school;
        }

        connection.close();
        return null;
    }
}
