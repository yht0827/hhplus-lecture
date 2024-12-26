package com.example.hhpluslecture.enrollCourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhpluslecture.enrollCourse.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
