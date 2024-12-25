create table enroll_course
(
    enroll_course_id bigint not null auto_increment,
    register_date    datetime(6),
    lecture_id       bigint,
    student_id       bigint,
    primary key (enroll_course_id)
);

create table lecture
(
    lecture_id    bigint not null auto_increment,
    enroll_count  integer,
    grade         integer,
    lecture_code  varchar(255),
    lecture_date  varchar(255),
    lecture_time  varchar(255),
    lecture_title varchar(255),
    teacher_id    bigint,
    primary key (lecture_id)
);

create table student
(
    student_id   bigint not null auto_increment,
    student_name varchar(255),
    primary key (student_id)
);

create table teacher
(
    teacher_id   bigint not null auto_increment,
    teacher_name varchar(255),
    primary key (teacher_id)
);