package io.github.choijy.quiz.webapp.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import lombok.extern.slf4j.Slf4j;

/**
 * Description : Modelmapper 관련 configuration
 *
 * Created by jychoi on 2021/07/23.
 */
@Slf4j
public class ModelMapperUtils {

	// thread-safe instance
	private static final ModelMapper modelmapper = new ModelMapper();

	private static void init() {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("modelmapper is loaded");
	}

	public static ModelMapper getModelmapper() {
		return modelmapper;
	}
}
