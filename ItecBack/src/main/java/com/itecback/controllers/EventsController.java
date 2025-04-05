package com.itecback.controllers;

import com.itecback.entities.Event;
import com.itecback.entities.generator.Generator;
import com.itecback.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    EventRepository eventRepository;

    //CREATE
    @PostMapping("/create")
    public void create(@RequestBody Event event)
    {
        event.setId(Generator.generateId());
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

    @PutMapping("/createCurricula/{eventId}")
    public void createCurricula(@RequestBody String curricula, @PathVariable String eventId)
    {
        Event event=this.eventRepository.findById(eventId).get();
        event.setCurricula(curricula);
        this.eventRepository.save(event);
    }
}
