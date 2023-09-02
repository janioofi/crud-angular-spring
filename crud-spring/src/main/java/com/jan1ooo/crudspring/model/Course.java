package com.jan1ooo.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jan1ooo.crudspring.enums.Category;
import com.jan1ooo.crudspring.enums.Status;
import com.jan1ooo.crudspring.enums.converters.CategoryConverter;
import com.jan1ooo.crudspring.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id_course;

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
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(Long id_course, @NotNull String name, @NotNull Category category, @NotNull Integer hours, @NotNull Status status, List<Lesson> lessons) {
        this.id_course = id_course;
        this.name = name;
        this.category = category;
        this.hours = hours;
        this.status = status;
        this.lessons = lessons;
    }

    public Course(){}

    public Long getId_course() {
        return id_course;
    }

    public void setId_course(Long id_course) {
        this.id_course = id_course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id_course=" + id_course +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", hours=" + hours +
                ", status=" + status +
                ", lessons=" + lessons +
                '}';
    }
}
