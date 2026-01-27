package com.example.examcell.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coursecode")
    @JsonProperty("course_code")
    private String courseCode;
    @JsonProperty("course_title")
    @Column(name = "coursetitle")
    private String courseTitle;
    private Double credits;
}
