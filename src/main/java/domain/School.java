package domain;

import java.util.ArrayList;
import java.util.List;

public class School {
    private static School instance;
    private Integer id;
    private String name;
    private String address;
    private List<Course> courses;

    private School(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static School getInstance(Integer id, String name, String address) {
        if (instance == null) {
            instance = new School(id, name, address);
        }
        return instance;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }
}
