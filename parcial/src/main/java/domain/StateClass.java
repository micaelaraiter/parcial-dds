package domain;

public abstract class  StateClass {

    public abstract boolean isAvailableToSendHomework(Student student);
    public abstract void changeState(Homework homework);

}
