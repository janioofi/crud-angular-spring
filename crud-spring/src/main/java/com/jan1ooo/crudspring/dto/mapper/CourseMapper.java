package com.jan1ooo.crudspring.dto.mapper;

import com.jan1ooo.crudspring.dto.CourseDTO;
import com.jan1ooo.crudspring.dto.LessonDTO;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.jan1ooo.crudspring.enums.Category.*;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId_lesson(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());
        return new CourseDTO(
                course.getId_course(),
                course.getName(),
                course.getCategory().getValue(),
                course.getHours(),
                lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId_course(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        course.setHours(courseDTO.hours());

       List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
           var lesson = new Lesson();
           lesson.setId_lesson(lessonDTO.id_lesson());
           lesson.setName(lessonDTO.name());
           lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
           lesson.setCourse(course);
           return lesson;
        }).collect(Collectors.toList());
       course.setLessons(lessons);

        return course;
    }

    public Category convertCategoryValue(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "Front-end" -> Category.FRONTEND;
            case "Back-end" -> Category.BACKEND;
            default -> throw new IllegalArgumentException("Categoria invalida: " + value);
        };
    }

}
