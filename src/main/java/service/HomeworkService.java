package service;

import domain.*;
import repository.HomeworkDAO;
import repository.SimpleHomeworkDAO;
import repository.TpDAO;
import repository.UsersDao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeworkService {

    public static SimpleHomework getSimpleHomeworkById(Integer id) throws SQLException {
        return SimpleHomeworkDAO.selectHomeworkById(id);
    }

    public static Tp getTpById(Integer id) throws SQLException {
        return TpDAO.selectTpById(id);
    }

    public static SimpleHomework getSimpleHomeworkByTitle(String title) throws SQLException {
        return HomeworkDAO.selectHomeworkByTitle(title);
    }

    public static int createTpHomework() throws SQLException {
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese titulo: ");
        String title = console.nextLine();
        System.out.println("\nIngrese descripcion: ");
        String description = console.nextLine();
        Tp tp = new Tp(title, description, 0, new ArrayList<>());
        int tpId = TpDAO.createTp(tp);
        tp.setId(tpId);
        return  tpId;
    }

    public static int  createSimpleHomework(int tpId, String courseCode) throws SQLException, ParseException {
        Scanner console = new Scanner(System.in);
        System.out.println("\nIngrese titulo: ");
        String title = console.nextLine();

        System.out.println("\nIngrese descripcion: ");
        String description = console.nextLine();

        System.out.println("\nIngrese fecha de vencimiento DD/MM/YYYY: ");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date duedDate = formatter.parse(console.nextLine());

        int order = 0;
        if(tpId != 0){
            System.out.println("\nIngrese el orden: ");
            order = console.nextInt();
        }

        SimpleHomework simpleHomework = new SimpleHomework(title, description, tpId);
        simpleHomework.setDuedDate(duedDate);
        simpleHomework.setOrder(order);

        Tp tp = TpDAO.selectTpById(tpId);
        if(tp != null){
            tp.addHomework(simpleHomework);
            return SimpleHomeworkDAO.createHomework(simpleHomework, tp, courseCode);
        }

        List<Teacher> teachers = UsersDao.getAllTeachersFromCourse(courseCode);
        simpleHomework.setSubscribers(teachers);

        return SimpleHomeworkDAO.createHomework(simpleHomework, null, courseCode);
    }

    public static HomeworkState getHomeworkStateByDescription(String description){
        switch (description.toUpperCase(Locale.ROOT)){
            case "DELIVERED":
                return new DeliveredState();
            case "OVERDUE":
                return new OverdueState();
            case "FINISHED":
                return new FinishedState();
            default:
                return new PendingState();
        }
    }
}
