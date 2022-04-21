package com.microserviclient.service;

import com.microserviclient.model.Client;
import com.microserviclient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository repository;

    @Override
    public Mono<Client> create(Client client) {
        return repository.save(client);
    }

    @Override
    public Flux<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Client> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<ResponseEntity<Client>> update(String id, Client client) {
        return repository.findById(id)
                .flatMap(cl ->{
                    cl.setFirstname(client.getFirstname());
                    cl.setLastname(client.getLastname());
                    cl.setNumberDucument(client.getNumberDucument());
                    cl.setPhone(client.getPhone());
                    cl.setAddress(client.getAddress());
                    cl.setBirthdate(client.getBirthdate());
                    return repository.save(cl);
                } )
                .map(update -> new ResponseEntity<>(update, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
