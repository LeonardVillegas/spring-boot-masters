package com.api.estramipyme.controllers;

import com.api.estramipyme.models.Option;
import com.api.estramipyme.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Trae todas las opciones
    @GetMapping
    public List<Question> getAllOptions() {
        return questionService.getAllOptions();
    }

    // Traer solo una opción por el id
    @GetMapping("/{id}")
    public ResponseEntity<Question> getOptionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getOptionById(id);
        return question.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva Pregunta, pensando en un futuro
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Option savedQuestion = questionService.creaQuestion(question);
        return new ResponseEntity<>(savedOption, HttpStatus.CREATED);
    }

    // Borrar una opción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteOptionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar una opción
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        Optional<Question> existingQuestion = questionService.getOptionById(id);

        if (existingQuestion.isPresent()) {
            Option questionToUpdate = existingQuestion.get();
            questionToUpdate.setQuestionId(optionDetails.getQuestionId());
            questionToUpdate.setQuestionText(optionDetails.getQuestionText());
            questionToUpdate.setSesion(optionDetails.getSesion());

            Option updatedQuestion = questionService.updateQuestion(questionToUpdate);
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

