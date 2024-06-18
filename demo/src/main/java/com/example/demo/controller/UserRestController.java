package com.example.demo.controller;

import com.example.demo.model.UserBean;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @PostMapping
    public ResponseEntity<UserBean> postUser(@RequestBody UserBean user) {
        UserService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserBean>> getAllUsers() {
        return new ResponseEntity<> (UserService.load(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBean> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(UserService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBean> updateUser(@PathVariable long id, @RequestBody UserBean user) {
        UserBean user1 = UserService.findById(id);
        if (user1 == null){
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(UserService.save(user), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {
        if (UserService.deleteById(id)){
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

}
