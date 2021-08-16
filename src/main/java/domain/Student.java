package domain;

import service.HomeworkService;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Number id;
    private Course course;
    private User user;
    private boolean hasPassed;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private List<Homework> homeworks = new ArrayList<>();

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public void receiveHomeworkNotification(HomeworkNotification notification) {
        SimpleHomework homework = HomeworkService.getSimpleHomeworkById(notification.getId());

        if(homework == null){ // TODO: error
            return;
        }

        switch(notification.getNewState()){
            case PENDING:
                this.addHomework(homework);
                break;
            case DELIVERED:
                // la tarea fue entregada con exito!!!
                // TODO: removeHomework? o capaz no hace falta
                break;
            case FINISHED:
                this.setHasPassed(homework.isPass());
                break;
            case OVERDUE:
                this.setHasPassed(false);
                break;
        }
    }
}
