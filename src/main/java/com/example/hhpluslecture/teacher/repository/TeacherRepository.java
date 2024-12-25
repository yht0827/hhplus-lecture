package com.example.hhpluslecture.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhpluslecture.teacher.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
