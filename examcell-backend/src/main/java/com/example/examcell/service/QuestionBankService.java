package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.questionbankdtos.QuestionBankCompleteDetailsDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankDTO;
import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.CourseOffering;
import com.example.examcell.model.QuestionBank;
import com.example.examcell.repository.contractrepos.QuestionBankRepository;
import com.example.examcell.repository.jparepos.CourseOfferingJpaRepository;
import com.example.examcell.repository.jparepos.QuestionBankJpaRepository;
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

    @Autowired
    public QuestionBankService(QuestionBankJpaRepository questionBankJpaRepository, Mapper mapper, CourseOfferingJpaRepository courseOfferingJpaRepository) {
        this.questionBankJpaRepository = questionBankJpaRepository;
        this.mapper = mapper;
        this.courseOfferingJpaRepository = courseOfferingJpaRepository;
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
    public QuestionBankDTO addQuestionBank(QuestionBank questionBank) {
        if (questionBank.getCourseOffering() != null) {
            CourseOffering completeCourseOffering = courseOfferingJpaRepository.findById(questionBank.getCourseOffering().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course offering not found"));
            questionBank.setCourseOffering(completeCourseOffering);
        }
        questionBankJpaRepository.save(questionBank);
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
        return new ArrayList<>(questionBank.getQuestions().stream().map((question) -> mapper.toQuestionDTO(question)).collect(Collectors.toList()));
    }
}
