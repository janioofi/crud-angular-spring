package com.jan1ooo.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Length(min = 3, max = 100) String name,
        @NotNull @Pattern(regexp = "Front-end|Back-end") String category,
        @NotNull @Min(1) @Max(500) Integer hours ,
        List<LessonDTO> lessons) {

}
