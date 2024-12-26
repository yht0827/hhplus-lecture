package com.example.hhpluslecture;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hhpluslecture.common.CustomException;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.service.ValidationService;

@DisplayName("특강 신청 유효성 검사 서비스 테스트")
@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

	private ValidationService validationService;

	@BeforeEach
	void setUp() {
		validationService = new ValidationService();
	}

	@Test
	@DisplayName("특강 신청 완료 목록 ID값 Null 테스트")
	public void enrollCompleteValidationTest() {
		// given
		Long studentId = null;

		// when
		CustomException customException = assertThrows(CustomException.class,
			() -> validationService.enrollCompleteValidation(studentId));

		// then
		assertThat("입력 값이 없습니다.")
			.isEqualTo(customException.getMessage());
	}

	@Test
	@DisplayName("특강 가능 목록 날짜 테스트")
	public void enrollAvailableValidationTest() {
		// given
		EnrollAvailableRequest enrollAvailableRequest = EnrollAvailableRequest.builder()
			.studentId(1L)
			.date("2024111")
			.build();

		// when
		CustomException customException = assertThrows(CustomException.class,
			() -> validationService.enrollAvailableValidation(enrollAvailableRequest));

		// then
		assertThat("YYYYMMDD 형태로 입력해주세요.")
			.isEqualTo(customException.getMessage());
	}

	@Test
	@DisplayName("특강 최대 인원 신청 테스트")
	public void enrollMaxCountValidationTest() {
		// given
		Integer enrollCount = 31;

		// when
		CustomException customException = assertThrows(CustomException.class,
			() -> validationService.enrollMaxValidation(enrollCount));

		// then
		assertThat("30명 정원 초과 하였습니다.")
			.isEqualTo(customException.getMessage());
	}

}
