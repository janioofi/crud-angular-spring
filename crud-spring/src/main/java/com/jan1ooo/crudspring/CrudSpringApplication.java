package com.jan1ooo.crudspring;

import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.model.Lesson;
import com.jan1ooo.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

			for(int i = 0; i < 20; i++){
				Course c = new Course();
				c.setName("Angular " + i);
				c.setCategory(Category.FRONTEND);
				c.setHours(80);

				Lesson l = new Lesson();
				l.setName("Introdução");
				l.setYoutubeUrl("pTIhNB_ulM8");
				l.setCourse(c);

				Lesson l2 = new Lesson();
				l2.setName("Teste 2");
				l2.setYoutubeUrl("pTIhNB_ulM8");
				l2.setCourse(c);
				c.getLessons().add(l);
				c.getLessons().add(l2);

				courseRepository.save(c);
			}
		};
	}

}
