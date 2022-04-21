package com.microserviclient.controller;

import com.microserviclient.model.Client;
import com.microserviclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/banking")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Mono<Client>> create(@RequestBody Client client){
        return new ResponseEntity<>(clientService.create(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<Flux<Client>> findAll(){
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Mono<Client>> findById(@PathVariable("id") String id){
        return new ResponseEntity<>(clientService.findById(id),HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public Mono<ResponseEntity<Client>> update(@PathVariable String id, @RequestBody Client client){
        return clientService.update(id, client);
    }

    @DeleteMapping("delete/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        return clientService.deleteById(id).
                map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
