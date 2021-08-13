package domain;

public enum HomeworkStateEnum {
    PENDING,
    DELIVERED,
    FINISHED,
    OVERDUE;

    public static HomeworkStateEnum fromInteger(int number) {
        switch (number) {
            case 0:
                return PENDING;
            case 1:
                return DELIVERED;
            case 2:
                return FINISHED;
            case 3:
                return OVERDUE;
        }
        return null;
    }
}
