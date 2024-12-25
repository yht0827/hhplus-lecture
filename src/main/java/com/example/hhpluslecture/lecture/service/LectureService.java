package com.example.hhpluslecture.lecture.service;

import org.springframework.stereotype.Service;

import com.example.hhpluslecture.enrollCourse.repository.EnrollCourseRepository;
import com.example.hhpluslecture.lecture.repository.LectureRepository;
import com.example.hhpluslecture.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureService {

	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	private final EnrollCourseRepository enrollCourseRepository;



}
