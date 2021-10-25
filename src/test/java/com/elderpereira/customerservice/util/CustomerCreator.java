package com.elderpereira.customerservice.util;

import com.elderpereira.customerservice.domain.Address;
import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;

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
        //customer.setId(1L);
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

        return customer;
    }

    public static CustomerPutRequestBody createCustomerPutRequestBodyValid(){
        CustomerPutRequestBody customer = new CustomerPutRequestBody();

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

        return customer;
    }

}
