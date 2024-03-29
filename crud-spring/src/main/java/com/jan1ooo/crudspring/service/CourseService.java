package com.jan1ooo.crudspring.service;

import com.jan1ooo.crudspring.dto.CourseDTO;
import com.jan1ooo.crudspring.dto.CoursePageDTO;
import com.jan1ooo.crudspring.dto.mapper.CourseMapper;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.exception.RecordNotFoundException;
import com.jan1ooo.crudspring.model.Course;
import com.jan1ooo.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO findAll(@PositiveOrZero int page, @Positive @Max(100) int pageSize){
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, pageSize));
        List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).toList();
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }

    /*public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }*/

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO save(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                    recordFound.setHours(courseDTO.hours());
                    //recordFound.setLessons(course.getLessons());
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteById(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

//        courseRepository.findById(id)
//                .map(recordFound -> {
//                    courseRepository.deleteById(id);
//                    return true;
//                })
//                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
