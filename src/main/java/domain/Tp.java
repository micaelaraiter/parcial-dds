package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tp extends Homework{
    private List<Homework> homeworks = new ArrayList<>();

    public Tp(String title, String description, Integer id, List<Homework> homeworks) {
        super(title, description, id);
        this.homeworks = homeworks;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void addHomework(Homework homework) {
        this.homeworks.add(homework);
    }

    public void removeHomework(Homework homework){
        this.homeworks.remove(homework);
    }

    @Override
    public Date getDuedDate() {
        return this.homeworks.stream()
                .map(homework -> homework.getDuedDate()).max(Date::compareTo).get();
    }

    @Override
    public Number getGrade() {
        return this.homeworks.stream()
                .map(homework -> homework.getGrade().intValue())
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }
}
