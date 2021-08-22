package domain;

import service.HomeworkService;
import service.MailingService;
import service.StudentService;
import service.TeacherService;
import service.entities.MailContent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private Integer id;
    private User user;
    private List<Homework> homeworks = new ArrayList<>();
    private boolean hasPassed;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean hasPassed() {
        return hasPassed;
    }

    public void setHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void addHomework(Homework homework) {
        this.homeworks.add(homework);
    }

    public void removeHomework(Homework homework) {
        this.homeworks.remove(homework);
    }

    public void handHomework(SimpleHomework homework, String courseCode) throws Exception {
        if(!homework.getState().isAvailableToSendHomework(this, homework)){
            System.out.println("NO PODES ENTREGAR LA TAREA - tenia fecha de vencimiento " + homework.getDuedDate().toString());
            return;
        }

        homework.hand(this);

        List<Teacher> teachersInCourse = TeacherService.getAllTeacherFromCourse(courseCode);
        String finalTitulo = "TAREA ENTREGADA";

        if(teachersInCourse != null && !teachersInCourse.isEmpty()) {
            teachersInCourse.forEach(teacher -> {
                // idealmente el emailFrom seria "teacher.getUser().getEmail()" pero puse en Sengrid que se mande desde mi mail, sino habria que cambiar el apiKey de Sendgrid
                MailContent mailContent = new MailContent(finalTitulo, "Hola " + teacher.getUser().getName() + " el alumno " + this.getUser().getName() + " entrego la tarea " + homework.getTitle(), "lmelamed@frba.utn.edu.ar", teacher.getUser().getEmail());
                try {
                    MailingService.sendMail(mailContent);
                    System.out.println("mail enviado a " + teacher.getUser().getEmail());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
