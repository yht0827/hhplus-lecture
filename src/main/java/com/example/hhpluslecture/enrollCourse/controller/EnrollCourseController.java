package com.example.hhpluslecture.enrollCourse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseResponse;
import com.example.hhpluslecture.enrollCourse.service.EnrollCourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollCourseController {

	private final EnrollCourseService enrollCourseService;

	// 특강 신청 완료 목록 조회
	@GetMapping("/completeCourses/{studentId}")
	public ResponseEntity<List<EnrollCompleteResponse>> enrollComplete(@PathVariable Long studentId) {

		List<EnrollCompleteResponse> responseList = enrollCourseService.getEnrollCompleteList(studentId);

		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

	// 특강 신청 가능 목록 조회 (날짜별로 조회 가능 lectureDate=20241228 이런식으로 조회 처리)
	@GetMapping("/availableCourses/{studentId}")
	public ResponseEntity<List<EnrollAvailableResponse>> enrollAvailable(
		EnrollAvailableRequest enrollAvailableRequest) {

		// 조회 로직
		List<EnrollAvailableResponse> responseList = getEnrollAvaliableList(enrollAvailableRequest);

		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

	// 특강 신청
	@PostMapping
	public ResponseEntity<EnrollCourseResponse> enrollCourse(@RequestBody EnrollCourseRequest enrollCourseRequest) {
		EnrollCourseResponse enrollCourseResponse = saveCourse(enrollCourseRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(enrollCourseResponse);
	}

	private EnrollCourseResponse saveCourse(EnrollCourseRequest enrollCourseRequest) {
		return EnrollCourseResponse.builder().enrollCourseId(1L).build();
	}

	private List<EnrollAvailableResponse> getEnrollAvaliableList(final EnrollAvailableRequest enrollAvailableRequest) {

		List<EnrollAvailableResponse> responseList = new ArrayList<>();

		EnrollAvailableResponse enrollAvailableResponse1 = EnrollAvailableResponse.builder()
			.lectureId(1L)
			.lectureTitle("디자인 패턴")
			.lectureCode("00003")
			.lectureTime("22:00 - 23:00")
			.teacherId(2L)
			.teacherName("허재")
			.grade(2)
			.enrollCount(15)
			.status("Y")
			.build();

		responseList.add(enrollAvailableResponse1);

		EnrollAvailableResponse enrollAvailableResponse2 = EnrollAvailableResponse.builder()
			.lectureId(2L)
			.lectureTitle("JPA")
			.lectureCode("00002")
			.lectureTime("11:00 - 12:00")
			.teacherId(1L)
			.teacherName("김영한")
			.grade(3)
			.enrollCount(20)
			.status("Y")
			.build();

		responseList.add(enrollAvailableResponse2);

		return responseList;
	}

}
