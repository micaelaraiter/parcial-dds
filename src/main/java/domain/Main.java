package domain;

import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Integer option = -1;
        while (option != 0) {
            Scanner console = new Scanner(System.in);
            System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                    "0.Inicia Sesion \n " +
                    "1.Registrarse como alumno");

            option = console.nextInt();
            switch (option) {
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
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }
}