package domain;

public class HomeworkNotification {
    private Integer id;
    private HomeworkStateEnum newState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HomeworkStateEnum getNewState() {
        return newState;
    }

    public void setNewState(HomeworkStateEnum newState) {
        this.newState = newState;
    }
}
