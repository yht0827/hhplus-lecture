package com.example.hhpluslecture.enrollCourse.entity;

import java.time.LocalDateTime;

import com.example.hhpluslecture.lecture.entity.Lecture;
import com.example.hhpluslecture.student.entity.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "enroll_course")
public class EnrollCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enroll_course_id")
	private Long enrollCourseId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@Column(name = "register_date")
	private LocalDateTime registerDate;

	@Builder
	public EnrollCourse(Long enrollCourseId, Lecture lecture, Student student) {
		this.enrollCourseId = enrollCourseId;
		this.lecture = lecture;
		this.student = student;
		this.registerDate = LocalDateTime.now();
	}
}
