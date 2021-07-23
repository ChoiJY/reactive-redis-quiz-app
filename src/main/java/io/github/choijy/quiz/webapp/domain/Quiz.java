package io.github.choijy.quiz.webapp.domain;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.annotation.Id;

/**
 * Description : Quiz domain class
 *
 * Created by jychoi on 2021/07/22.
 */
public class Quiz implements Serializable {

	// 구분값
	@Id
	private Long id;
	// 퀴즈 유형
	private Category type;
	// 퀴즈 문제
	private String quiz;
	// 정답 배점
	private Long point;
	// 보기
	private Set<Candidate> candidate;
}
