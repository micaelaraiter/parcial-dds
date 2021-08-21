package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IHomework {


   /* public void notifySubscribers(HomeworkNotification notification){
        this.subscribers.forEach(subscriber -> {
            try {
                subscriber.receiveHomeworkNotification(notification);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }*/
    Integer getId();

    void setId(Integer id);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    Date getDuedDate();

    void setDuedDate(Date date);

    Number getGrade();

    void setGrade(Number grade);

}
