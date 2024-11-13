package com.api.estramipyme.controllers;

import com.api.estramipyme.DTOs.OptionDTO;
import com.api.estramipyme.models.Option;
import com.api.estramipyme.models.Question;
import com.api.estramipyme.services.OptionService;
import com.api.estramipyme.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;
    private final QuestionService questionService ;

    @Autowired
    public OptionController(OptionService optionService, QuestionService questionService) {

        this.optionService = optionService;
        this.questionService = questionService;
    }

    // Trae todas las opciones
    @GetMapping
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    // Trae una opción por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        Optional<Option> option = optionService.getOptionById(id);
        return option.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Trae todas las opciones asociadas a una pregunta
    @GetMapping("/question/{questionId}")
    public List<Option> getOptionsByQuestionId(@PathVariable Long questionId) {
        return optionService.getOptionsByQuestionId(questionId);
    }

    // Crea una nueva opción
    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody OptionDTO optionRequest) {
        Question question = questionService.getQuestionById(optionRequest.questionId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Option option = new Option();
        option.setQuestion(question);
        option.setOptionText(optionRequest.optionText());
        option.setOptionValue(optionRequest.optionValue());
        Option savedOption = optionService.saveOption(option);
        return new ResponseEntity<>(savedOption, HttpStatus.CREATED);
    }

    // Elimina una opción por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOptionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

