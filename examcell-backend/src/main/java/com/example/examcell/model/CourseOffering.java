package com.example.examcell.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int id;
    @Column(name = "academicyear")
    private String academicYear;
    private String semester;
    @Column(name = "yearofstudy")
    private String yearOfStudy;
    @Column(name = "modulecount")
    private int moduleCount;

    // Foreign keys
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("courseOfferings")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    @ManyToOne
    @JoinColumn(name = "regulation_id")
    private Regulation regulation;
    @ManyToOne
    @JoinColumn(name = "submitter_user_id")
    private User submitter;
    @ManyToMany
    @JoinTable(name = "user_courseoffering",
            joinColumns = @JoinColumn(name = "courseofferings_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("courseOfferings")
    private List<User> instructors;

    // bidirectional relationship
    @OneToMany(mappedBy = "courseOffering")
    @JsonIgnoreProperties("courseOffering")
    private List<QuestionBank> questionBanks;

}
