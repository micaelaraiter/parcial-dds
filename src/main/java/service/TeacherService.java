package service;

import service.entities.CreateHomeworkResult;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class TeacherService {
    public static int showTeacherMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "1.Crear tarea \n " +
                "2.Corregir tarea");
        return console.nextInt();
    }

    public static CreateHomeworkResult createHomework() throws SQLException, ParseException {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresar \n" +
                "true para crear un tp \n " +
                "false para crear una tarea ");
        boolean isATp = console.nextBoolean();
        CreateHomeworkResult result = new CreateHomeworkResult(0,0);
        if(isATp){
            result.setTpId(HomeworkService.createTpHomework());
        } else{
            result.setSimpleHomeworkId(HomeworkService.createSimpleHomework(0));
        }
        return result;
    }


}
