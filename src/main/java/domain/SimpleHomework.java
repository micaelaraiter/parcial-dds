package domain;

import java.util.Date;

public class SimpleHomework extends Homework{
   private Date duedDate;
   private Number grade;
   private HomeworkStateEnum state;
   private boolean pass; // aprobar un examen es "pass an exam"

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
