package com.jan1ooo.crudspring.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LessonDTO(
        @JsonProperty("_id") Long id_lesson,
        @NotNull @NotEmpty @Length(min = 5, max = 100) String name,
        @NotNull @NotEmpty @Length(min = 10, max = 11) String youtubeUrl) {
}
