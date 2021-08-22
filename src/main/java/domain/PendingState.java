package domain;

import repository.HomeworkDAO;

import java.util.Date;

public class PendingState extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        if(student.getHomeworks().stream().anyMatch(homework1 -> homework1.getId() == homework.getId())){
            Date today = new Date();
            return homework.getDuedDate().after(today) ? true : false;
        }
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework, Student student) throws Exception {
        if(homework.getDuedDate().before(new Date())){
            homework.setState(new DeliveredState());
            HomeworkDAO.updateHomeworkState(new DeliveredState().getDescription(), homework.getId(), student.getId());
            homework.notifySubscribers();
        }
        else{
            homework.setState(new OverdueState());
        }
    }

    @Override
    public String getDescription() {
        return "PENDING";
    }
}
