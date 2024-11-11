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

    // Traer solo una opción por el id
    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        Optional<Option> option = optionService.getOptionById(id);
        return option.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva opción
    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody Option option) {
        Option savedOption = optionService.createOption(option);
        return new ResponseEntity<>(savedOption, HttpStatus.CREATED);
    }

    // Borrar una opción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOptionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar una opción
    @PutMapping("/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option optionDetails) {
        Optional<Option> existingOption = optionService.getOptionById(id);

        if (existingOption.isPresent()) {
            Option optionToUpdate = existingOption.get();
            optionToUpdate.setOptionValue(optionDetails.getOptionValue());
            optionToUpdate.setOptionText(optionDetails.getOptionText());
            optionToUpdate.setQuestion(optionDetails.getQuestion());

            Option updatedOption = optionService.updateOption(optionToUpdate);
            return ResponseEntity.ok(updatedOption);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

