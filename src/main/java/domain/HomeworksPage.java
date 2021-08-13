package domain;

import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class HomeworksPage {
    public static void main(String[] args) throws SQLException {

        Integer studentId = -1;
        Scanner console = new Scanner(System.in);
        System.out.println("Ingrese su ID de alumno \n "); // TODO: claramente no se lo vamos a pedir, esta en el resultado del login
        studentId = console.nextInt();

        switch (studentId) {
            case 0: {
                User userLogged = UserService.showFormLogin();
                boolean isUserLogged = UserService.login(userLogged);
                if(!isUserLogged){
                    System.out.println("No se ha encontrado el email ingresado");
                }
                //todo: mostrarMenu segun userType
            }
            break;
            case 1: {
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + studentId);
        }
    }
}