package com.example.demo.controller;

import com.example.demo.model.MessageBean;
import com.example.demo.model.UserBean;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyMessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping ("/sendMessage")
    public void sendMessage(@RequestBody MessageBean messageBean) {
        messageService.SendMessage(messageBean);
    }

    @GetMapping ("/mesages")
    public List<MessageBean> getMessages() {
        return messageService.getMessages();
    }
}


