package com.example.hhpluslecture.enrollCourse.service;

import static com.example.hhpluslecture.common.ErrorMessage.*;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.hhpluslecture.common.CustomException;
import com.example.hhpluslecture.enrollCourse.dto.EnrollAvailableRequest;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;

@Component
public class ValidationService {

	private static final Integer MAX_ENROLL_COUNT = 30;

	public void enrollCompleteValidation(final Long studentId) {
		inputLongCheckValidation(studentId);
	}

	public void enrollAvailableValidation(final EnrollAvailableRequest enrollAvailableRequest) {
		inputLongCheckValidation(enrollAvailableRequest.studentId());
		inputStringCheckValidation(enrollAvailableRequest.date());
		lectureDatePatternValidation(enrollAvailableRequest.date());
	}

	public void saveEnrollValidation(final EnrollCourseRequest enrollCourseRequest, final Integer enrollCount) {
		enrollMaxValidation(enrollCount);
		inputLongCheckValidation(enrollCourseRequest.studentId());
		inputLongCheckValidation(enrollCourseRequest.lectureId());
	}

	public void enrollMaxValidation(final Integer enrollCount) {
		if (enrollCount >= MAX_ENROLL_COUNT)
			throw new CustomException(CANT_ENROLL_LECTURE);
	}

	public void inputLongCheckValidation(final Long id) {
		if (id == null)
			throw new CustomException(NO_INPUT_VALUE);
	}

	public void inputStringCheckValidation(final String date) {
		if (date == null)
			throw new CustomException(NO_INPUT_VALUE);
	}

	public void lectureDatePatternValidation(final String date) {
		if (!Pattern.matches("[0-9]{8}", date)) {
			throw new CustomException(LECTURE_DATE_FORM_ERROR);
		}
	}

}
