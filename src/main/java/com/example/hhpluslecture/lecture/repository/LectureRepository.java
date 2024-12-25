package com.example.hhpluslecture.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhpluslecture.lecture.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
