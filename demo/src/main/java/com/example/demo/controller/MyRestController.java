package com.example.demo.controller;

import com.example.demo.model.StudentBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyRestController {

    //http://localhost:8080/student
    @GetMapping("/student")
    public StudentBean getStudent() {
        System.out.println("/student");
        return new StudentBean(20, "Samer M");
    }
    //http://localhost:8080/create-student?note=30&name=Andrea
    @GetMapping("/create-student")
    public StudentBean createStudent(double note, String name) {
        System.out.println("/create-student");
        return new StudentBean(note,name);
    }
    //http://localhost:8080/max?p1=5&p2=6
    //http://localhost:8080/max?p1=5
    //http://localhost:8080/max?p2=-6
    //http://localhost:8080/max
    @GetMapping("/max")
    public Integer getMax(@RequestParam(required = false) Optional<Integer> p1, @RequestParam(required = false) Optional<Integer> p2) {
        System.out.println("/max " + p1 + " & " + p2);
        Integer value1 = null;

        if (p1.isPresent()){
            try {
                value1 = Integer.parseInt(String.valueOf(p1.get()));
            } catch ( NumberFormatException e){
                return null;
            }
        }

        Integer value2 = null;
        if (p1.isPresent()){
            try {
                value2 = Integer.parseInt(String.valueOf(p2.get()));
            } catch ( NumberFormatException e){
                return null;
            }
        }

        if (p1.isPresent() && p2.isPresent()) {
            return Math.max(p1.get(), p2.get());
        }

        if (p1.isPresent()){
            return p1.get();
        }

        if (p2.isPresent()){
            return p2.get();
        }
        return null;


    }

    @PostMapping("/increment")
    private StudentBean receiveStudent(@RequestBody StudentBean student){
        System.out.println("/increment student=" + student);
        student.setNote(student.getNote()+1);
        return student;
    }

}
