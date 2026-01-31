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
@Table(name = "module_info")
public class ModuleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("module_no")
    private Integer moduleNo;
    @JsonProperty("module_name")
    private String moduleName;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    private CourseOffering courseOffering;
}
