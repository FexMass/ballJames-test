package com.ballJamesTask.reactjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Spring Boot starter Class
 * @author Mass
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ReactJavaApplication {
	public static void main(String[] args) { SpringApplication.run(ReactJavaApplication.class, args); }
}
