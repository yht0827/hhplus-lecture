package com.example.hhpluslecture.enrollCourse.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lecture")
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lecture_id")
	private Long lectureId;

	@Column(name = "lecture_title")
	private String lectureTitle;

	@Column(name = "lecture_date")
	private String lectureDate;

	@Column(name = "lecture_time")
	private String lectureTime;

	@Column(name = "lecture_code")
	private String lectureCode;

	@Column
	private Integer grade;

	@Column(name = "enroll_count")
	private Integer enrollCount;

	@OneToMany(mappedBy = "lecture")
	private List<EnrollCourse> enrollCourses;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@Builder
	public Lecture(Long lectureId, String lectureTitle, String lectureDate, String lectureTime, String lectureCode,
		Integer grade, Integer enrollCount, List<EnrollCourse> enrollCourses, Teacher teacher) {
		this.lectureId = lectureId;
		this.lectureTitle = lectureTitle;
		this.lectureDate = lectureDate;
		this.lectureTime = lectureTime;
		this.lectureCode = lectureCode;
		this.grade = grade;
		this.enrollCount = enrollCount;
		this.enrollCourses = enrollCourses;
		this.teacher = teacher;
	}

	public void updateEnrollCount() {
		this.enrollCount = this.enrollCount + 1;
	}
}
