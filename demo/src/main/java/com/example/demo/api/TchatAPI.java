package com.example.demo.api;

import com.example.demo.model.MessageBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/tchat")
public class TchatAPI {

    private ArrayList<MessageBean> messages = new ArrayList<>();

    @PostMapping("/saveMessage")
    private void saveMessage (@RequestBody MessageBean message){
        System.out.println(message);
        messages.add(message);
    }

    @GetMapping("/allMessages")
    private  ArrayList<MessageBean> getAllMessages(){
        return messages;
    }
}

