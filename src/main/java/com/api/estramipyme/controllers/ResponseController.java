package com.api.estramipyme.controllers;


import com.api.estramipyme.DTOs.ResponseDTO;
import com.api.estramipyme.models.Client;
import com.api.estramipyme.models.Option;
import com.api.estramipyme.models.Question;
import com.api.estramipyme.models.Response;
import com.api.estramipyme.services.ClientService;
import com.api.estramipyme.services.OptionService;
import com.api.estramipyme.services.QuestionService;
import com.api.estramipyme.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/response")
public class ResponseController {

    private final QuestionService questionService;
    private final ClientService clientService;
    private final OptionService optionService;
    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService, QuestionService questionService, ClientService clientService, OptionService optionService) {
        this.responseService = responseService;
        this.questionService = questionService;
        this.clientService = clientService;
        this.optionService = optionService;
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
    public ResponseEntity<Response> createResponse(@RequestBody ResponseDTO responseRequest) {
        Question question = questionService.getQuestionById(responseRequest.questionId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Client client = clientService.getClientById(responseRequest.clientId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Option option = optionService.getOptionById(responseRequest.optionId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Response response = new Response();
        response.setQuestion(question);
        response.setClient(client);
        response.setOption(option);
        responseService.saveResponse(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Borrar una respuesta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteResponse(@PathVariable Long id) {
        responseService.deleteResponseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
