package service;

import domain.Course;
import domain.User;
import repository.StudentDAO;
import repository.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    public static User login(User user) throws SQLException {
        String email = user.getEmail();
        return UserDAO.selectUserByEmail(email);

    }

    public static User showFormLogin() {
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese email: ");
        String mail = console.nextLine();
        System.out.println("\nIngrese contrasena: ");
        String password = console.nextLine();
        User userLogged = new User(mail, password);
        return userLogged;
    }

    public static void register(User user, Course course) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UserDAO.registerUser(user);
        User userCreated = UserDAO.selectUserByEmail(user.getEmail());
        StudentDAO.registerStudent((Integer) userCreated.getId(), course.getId());
    }

    public static User showFormRegister() {
        Scanner console = new Scanner(System.in);

        System.out.println("\nIngrese email: ");
        String mail = console.nextLine();

        System.out.println("\nIngrese contrasena: ");
        String pass = console.nextLine();

        User userRegister = new User(mail, pass);

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
