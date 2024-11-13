package com.api.estramipyme.models;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private double optionValue;

    @Column(nullable = false)
    private String optionText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public double getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(double optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
