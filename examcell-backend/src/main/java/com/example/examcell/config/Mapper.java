package com.example.examcell.config;

import com.example.examcell.dto.CourseOfferingDetailsDTO;
import com.example.examcell.dto.DepartmentDTO;
import com.example.examcell.dto.UserDTO;
import com.example.examcell.model.CourseOffering;
import com.example.examcell.model.Department;
import com.example.examcell.model.Role;
import com.example.examcell.model.User;
import com.example.examcell.repository.CourseOfferingJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private final CourseOfferingJpaRepository courseOfferingJpaRepository;

    @Autowired
    public Mapper(CourseOfferingJpaRepository courseOfferingJpaRepository) {
        this.courseOfferingJpaRepository = courseOfferingJpaRepository;
    }

    public UserDTO toUserDTO(User user) {
        List<CourseOffering> courseOfferings = user.getCourseOfferings();
        List<Role> roles = user.getRoles();
        List<CourseOfferingDetailsDTO> courseOfferingDetails = new ArrayList<>();
        List<String> roleNames = new ArrayList<>();
        if (courseOfferings != null) {
            courseOfferingDetails = courseOfferings.stream().map(courseOffering -> toCourseOfferingDetailsDTO(courseOffering)).collect(Collectors.toList());
        }
        if (roles != null) {
            roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
        }
        return new UserDTO(user.getId(), user.getUsername(), user.getName(), roleNames, courseOfferingDetails);
    }

    public CourseOfferingDetailsDTO toCourseOfferingDetailsDTO(CourseOffering courseOffering) {
        return new CourseOfferingDetailsDTO(courseOffering.getCourse().getCourseTitle(), courseOffering.getYearOfStudy(), courseOffering.getSemester(), courseOffering.getDepartment().getAbbreviation(), courseOffering.getAcademicYear());
    }

    public DepartmentDTO toDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getDepartmentName(), department.getDepartmentReviewer().getUser().getName());
    }
}
