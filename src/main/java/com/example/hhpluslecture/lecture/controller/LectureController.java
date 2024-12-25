package com.example.hhpluslecture.lecture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhpluslecture.lecture.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

	private final LectureService lectureService;


}
