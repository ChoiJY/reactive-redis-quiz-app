package io.github.choijy.quiz.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.github.choijy.quiz.webapp.properties.RedisProperties;

@SpringBootApplication
@EnableConfigurationProperties(RedisProperties.class)
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
