package com.example.demo.service;

import com.example.demo.model.MessageBean;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void SendMessage(MessageBean message) {
        messageRepository.save(message);
    }

    public List<MessageBean> getMessages() {
        return messageRepository.findAll();
    }
}
