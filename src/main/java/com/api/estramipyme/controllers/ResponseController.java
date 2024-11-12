package com.api.estramipyme.controllers;

import com.api.estramipyme.models.Response;
import com.api.estramipyme.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/response")
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    //Obtener todas las respuestas
    @GetMapping
    public List<Response> getAllResponse() {
        return responseService.getAllResponse();
    }

    //Obtener una respuesta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable Long id) {
        Optional<Response> response = responseService.getResponseById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear una nueva respuesta
    @PostMapping
    public ResponseEntity<Response> createResponse(@RequestBody Response response) {
        Response savedResponse = responseService.saveResponse(response);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }

    //Borrar una respuesta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteResponse(@PathVariable Long id) {
        responseService.deleteResponseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
