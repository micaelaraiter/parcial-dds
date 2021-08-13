package domain;

public class OverdueSimple extends HomeworkState {
    @Override
    public boolean isAvailableToSendHomework(Student student, SimpleHomework homework) {
        return false;
    }

    @Override
    public void changeState(SimpleHomework homework) throws Exception {
        throw new Exception("Una vez que la tarea esta vencida, no se puede cambiar de estado");
    }

    @Override
    public HomeworkStateEnum getState() {
        return HomeworkStateEnum.OVERDUE;
    }
}
