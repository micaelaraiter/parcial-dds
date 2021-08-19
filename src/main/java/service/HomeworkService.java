package service;

import domain.SimpleHomework;
import domain.Tp;
import repository.SimpleHomeworkDAO;
import repository.TpDAO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HomeworkService {

    public static SimpleHomework getSimpleHomeworkById(Integer id) throws SQLException {
        return SimpleHomeworkDAO.selectHomeworkById(id);
    }

    public static Tp getTpById(Integer id) throws SQLException {
        return TpDAO.selectTpById(id);
    }

    public static int createTpHomework() throws SQLException {
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese titulo: ");
        String title = console.nextLine();
        Tp tp = new Tp(title);
        return TpDAO.createTp(tp);
    }

    public static int  createSimpleHomework(int tpId) throws SQLException, ParseException {
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese titulo: ");
        String title = console.nextLine();

        System.out.println("\nIngrese fecha de vencimiento DD/MM/YYYY: ");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date duedDate = formatter.parse(console.nextLine());

        int order = 0;
        if(tpId != 0){
            System.out.println("\nIngrese el orden: ");
            order = console.nextInt();
        }

        SimpleHomework simpleHomework = new SimpleHomework(title, duedDate, order, tpId);

        return SimpleHomeworkDAO.createHomework(simpleHomework);
    }
}
