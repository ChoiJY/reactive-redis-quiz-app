package io.github.choijy.quiz.webapp.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

import io.github.choijy.quiz.webapp.domain.Quiz;
import reactor.core.publisher.Flux;

/**
 * Description : Quiz Handler
 *
 * Created by jychoi on 2021/07/22.
 */
@Service
public class QuizHandler {

	private final ReactiveRedisOperations<Long, Quiz> quizReactiveRedisOperations;
	private final ReactiveRedisConnectionFactory factory;

	public QuizHandler(
		ReactiveRedisOperations<Long, Quiz> quizReactiveRedisOperations,
		ReactiveRedisConnectionFactory factory
	) {
		this.quizReactiveRedisOperations = quizReactiveRedisOperations;
		this.factory = factory;
	}

	void loadData() {
		AtomicLong count = new AtomicLong(0L);
		List<String> data = new ArrayList<>();
		IntStream.range(0, 100000).forEach(i -> data.add(UUID.randomUUID().toString()));
		Flux<String> stringFlux = Flux.fromIterable(data);
		factory.getReactiveConnection()
			.serverCommands()
			.flushAll()
			.thenMany(stringFlux.flatMap(
				uid -> quizReactiveRedisOperations.opsForValue().set(count.getAndAdd(1), new Quiz())))
			.subscribe();
	}

	public Flux<Quiz> getAllQuiz() {
		return quizReactiveRedisOperations.scan()
			.flatMap(each -> quizReactiveRedisOperations.opsForValue()::get);
	}
}
