package com.jan1ooo.crudspring.repository;

import com.jan1ooo.crudspring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
