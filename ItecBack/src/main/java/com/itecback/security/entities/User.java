package com.itecback.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itecback.entities.Event;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private String id;

    private String email;
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, mappedBy = "users")
    private List<Event> events;

    public User() {
    }

    public User(String id, String email, String password, List<Event> events) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.events = events;
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Event> getEvents() {
        return events;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEvent(List<Event> events) {
        this.events = events;
    }
}
