# 특강 신청 서비스

# Entity-Relationship Diagram (ERD)

![erd.png](./erd.png)

## student, teacher 엔티티로 나누어서 관리

## lecture 엔티티가 teacher와 1:N 관계

`user_course_enrollment` 엔티티를 통해 user와 course의 다대다 관계를 구현했습니다.

- 한 학생이 여러 특강을 수강하고, 한 특강에 여러 학생이 수강할 수 있는 구조입니다.
- 중복 신청 방지: `(student_id, course_id)` 컬럼을 `unique`로 설정하여 특강 중복 신청을 방지합니다.

## 1-3. course 엔티티

`course` 엔티티는 특강 정보를 담고 있습니다.

- 수강 인원 제한: `max_students` 컬럼을 통해 수강 인원을 제한합니다. 매 특강(ex. 온라인, 오프라인 장소에 따라)마다 인원이 바뀔 수 있기 때문에 DB default 값은 설정하지 않습니다.
    - 날짜는 `yyyy-MM-dd`(e.g. 2024-12-25) 형식이기 때문에 `CHAR(10)`으로 고정시켰습니다.
- 특강은 토요일마다 열립니다. 단, 평일이나 일요일에 특강이 열릴 수도 있기 때문에 관련 constraint는 추가하지 않았습니다. (확장 가능 고려)