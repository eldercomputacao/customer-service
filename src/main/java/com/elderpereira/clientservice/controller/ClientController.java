package com.elderpereira.clientservice.controller;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.request.ClientPostRequestBody;
import com.elderpereira.clientservice.request.ClientPutRequestBody;
import com.elderpereira.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable long id){
        return new ResponseEntity<>(clientService.findByIdOrThrowClientNotFoundException(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<Page<Client>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(clientService.findAllPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientPostRequestBody clientPostRequestBody) {
        return new ResponseEntity<>(clientService.save(clientPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Client> replace(@RequestBody ClientPutRequestBody clientPutRequestBody) {
        return new ResponseEntity<>(clientService.replace(clientPutRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
