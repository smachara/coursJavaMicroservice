package org.example.movieservice.repository;

import org.example.movieservice.model.MovieBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource( path ="movie")
public interface MovieRepository extends JpaRepository<MovieBean, Long> {

}
