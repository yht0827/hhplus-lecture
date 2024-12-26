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
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseResponse;
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.facade.EnrollCourseFacade;
import com.example.hhpluslecture.enrollCourse.service.EnrollCourseService;
import com.example.hhpluslecture.enrollCourse.service.ValidationService;

@DisplayName("특강 신청 Facade 테스트")
@ExtendWith(MockitoExtension.class)
public class EnrollCourseFacadeTest {

	private EnrollCourseFacade enrollCourseFacade;
	private EnrollCourseService enrollCourseService;
	private ValidationService validationService;

	@BeforeEach
	void setUp() {
		validationService = Mockito.mock(ValidationService.class);
		enrollCourseService = Mockito.mock(EnrollCourseService.class);
		enrollCourseFacade = new EnrollCourseFacade(enrollCourseService, validationService);
	}

	@Test
	@DisplayName("특강 신청 완료 목록 조회 Facade 테스트")
	void getEnrollCompleteListTest() {
		// given
		Long studentId = 1L;

		List<EnrollCompleteResponse> enrollCompleteList = getEnrollCompleteResponseList();

		doNothing().when(validationService).enrollCompleteValidation(studentId);
		when(enrollCourseService.getEnrollCompleteList(studentId)).thenReturn(enrollCompleteList);

		// when
		List<EnrollCompleteResponse> responseList = enrollCourseFacade.getEnrollCompleteList(studentId);

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
	@DisplayName("특강 신청 가능 목록 조회 Facade 테스트")
	void getEnrollAvailableListTest() {
		// given
		EnrollAvailableRequest enrollAvailableRequest = EnrollAvailableRequest.builder()
			.studentId(1L)
			.date("20241228")
			.build();

		List<EnrollAvailableResponse> enrollAvailableResponseList = getEnrollAvailResponseList();

		doNothing().when(validationService).enrollAvailableValidation(enrollAvailableRequest);
		when(enrollCourseService.getEnrollAvailableList(enrollAvailableRequest)).thenReturn(
			enrollAvailableResponseList);

		// when
		List<EnrollAvailableResponse> list = enrollCourseFacade.getEnrollAvailableList(enrollAvailableRequest);

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
	@DisplayName("특강 신청 저장 Facade 테스트")
	void getEnrollCourseTest() {
		// given
		EnrollCourse enrollCourse = getEnrollCourseList().getFirst();
		Integer enrollCount = 20;

		EnrollCourseRequest enrollCourseRequest = EnrollCourseRequest.builder()
			.lectureId(1L)
			.studentId(2L)
			.build();

		doNothing().when(validationService).saveEnrollValidation(enrollCourseRequest, enrollCount);
		when(enrollCourseService.getLectureById(any())).thenReturn(getLectureList().getFirst());
		when(enrollCourseService.saveEnrollCourse(any())).thenReturn(enrollCourse);

		// when
		EnrollCourseResponse enrollCourseResponse = enrollCourseFacade.saveEnrollCourse(enrollCourseRequest);

		// then
		assertThat(enrollCourseResponse).isNotNull();
		assertThat(enrollCourseResponse.enrollCourseId()).isEqualTo(enrollCourse.getEnrollCourseId());
	}

}
