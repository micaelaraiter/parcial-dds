package service;

import controller.Repository;
import domain.SimpleHomework;
import domain.Tp;
import domain.User;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class HomeworkService {

    public static SimpleHomework getSimpleHomeworkById(Integer id){
        // TODO: traer de DB
        return new SimpleHomework();
    }

    public static void createTpHomework() throws SQLException {
        Tp tp = new Tp();
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese titulo: ");
        tp.setTitle(console.nextLine());
        Repository.createTp(tp);
    }

    public static void  createSimpleHomework(int tpId) throws SQLException {
        SimpleHomework simpleHomework = new SimpleHomework();
        Scanner console = new Scanner(System.in);
         System.out.println("\nIngrese titulo: ");
        simpleHomework.setTitle(console.nextLine());
        System.out.println("\nIngrese fecha de vencimiento DD/MM/YYYY: ");
        simpleHomework.setDuedDate(console.nextLine());
        if(tpId != 0){
            System.out.println("\nIngrese el orden: ");
            simpleHomework.setOrder(console.nextInt());
            simpleHomework.setTpId(tpId);
        }
        Repository.createHomework(simpleHomework);
    }
}
