package service.entities;

public class CreateHomeworkResult {
    private Integer tpId;
    private Integer simpleHomeworkId;

    public CreateHomeworkResult(Integer tpId, Integer simpleHomeworkId) {
        this.tpId = tpId;
        this.simpleHomeworkId = simpleHomeworkId;
    }

    public Integer getTpId() {
        return tpId;
    }

    public void setTpId(Integer tpId) {
        this.tpId = tpId;
    }

    public Integer getSimpleHomeworkId() {
        return simpleHomeworkId;
    }

    public void setSimpleHomeworkId(Integer simpleHomeworkId) {
        this.simpleHomeworkId = simpleHomeworkId;
    }
}
