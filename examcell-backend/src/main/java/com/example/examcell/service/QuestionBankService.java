package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.questionbankconfigdtos.ConfigurationRequestDTO;
import com.example.examcell.dto.questionbankconfigdtos.ModuleInfoDTO;
import com.example.examcell.dto.questionbankconfigdtos.QuestionBankConfigurationDetailsDTO;
import com.example.examcell.dto.questionbankconfigdtos.SectionRulesDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankCompleteDetailsDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankDTO;
import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.CourseOffering;
import com.example.examcell.model.ModuleInfo;
import com.example.examcell.model.QuestionBank;
import com.example.examcell.model.SectionRules;
import com.example.examcell.repository.contractrepos.QuestionBankRepository;
import com.example.examcell.repository.jparepos.CourseOfferingJpaRepository;
import com.example.examcell.repository.jparepos.QuestionBankJpaRepository;
import com.example.examcell.repository.jparepos.RegulationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class QuestionBankService implements QuestionBankRepository {

    private final QuestionBankJpaRepository questionBankJpaRepository;
    private final Mapper mapper;
    private final CourseOfferingJpaRepository courseOfferingJpaRepository;
    private final RegulationJpaRepository regulationJpaRepository;

    @Autowired
    public QuestionBankService(QuestionBankJpaRepository questionBankJpaRepository, Mapper mapper, CourseOfferingJpaRepository courseOfferingJpaRepository, RegulationJpaRepository regulationJpaRepository) {
        this.questionBankJpaRepository = questionBankJpaRepository;
        this.mapper = mapper;
        this.courseOfferingJpaRepository = courseOfferingJpaRepository;
        this.regulationJpaRepository = regulationJpaRepository;
    }

    @Override
    public ArrayList<QuestionBankDTO> getAllQuestionBanks() {
        ArrayList<QuestionBank> questionBanks = new ArrayList<>(questionBankJpaRepository.findAll());
        return questionBanks.stream().map(mapper::toQuestionBankDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public QuestionBankCompleteDetailsDTO getQuestionBankById(int id) {
        QuestionBank questionBank = questionBankJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.toQuestionBakCompleteDetailsDTO(questionBank);
    }

    @Override
    public QuestionBankDTO addQuestionBank(ConfigurationRequestDTO configurationRequestDTO) {
        CourseOffering courseOffering = courseOfferingJpaRepository.findCourseOfferingByDepartmentCourseAndProgram(configurationRequestDTO.getCourseId(), configurationRequestDTO.getDepartmentId(), configurationRequestDTO.getProgramId());
        QuestionBank questionBank = new QuestionBank();
        questionBank.setCourseOffering(courseOffering);
        questionBank.setReviewStatus("Not submitted");
        questionBank.setSubmittedAt("Not submitted");
        return mapper.toQuestionBankDTO(questionBankJpaRepository.save(questionBank));
    }

    @Override
    public QuestionBankCompleteDetailsDTO updateQuestionBank(int id, QuestionBank questionBank) {
        QuestionBank savedQuestionBank = questionBankJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question bank not found"));
        if (questionBank.getReviewStatus() != null) {
            savedQuestionBank.setReviewStatus(questionBank.getReviewStatus());
        }
        if (questionBank.getSubmittedAt() != null) {
            savedQuestionBank.setSubmittedAt(questionBank.getSubmittedAt());
        }
        if (questionBank.getCourseOffering() != null) {
            CourseOffering courseOffering = courseOfferingJpaRepository.findById(questionBank.getCourseOffering().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course offering not found"));
            savedQuestionBank.setCourseOffering(courseOffering);
        }
        questionBankJpaRepository.save(savedQuestionBank);
        return mapper.toQuestionBakCompleteDetailsDTO(savedQuestionBank);
    }

    @Override
    public void deleteQuestionBank(int id) {
        QuestionBank questionBank = questionBankJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question bank not found!"));
        questionBankJpaRepository.delete(questionBank);
    }

    @Override
    public ArrayList<QuestionDTO> getAllQuestionsForQuestionBank(int id) {
        QuestionBank questionBank = questionBankJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question bank not found"));
        return questionBank.getQuestions().stream().map(mapper::toQuestionDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    public QuestionBankConfigurationDetailsDTO getQuestionBankConfigurationDetails(ConfigurationRequestDTO configurationRequestDTO) {
        ArrayList<SectionRules> sectionsRules = new ArrayList<>(regulationJpaRepository.findSectionRulesByRegulation(configurationRequestDTO.getRegulationId()));
        ArrayList<SectionRulesDTO> sectionRulesDTOS = sectionsRules.stream().map(mapper::toSectionRulesDTO).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<ModuleInfo> modulesInfo = new ArrayList<>(courseOfferingJpaRepository.findAllModuleInfos(configurationRequestDTO.getCourseId(), configurationRequestDTO.getDepartmentId(), configurationRequestDTO.getProgramId()));
        ArrayList<ModuleInfoDTO> moduleInfoDTOS = modulesInfo.stream().map(mapper::toModuleInfoDTO).collect(Collectors.toCollection(ArrayList::new));
        return new QuestionBankConfigurationDetailsDTO(moduleInfoDTOS, sectionRulesDTOS);
    }
}
