package com.example.hhpluslecture.enrollCourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhpluslecture.enrollCourse.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
