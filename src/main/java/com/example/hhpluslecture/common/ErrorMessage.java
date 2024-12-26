package com.example.hhpluslecture.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

	NO_COURSE(HttpStatus.BAD_REQUEST, "해당 강의가 없습니다."),
	NO_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력 값이 없습니다."),
	LECTURE_DATE_FORM_ERROR(HttpStatus.BAD_REQUEST, "YYYYMMDD 형태로 입력해주세요."),
	CANT_ENROLL_LECTURE(HttpStatus.BAD_REQUEST, "30명 정원 초과 하였습니다."),
	DUPLICATE_ENROLL_LECTURE(HttpStatus.BAD_REQUEST, "이미 신청 하였습니다."),
	DUMMY(null, "");

	private final HttpStatus httpStatus;
	private final String message;
}
