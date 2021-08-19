package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Homework {
    private Integer id; // homeworkId o tpId
    private String title;
    private List<Student> subscribers = new ArrayList<>();

    public Homework(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Student> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Student> subscribers) {
        this.subscribers = subscribers;
    }

    public void addSubscriber(Student subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeSubscriber(Student subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void notifySubscribers(HomeworkNotification notification){
        this.subscribers.forEach(subscriber -> {
            try {
                subscriber.receiveHomeworkNotification(notification);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
