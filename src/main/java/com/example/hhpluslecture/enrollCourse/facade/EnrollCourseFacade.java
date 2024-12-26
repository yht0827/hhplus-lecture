package com.example.hhpluslecture.enrollCourse.facade;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCompleteResponse;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseResponse;
import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;
import com.example.hhpluslecture.enrollCourse.entity.Lecture;
import com.example.hhpluslecture.enrollCourse.service.EnrollCourseService;
import com.example.hhpluslecture.enrollCourse.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnrollCourseFacade {

	private final EnrollCourseService enrollCourseService;
	private final ValidationService validationService;

	@Transactional(readOnly = true)
	public List<EnrollCompleteResponse> getEnrollCompleteList(final Long studentId) {
		validationService.enrollCompleteValidation(studentId);

		return enrollCourseService.getEnrollCompleteList(studentId);
	}

	@Transactional(readOnly = true)
	public List<EnrollAvailableResponse> getEnrollAvailableList(final EnrollAvailableRequest enrollAvailableRequest) {
		validationService.enrollAvailableValidation(enrollAvailableRequest);

		return enrollCourseService.getEnrollAvailableList(enrollAvailableRequest);
	}

	@Transactional
	public EnrollCourseResponse saveEnrollCourse(final EnrollCourseRequest enrollCourseRequest) {
		Lecture lecture = getLectureById(enrollCourseRequest.lectureId());

		validationService.saveEnrollValidation(enrollCourseRequest, lecture.getEnrollCount());

		EnrollCourse newEnrollCourse = enrollCourseService.saveEnrollCourse(enrollCourseRequest);

		lecture.updateEnrollCount();

		return EnrollCourseResponse.of(newEnrollCourse.getEnrollCourseId());
	}
	
	public Lecture getLectureById(final Long lectureId) {
		return enrollCourseService.getLectureById(lectureId);
	}

}
