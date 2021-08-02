package domain;

import java.util.Date;

public interface Homework {
    Date getDuedDate();
    Number getNote();
    void sendNote();
}
