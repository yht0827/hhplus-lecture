package com.example.hhpluslecture.enrollCourse.dto;

import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;

import lombok.Builder;

@Builder
public record EnrollCompleteResponse(Long enrollCourseId, Long lectureId, String lectureTitle,
									 String lectureCode, String lectureTime, String lectureDate, String teacherName,
									 Integer grade, Integer enrollCount, Long teacherId) {

	public static EnrollCompleteResponse of(EnrollCourse enrollCourse) {
		return EnrollCompleteResponse.builder()
			.enrollCourseId(enrollCourse.getEnrollCourseId())
			.lectureId(enrollCourse.getLecture().getLectureId())
			.lectureTitle(enrollCourse.getLecture().getLectureTitle())
			.lectureCode(enrollCourse.getLecture().getLectureCode())
			.lectureTime(enrollCourse.getLecture().getLectureTime())
			.lectureDate(enrollCourse.getLecture().getLectureDate())
			.teacherName(enrollCourse.getLecture().getTeacher().getTeacherName())
			.grade(enrollCourse.getLecture().getGrade())
			.teacherId(enrollCourse.getLecture().getTeacher().getTeacherId())
			.enrollCount(enrollCourse.getLecture().getEnrollCount())
			.build();
	}

}
