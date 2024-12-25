package com.example.hhpluslecture.enrollCourse.dto;

import lombok.Builder;

@Builder
public record EnrollAvailableResponse(Long lectureId, String lectureTitle, String lectureCode, String lectureTime,
									  Long teacherId, String teacherName, Integer grade, Integer enrollCount,
									  String status) {
}
