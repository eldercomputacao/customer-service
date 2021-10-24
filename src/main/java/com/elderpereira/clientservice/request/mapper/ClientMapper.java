package com.elderpereira.clientservice.request.mapper;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.request.ClientPostRequestBody;
import com.elderpereira.clientservice.request.ClientPutRequestBody;


public class ClientMapper {

    public static Client toClient(ClientPostRequestBody clientPostRequestBody){
        Client client = new Client();
        client.setCpf(clientPostRequestBody.getCpf());
        client.setName(clientPostRequestBody.getName());
        client.setEmail(clientPostRequestBody.getEmail());
        client.setPhone(clientPostRequestBody.getPhone());
        client.setAddress(clientPostRequestBody.getAddress());

        client.getAddress().setClient(client);

        return client;
    }

    public static Client toClient(ClientPutRequestBody clientPutRequestBody){
        Client client = new Client();
        client.setId(clientPutRequestBody.getId());
        client.setCpf(clientPutRequestBody.getCpf());
        client.setName(clientPutRequestBody.getName());
        client.setEmail(clientPutRequestBody.getEmail());
        client.setPhone(clientPutRequestBody.getPhone());
        client.setAddress(clientPutRequestBody.getAddress());

        client.getAddress().setClient(client);

        return client;
    }

    public static ClientPostRequestBody toClientPostRequestBody(Client client){
        ClientPostRequestBody clientPostRequestBody = new ClientPostRequestBody();
        clientPostRequestBody.setCpf(client.getCpf());
        clientPostRequestBody.setName(client.getName());
        clientPostRequestBody.setEmail(client.getEmail());
        clientPostRequestBody.setPhone(client.getPhone());

        clientPostRequestBody.setAddress(client.getAddress());

        return clientPostRequestBody;
    }


}
