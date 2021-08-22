package service;

import domain.Teacher;
import repository.UsersDao;
import service.entities.CreateHomeworkResult;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class TeacherService {
    public static int showTeacherMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "1.Crear tarea \n " +
                "2.Corregir tarea");
        return console.nextInt();
    }

    public static CreateHomeworkResult createHomework(String courseCode) throws SQLException, ParseException {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresar \n" +
                "true para crear un tp \n " +
                "false para crear una tarea ");
        boolean isATp = console.nextBoolean();
        CreateHomeworkResult result = new CreateHomeworkResult(0,0);
        if(isATp){
            result.setTpId(HomeworkService.createTpHomework());
        } else{
            System.out.println("Ingrese el ID del tp al cual pertenece");
            Integer tpId = console.nextInt();
            // TODO: validar que el tp exista
            result.setSimpleHomeworkId(HomeworkService.createSimpleHomework(tpId, courseCode));
        }
        return result;
    }

    public static List<Teacher> getAllTeacherFromCourse(String courseCode) throws SQLException {
        return UsersDao.getAllTeachersFromCourse(courseCode);
    }

}
