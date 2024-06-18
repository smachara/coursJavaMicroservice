package org.example.movieservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")

public class MovieBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //c'est l'id est il est auto incrément
    private String title;
    private Integer length;
}