package com.api.estramipyme.services;

import com.api.estramipyme.DTOs.DatabaseLoadDTO;
import com.api.estramipyme.models.Option;
import com.api.estramipyme.models.Question;
import com.api.estramipyme.respositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public Question creaQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

    public boolean hasQuestion() {
        return questionRepository.count()>0;
    }

    public List<DatabaseLoadDTO> getQuestionsWithOptions() {
        List<Object[]> results = questionRepository.fetchQuestionsWithOptions();
        List<DatabaseLoadDTO> questions = new ArrayList<>();

        for (Object[] row : results) {
            long id = ((Long) row[0]).longValue();
            String section = (String) row[1];
            String question = (String) row[2];
            String optionsString = (String) row[3];

            List<String> options = optionsString.isEmpty() ? null : Arrays.asList(optionsString.split(" \\| "));
            questions.add(new DatabaseLoadDTO(id, section, question, options));
        }

        return questions;
    }
}
