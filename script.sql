CREATE DATABASE parcial;
use parcial;

CREATE TABLE IF NOT EXISTS User
(
    user_id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    priority  TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS School
(
	school_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	direction VARCHAR(255) NOT NULL,
	state_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Course
(
    course_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255),
    code      VARCHAR(255),
    school_id INT NOT NULL,
    foreign key (school_id) references School (school_id)
);

CREATE TABLE IF NOT EXISTS Teacher
(
   	teacher_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	user_id INT NOT NULL,
	course_id INT NOT NULL,
	foreign key (user_id) references User (user_id),
	foreign key (course_id) references Course (course_id)
);

CREATE TABLE IF NOT EXISTS Student
(
    student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	course_id INT NOT NULL,
	foreign key (user_id) references User (user_id),
	foreign key (course_id) references Course (course_id)
);

CREATE TABLE IF NOT EXISTS TP
(
    tp_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    tp_father_id INT,
    foreign key (tp_father_id) references Tp (tp_id)
);

CREATE TABLE IF NOT EXISTS State
(
    state_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Homework
(
    homework_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),   
    dued_date DATE,
    `order` INT,
    tp_id INT,
    foreign key (tp_id) references TP (tp_id)
);

CREATE TABLE IF NOT EXISTS HomeworkStudent
(
 	homework_student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	student_id INT NOT NULL,
	homework_id INT NOT NULL,
	state_id INT NOT NULL,
   	grade INT,
	foreign key (student_id) references Student (student_id),
	foreign key (state_id) references State (state_id),
	foreign key (homework_id) references Homework (homework_id)
);