package domain;

import java.util.Date;

public class SimpleHomework extends Homework{
   private Date duedDate;
   private Number grade;
   private HomeworkStateEnum state;
   private boolean pass; // aprobar un examen es "pass an exam"
   private int order;
   private int tpId;

    public SimpleHomework(String title, Date duedDate, int order, int tpId) {
        super(title);
        this.duedDate = duedDate;
        this.state = HomeworkStateEnum.PENDING;
        this.pass = false;
        this.order = order;
        this.tpId = tpId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTpId() {
        return tpId;
    }

    public void setTpId(int tpId) {
        this.tpId = tpId;
    }

    public Date getDuedDate() {
        return duedDate;
    }

    public void setDuedDate(Date duedDate) {
        this.duedDate = duedDate;
    }

    public Number getGrade() {
        return grade;
    }

    public void setGrade(Number grade) {
        this.grade = grade;
    }

    public HomeworkStateEnum getState() {
        return state;
    }

    public void setState(HomeworkStateEnum state) {
        this.state = state;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
