package com.jan1ooo.crudspring.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record LessonDTO(
        @JsonProperty("_id") Long id_lesson,
        String name,
        String youtubeUrl) {
}
