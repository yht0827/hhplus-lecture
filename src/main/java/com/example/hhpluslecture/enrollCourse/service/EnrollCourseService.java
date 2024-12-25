package com.example.hhpluslecture.enrollCourse.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseResponse;
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.repository.EnrollCourseRepository;
import com.example.hhpluslecture.lecture.entity.Lecture;
import com.example.hhpluslecture.lecture.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollCourseService {

	private final EnrollCourseRepository enrollCourseRepository;
	private final LectureRepository lectureRepository;

	@Transactional(readOnly = true)
	public List<EnrollCompleteResponse> getEnrollCompleteList(final Long studentId) {
		return enrollCourseRepository.findByStudentId(studentId).stream().map(EnrollCompleteResponse::of).toList();
	}

	@Transactional(readOnly = true)
	public List<EnrollAvailableResponse> getEnrollAvailableList(final EnrollAvailableRequest enrollAvailableRequest) {
		return lectureRepository.findLecturesNotEnrolledByStudentId(enrollAvailableRequest.studentId(),
			enrollAvailableRequest.date()).stream().map(EnrollAvailableResponse::of).toList();
	}

	@Transactional
	public EnrollCourseResponse saveEnrollCourse(final EnrollCourseRequest enrollCourseRequest) {
		Lecture lecture = lectureRepository.findById(enrollCourseRequest.lectureId())
			.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다."));

		EnrollCourse enrollCourse = EnrollCourse.builder()
			.studentId(enrollCourseRequest.studentId())
			.lectureId(enrollCourseRequest.lectureId())
			.registerDate(LocalDateTime.now())
			.build();

		EnrollCourse newEnrollCourse = enrollCourseRepository.save(enrollCourse);

		lecture.updateEnrollCount();

		return EnrollCourseResponse.builder().enrollCourseId(newEnrollCourse.getEnrollCourseId()).build();
	}

}
