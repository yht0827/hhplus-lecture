-- 교수 Insert
INSERT INTO teacher (teacher_id, teacher_name)
VALUES (1, '김영한');

INSERT INTO teacher (teacher_id, teacher_name)
VALUES (2, '토비');

INSERT INTO teacher (teacher_id, teacher_name)
VALUES (3, '허재');

-- 학생 Insert
INSERT INTO student (student_id, student_name)
VALUES (1, '카리나');

INSERT INTO student (student_id, student_name)
VALUES (2, '윈터');

INSERT INTO student (student_id, student_name)
VALUES (3, '닝닝');

INSERT INTO student (student_id, student_name)
VALUES (4, '지젤');

-- 강의 Insert
INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (1, 1, 3, '00001', '20241226', '09:00 - 10:00',
        '스프링 부트', 1);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (2, 0, 2, '00002', '20241228', '10:00 - 11:00',
        'JPA', 2);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (3, 1, 3, '00003', '20241228', '11:00 - 12:00',
        '디자인 패턴', 3);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title,
                     teacher_id)
VALUES (4, 0, 3, '00004', '20241228', '22:00 - 23:00',
        '클린 코드', 4);

-- 수강 신청 Insert
INSERT INTO enroll_course (enroll_course_id, lecture_id, student_id)
VALUES (1, 1, 1);

INSERT INTO enroll_course (enroll_course_id, lecture_id, student_id)
VALUES (2, 3, 1);