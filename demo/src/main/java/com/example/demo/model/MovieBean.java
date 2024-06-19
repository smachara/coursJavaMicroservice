package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //c'est l'id est il est auto incrément
    private String title;
    private Integer length;
}