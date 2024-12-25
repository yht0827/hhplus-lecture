package com.example.hhpluslecture.enrollCourse.dto;

import lombok.Builder;

@Builder
public record EnrollCourseRequest(Long lectureId, Long studentId) {
}
