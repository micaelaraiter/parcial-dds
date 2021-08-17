package service;

import java.sql.SQLException;
import java.util.Scanner;

public class TeacherService {
    public static int showTeacherMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "1.Crear tarea \n " +
                "2.Corregir tarea");
        return console.nextInt();
    }

    public static void createHomework() throws SQLException {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresar \n" +
                "true para crear un tp \n " +
                "false para crear una tarea ");
        boolean isATp = console.nextBoolean();
        if(isATp){
            HomeworkService.createTpHomework();
        } else{
            HomeworkService.createSimpleHomework(0);
        }
    }


}
