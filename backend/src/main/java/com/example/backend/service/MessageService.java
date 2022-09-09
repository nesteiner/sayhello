package com.example.backend.service;

import com.example.backend.model.Message;
import com.example.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public Message insertOne(Message message) {
        return messageRepository.save(message);
    }

    public void deleteOne(Long id) {
        messageRepository.deleteById(id);
    }

    public Message updateOne(Message message) {
        return messageRepository.save(message);
    }

    public Optional<Message> findOne(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findAllByUserid(Long userid) {
        return messageRepository.findAllByUserid(userid);
    }
}
