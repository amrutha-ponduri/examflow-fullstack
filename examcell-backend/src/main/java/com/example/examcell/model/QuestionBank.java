package com.example.examcell.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("review_status")
    @Column(name = "reviewstatus")
    private String reviewStatus;
    @JsonProperty("submitted_at")
    @Column(name = "submittedat")
    private String submittedAt;

    // Foreign keys
    @ManyToOne
    @JoinColumn(name = "courseoffering")
    @JsonIgnoreProperties("questionbanks")
    @JsonProperty("course_offering")
    private CourseOffering courseOffering;

    // bidirectional relationship
    @OneToMany(mappedBy = "questionBank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("questionBank")
    private List<Question> questions;

}
