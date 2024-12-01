package com.api.estramipyme.models;

import jakarta.persistence.*;

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
}
