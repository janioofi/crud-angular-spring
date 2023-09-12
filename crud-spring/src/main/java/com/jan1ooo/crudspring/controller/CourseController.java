package com.jan1ooo.crudspring.controller;

import com.jan1ooo.crudspring.dto.CourseDTO;
import com.jan1ooo.crudspring.dto.CoursePageDTO;
import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public CoursePageDTO findAll(@RequestParam(defaultValue = "0") @PositiveOrZero int page, @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize){
        return courseService.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id){
            return courseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO save(@RequestBody @Valid CourseDTO course){
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @NotNull @Valid CourseDTO course){
            return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @NotNull @Positive Long id) {
        courseService.deleteById(id);
    }
}
