package domain;

import repository.HomeworkDAO;

public class DeliveredState extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework, Student student) throws Exception {
        HomeworkDAO.updateHomeworkState(new DeliveredState().getDescription(), homework.getId(), student.getId());
        homework.setState(new FinishedState());
    }

    @Override
    public String getDescription() {
        return "DELIVERED";
    }
}
