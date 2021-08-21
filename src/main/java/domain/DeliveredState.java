package domain;

public class DeliveredState extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework) throws Exception {
        homework.setState(new FinishedState());
    }
}
