package io.github.choijy.quiz.webapp.router;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.github.choijy.quiz.webapp.domain.Quiz;
import io.github.choijy.quiz.webapp.handler.QuizHandler;
import lombok.RequiredArgsConstructor;

/**
 * Description : Quiz Router
 *
 * Created by jychoi on 2021/07/22.
 */
@Component
@RequiredArgsConstructor
public class QuizRouter {

	private final QuizHandler quizHandler;

	@Bean
	RouterFunction<ServerResponse> routes() {
		return route()
			.GET("/list", (request ->
				ServerResponse.ok()
					.contentType(APPLICATION_JSON)
					.body(quizHandler.getAllQuiz(), Quiz.class)
			))
			.build()
			.filter(exceptionFilter());
	}

	// exception 발생 시, bad request로 일단 mapping
	private HandlerFilterFunction<ServerResponse, ServerResponse> exceptionFilter() {
		return (request, next) -> next.handle(request)
			.onErrorResume(Exception.class, e -> ServerResponse.badRequest().build());
	}
}
