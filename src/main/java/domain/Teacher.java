package domain;

public class Teacher {
    private static Teacher instance;
    private User user;
    private Course course;

    public static Teacher getInstance() {
        if (instance == null) {
            instance = new Teacher();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
