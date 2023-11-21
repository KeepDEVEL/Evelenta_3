package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private List<Message> messages = new ArrayList<>();

    @GetMapping
    public List<Message> getAllMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messages.stream()
                .filter(message -> message.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void createMessage(@RequestBody Message message) {
        messages.add(message);
    }

    @PutMapping("/{id}")
    public void updateMessage(@PathVariable int id, @RequestBody Message updatedMessage) {
        Message message = getMessageById(id);
        if (message != null) {
            message.setTitle(updatedMessage.getTitle());
            message.setText(updatedMessage.getText());
            message.setTime(updatedMessage.getTime());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        Message message = getMessageById(id);
        if (message != null) {
            messages.remove(message);
        }
    }
}

class Message {
    private int id;
    private String title;
    private String text;
    private LocalDateTime time;

    public Message(int id, String title, String text, LocalDateTime time) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}