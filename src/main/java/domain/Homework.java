package domain;

import java.util.Date;

public class Homework implements IHomework { // comportamiento default
    private String title;
    private String description;
    private Date duedDate;
    private Number grade;
    private Integer id;

    public Homework(String title, String description, Integer id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.grade = -1;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDuedDate() {
        return this.duedDate;
    }

    public void setDuedDate(Date date) {
        this.duedDate = date;
    }

    public Number getGrade() {
        return this.grade;
    }

    public void setGrade(Number grade) {
        this.grade = grade;
    }
}
