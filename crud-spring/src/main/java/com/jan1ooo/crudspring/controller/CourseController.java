package com.jan1ooo.crudspring.controller;

import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    @Autowired
    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course){
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }
}
