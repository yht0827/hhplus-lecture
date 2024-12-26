package com.example.hhpluslecture.enrollCourse.dto;

import lombok.Builder;

@Builder
public record EnrollCourseResponse(Long enrollCourseId) {

	public static EnrollCourseResponse of(Long enrollCourseId) {
		return EnrollCourseResponse.builder()
			.enrollCourseId(enrollCourseId)
			.build();
	}
}
