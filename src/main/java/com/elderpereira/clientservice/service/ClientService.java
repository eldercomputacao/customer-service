package com.elderpereira.clientservice.service;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.exceptions.ClientNotFoundException;
import com.elderpereira.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findByIdOrThrowClientNotFoundException(long id){
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client Not Found"));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findAllPageable(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public void delete(long id) {
        clientRepository.delete(findByIdOrThrowClientNotFoundException(id));
    }

}
