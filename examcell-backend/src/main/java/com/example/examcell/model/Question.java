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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer marks;
    private Integer sno;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonProperty("module_info")
    private ModuleInfo moduleInfo;
    @ManyToOne
    @JoinColumn(name = "questionbank_id")
    @JsonIgnoreProperties("questions")
    @JsonProperty("question_bank")
    private QuestionBank questionBank;

    // bidirectional --> only reading
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("question")
    private List<Subquestion> subquestions;
}
