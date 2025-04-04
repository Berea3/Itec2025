package com.itecback.security.entities;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, mappedBy = "users")
    private List<Event> event;

    public User(String id, String email, String password, List<Event> event) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.event = event;
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

    public List<Event> getEvent() {
        return event;
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

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
