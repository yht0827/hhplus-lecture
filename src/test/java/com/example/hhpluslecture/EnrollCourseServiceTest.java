package com.example.hhpluslecture;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseResponse;
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.repository.EnrollCourseRepository;
import com.example.hhpluslecture.enrollCourse.service.EnrollCourseService;
import com.example.hhpluslecture.lecture.entity.Lecture;
import com.example.hhpluslecture.lecture.repository.LectureRepository;
import com.example.hhpluslecture.student.entity.Student;
import com.example.hhpluslecture.teacher.entity.Teacher;

@DisplayName("특강 신청 서비스 테스트")
@ExtendWith(MockitoExtension.class)
public class EnrollCourseServiceTest {

	private EnrollCourseService enrollCourseService;
	private EnrollCourseRepository enrollCourseRepository;
	private LectureRepository lectureRepository;

	@BeforeEach
	void setUp() {
		enrollCourseRepository = Mockito.mock(EnrollCourseRepository.class);
		lectureRepository = Mockito.mock(LectureRepository.class);
		enrollCourseService = new EnrollCourseService(enrollCourseRepository, lectureRepository);
	}

	@Test
	@DisplayName("특강 신청 완료 목록 조회 서비스 테스트")
	void getEnrollCompleteListTest() {
		// given
		Long studentId = 1L;
		List<EnrollCourse> enrollCourseList = getEnrollCourseList();

		when(enrollCourseRepository.findByStudentId(studentId)).thenReturn(enrollCourseList);

		// when
		List<EnrollCompleteResponse> responseList = enrollCourseService.getEnrollCompleteList(studentId);

		// then
		assertThat(responseList).hasSize(3);
		assertThat(responseList.getFirst().lectureTitle()).isEqualTo("스프링 부트");
		assertThat(responseList.getFirst().lectureCode()).isEqualTo("00001");
		assertThat(responseList.getFirst().teacherName()).isEqualTo("김영한");
		assertThat(responseList.get(1).lectureTitle()).isEqualTo("JPA");
		assertThat(responseList.get(1).lectureCode()).isEqualTo("00002");
		assertThat(responseList.get(1).teacherName()).isEqualTo("토비");
	}

	@Test
	@DisplayName("특강 신청 가능 목록 조회 서비스 테스트")
	void getEnrollAvailableListTest() {
		// given
		EnrollAvailableRequest enrollAvailableRequest = EnrollAvailableRequest.builder()
			.studentId(1L)
			.date("20241228")
			.build();

		when(lectureRepository.findLecturesNotEnrolledByStudentId(any(), any())).thenReturn(getLectureList());

		// when
		List<EnrollAvailableResponse> list = enrollCourseService.getEnrollAvailableList(enrollAvailableRequest);

		// then
		assertThat(list).hasSize(3);
		assertThat(list.getFirst().lectureTitle()).isEqualTo("스프링 부트");
		assertThat(list.getFirst().lectureCode()).isEqualTo("00001");
		assertThat(list.getFirst().teacherName()).isEqualTo("김영한");
		assertThat(list.get(1).lectureTitle()).isEqualTo("JPA");
		assertThat(list.get(1).lectureCode()).isEqualTo("00002");
		assertThat(list.get(1).teacherName()).isEqualTo("토비");
		assertThat(list.get(2).lectureTitle()).isEqualTo("클린 코드");
		assertThat(list.get(2).lectureCode()).isEqualTo("00003");
		assertThat(list.get(2).teacherName()).isEqualTo("허재");
	}

	@Test
	@DisplayName("특강 신청 서비스 테스트")
	void getEnrollCourseTest() {
		// given
		EnrollCourse enrollCourse = getEnrollCourseList().getFirst();

		EnrollCourseRequest enrollCourseRequest = EnrollCourseRequest.builder()
			.lectureId(1L)
			.studentId(2L)
			.build();

		when(lectureRepository.findById(any())).thenReturn(Optional.ofNullable(getLectureList().getFirst()));
		when(enrollCourseRepository.save(any())).thenReturn(enrollCourse);

		// when
		EnrollCourseResponse enrollCourseResponse = enrollCourseService.saveEnrollCourse(enrollCourseRequest);

		// then
		assertThat(enrollCourseResponse).isNotNull();
		assertThat(enrollCourseResponse.enrollCourseId()).isEqualTo(enrollCourse.getEnrollCourseId());
	}

	List<EnrollCourse> getEnrollCourseList() {

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

	List<Lecture> getLectureList() {
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

	List<Teacher> getTeacherList() {
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

	List<Student> getStudentList() {
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
