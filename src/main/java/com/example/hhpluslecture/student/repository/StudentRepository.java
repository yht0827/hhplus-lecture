package com.example.hhpluslecture.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhpluslecture.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
