package domain;

import java.util.Date;

public class PendingState extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        if(student.getHomeworks().contains(homework)){
            return homework.getDuedDate().before(new Date()) ? true : false;
        }
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework) throws Exception {
        if(homework.getDuedDate().before(new Date())){
            homework.setState(new DeliveredState());
            homework.notifySubscribers();
        }
        else{
            homework.setState(new OverdueState());
        }
    }
}
