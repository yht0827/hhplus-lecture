package com.example.hhpluslecture;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseResponse;

@DisplayName("수강신청 E2E 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:data.sql")
public class EnrollCourseControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("특강 신청 완료 목록 조회 E2E 테스트")
	public void enrollCompleteEtoETest() {
		String url = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/enrollment/completeCourses/1")
			.toUriString();

		ResponseEntity<List<EnrollCompleteResponse>> response = restTemplate
			.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
			});

		// 상태 코드 확인
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());

		// 응답 데이터 확인
		List<EnrollCompleteResponse> enrollCompleteResponseList = response.getBody();
		assertThat(enrollCompleteResponseList).isNotNull();
		assertThat(enrollCompleteResponseList.size()).isEqualTo(2);

		// 데이터 검증
		EnrollCompleteResponse response1 = enrollCompleteResponseList.getFirst();
		assertThat(response1.lectureId()).isEqualTo(1L);
		assertThat(response1.lectureTitle()).isEqualTo("스프링 부트");
		assertThat(response1.teacherName()).isEqualTo("김영한");
		assertThat(response1.lectureDate()).isEqualTo("20241226");

		EnrollCompleteResponse response2 = enrollCompleteResponseList.get(1);
		assertThat(response2.lectureId()).isEqualTo(3L);
		assertThat(response2.lectureTitle()).isEqualTo("디자인 패턴");
		assertThat(response2.teacherName()).isEqualTo("허재");
		assertThat(response2.lectureDate()).isEqualTo("20241228");
	}

	@Test
	@DisplayName("특강 신청 가능 목록 조회 E2E 테스트")
	public void enrollAvailableEtoETest() {
		String url = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/enrollment/availableCourses/1")
			.queryParam("date", "20241228")
			.toUriString();

		ResponseEntity<List<EnrollAvailableResponse>> response = restTemplate
			.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
			});

		// 상태 코드 확인
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());

		// 응답 데이터 확인
		List<EnrollAvailableResponse> enrollAvailableResponseList = response.getBody();
		assertThat(enrollAvailableResponseList).isNotNull();
		assertThat(enrollAvailableResponseList.size()).isEqualTo(2);

		// 데이터 검증
		EnrollAvailableResponse response1 = enrollAvailableResponseList.getFirst();
		assertThat(response1.lectureId()).isEqualTo(1L);
		assertThat(response1.lectureTitle()).isEqualTo("디자인 패턴");
		assertThat(response1.teacherName()).isEqualTo("허재");
		assertThat(response1.grade()).isEqualTo(2);
		assertThat(response1.status()).isEqualTo("Y");

		EnrollAvailableResponse response2 = enrollAvailableResponseList.get(1);
		assertThat(response2.lectureId()).isEqualTo(2L);
		assertThat(response2.lectureTitle()).isEqualTo("JPA");
		assertThat(response2.teacherName()).isEqualTo("김영한");
		assertThat(response2.grade()).isEqualTo(3);
		assertThat(response2.enrollCount()).isEqualTo(20);
		assertThat(response2.status()).isEqualTo("Y");
	}

	@Test
	@DisplayName("특강 신청 E2E 테스트")
	public void enrollCourseEtoETest() {
		String url = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/enrollment")
			.toUriString();

		EnrollCourseRequest enrollCourseRequest = EnrollCourseRequest.builder()
			.lectureId(1L)
			.studentId(1L)
			.build();

		ResponseEntity<EnrollCourseResponse> response = restTemplate
			.postForEntity(url, enrollCourseRequest, EnrollCourseResponse.class);

		// 상태 코드 확인
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());

		// 응답 데이터 확인
		EnrollCourseResponse enrollCourseResponse = response.getBody();
		assertThat(enrollCourseResponse).isNotNull();
		assertThat(enrollCourseResponse.enrollCourseId()).isEqualTo(1L);
	}

}
