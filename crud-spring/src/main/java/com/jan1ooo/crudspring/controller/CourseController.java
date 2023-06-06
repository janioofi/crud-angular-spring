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

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseRepository.findById(id)
                .map(recordFound-> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course){
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
            return courseRepository.findById(id)
                    .map(recordFound -> {
                        recordFound.setName(course.getName());
                        recordFound.setCategory(course.getCategory());
                        recordFound.setHours(course.getHours());
                        Course update = courseRepository.save(recordFound);
                        return ResponseEntity.ok().body(update);
            }).orElse(ResponseEntity.notFound().build());

    }
}
