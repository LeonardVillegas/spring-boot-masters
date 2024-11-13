package com.api.estramipyme;

import com.api.estramipyme.DTOs.DatabaseLoadDTO;
import com.api.estramipyme.models.Option;
import com.api.estramipyme.models.Question;
import com.api.estramipyme.services.OptionService;
import com.api.estramipyme.services.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.gson.Gson;

import java.io.FileReader;


import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;


@Configuration
public class DatabaseConfig {

    private final QuestionService questionService;
    private final OptionService optionService;

    public DatabaseConfig(QuestionService questionService, OptionService optionService) {
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @Bean
    public ApplicationRunner initializeDatabase() {

        System.out.println("Inicialización de la base de datos");
        if (!questionService.hasQuestion()) {
            System.out.println("Leyendo archivo db.json para hacer la carga");

            List<DatabaseLoadDTO> questions = loadQuestionsFromJson("src/main/resources/static/db.json");

            // Loop through the list and print each question
            for (DatabaseLoadDTO question : questions) {
                Question q = new Question();
                q.setQuestionText(question.question());
                q.setSesion(question.section());
                questionService.creaQuestion(q);

                Double value = 1.0;
                for (String option : question.options()) {
                    Option o = new Option();
                    o.setOptionText(option);
                    o.setOptionValue(value);
                    o.setQuestion(q);
                    optionService.saveOption(o);
                    value = value + 1.0;
                }
            }

        } else{

        System.out.println("Hay registros en la base de datos, carga de la base de datos no se hizo");
        }

        return args -> System.out.println("Inicialización de la base de datos terminada");
    }

    public static List<DatabaseLoadDTO> loadQuestionsFromJson(String filePath) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<DatabaseLoadDTO>>>() {
            }.getType();
            Map<String, List<DatabaseLoadDTO>> data = gson.fromJson(reader, type);
            return data.get("questions");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
