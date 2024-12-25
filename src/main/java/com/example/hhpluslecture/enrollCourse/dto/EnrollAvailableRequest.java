package com.example.hhpluslecture.enrollCourse.dto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public record EnrollAvailableRequest(@PathVariable Long studentId, @RequestParam String date) {
}
