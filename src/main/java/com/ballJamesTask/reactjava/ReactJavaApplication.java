package com.ballJamesTask.reactjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ReactJavaApplication {

	private static final Logger log = LoggerFactory.getLogger(ReactJavaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReactJavaApplication.class, args);
	}
}
