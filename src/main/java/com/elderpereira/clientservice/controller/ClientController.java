package com.elderpereira.clientservice.controller;

import com.elderpereira.clientservice.domain.Client;
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
    public ResponseEntity<List<Client>> listAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<Page<Client>> list(Pageable pageable) {
        return ResponseEntity.ok(clientService.findAllPageable(pageable));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
