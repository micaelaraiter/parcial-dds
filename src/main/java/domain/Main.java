package domain;

import com.twilio.twiml.voice.Sim;
import repository.CourseDAO;
import repository.SchoolDAO;
import service.*;
import service.entities.CreateHomeworkResult;
import service.entities.MailContent;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        SchoolDAO.insertSchool("UTN", "Medrano 951");

        Integer option = -1;
        while (option != 0) {
            Scanner console = new Scanner(System.in);
            System.out.println("Indique el codigo de curso para el cual quiere realizar operaciones");
            String courseCode = console.nextLine();

            System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                    "0.Salir\n " +
                    "1.Inicia Sesion como alumno \n " +
                    "2.Inicia Sesion como profesor \n " +
                    "3.Registrarse como profesor \n " +
                    "4.Registrarse como alumno");

            option = console.nextInt();
            switch (option) {
                case 1: {
                    User userLogged = UserService.showFormLogin();
                    User user = UserService.login(userLogged);
                    if(user == null){
                        System.out.println("No se ha encontrado el email ingresado");
                    }

                    Student student = StudentService.getStudent(user);
                    Integer optionSecondMenu = -1;
                    optionSecondMenu = UserService.showStudentMenu();

                    List<Homework> homework = StudentService.getAllStudentHomework((Integer) student.getId());
                    System.out.println("size homework = " + homework.size());
                    student.setHomeworks(homework);

                    switch (optionSecondMenu){
                        case 1:{
                            student.getHomeworks().forEach(homework1 -> {
                                System.out.println("TAREA: " + homework1.getId().toString() + " \n TITULO: " + homework1.getTitle() +
                                        "\n FECHA: " + homework1.getDuedDate().toString());
                            });
                        }break;
                        case 2:{
                            StudentService.handHomework(student, courseCode);
                        }break;
                    }
                }
                break;
                case 2: {
                    Integer optionSecondMenu = -1;
                    User userLogged = UserService.showFormLogin();
                    User user = UserService.login(userLogged);
                    if(user == null){
                        System.out.println("No se ha encontrado el email ingresado");
                    }
                    Teacher teacher = new Teacher();
                    teacher.setUser(user);
                    optionSecondMenu = TeacherService.showTeacherMenu();
                    switch(optionSecondMenu) {
                        case 1: {
                            CreateHomeworkResult result = TeacherService.createHomework(courseCode);
                            String titulo = "";
                            boolean esSimple = false;

                            if (result.getTpId() != 0) {
                                Tp tp = HomeworkService.getTpById(result.getTpId());
                                titulo = tp.getTitle();
                                System.out.println("se obtuvo el tp recien creado, " + titulo);
                            } else if (result.getSimpleHomeworkId() != 0) {
                                SimpleHomework homework = HomeworkService.getSimpleHomeworkById(result.getSimpleHomeworkId());
                                titulo = homework.getTitle();
                                esSimple = true;
                                System.out.println("se obtuvo la tarea recien creada, " + titulo);
                            } else {
                                System.out.println("Algo salio mal en la creacion de tareas");
                            }

                            //List<Student> studentsInCourse = teacher.getCourse().getStudents();
                            //List<Student> studentsInCourse = StudentService.getAllStudentsFromCourse(teacher.getCourse().getCode());
                            List<Student> studentsInCourse = StudentService.getAllStudentsFromCourse(courseCode);
                            String finalTitulo = titulo;

                            if(studentsInCourse != null && !studentsInCourse.isEmpty()) {
                                studentsInCourse.forEach(student -> {
                                    // idealmente el emailFrom seria "teacher.getUser().getEmail()" pero puse en Sengrid que se mande desde mi mail, sino habria que cambiar el apiKey de Sendgrid
                                    MailContent mailContent = new MailContent("Nueva tarea: " + finalTitulo, "Hola " + student.getUser().getName() + " tenes una nueva tarea.", "lmelamed@frba.utn.edu.ar", student.getUser().getEmail());
                                    try {
                                        MailingService.sendMail(mailContent);
                                        System.out.println("mail enviado a " + student.getUser().getEmail());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }
                        break;
                        case 2: {
                            Scanner console2 = new Scanner(System.in);
                            System.out.println("Ingresa el titulo de la tarea a coregir");
                            SimpleHomework homework = HomeworkService.getSimpleHomeworkByTitle(console2.nextLine());
                            teacher.reviewHomework(homework);
                        }
                        break;
                    }
                    break;
                }
                case 3: {
                    Teacher teacher = new Teacher();
                    User userRegistered = UserService.showFormRegister();
                    Course course = CourseDAO.selectCourseByCode(courseCode);
                    if(course == null){
                        course = new Course(courseCode, courseCode);
                        School school = SchoolDAO.selectSchool();
                        school.addCourse(course);
                        Integer courseId = CourseDAO.insertCourse(course, school.getId());
                        course.setId(courseId);
                    }
                    course.addTeacher(teacher);
                    UserService.registerTeacher(userRegistered, course);
                    teacher.setUser(userRegistered);
                }
                break;
                case 4: {
                    Student student = new Student();
                    User userRegistered = UserService.showFormRegister();
                    Course course = CourseDAO.selectCourseByCode(courseCode);
                    if(course == null){
                        course = new Course(courseCode, courseCode);
                        School school = SchoolDAO.selectSchool();
                        school.addCourse(course);
                        Integer courseId = CourseDAO.insertCourse(course, school.getId());
                        course.setId(courseId);
                    }
                    course.addStudent(student);
                    UserService.registerStudent(userRegistered, course);
                    student.setUser(userRegistered);
                }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }
}