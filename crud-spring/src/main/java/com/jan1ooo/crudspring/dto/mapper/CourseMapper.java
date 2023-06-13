package com.jan1ooo.crudspring.dto.mapper;

import com.jan1ooo.crudspring.dto.CourseDTO;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.model.Course;
import org.springframework.stereotype.Component;

import static com.jan1ooo.crudspring.enums.Category.*;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId_course(),
                course.getName(),
                course.getCategory().getValue(),
                course.getHours(),
                course.getLessons());
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
