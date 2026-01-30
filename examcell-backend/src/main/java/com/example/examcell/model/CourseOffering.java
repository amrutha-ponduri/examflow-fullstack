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
@Table(name = "courseoffering",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {
                        "course_id",
                        "program_id",
                        "department_id"
                }
        ))
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("academic_year")
    @Column(name = "academicyear")
    private String academicYear;
    @JsonProperty("semester")
    private String semester;
    @JsonProperty("year_of_study")
    @Column(name = "yearofstudy")
    private String yearOfStudy;
    @JsonProperty("module_count")
    @Column(name = "modulecount")
    private Integer moduleCount;

    // Foreign keys
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("courseOfferings")
    @JsonProperty("department")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonProperty("course")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonProperty("program")
    private Program program;
    @ManyToOne
    @JoinColumn(name = "regulation_id")
    @JsonProperty("regulation")
    private Regulation regulation;
    @ManyToOne
    @JoinColumn(name = "submitter_user_id")
    @JsonProperty("submitter")
    private User submitter;
    @ManyToMany
    @JoinTable(name = "user_courseoffering",
            joinColumns = @JoinColumn(name = "courseofferings_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("courseOfferings")
    @JsonProperty("instructors")
    private List<User> instructors;


    @OneToMany(mappedBy = "courseOffering", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("module_infos")
    private List<ModuleInfo> moduleInfos;
}
