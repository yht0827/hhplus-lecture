package com.example.hhpluslecture.lecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hhpluslecture.lecture.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

	@Query("SELECT l FROM Lecture l LEFT JOIN EnrollCourse ec ON l.lectureId = ec.lecture.lectureId AND " +
		"ec.student.studentId = :studentId WHERE l.lectureDate = :lectureDate AND ec.enrollCourseId IS NULL")
	List<Lecture> findLecturesNotEnrolledByStudentId(final Long studentId, final String lectureDate);
}
