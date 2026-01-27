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
@Table(name = "regulation")
public class Regulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("regulation_name")
    private String regulationName;


    // bidirectional mapping
    @OneToMany(mappedBy = "regulation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("regulation")
    @JsonProperty("section_rules")
    private List<SectionRules> sectionsRules;
}
