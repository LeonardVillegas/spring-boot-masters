package com.api.estramipyme.services;

import com.api.estramipyme.models.Response;
import com.api.estramipyme.respositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public Optional<Response> getQuestionById(Long id) {
        return responseRepository.findById(id);
    }

    public List<Response> getAllQuestion() {
        return responseRepository.findAll();
    }

    public Response createResponse(Response response) {
        return responseRepository.save(response);
    }

    public void deleteQuestionById(Long id) {
        responseRepository.deleteById(id);
    }
}


