package com.example.hhpluslecture.enrollCourse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.entity.Lecture;
import com.example.hhpluslecture.enrollCourse.repository.EnrollCourseRepository;
import com.example.hhpluslecture.enrollCourse.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollCourseService {

	private final EnrollCourseRepository enrollCourseRepository;
	private final LectureRepository lectureRepository;

	public List<EnrollCompleteResponse> getEnrollCompleteList(final Long studentId) {
		return enrollCourseRepository.findByStudentId(studentId).stream().map(EnrollCompleteResponse::of).toList();
	}

	public List<EnrollAvailableResponse> getEnrollAvailableList(final EnrollAvailableRequest enrollAvailableRequest) {
		return lectureRepository.findLecturesNotEnrolledByStudentId(enrollAvailableRequest.studentId(),
			enrollAvailableRequest.date()).stream().map(EnrollAvailableResponse::of).toList();
	}

	public EnrollCourse saveEnrollCourse(final EnrollCourseRequest enrollCourseRequest) {
		return enrollCourseRepository.save(enrollCourseRequest.toEntity());
	}

	public Lecture getLectureById(final Long lectureId) {
		return lectureRepository.findByLectureId(lectureId);
	}
}
