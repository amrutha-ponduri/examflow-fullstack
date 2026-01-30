package com.example.examcell.dto.userdtos;

import com.example.examcell.dto.courseofferingdtos.CourseOfferingDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private String name;
    private List<String> roles;
    private List<CourseOfferingDetailsDTO> courses;
}
