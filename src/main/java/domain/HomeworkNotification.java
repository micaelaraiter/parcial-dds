package domain;

public class HomeworkNotification {
    private Integer id;
    private HomeworkState newState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HomeworkState getNewState() {
        return newState;
    }

    public void setNewState(HomeworkState newState) {
        this.newState = newState;
    }
}
