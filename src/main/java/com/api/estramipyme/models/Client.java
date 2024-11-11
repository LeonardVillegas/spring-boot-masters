package com.api.estramipyme.models;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String docNumber;

    @Column(nullable = false)
    private String docType;

    @Column(nullable = false)
    private String personType;
}
