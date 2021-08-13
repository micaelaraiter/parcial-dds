package domain;

public abstract class HomeworkState {

    public abstract boolean isAvailableToSendHomework(Student student, SimpleHomework homework);
    public abstract void changeState(SimpleHomework homework) throws Exception;
    public abstract HomeworkStateEnum getState();
}

