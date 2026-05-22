package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.ModuleInfo;
import com.example.examcell.model.Question;
import com.example.examcell.model.QuestionBank;
import com.example.examcell.model.Subquestion;
import com.example.examcell.repository.contractrepos.QuestionRepository;
import com.example.examcell.repository.jparepos.ModuleInfoJpaRepository;
import com.example.examcell.repository.jparepos.QuestionBankJpaRepository;
import com.example.examcell.repository.jparepos.QuestionJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class QuestionService implements QuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionBankJpaRepository questionBankJpaRepository;
    private final ModuleInfoJpaRepository moduleInfoJpaRepository;
    private final Mapper mapper;

    @Autowired
    public QuestionService(QuestionJpaRepository questionJpaRepository, QuestionBankJpaRepository questionBankJpaRepository, ModuleInfoJpaRepository moduleInfoJpaRepository, Mapper mapper) {
        this.questionJpaRepository = questionJpaRepository;
        this.questionBankJpaRepository = questionBankJpaRepository;
        this.moduleInfoJpaRepository = moduleInfoJpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public QuestionDTO addQuestion(Question question) {

        if (question.getQuestionBank() == null || question.getQuestionBank().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "QuestionBank id is required");
        }

        if (question.getModuleInfo() == null || question.getModuleInfo().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Module id is required");
        }

        QuestionBank questionBank = questionBankJpaRepository
                .findById(question.getQuestionBank().getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Question bank not found")
                );

        ModuleInfo moduleInfo = moduleInfoJpaRepository
                .findById(question.getModuleInfo().getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found")
                );

        question.setQuestionBank(questionBank);
        question.setModuleInfo(moduleInfo);

        if (question.getSubquestions() != null) {
            for (Subquestion subquestion : question.getSubquestions()) {
                subquestion.setQuestion(question);
            }
        }

        return mapper.toQuestionDTO(questionJpaRepository.save(question));
    }


    @Override
    @Transactional
    public QuestionDTO updateQuestion(int id, Question question) {
        Question savedQuestion = questionJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
        if (question.getMarks() != null) {
            savedQuestion.setMarks(question.getMarks());
        }
        if (question.getModuleInfo() != null) {
            ModuleInfo completeInfo = moduleInfoJpaRepository.findById(question.getModuleInfo().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found"));
            savedQuestion.setModuleInfo(completeInfo);
        }
        if (question.getQuestionBank() != null) {
            QuestionBank questionBank = questionBankJpaRepository.findById(question.getQuestionBank().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question bank not found"));
            savedQuestion.setQuestionBank(questionBank);
        }
        if (question.getSno() != null) {
            savedQuestion.setSno(question.getSno());
        }
        if (question.getSubquestions() != null) {
            savedQuestion.getSubquestions().clear();
            for (Subquestion subquestion : question.getSubquestions()) {
                subquestion.setQuestion(savedQuestion);
            }
        }
        return mapper.toQuestionDTO(questionJpaRepository.save(savedQuestion));
    }

    @Override
    public void deleteQuestion(int id) {
        Question question = questionJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found!"));
        questionJpaRepository.delete(question);
    }
}
