-- 교수 Insert
INSERT INTO teacher (teacher_id, teacher_name)
VALUES (1, '김영한');

INSERT INTO teacher (teacher_id, teacher_name)
VALUES (2, '토비');

INSERT INTO teacher (teacher_id, teacher_name)
VALUES (3, '허재');

-- 학생 Insert
INSERT INTO student (student_name)
VALUES ('카리나'),
       ('윈터'),
       ('닝닝'),
       ('지젤'),
       ('김길동'),
       ('이길동'),
       ('신길동'),
       ('고길동'),
       ('홍길동'),
       ('허길동'),
       ('양길동'),
       ('호길동'),
       ('강길동'),
       ('서길동'),
       ('노길동'),
       ('암길동'),
       ('도길동'),
       ('이길동'),
       ('남길동'),
       ('하길동'),
       ('페이커1'),
       ('페이커2'),
       ('페이커3'),
       ('페이커4'),
       ('페이커5'),
       ('페이커6'),
       ('페이커7'),
       ('페이커8'),
       ('페이커9'),
       ('페이커10'),
       ('페이커11'),
       ('페이커12'),
       ('페이커13'),
       ('페이커14'),
       ('페이커15'),
       ('페이커16'),
       ('페이커17'),
       ('페이커18'),
       ('페이커19'),
       ('페이커20');

-- 강의 Insert
INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (1, 1, 3, '00001', '20241226', '09:00-10:00',
        '스프링 부트', 1);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (2, 0, 2, '00002', '20241228', '10:00-11:00',
        'JPA', 2);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (3, 1, 3, '00003', '20241228', '11:00-12:00',
        '디자인 패턴', 3);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (4, 0, 3, '00004', '20241228', '22:00-23:00',
        '클린 코드', 3);

-- 수강 신청 Insert
INSERT INTO enroll_course (enroll_course_id, register_date, lecture_id, student_id)
VALUES (1, NOW(6), 1, 1);

INSERT INTO enroll_course (enroll_course_id, register_date, lecture_id, student_id)
VALUES (2, NOW(6), 3, 1);