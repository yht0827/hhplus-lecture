package com.example.hhpluslecture;

import java.time.LocalDateTime;
import java.util.List;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.entity.Lecture;
import com.example.hhpluslecture.enrollCourse.entity.Student;
import com.example.hhpluslecture.enrollCourse.entity.Teacher;

public class EnrollCourseTestData {

	public static List<EnrollAvailableResponse> getEnrollAvailResponseList() {

		EnrollAvailableResponse response1 = EnrollAvailableResponse.builder()
			.lectureId(1L)
			.lectureTitle("스프링 부트")
			.lectureCode("00001")
			.lectureTime("09:00-10:00")
			.teacherId(1L)
			.teacherName("김영한")
			.grade(3)
			.enrollCount(20)
			.build();

		EnrollAvailableResponse response2 = EnrollAvailableResponse.builder()
			.lectureId(2L)
			.lectureTitle("JPA")
			.lectureCode("00002")
			.lectureTime("11:00-12:00")
			.teacherId(2L)
			.teacherName("토비")
			.grade(2)
			.enrollCount(10)
			.build();

		EnrollAvailableResponse response3 = EnrollAvailableResponse.builder()
			.lectureId(3L)
			.lectureTitle("클린 코드")
			.lectureCode("00003")
			.lectureTime("22:00-23:00")
			.teacherId(3L)
			.teacherName("허재")
			.grade(3)
			.enrollCount(15)
			.build();

		return List.of(response1, response2, response3);
	}

	public static List<EnrollCompleteResponse> getEnrollCompleteResponseList() {

		EnrollCompleteResponse completeResponse1 = EnrollCompleteResponse.builder()
			.enrollCourseId(1L)
			.lectureId(1L)
			.lectureTitle("스프링 부트")
			.lectureCode("00001")
			.lectureTime("09:00-10:00")
			.lectureDate("20241228")
			.teacherId(1L)
			.teacherName("김영한")
			.grade(3)
			.enrollCount(20)
			.build();

		EnrollCompleteResponse completeResponse2 = EnrollCompleteResponse.builder()
			.enrollCourseId(2L)
			.lectureId(2L)
			.lectureTitle("JPA")
			.lectureCode("00002")
			.lectureTime("11:00-12:00")
			.grade(2)
			.enrollCount(10)
			.lectureDate("20241228")
			.teacherId(2L)
			.teacherName("토비")
			.build();

		EnrollCompleteResponse completeResponse3 = EnrollCompleteResponse.builder()
			.enrollCourseId(3L)
			.lectureId(3L)
			.lectureTitle("클린 코드")
			.lectureCode("00003")
			.lectureTime("22:00-23:00")
			.grade(3)
			.enrollCount(15)
			.lectureDate("20241229")
			.teacherId(3L)
			.teacherName("허재")
			.build();

		return List.of(completeResponse1, completeResponse2, completeResponse3);
	}

	public static List<EnrollCourse> getEnrollCourseList() {

		EnrollCourse enrollCourse1 = EnrollCourse.builder()
			.enrollCourseId(1L)
			.lecture(getLectureList().getFirst())
			.student(getStudentList().getFirst())
			.registerDate(LocalDateTime.now())
			.build();

		EnrollCourse enrollCourse2 = EnrollCourse.builder()
			.enrollCourseId(2L)
			.lecture(getLectureList().get(1))
			.student(getStudentList().get(1))
			.registerDate(LocalDateTime.now())
			.build();

		EnrollCourse enrollCourse3 = EnrollCourse.builder()
			.enrollCourseId(3L)
			.lecture(getLectureList().get(2))
			.student(getStudentList().get(2))
			.registerDate(LocalDateTime.now())
			.build();

		return List.of(enrollCourse1, enrollCourse2, enrollCourse3);
	}

	public static List<Lecture> getLectureList() {
		Lecture lecture1 = Lecture.builder()
			.lectureId(1L)
			.lectureTitle("스프링 부트")
			.lectureCode("00001")
			.lectureTime("09:00-10:00")
			.grade(3)
			.teacher(getTeacherList().getFirst())
			.enrollCount(20)
			.build();

		Lecture lecture2 = Lecture.builder()
			.lectureId(2L)
			.lectureTitle("JPA")
			.lectureCode("00002")
			.lectureTime("11:00-12:00")
			.grade(2)
			.teacher(getTeacherList().get(1))
			.enrollCount(10)
			.build();

		Lecture lecture3 = Lecture.builder()
			.lectureId(3L)
			.lectureTitle("클린 코드")
			.lectureCode("00003")
			.lectureTime("22:00-23:00")
			.grade(3)
			.teacher(getTeacherList().get(2))
			.enrollCount(15)
			.build();

		return List.of(lecture1, lecture2, lecture3);
	}

	public static List<Teacher> getTeacherList() {
		Teacher teacher1 = Teacher.builder()
			.teacherId(1L)
			.teacherName("김영한")
			.build();

		Teacher teacher2 = Teacher.builder()
			.teacherId(2L)
			.teacherName("토비")
			.build();

		Teacher teacher3 = Teacher.builder()
			.teacherId(3L)
			.teacherName("허재")
			.build();

		return List.of(teacher1, teacher2, teacher3);
	}

	public static List<Student> getStudentList() {
		Student student1 = Student.builder()
			.studentId(1L)
			.studentName("카리나")
			.build();

		Student student2 = Student.builder()
			.studentId(2L)
			.studentName("윈터")
			.build();

		Student student3 = Student.builder()
			.studentId(3L)
			.studentName("닝닝")
			.build();

		Student student4 = Student.builder()
			.studentId(4L)
			.studentName("지젤")
			.build();

		return List.of(student1, student2, student3, student4);
	}
}
