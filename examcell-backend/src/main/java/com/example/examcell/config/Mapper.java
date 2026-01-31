package com.example.examcell.config;

import com.example.examcell.dto.courseofferingdtos.CourseOfferingCompleteDetailsDTO;
import com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO;
import com.example.examcell.dto.courseofferingdtos.CourseOfferingDetailsDTO;
import com.example.examcell.dto.departmentdtos.DepartmentDTO;
import com.example.examcell.dto.moduledtos.ModuleDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankCompleteDetailsDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankDTO;
import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.dto.questiondtos.SubquestionDTO;
import com.example.examcell.dto.userdtos.UserDTO;
import com.example.examcell.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

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

    public CourseOfferingCompleteDetailsDTO toCourseOfferingCompleteDetailsDTO(CourseOffering courseOffering) {

        List<String> instructorNames = courseOffering.getInstructors().stream().map(User::getName).collect(Collectors.toList());
        List<ModuleDTO> modulesInfo = courseOffering.getModuleInfos().stream().map(this::toModuleDTO).collect(Collectors.toList());
        return new CourseOfferingCompleteDetailsDTO(courseOffering.getId(), courseOffering.getAcademicYear(), courseOffering.getSemester(),
                courseOffering.getYearOfStudy(), courseOffering.getDepartment().getDepartmentName(),
                courseOffering.getCourse().getCourseCode(), courseOffering.getCourse().getCourseTitle(),
                courseOffering.getProgram().getProgramName(), courseOffering.getProgram().getProgramName(),
                courseOffering.getSubmitter().getName(), instructorNames, modulesInfo);
    }

    public ModuleDTO toModuleDTO(ModuleInfo moduleInfo) {
        return new ModuleDTO(moduleInfo.getModuleNo(), moduleInfo.getModuleName());
    }

    public CourseOfferingDTO toCourseOfferingDTO(CourseOffering courseOffering) {
        return new CourseOfferingDTO(courseOffering.getId(),
                courseOffering.getSemester(), courseOffering.getYearOfStudy(),
                courseOffering.getDepartment().getAbbreviation(),
                courseOffering.getRegulation().getRegulationName(),
                courseOffering.getCourse().getCourseTitle());
    }

    public QuestionBankDTO toQuestionBankDTO(QuestionBank questionBank) {
        return new QuestionBankDTO(questionBank.getId(), questionBank.getReviewStatus(), questionBank.getSubmittedAt(), questionBank.getCourseOffering().getCourse().getCourseCode(), questionBank.getCourseOffering().getCourse().getCourseTitle());
    }

    public QuestionBankCompleteDetailsDTO toQuestionBakCompleteDetailsDTO(QuestionBank questionBank) {
        return new QuestionBankCompleteDetailsDTO(questionBank.getId(), questionBank.getReviewStatus(), questionBank.getSubmittedAt(), questionBank.getCourseOffering().getCourse().getCourseCode(), questionBank.getCourseOffering().getCourse().getCourseTitle(), questionBank.getCourseOffering().getSubmitter().getName(), questionBank.getCourseOffering().getDepartment().getDepartmentReviewer().getUser().getName(), questionBank.getCourseOffering().getDepartment().getDepartmentName(), questionBank.getCourseOffering().getRegulation().getRegulationName(), questionBank.getCourseOffering().getProgram().getProgramName());
    }

    public QuestionDTO toQuestionDTO(Question question) {
        List<SubquestionDTO> subquestions = question.getSubquestions().stream().map(this::toSubquestionDTO).collect(Collectors.toList());
        return new QuestionDTO(question.getId(), question.getMarks(), question.getSno(), question.getModuleInfo().getModuleNo(), subquestions);
    }

    public SubquestionDTO toSubquestionDTO(Subquestion subquestion) {
        return new SubquestionDTO(subquestion.getId(), subquestion.getContent(), subquestion.getImageURLs(), subquestion.getMarks(), subquestion.getBloomsLevel());
    }
}
