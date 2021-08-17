package domain;

public class Teacher {
    private static Teacher instance;
    private User user;

    public Teacher(User user) {
        this.user = user;
    }

    public static Teacher getInstance(User user) {
        if (instance == null) {
            instance = new Teacher(user);
        }
        return instance;
    }
}
