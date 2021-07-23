package io.github.choijy.quiz.webapp.properties;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : Redis Properties mapping class
 *
 * Created by jychoi on 2021/07/23.
 */
@ConfigurationProperties(prefix = "spring.redis")
@ConstructorBinding
@RequiredArgsConstructor
@Slf4j
public class RedisProperties {
	private final String host;
	private final int port;

	@PostConstruct
	private void printSettings() {
		log.info("redis running on {}:{}", host, port);
	}
}
