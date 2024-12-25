package com.example.hhpluslecture.enrollCourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;

public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, Long> {

	List<EnrollCourse> findByStudent_StudentId(Long studentStudentId);
}
