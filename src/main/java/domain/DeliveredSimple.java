package domain;

public class DeliveredSimple extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework) throws Exception {
        homework.setState(HomeworkStateEnum.FINISHED);
    }

    @Override
    public HomeworkStateEnum getState() {
        return HomeworkStateEnum.DELIVERED;
    }
}
