package com.example.hhpluslecture.enrollCourse.dto;

import com.example.hhpluslecture.lecture.entity.Lecture;

import lombok.Builder;

@Builder
public record EnrollAvailableResponse(Long lectureId, String lectureTitle, String lectureCode, String lectureTime,
									  Long teacherId, String teacherName, Integer grade, Integer enrollCount) {

	public static EnrollAvailableResponse of(Lecture lecture) {
		return EnrollAvailableResponse.builder()
			.lectureId(lecture.getLectureId())
			.lectureTitle(lecture.getLectureTitle())
			.lectureCode(lecture.getLectureCode())
			.lectureTime(lecture.getLectureTime())
			.teacherId(lecture.getTeacher().getTeacherId())
			.teacherName(lecture.getTeacher().getTeacherName())
			.grade(lecture.getGrade())
			.enrollCount(lecture.getEnrollCount())
			.build();
	}

}
