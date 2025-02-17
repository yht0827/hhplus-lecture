package com.example.hhpluslecture.enrollCourse.controller;

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
import com.example.hhpluslecture.enrollCourse.facade.EnrollCourseFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollCourseController {

	private final EnrollCourseFacade enrollCourseFacade;

	// 특강 신청 완료 목록 조회
	@GetMapping("/completeCourses/{studentId}")
	public ResponseEntity<List<EnrollCompleteResponse>> enrollComplete(@PathVariable Long studentId) {
		List<EnrollCompleteResponse> responseList = enrollCourseFacade.getEnrollCompleteList(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

	// 특강 신청 가능 목록 조회
	@GetMapping("/availableCourses/{studentId}")
	public ResponseEntity<List<EnrollAvailableResponse>> enrollAvailable(
		EnrollAvailableRequest enrollAvailableRequest) {
		List<EnrollAvailableResponse> responseList = enrollCourseFacade.getEnrollAvailableList(enrollAvailableRequest);
		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

	// 특강 신청
	@PostMapping
	public ResponseEntity<EnrollCourseResponse> enrollCourse(@RequestBody EnrollCourseRequest enrollCourseRequest) {
		EnrollCourseResponse enrollCourseResponse = enrollCourseFacade.saveEnrollCourse(enrollCourseRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(enrollCourseResponse);
	}
}
