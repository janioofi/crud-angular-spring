package com.jan1ooo.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Length(min = 3, max = 100) String name,
        @NotNull @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @Min(1) @Max(500) Integer hours ,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {

}
