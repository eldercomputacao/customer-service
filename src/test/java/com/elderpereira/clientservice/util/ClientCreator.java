package com.elderpereira.clientservice.util;

import com.elderpereira.clientservice.domain.Address;
import com.elderpereira.clientservice.domain.Client;

public class ClientCreator {
    public static Client createClientToBeSaved(){
        Client client = new Client();
        client.setCpf("99999999999");
        client.setName("Maria");
        client.setPhone("(83)9999-9999");
        client.setEmail("maria@gmail.com");

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
}
