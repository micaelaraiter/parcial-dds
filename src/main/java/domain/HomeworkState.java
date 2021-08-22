package domain;

public abstract class HomeworkState {
    public abstract boolean isAvailableToSendHomework(Student student, SimpleHomework homework);
    public abstract void changeState(SimpleHomework homework, Student student) throws Exception;
    public abstract String getDescription();
}

