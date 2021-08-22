package domain;

import repository.HomeworkDAO;

public class OverdueState extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework, Student student) throws Exception {
        HomeworkDAO.updateHomeworkState(new DeliveredState().getDescription(), homework.getId(), student.getId());
        throw new Exception("Una vez que la tarea esta vencida, no se puede cambiar de estado");
    }

    @Override
    public String getDescription() {
        return "OVERDUE";
    }
}
