package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.courseofferingdtos.CourseOfferingCompleteDetailsDTO;
import com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO;
import com.example.examcell.model.*;
import com.example.examcell.repository.contractrepos.CourseOfferingRepository;
import com.example.examcell.repository.jparepos.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseOfferingService implements CourseOfferingRepository {

    private final CourseOfferingJpaRepository courseOfferingJpaRepository;
    private final Mapper mapper;
    private final UserJpaRepository userJpaRepository;
    private final DepartmentJpaRepository departmentJpaRepository;
    private final ProgramJpaRepository programJpaRepository;
    private final RegulationJpaRepository regulationJpaRepository;
    private final CourseJpaRepository courseJpaRepository;

    @Autowired
    public CourseOfferingService(CourseOfferingJpaRepository courseOfferingJpaRepository, Mapper mapper, UserJpaRepository userJpaRepository, DepartmentJpaRepository departmentJpaRepository, ProgramJpaRepository programJpaRepository, RegulationJpaRepository regulationJpaRepository, CourseJpaRepository courseJpaRepository) {
        this.courseOfferingJpaRepository = courseOfferingJpaRepository;
        this.mapper = mapper;
        this.userJpaRepository = userJpaRepository;
        this.departmentJpaRepository = departmentJpaRepository;
        this.programJpaRepository = programJpaRepository;
        this.regulationJpaRepository = regulationJpaRepository;
        this.courseJpaRepository = courseJpaRepository;
    }


    @Override
    public ArrayList<CourseOfferingDTO> getAllCourseOfferings() {
        return new ArrayList<>(courseOfferingJpaRepository.findAllCourseOfferings());
    }

    @Override
    public CourseOfferingCompleteDetailsDTO getCourseOfferingById(int id) {
        try {
            return mapper.toCourseOfferingCompleteDetailsDTO(courseOfferingJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course offering details not found")));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public CourseOfferingDTO addCourseOffering(CourseOffering courseOffering) {
        if (courseOffering.getSubmitter() != null) {
            User submitterCompleteDetails = userJpaRepository.findById(courseOffering.getSubmitter().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            courseOffering.setSubmitter(submitterCompleteDetails);
        }
        if (courseOffering.getDepartment() != null) {
            Department completeDepartment = departmentJpaRepository.findById(courseOffering.getDepartment().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found!"));
            courseOffering.setDepartment(completeDepartment);
        }

        if (courseOffering.getProgram() != null) {
            Program completeProgram = programJpaRepository.findById(courseOffering.getProgram().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found!"));
            courseOffering.setProgram(completeProgram);
        }
        if (courseOffering.getRegulation() != null) {
            Regulation completeRegulation = regulationJpaRepository.findById(courseOffering.getRegulation().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Regulation not found!"));
            courseOffering.setRegulation(completeRegulation);
        }
        if (courseOffering.getCourse() != null) {
            Course completeCourse = courseJpaRepository.findById(courseOffering.getCourse().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
            courseOffering.setCourse(completeCourse);
        }
        if (courseOffering.getInstructors() != null) {
            List<Integer> instructorIds = courseOffering.getInstructors().stream().map(User::getId).collect(Collectors.toList());
            List<User> instructors = userJpaRepository.findAllById(instructorIds);
            courseOffering.setInstructors(instructors);
        }

        if (courseOffering.getModuleInfos() != null) {
            for (ModuleInfo moduleInfo : courseOffering.getModuleInfos()) {
                moduleInfo.setCourseOffering(courseOffering);
            }
        }
        CourseOffering savedCourseOffering = courseOfferingJpaRepository.save(courseOffering);
        return mapper.toCourseOfferingDTO(savedCourseOffering);
    }

    @Transactional
    @Override
    public CourseOfferingDTO updateCourseOffering(int id, CourseOffering courseOffering) {
        CourseOffering savedCourseOffering = courseOfferingJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course offering details not found"));
        if (courseOffering.getAcademicYear() != null) {
            savedCourseOffering.setAcademicYear(courseOffering.getAcademicYear());
        }
        if (courseOffering.getSemester() != null) {
            savedCourseOffering.setSemester(courseOffering.getSemester());
        }
        if (courseOffering.getModuleCount() != null) {
            savedCourseOffering.setModuleCount(courseOffering.getModuleCount());
        }
        if (courseOffering.getYearOfStudy() != null) {
            savedCourseOffering.setYearOfStudy(courseOffering.getYearOfStudy());
        }
        if (courseOffering.getSubmitter() != null) {
            User submitterCompleteDetails = userJpaRepository.findById(courseOffering.getSubmitter().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            savedCourseOffering.setSubmitter(submitterCompleteDetails);
        }
        if (courseOffering.getDepartment() != null) {
            Department completeDepartment = departmentJpaRepository.findById(courseOffering.getDepartment().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            savedCourseOffering.setDepartment(completeDepartment);
        }

        if (courseOffering.getProgram() != null) {
            Program completeProgram = programJpaRepository.findById(courseOffering.getProgram().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            savedCourseOffering.setProgram(completeProgram);
        }
        if (courseOffering.getRegulation() != null) {
            Regulation completeRegulation = regulationJpaRepository.findById(courseOffering.getRegulation().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            savedCourseOffering.setRegulation(completeRegulation);
        }
        if (courseOffering.getCourse() != null) {
            Course completeCourse = courseJpaRepository.findById(courseOffering.getCourse().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            savedCourseOffering.setCourse(completeCourse);
        }
        if (courseOffering.getInstructors() != null) {
            List<Integer> instructorIds = new ArrayList<>(courseOffering.getInstructors().stream().map(User::getId).collect(Collectors.toList()));
            List<User> instructors = userJpaRepository.findAllById(instructorIds);
            savedCourseOffering.setInstructors(instructors);
        }

        if (courseOffering.getModuleInfos() != null) {
            for (ModuleInfo moduleInfo : courseOffering.getModuleInfos()) {
                moduleInfo.setCourseOffering(savedCourseOffering);
            }
        }
        courseOfferingJpaRepository.save(savedCourseOffering);
        return mapper.toCourseOfferingDTO(savedCourseOffering);
    }

    @Override
    public void deleteCourseOffering(int id) {
        CourseOffering courseOffering = courseOfferingJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course offering not found!"));
        courseOfferingJpaRepository.delete(courseOffering);
    }
}
