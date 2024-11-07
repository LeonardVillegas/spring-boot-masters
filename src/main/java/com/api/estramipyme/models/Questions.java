package com.api.estramipyme.models;

import jakarta.persistence.*;


@Entity
@Table(name = "Questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "sesion")
    private String sesion;

    public Questions() {
    }

    public Questions(String text, String sesion) {
        this.text = text;
        this.sesion = sesion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }
}
