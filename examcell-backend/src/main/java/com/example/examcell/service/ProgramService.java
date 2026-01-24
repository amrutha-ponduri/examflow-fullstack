package com.example.examcell.service;

import com.example.examcell.model.Program;
import com.example.examcell.repository.ProgramJpaRepository;
import com.example.examcell.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class ProgramService implements ProgramRepository {
    private final ProgramJpaRepository programJpaRepository;

    @Autowired
    public ProgramService(ProgramJpaRepository programJpaRepository) {
        this.programJpaRepository = programJpaRepository;
    }

    @Override
    public ArrayList<Program> getAllPrograms() {
        return new ArrayList<>(programJpaRepository.findAll());
    }

    @Override
    public Program getProgramById(int id) {
        try {
            return programJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException("Invalid argument");
        }
    }

    @Override
    public Program addProgram(Program program) {
        return programJpaRepository.save(program);
    }

    @Override
    public Program updateProgram(int id, Program program) {
        try {
            Program savedProgram = getProgramById(id);
            if (program.getType() != null) {
                savedProgram.setType(program.getType());
            }
            return programJpaRepository.save(savedProgram);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProgramById(int id) {
        try {
            Program savedProgram = getProgramById(id);
            programJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
