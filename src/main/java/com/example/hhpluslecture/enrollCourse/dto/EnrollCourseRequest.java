package com.example.hhpluslecture.enrollCourse.dto;

import java.time.LocalDateTime;

import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;

import lombok.Builder;

@Builder
public record EnrollCourseRequest(Long lectureId, Long studentId) {

	public EnrollCourse toEntity() {
		return EnrollCourse.builder()
			.studentId(this.studentId)
			.lectureId(this.lectureId())
			.registerDate(LocalDateTime.now())
			.build();
	}
}
