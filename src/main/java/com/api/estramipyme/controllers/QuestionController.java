package com.api.estramipyme.controllers;

import com.api.estramipyme.DTOs.DatabaseLoadDTO;
import com.api.estramipyme.models.Option;
import com.api.estramipyme.models.Question;
import com.api.estramipyme.services.QuestionService;
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
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestion();
    }

    // Traer solo una opción por el id
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva Pregunta, pensando en un futuro
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.creaQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    // Borrar una opción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar una pregunta
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        Optional<Question> existingQuestion = questionService.getQuestionById(id);

        if (existingQuestion.isPresent()) {
            Question questionToUpdate = existingQuestion.get();
            questionToUpdate.setQuestionText(questionDetails.getQuestionText());
            questionToUpdate.setSesion(questionDetails.getSesion());

            Question updatedQuestion = questionService.creaQuestion(questionToUpdate);
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("with-options")
    public ResponseEntity<List<DatabaseLoadDTO>> getQuestionsWithOptions() {
        List<DatabaseLoadDTO> questions = questionService.getQuestionsWithOptions();
        return ResponseEntity.ok(questions);
    }
}

