package com.elderpereira.clientservice.service;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.exceptions.ClientNotFoundException;
import com.elderpereira.clientservice.repository.ClientRepository;
import com.elderpereira.clientservice.request.ClientPostRequestBody;
import com.elderpereira.clientservice.request.ClientPutRequestBody;
import com.elderpereira.clientservice.request.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Client save(ClientPostRequestBody clientPostRequestBody) {
        return clientRepository.save(ClientMapper.toClient(clientPostRequestBody));
    }

    @Transactional
    public Client replace(ClientPutRequestBody clientPutRequestBody) {
        clientRepository.delete(findByIdOrThrowClientNotFoundException(clientPutRequestBody.getId()));
        return clientRepository.save(ClientMapper.toClient(clientPutRequestBody));
    }

    @Transactional
    public void delete(long id) {
        clientRepository.delete(findByIdOrThrowClientNotFoundException(id));
    }

}
