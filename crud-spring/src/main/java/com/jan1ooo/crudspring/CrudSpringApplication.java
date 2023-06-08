package com.jan1ooo.crudspring;

import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();
			Course c = new Course();
			Course c1 = new Course();
			c.setName("Angular");
			c.setCategory("front-end");
			c.setHours(80);

			c1.setName("React");
			c1.setCategory("front-end");
			c1.setHours(100);
			courseRepository.save(c);
			courseRepository.save(c1);
		};
	}

}
