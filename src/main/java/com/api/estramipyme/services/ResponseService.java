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

    // Obtener todas las respuestas
    public List<Response> getAllResponse() {

        return responseRepository.findAll();
    }

    // Obtener una respuesta por su ID
    public Optional<Response> getResponseById(Long id) {

        return responseRepository.findById(id);
    }

    //Crear una nueva respuesta
    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    // Eliminar una respuesta por su ID
    public void deleteResponseById(Long id) {

       responseRepository.deleteById(id);
    }

}
