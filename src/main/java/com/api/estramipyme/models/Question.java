package com.api.estramipyme.models;

import jakarta.persistence.*;


@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String sesion;

}
