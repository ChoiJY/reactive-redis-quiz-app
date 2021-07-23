package io.github.choijy.quiz.webapp.domain;

import java.io.Serializable;

import lombok.RequiredArgsConstructor;

/**
 * Description : Quiz 내 선지
 *
 * Created by jychoi on 2021/07/23.
 */
@RequiredArgsConstructor
public class Candidate implements Serializable {

	final String question;
	final boolean isAnswer;
}
