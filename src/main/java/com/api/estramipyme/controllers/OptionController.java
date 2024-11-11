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
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
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
    public ResponseEntity<Option> createOption(@RequestBody Option option) {
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

