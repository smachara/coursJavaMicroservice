package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    //Requête d'API classique
    @Bean
    //Si plusieurs objet de même type permet de les différencier
    @Qualifier("moviesAPIClient")
    public WebClient moviesAPIClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }
    //Pour Utiliser avec le LoadBalancing et Eureka
    @Bean
    @Qualifier("moviesAPIClientWithLoadBalancing")
    @LoadBalanced
    public WebClient moviesAPIClientWithLoadBalancing(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        return WebClient.builder().baseUrl("http://localhost").filter(lbFunction).build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}