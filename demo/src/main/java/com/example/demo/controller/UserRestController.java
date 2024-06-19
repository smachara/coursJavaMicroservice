package com.example.demo.controller;

import com.example.demo.model.MovieBean;
import com.example.demo.model.UserBean;
import com.example.demo.service.MovieService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private final MovieService movieService;

    public UserRestController(MovieService movieService) {
        this.movieService = movieService;
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

    @PostMapping
    public ResponseEntity<UserBean> postUser(@RequestBody UserBean user) {
        UserService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    //http://localhost:8080/users/directAccessReactive
    @GetMapping("/directAccessReactive")
    public Mono<MovieBean> directAccessReactive() {
        System.out.println("directAccessReactive");
        return movieService.getMovieByIdAsync(1L);
    }


    @GetMapping("/eurekaAccess")
    public String eurekaAccess() {
        System.out.println("/eurekaAccess");
        movieService.setAnyApiClient();
        return "eurekaAccess";
    }


    //http://localhost:8080/users/eurekaAccessReactive
    @GetMapping("/eurekaAccessReactive")
    public MovieBean eurekaAccessReactive() {
        System.out.println("/eurekaAccessReactive");
        //return movieService.getMovieByIdAsync(1L).block();
        return movieService.getMovieByIdAsyncWithLB(1L);
    }

}
