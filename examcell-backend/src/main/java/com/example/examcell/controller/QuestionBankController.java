package com.example.examcell.controller;

import com.example.examcell.dto.questionbankconfigdtos.ConfigurationRequestDTO;
import com.example.examcell.dto.questionbankconfigdtos.QuestionBankConfigurationDetailsDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankCompleteDetailsDTO;
import com.example.examcell.dto.questionbankdtos.QuestionBankDTO;
import com.example.examcell.dto.questiondtos.QuestionDTO;
import com.example.examcell.model.QuestionBank;
import com.example.examcell.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/questionbanks")
public class QuestionBankController {
    private final QuestionBankService questionBankService;

    @Autowired
    public QuestionBankController(QuestionBankService questionBankService) {
        this.questionBankService = questionBankService;
    }


    @GetMapping("")
    public ArrayList<QuestionBankDTO> getAllQuestionBanks() {
        return questionBankService.getAllQuestionBanks();
    }

    @GetMapping("/{id}")
    public QuestionBankCompleteDetailsDTO getQuestionBankById(@PathVariable("id") int id) {
        return questionBankService.getQuestionBankById(id);
    }

    @PostMapping("")
    public QuestionBankDTO addQuestionBank(@RequestBody ConfigurationRequestDTO configurationRequestDTO) {
        return questionBankService.addQuestionBank(configurationRequestDTO);
    }

    @PutMapping("/{id}")
    public QuestionBankCompleteDetailsDTO updateQuestionBank(@PathVariable("id") int id, @RequestBody QuestionBank questionBank) {
        return questionBankService.updateQuestionBank(id, questionBank);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestionBank(@PathVariable("id") int id) {
        questionBankService.deleteQuestionBank(id);
    }

    @GetMapping("/{id}/questions")
    public ArrayList<QuestionDTO> getAllQuestionsForQuestionBank(@PathVariable("id") int id) {
        return questionBankService.getAllQuestionsForQuestionBank(id);
    }

    @PostMapping("/configuration_details")
    public QuestionBankConfigurationDetailsDTO getQuestionBankConfigurationDetails(@RequestBody ConfigurationRequestDTO configurationRequestDTO) {
        return questionBankService.getQuestionBankConfigurationDetails(configurationRequestDTO);
    }
}
