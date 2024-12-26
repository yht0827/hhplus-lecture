package com.example.hhpluslecture;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hhpluslecture.common.CustomException;
import com.example.hhpluslecture.common.ErrorMessage;
import com.example.hhpluslecture.enrollCourse.dto.EnrollCourseRequest;
import com.example.hhpluslecture.enrollCourse.entity.Lecture;
import com.example.hhpluslecture.enrollCourse.facade.EnrollCourseFacade;
import com.example.hhpluslecture.enrollCourse.repository.LectureRepository;

@DisplayName("특강 신청 동시성 테스트")
@SpringBootTest
public class EnrollCourseConcurrentTest {

	@Autowired
	private EnrollCourseFacade enrollCourseFacade;

	@Autowired
	private LectureRepository lectureRepository;

	@Test
	@DisplayName("동시에 40명 수강신청 해서 30명 신청 테스트")
	void enrollCourseConcurrentTest() throws InterruptedException {
		int thread_count = 40;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(thread_count);
		Long lectureId = 4L;

		for (int i = 1; i <= thread_count; i++) {
			long studentId = i;
			executorService.submit(() -> {
				try {
					EnrollCourseRequest enrollCourseRequest = EnrollCourseRequest.builder()
						.lectureId(lectureId)
						.studentId(studentId)
						.build();

					enrollCourseFacade.saveEnrollCourse(enrollCourseRequest);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		executorService.shutdown();

		Lecture lecture = lectureRepository.findById(lectureId)
			.orElseThrow(() -> new CustomException(ErrorMessage.NO_COURSE));
		assertThat(lecture.getEnrollCount()).isEqualTo(30);
	}

}
