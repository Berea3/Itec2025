package com.itecback.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itecback.entities.Event;
import com.itecback.entities.generator.Generator;
import com.itecback.repositories.EventRepository;
import com.itecback.security.entities.User;
import com.itecback.security.entities.UserRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatModel chatModel;

    //CREATE
    @PostMapping("/create")
    public void create(@RequestBody Event event) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        event.setId(Generator.generateId());
        event.setOrganizerId(objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),User.class).getId());
        event.addUser(userRepository.findById(objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),User.class).getId()).get());
        this.eventRepository.save(event);
    }


    //READ
    @GetMapping("/getById/{id}")
    public Optional<Event> getById(@PathVariable String id)
    {
        Optional<Event> optionalEvent=eventRepository.findById(id);
        return optionalEvent;
    }

    @GetMapping("/getAll")
    public List<Event> getAll()
    {
        return eventRepository.findAll();
    }

    @GetMapping("/getUsersByEventId/{eventId}")
    public List<User> getUsersByEventId(@PathVariable String eventId)
    {
        return this.eventRepository.findById(eventId).get().getUsers();
    }

    @GetMapping("/getEventsByUser")
    public List<Event> getEventsByUser() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        User user=this.userRepository.findById(objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),User.class).getId()).get();
        return user.getEvents();
    }



    //UPDATE
    @PutMapping("/createCurricula/{eventId}")
    public void createCurricula(@RequestBody String curricula, @PathVariable String eventId)
    {
        Event event=this.eventRepository.findById(eventId).get();
        event.setCurricula(curricula);
        String message="Summarize this text without saying you summarized it:\""+curricula+"\"";
        String summarized=chatModel.call(message);
        event.setAiSummary(summarized);
        this.eventRepository.save(event);
    }

    @PutMapping("/sign-up")
    public User signUp(@RequestBody String eventId) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        User user=objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),User.class);
        user=userRepository.findById(user.getId()).get();
        Event event=eventRepository.findById(eventId).get();
        event.addUser(user);
        this.eventRepository.save(event);
        return user;
    }

    @PutMapping("/send-message/{eventId}")
    public void sendMessage(@PathVariable String eventId, @RequestBody String message) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        User user=objectMapper.readValue(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),User.class);
        Event event=this.eventRepository.findById(eventId).get();
        if (event.getChat()==null) event.setChat("");
        event.setChat(event.getChat()+user.getEmail()+": "+message+"\n");
        this.eventRepository.save(event);
    }
}
