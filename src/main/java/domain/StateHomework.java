package domain;

import java.util.Date;

public class StateHomework implements Homework{
   private Date duedDate;
   private Number note;
   private StateHomework stateHomework;
   private boolean isApproved;


    @Override
    public Date getDuedDate() {
        return null;
    }

    @Override
    public Number getNote() {
        return null;
    }

    @Override
    public void sendNote() {

    }
}
