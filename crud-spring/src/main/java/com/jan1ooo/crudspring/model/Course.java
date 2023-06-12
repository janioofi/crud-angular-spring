package com.jan1ooo.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.enums.converters.CategoryConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10,nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Min(1)
    @Max(500)
    @Column(nullable = false)
    private Integer hours;

    @NotNull
    @Length(max = 10)
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "Ativo|Inativo")
    private String status = "Ativo";



}
