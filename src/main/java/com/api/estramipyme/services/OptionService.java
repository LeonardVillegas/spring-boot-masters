package com.api.estramipyme.services;

import com.api.estramipyme.models.Option;
import com.api.estramipyme.respositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    // Obtener todas las opciones
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    // Obtener una opción por su ID
    public Optional<Option> getOptionById(Long id) {
        return optionRepository.findById(id);
    }

    // Obtener todas las opciones asociadas a una pregunta
    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionId(questionId);
    }

    // Crear una nueva opción
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    // Eliminar una opción por su ID
    public void deleteOptionById(Long id) {
        optionRepository.deleteById(id);
    }

}
