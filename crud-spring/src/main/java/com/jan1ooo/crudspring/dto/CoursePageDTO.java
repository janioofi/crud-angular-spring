package com.jan1ooo.crudspring.dto;

import java.util.List;

public record CoursePageDTO(List<CourseDTO> courses, long totalElements,long totalPages) {
}
