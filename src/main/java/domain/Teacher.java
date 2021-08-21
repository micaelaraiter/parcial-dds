package domain;

import java.util.Scanner;

public class Teacher {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void reviewHomework(SimpleHomework homework) throws Exception {
        Scanner console = new Scanner(System.in);
        if(!homework.isAlreadyReviewed()){
            System.out.println("Indique la nota que le pondra a la tarea " + homework.getTitle());
            Integer grade = console.nextInt();

            homework.reviewed(grade);
        }
        else{
            System.out.println("La tarea " + homework.getTitle() + " ya fue corregida, le quedo una nota de " + homework.getGrade());
        }
    }
}
