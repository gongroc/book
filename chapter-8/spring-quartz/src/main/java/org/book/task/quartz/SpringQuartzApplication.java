package org.book.task.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("quartz.xml")
@SpringBootApplication
public class SpringQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringQuartzApplication.class, args);
	}
}
