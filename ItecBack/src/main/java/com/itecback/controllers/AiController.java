package com.itecback.controllers;

import com.itecback.entities.Event;
import com.itecback.repositories.EventRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/summarize/{eventId}")
    public String summarize(@PathVariable String eventId)
    {
        Event event=eventRepository.findById(eventId).get();
        String message="Summarize this text:\""+event.getCurricula()+"\"";
        return chatModel.call(message);
    }
}
