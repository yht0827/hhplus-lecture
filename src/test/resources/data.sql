INSERT INTO teacher (teacher_id, teacher_name) VALUES (1, '김영한');

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title, status, teacher_id)
VALUES (1, 0, 3, '00001', '20241226', '09:00 - 10:00',
        '스프링 부트', 'Y', 1);

INSERT INTO student (student_id, student_name) VALUES (1, '김길동');

INSERT INTO enroll_course (enroll_course_id, lecture_id, student_id) VALUES (1, 1, 1);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title, status, teacher_id)
VALUES (2, 0, 2, '00002', '20241228', '10:00 - 11:00',
        'JPA', 'Y', 1);

INSERT INTO teacher (teacher_id, teacher_name) VALUES (2, '허재');

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title, status, teacher_id)
VALUES (3, 1, 3, '00003', '20241228', '11:00 - 12:00',
        '디자인 패턴', 'Y', 2);

INSERT INTO enroll_course (enroll_course_id, lecture_id, student_id) VALUES (2, 3, 1);

INSERT INTO lecture (lecture_id, enroll_count, grade, lecture_code, lecture_date, lecture_time, lecture_title, status, teacher_id)
VALUES (4, 0, 3, '00004', '20241228', '22:00 - 23:00',
        '클린 코드', 'Y', 2);