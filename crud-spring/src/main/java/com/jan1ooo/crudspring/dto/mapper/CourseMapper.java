package com.jan1ooo.crudspring.dto.mapper;

import com.jan1ooo.crudspring.dto.CourseDTO;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), "FRONTEND", course.getHours());
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(Category.FRONTEND);
        course.setHours(courseDTO.hours());
        return course;
    }

}
