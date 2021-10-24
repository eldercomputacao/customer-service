package com.elderpereira.clientservice.util;

import com.elderpereira.clientservice.domain.Address;
import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.request.ClientPostRequestBody;
import com.elderpereira.clientservice.request.ClientPutRequestBody;

import java.time.LocalDate;

public class ClientCreator {

    public static Client createClientToBeSaved(){
        Client client = new Client();
        client.setCpf("99999999999");
        client.setName("Maria");
        client.setPhone("(83)9999-9999");
        client.setEmail("maria@gmail.com");
        client.setBirthDate(LocalDate.of(1986, 2, 10));

        Address address = new Address();
        address.setCountry("Brazil");
        address.setState("Paraiba");
        address.setCity("João Pessoa");
        address.setPostalCode("11111-111");
        address.setDistrict("Bessa");
        address.setStreet("Av Campos Sales");
        address.setNumber(1111);
        address.setComplement("Apartamento");

        client.setAddress(address);
        address.setClient(client);

        return client;
    }

    public static Client createClientValid(){
        Client client = new Client();
        client.setId(1L);
        client.setCpf("99999999999");
        client.setName("Maria");
        client.setPhone("(83)9999-9999");
        client.setEmail("maria@gmail.com");
        client.setBirthDate(LocalDate.of(1986, 2, 10));

        Address address = new Address();
        address.setCountry("Brazil");
        address.setState("Paraiba");
        address.setCity("João Pessoa");
        address.setPostalCode("11111-111");
        address.setDistrict("Bessa");
        address.setStreet("Av Campos Sales");
        address.setNumber(1111);
        address.setComplement("Apartamento");

        client.setAddress(address);
        address.setClient(client);

        return client;
    }

    public static ClientPostRequestBody createClientPostRequestBodyValid(){
        ClientPostRequestBody client = new ClientPostRequestBody();
        //client.setId(1L);
        client.setCpf("99999999999");
        client.setName("Maria");
        client.setPhone("(83)9999-9999");
        client.setEmail("maria@gmail.com");
        client.setBirthDate(LocalDate.of(1986, 2, 10));

        Address address = new Address();
        address.setCountry("Brazil");
        address.setState("Paraiba");
        address.setCity("João Pessoa");
        address.setPostalCode("11111-111");
        address.setDistrict("Bessa");
        address.setStreet("Av Campos Sales");
        address.setNumber(1111);
        address.setComplement("Apartamento");

        client.setAddress(address);

        return client;
    }

    public static ClientPutRequestBody createClientPutRequestBodyValid(){
        ClientPutRequestBody client = new ClientPutRequestBody();

        client.setId(1L);
        client.setCpf("99999999999");
        client.setName("Maria");
        client.setPhone("(83)9999-9999");
        client.setEmail("maria@gmail.com");
        client.setBirthDate(LocalDate.of(1986, 2, 10));

        Address address = new Address();
        address.setCountry("Brazil");
        address.setState("Paraiba");
        address.setCity("João Pessoa");
        address.setPostalCode("11111-111");
        address.setDistrict("Bessa");
        address.setStreet("Av Campos Sales");
        address.setNumber(1111);
        address.setComplement("Apartamento");

        client.setAddress(address);

        return client;
    }

}
