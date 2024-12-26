package com.example.hhpluslecture;

import static com.example.hhpluslecture.EnrollCourseTestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

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
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.repository.EnrollCourseRepository;
import com.example.hhpluslecture.enrollCourse.repository.LectureRepository;
import com.example.hhpluslecture.enrollCourse.service.EnrollCourseService;

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
		when(enrollCourseRepository.findByStudentId(studentId))
			.thenReturn(getEnrollCourseList());

		// when
		List<EnrollCompleteResponse> list = enrollCourseService.getEnrollCompleteList(studentId);

		// then
		assertThat(list.size()).isEqualTo(3);
		assertThat(list.getFirst().lectureTitle()).isEqualTo("스프링 부트");
		assertThat(list.getFirst().lectureCode()).isEqualTo("00001");
		assertThat(list.getFirst().teacherName()).isEqualTo("김영한");
		assertThat(list.get(1).lectureTitle()).isEqualTo("JPA");
		assertThat(list.get(1).lectureCode()).isEqualTo("00002");
		assertThat(list.get(1).teacherName()).isEqualTo("토비");
	}

	@Test
	@DisplayName("특강 신청 가능 목록 조회 서비스 테스트")
	void getEnrollAvailableListListTest() {
		// given
		EnrollAvailableRequest enrollAvailableRequest = EnrollAvailableRequest.builder()
			.studentId(1L)
			.date("20241228")
			.build();

		when(lectureRepository.findLecturesNotEnrolledByStudentId(enrollAvailableRequest.studentId(),
			enrollAvailableRequest.date())).thenReturn(getLectureList());

		// when
		List<EnrollAvailableResponse> list = enrollCourseService.getEnrollAvailableList(
			enrollAvailableRequest);

		assertThat(list.size()).isEqualTo(3);
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
	@DisplayName("특강 신청 저장 서비스 테스트")
	void saveEnrollCourseTest() {
		// given
		EnrollCourse enrollCourse = getEnrollCourseList().getFirst();

		EnrollCourseRequest enrollCourseRequest = EnrollCourseRequest.builder()
			.lectureId(1L)
			.studentId(2L)
			.build();

		when(enrollCourseRepository.save(any())).thenReturn(enrollCourse);

		// when
		EnrollCourse newEnrollCourse = enrollCourseService.saveEnrollCourse(enrollCourseRequest);

		// then
		assertThat(newEnrollCourse.getEnrollCourseId()).isEqualTo(1L);
	}

}
