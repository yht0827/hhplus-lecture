package com.example.hhpluslecture.enrollCourse.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.repository.EnrollCourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollCourseService {

	private final EnrollCourseRepository enrollCourseRepository;

	@Transactional(readOnly = true)
	public List<EnrollCompleteResponse> getEnrollCompleteList(final Long studentId) {
		return enrollCourseRepository.findByStudent_StudentId(studentId).stream()
			.map(EnrollCompleteResponse::of).toList();
	}

	@Transactional(readOnly = true)
	public List<EnrollAvailableResponse> getEnrollAvailableList(final EnrollAvailableRequest enrollAvailableRequest) {
		return null;
	}

}
