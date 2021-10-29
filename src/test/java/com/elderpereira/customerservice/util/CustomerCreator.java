package com.elderpereira.customerservice.util;

import com.elderpereira.customerservice.domain.Address;
import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;

import java.time.LocalDate;

public class CustomerCreator {

    public static Customer createCustomerToBeSaved(){
        Customer customer = new Customer();
        customer.setCpf("99999999999");
        customer.setName("Maria");
        customer.setPhone("(83)9999-9999");
        customer.setEmail("maria@gmail.com");
        customer.setBirthDate(LocalDate.of(1986, 2, 10));

        Address address = new Address();
        address.setCountry("Brazil");
        address.setState("Paraiba");
        address.setCity("João Pessoa");
        address.setPostalCode("11111-111");
        address.setDistrict("Bessa");
        address.setStreet("Av Campos Sales");
        address.setNumber(1111);
        address.setComplement("Apartamento");

        customer.setAddress(address);
        address.setCustomer(customer);

        return customer;
    }

    public static Customer createCustomerValid(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCpf("99999999999");
        customer.setName("Maria");
        customer.setPhone("(83)9999-9999");
        customer.setEmail("maria@gmail.com");
        customer.setBirthDate(LocalDate.of(1986, 2, 10));

        Address address = new Address();
        address.setCountry("Brazil");
        address.setState("Paraiba");
        address.setCity("João Pessoa");
        address.setPostalCode("11111-111");
        address.setDistrict("Bessa");
        address.setStreet("Av Campos Sales");
        address.setNumber(1111);
        address.setComplement("Apartamento");

        customer.setAddress(address);
        address.setCustomer(customer);

        return customer;
    }

    public static CustomerPostRequestBody createCustomerPostRequestBodyValid(){
        CustomerPostRequestBody customer = new CustomerPostRequestBody();

        customer.setCpf("222.222.222-22");
        customer.setName("Maria");
        customer.setPhone("(83) 99999-9999");
        customer.setEmail("maria@gmail.com");
        customer.setBirthDate(LocalDate.of(1986, 2, 10));
        customer.setCountry("Brazil");
        customer.setState("Paraiba");
        customer.setCity("João Pessoa");
        customer.setPostalCode("11111-111");
        customer.setDistrict("Bessa");
        customer.setStreet("Av Campos Sales");
        customer.setNumber(1111);
        customer.setComplement("Apartamento");

        return customer;
    }

}
