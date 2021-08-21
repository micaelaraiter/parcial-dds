package domain;

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
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, IOException {
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
                    "3.Registrarse como alumno \n" +
                    "4.Registrarse como profesor");

            option = console.nextInt();
            switch (option) {
                case 1: {
                    User userLogged = UserService.showFormLogin();
                    User user = UserService.login(userLogged);
                    if(user == null){
                        System.out.println("No se ha encontrado el email ingresado");
                    }
                    //todo: mostrarMenu segun userType

                    Student student = StudentService.getStudent(user);
                    Integer optionSecondMenu = -1;
                    optionSecondMenu = UserService.showStudentMenu();

                    switch (optionSecondMenu){
                        case 1:{
                            StudentService.getAllStudentHomework((Integer) student.getId());
                        }break;
                        case 2:{}break;
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
                    UserService.register(userRegistered, course);
                    teacher.setUser(userRegistered);

                }
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
                    UserService.register(userRegistered, course);
                    student.setUser(userRegistered);
                }
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }
}