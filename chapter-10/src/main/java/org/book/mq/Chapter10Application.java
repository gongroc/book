package org.book.mq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class Chapter10Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter10Application.class, args);
	}
}
