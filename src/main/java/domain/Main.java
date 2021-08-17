package domain;

import service.StudentService;
import service.TeacherService;
import service.UserService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

        Integer option = -1;
        while (option != 0) {
            Scanner console = new Scanner(System.in);
            System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                    "0.Salir\n " +
                    "1.Inicia Sesion \n " +
                    "2.Registrarse como alumno");

            option = console.nextInt();
            switch (option) {
                case 1: {
                    User userLogged = UserService.showFormLogin();
                    User user = UserService.login(userLogged);
                    if(user == null){
                        System.out.println("No se ha encontrado el email ingresado");
                    }
                    //todo: mostrarMenu segun userType

                    Teacher teacher = new Teacher(user);
                    Integer optionSecondMenu = -1;
                    optionSecondMenu = TeacherService.showTeacherMenu();
                    switch(optionSecondMenu){
                        case 1:{
                            TeacherService.createHomework();
                        }break;
                    }


                    Student student = StudentService.getStudent(user);
                    optionSecondMenu = UserService.showStudentMenu();

                    switch (optionSecondMenu){
                        case 1:{
                            StudentService.getAllStudentHomework((Integer) student.getId());
                        }break;
                        case 2:{}break;
                    }
                }
                break;
                case 2: {
                    User userRegistered = UserService.showFormRegister();
                    UserService.register(userRegistered);
                }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }
}