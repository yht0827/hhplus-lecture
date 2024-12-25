package com.example.hhpluslecture.enrollCourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hhpluslecture.enrollCourse.entity.EnrollCourse;

public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, Long> {

	@Query("SELECT ec FROM EnrollCourse ec INNER JOIN ec.student s WHERE s.studentId = :studentId")
	List<EnrollCourse> findByStudentId(final Long studentId);
}