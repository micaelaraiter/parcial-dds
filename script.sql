use parcial;
CREATE TABLE IF NOT EXISTS User
(
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    priority  TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Teacher
(
    teacher_id INT AUTO_INCREMENT PRIMARY KEY
);

ALTER TABLE Teacher
    ADD
        user_id INT,
    add foreign key (user_id)
        references User (user_id);


CREATE TABLE IF NOT EXISTS Course
(
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255),
    code      VARCHAR(255)
);
ALTER TABLE Course
    ADD
        teacher_id INT,
    add foreign key (teacher_id)
        references Teacher (teacher_id);

CREATE TABLE IF NOT EXISTS Student
(
    student_id INT AUTO_INCREMENT PRIMARY KEY
);

ALTER TABLE Student
    ADD
        user_id INT,
    add foreign key (user_id)
        references User (user_id);

ALTER TABLE Student
    ADD
        course_id INT,
    add foreign key (course_id)
        references Course (course_id);

CREATE TABLE IF NOT EXISTS TP
(
    tp_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    final_grade INT
);

CREATE TABLE IF NOT EXISTS State
(
    state_id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255)
);



CREATE TABLE IF NOT EXISTS Homework
(
    homework_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    date_dued DATE,
    orden INT);

ALTER TABLE Homework
    ADD
        state_id INT,
    add foreign key (state_id)
        references State (state_id);

ALTER TABLE Homework
    ADD
        tp_id INT,
    add foreign key (tp_id)
        references TP (tp_id);


CREATE TABLE IF NOT EXISTS HomeworkStudent
(
    homework_student_id INT AUTO_INCREMENT PRIMARY KEY,
    grade INT
);

ALTER TABLE HomeworkStudent
    ADD
        student_id INT,
    add foreign key (student_id)
        references Student (student_id);
use parcial;
CREATE TABLE IF NOT EXISTS User
(
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    priority  TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Teacher
(
    teacher_id INT AUTO_INCREMENT PRIMARY KEY
);

ALTER TABLE Teacher
    ADD
        user_id INT,
    add foreign key (user_id)
        references User (user_id);


CREATE TABLE IF NOT EXISTS Course
(
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255),
    code      VARCHAR(255)
);
ALTER TABLE Course
    ADD
        teacher_id INT,
    add foreign key (teacher_id)
        references Teacher (teacher_id);

CREATE TABLE IF NOT EXISTS Student
(
    student_id INT AUTO_INCREMENT PRIMARY KEY
);

ALTER TABLE Student
    ADD
        user_id INT,
    add foreign key (user_id)
        references User (user_id);

ALTER TABLE Student
    ADD
        course_id INT,
    add foreign key (course_id)
        references Course (course_id);

CREATE TABLE IF NOT EXISTS TP
(
    tp_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    final_grade INT
);

CREATE TABLE IF NOT EXISTS State
(
    state_id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255)
);



CREATE TABLE IF NOT EXISTS Homework
(
    homework_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    date_dued DATE,
    orden INT);

ALTER TABLE Homework
    ADD
        state_id INT,
    add foreign key (state_id)
        references State (state_id);

ALTER TABLE Homework
    ADD
        tp_id INT,
    add foreign key (tp_id)
        references TP (tp_id);


CREATE TABLE IF NOT EXISTS HomeworkStudent
(
    homework_student_id INT AUTO_INCREMENT PRIMARY KEY,
    grade INT
);

ALTER TABLE HomeworkStudent
    ADD
        student_id INT,
    add foreign key (student_id)
        references Student (student_id);

ALTER TABLE HomeworkStudent
    ADD
        homework_id INT,
    add foreign key (homework_id)
        references Homework (homework_id);

ALTER TABLE HomeworkStudent
    ADD
        homework_id INT,
    add foreign key (homework_id)
        references Homework (homework_id);

