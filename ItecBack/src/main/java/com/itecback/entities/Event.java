package com.itecback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    private String id;

    private String name;
    private String curricula;
    private LocalDate date;

    public Event() {
    }

    public Event(String id, String name, String curricula, LocalDate date) {
        this.id = id;
        this.name = name;
        this.curricula = curricula;
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurricula() {
        return curricula;
    }

    public LocalDate getDate() {
        return date;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurricula(String curricula) {
        this.curricula = curricula;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
