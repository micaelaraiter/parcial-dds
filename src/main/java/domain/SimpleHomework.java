package domain;

import java.util.ArrayList;
import java.util.List;

public class SimpleHomework extends Homework {
    private HomeworkState state;
    private boolean pass; // aprobar un examen es "pass an exam"
    private int order;
    private List<Teacher> subscribers = new ArrayList<>();

    public SimpleHomework(String title, String description, Integer id) {
        super(title, description, id);
    }

    public HomeworkState getState() {
        return this.state;
    }

    public void setState(HomeworkState state) {
        this.state = state;
    }

    public boolean isPass() {
        return this.pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Teacher> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Teacher> subscribers) {
        this.subscribers = subscribers;
    }

    public void hand(Student student) throws Exception {
        this.state.changeState(this, student);
    }

    public void reviewed(Number grade, Student student) throws Exception {
        boolean pass = grade.intValue() >= 6 ? true : false;

        this.setGrade(grade);
        this.setPass(pass);

        this.state.changeState(this, student);
    }

    public boolean isAlreadyReviewed(){
        return this.getGrade().intValue() != -1;
    }

    public void notifySubscribers(){
        this.subscribers.forEach(subscriber -> {
            try {
                subscriber.reviewHomework(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
