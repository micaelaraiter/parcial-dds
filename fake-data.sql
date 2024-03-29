use parcial;

-- fake data --

-- USAR ESTE SCRIPT PARA INGRESAR INFORMACION BASICA A LA DB, PARA QUE LAS PRUEBAS SEAN MAS SENCILLAS --

-- la escuela se crea sola en el codigo --

INSERT INTO User (user_id,firstName,lastName,email,password) -- a proposito va el user_id, para la fake data nomas
VALUES (1, 'Micaela', 'Raiter', 'mraiter@frba.utn.edu.ar', 'asd123asd'),
 (2, 'Lucila', 'Melamed', 'lmelamed@frba.utn.edu.ar', 'asd123asd'),
 (3, 'Luciano', 'Straccia', 'lstraccia@frba.utn.edu.ar', 'asd123asd');

INSERT INTO `parcial`.`teacher` (`teacher_id`, `user_id`)
VALUES (1, 3);

INSERT INTO `parcial`.`course` (`course_id`, `name`, `code`, `teacher_id`)
VALUES (1,'K3002','K3002',1);

INSERT INTO `parcial`.`student`(`student_id`,`user_id`,`course_id`)
VALUES(1,1,1),
(2,2,1);

INSERT INTO `parcial`.`tp`(`tp_id`,`title`,`description`, `tp_father_id`)
VALUES (1,'TP 1','El primer TP', NULL);

INSERT INTO `parcial`.`homework`(`homework_id`,`title`,`description`,`dued_date`,`order`,`tp_id`)
VALUES(1,'Tarea 1.1','Esta es la primera tarea del TP 1',NOW() + INTERVAL 7 DAY,1,1),
(2,'Tarea 1.2','Esta es la segunda tarea del TP 1',NOW() + INTERVAL 14 DAY,2,1),
(3,'Tarea 2','Esta es una tarea simple',NOW() + INTERVAL 3 DAY,1,NULL);

INSERT INTO `parcial`.`homeworkstudent`(`homework_student_id`,`student_id`,`homework_id`,`state_id`,`grade`)
VALUES(1,1,1,2,NULL),
(2,2,1,1,NULL),
(3,2,3,3,10),
(4,1,2,1,NULL);
