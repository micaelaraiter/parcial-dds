package service;

import controller.Repository;
import domain.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    public static User login(User user) throws SQLException {
        String email = user.getEmail();
        return Repository.selectUserByEmail(email);

    }

    public static User showFormLogin() {
        Scanner console = new Scanner(System.in);
        User userLogged = new User();
        System.out.println("\nIngrese email: ");
        userLogged.setEmail(console.nextLine());
        System.out.println("\nIngrese contrasena: ");
        userLogged.setPassword(console.nextLine());
        return userLogged;
    }

    public static void register(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Repository.registerUser(user);
        User userCreated = Repository.selectUserByEmail(user.getEmail());
        Repository.registerStudent((Integer) userCreated.getId());
    }

    public static User showFormRegister() {
        Scanner console = new Scanner(System.in);
        User userRegister = new User();
        System.out.println("\nIngrese email: ");
        userRegister.setEmail(console.nextLine());
        System.out.println("\nIngrese contrasena: ");
        userRegister.setPassword(console.nextLine());
        System.out.println("\nIngrese nombre: ");
        userRegister.setName(console.nextLine());
        System.out.println("\nIngrese apellido: ");
        userRegister.setLastName(console.nextLine());
        return userRegister;
    }

    public static int showStudentMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "1.Ver mis tareas \n " +
                "2.Entregar tarea");
        return console.nextInt();
    }
}
