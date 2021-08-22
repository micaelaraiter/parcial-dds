package domain;

import service.StudentService;

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

            System.out.println("Indique el mail del usuario al que le corregira ");
            String email = console.nextLine();
            Student student = StudentService.getStudentByEmail(email);

            homework.reviewed(grade, student);
        }
        else{
            System.out.println("La tarea " + homework.getTitle() + " ya fue corregida, le quedo una nota de " + homework.getGrade());
        }
    }
}
