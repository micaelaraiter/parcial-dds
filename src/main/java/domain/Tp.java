package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tp extends Homework{
    private List<Homework> homeworks = new ArrayList<>();

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public Tp(String title) {
        super(title);
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public Date getDuedDate() {
        //TODO: review :(
       // return this.homeworks.stream()
       //         .map(homework -> homework.getDuedDate()).max(Date::compareTo).get();

        return new Date();
    }
}
