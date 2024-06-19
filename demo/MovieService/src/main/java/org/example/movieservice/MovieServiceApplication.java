package org.example.movieservice;

import org.example.movieservice.model.MovieBean;
import org.example.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void run(String... args) throws Exception {
		MovieBean movieBean = new MovieBean(0, "Inception", 120);
		movieRepository.save(movieBean);
	}
}
