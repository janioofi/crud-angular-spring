package com.jan1ooo.crudspring.service;

import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Optional<Course> findById(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id);
    }

    public Course save(@Valid Course course){
        return courseRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id,@Valid Course course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    recordFound.setHours(course.getHours());
                    return courseRepository.save(recordFound);
                });
    }

    public boolean deleteById(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
