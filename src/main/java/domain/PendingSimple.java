package domain;

import java.util.Date;

public class PendingSimple extends HomeworkState {
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
            homework.setState(HomeworkStateEnum.DELIVERED);
        }
        else{
            homework.setState(HomeworkStateEnum.OVERDUE);
        }
    }

    @Override
    public HomeworkStateEnum getState() {
        return HomeworkStateEnum.PENDING;
    }
}
