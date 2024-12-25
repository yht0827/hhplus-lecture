package com.example.hhpluslecture.teacher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "teacher")
public class Teacher {

	@Id
	@Column(name = "teacher_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;

	@Column(name = "teacher_name")
	private String teacherName;
}
