package com.example.demo.service;

import com.example.demo.model.MovieBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public final class MovieService {

    @Autowired//Laisse à Spring le remplissage
    //Si plusieurs objet de même type permet de les différencier
    @Qualifier("moviesAPIClient")
    private WebClient anyApiClient;

    @Autowired
    @Qualifier("moviesAPIClientWithLoadBalancing")
    private WebClient clientWithLoadBalancing;

    @Autowired
    private RestTemplate restTemplate;



    // Obtenir un utilisateur par ID (GET) Asynchrone
    public Mono<MovieBean> getMovieByIdAsync(Long id) {
        return anyApiClient.get().uri("/movie/{id}", id).retrieve().bodyToMono(MovieBean.class);
    }

    // Utilise le nom du micro-service sur Eureka
    private static final String URL = "http://MovieService";
    public void setAnyApiClient() {
        //Récéption
        MovieBean movie = restTemplate.getForObject(URL + "/movie/1", MovieBean.class);
    }

    // Obtenir un utilisateur par ID (GET) Asynchrone
    public MovieBean getMovieByIdAsyncWithLB(Long id) {
        System.out.println("getMovieByIdAsyncWithLB - " + URL + "/movie/{"+ id + "}");
        return restTemplate.getForObject(URL + "/movie/"+ id , MovieBean.class);
    }

    // Obtenir tous les utilisateurs (GET)
    public Flux<MovieBean> getAllMovie() {
        return anyApiClient.get().retrieve().bodyToFlux(MovieBean.class);
    }

    // Créer un nouvel utilisateur (POST)
    public Mono<MovieBean> createMovie(MovieBean user) {
        return anyApiClient.post().bodyValue(user).retrieve().bodyToMono(MovieBean.class);
    }

    // Supprimer un utilisateur (DELETE)
    public Mono<Void> deleteMovie(Long id) {
        return anyApiClient.delete().uri("/{id}", id).retrieve().bodyToMono(Void.class);
    }
}

