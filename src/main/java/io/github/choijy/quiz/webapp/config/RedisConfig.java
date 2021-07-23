package io.github.choijy.quiz.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.github.choijy.quiz.webapp.domain.Quiz;

/**
 * Description : Redis configuration
 *
 * Created by jychoi on 2021/07/22.
 */
@Configuration
public class RedisConfig {

	@Bean
	public ReactiveRedisOperations<Long, Quiz> quizReactiveRedisOperations(ReactiveRedisConnectionFactory factory) {
		Jackson2JsonRedisSerializer<Quiz> serializer =
			new Jackson2JsonRedisSerializer<>(Quiz.class);

		RedisSerializationContext.RedisSerializationContextBuilder<Long, Quiz> builder =
			RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

		RedisSerializationContext<Long, Quiz> context = builder.value(serializer).build();

		return new ReactiveRedisTemplate<>(factory, context);
	}
}
