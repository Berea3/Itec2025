package com.itecback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itecback.security.entities.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    private String id;

    private String name;
    @Column(name="curricula", length=10000)
    private String curricula;
    private LocalDate date;
    private String organizerId;
    private String chat;
    @Column(name="aiSummary", length=10000)
    private String aiSummary;
    private String category;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public void addUser(User user)
    {
        if (users==null) users=new ArrayList<>();
        users.add(user);
    }

    public Event() {
    }

    public Event(String id, String name, String curricula, LocalDate date, String organizerId, String chat, String aiSummary, String category, List<User> users) {
        this.id = id;
        this.name = name;
        this.curricula = curricula;
        this.date = date;
        this.organizerId=organizerId;
        this.chat=chat;
        this.aiSummary=aiSummary;
        this.category=category;
        this.users = users;
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

    public String getOrganizerId() {
        return organizerId;
    }

    public String getChat() {
        return chat;
    }

    public String getAiSummary() {
        return aiSummary;
    }

    public String getCategory() {
        return category;
    }

    public List<User> getUsers() {
        return users;
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

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
