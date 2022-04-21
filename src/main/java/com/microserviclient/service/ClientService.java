package com.microserviclient.service;


import com.microserviclient.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ClientService {

    Mono<Client> create(Client client);

    Flux<Client> findAll();

    Mono<Client> findById(String id);

    Mono<ResponseEntity<Client>> update(String id, Client client);

    Mono<Void> deleteById(String id);





}
