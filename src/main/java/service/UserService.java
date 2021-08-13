package service;

import controller.Repository;
import domain.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    public static boolean login(User user) throws SQLException {
        String email = user.getEmail();
        return Repository.selectUserByEmail(email);
    }

    public static User showFormLogin(){
        Scanner console = new Scanner(System.in);
        User userLogged = new User();
        System.out.println("\nIngrese email: ");
        userLogged.setEmail(console.nextLine());
        System.out.println("\nIngrese contrasena: ");
        userLogged.setPassword(console.nextLine());
        return userLogged;
    }
}
