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
@Table(name = "subquestion")
public class Subquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ElementCollection
    @CollectionTable(name = "subquestion_image_urls", joinColumns = @JoinColumn(name = "subquestion_id"))
    @Column(name = "image_url")
    @JsonProperty("image_urls")
    private List<String> imageURLs;
    private Integer marks;
    @Column(name = "bloomslevel")
    @JsonProperty("blooms_level")
    private Integer bloomsLevel;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties("subquestions")
    private Question question;
}
