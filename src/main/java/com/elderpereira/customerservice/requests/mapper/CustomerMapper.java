package com.elderpereira.customerservice.requests.mapper;

import com.elderpereira.customerservice.domain.Address;
import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;


public class CustomerMapper {

    public static Customer toCustomer(CustomerPostRequestBody customerPostRequestBody){
        Customer customer = new Customer();
        customer.setCpf(customerPostRequestBody.getCpf());
        customer.setName(customerPostRequestBody.getName());
        customer.setEmail(customerPostRequestBody.getEmail());
        customer.setPhone(customerPostRequestBody.getPhone());
        customer.setBirthDate(customerPostRequestBody.getBirthDate());

        Address address = new Address();
        address.setCountry(customerPostRequestBody.getCountry());
        address.setState(customerPostRequestBody.getState());
        address.setCity(customerPostRequestBody.getCity());
        address.setDistrict(customerPostRequestBody.getDistrict());
        address.setPostalCode(customerPostRequestBody.getPostalCode());
        address.setStreet(customerPostRequestBody.getStreet());
        address.setNumber(customerPostRequestBody.getNumber());
        address.setComplement(customerPostRequestBody.getComplement());

        address.setCustomer(customer);
        customer.setAddress(address);

        return customer;
    }

    public static Customer toCustomer(CustomerPutRequestBody customerPutRequestBody){
        Customer customer = new Customer();
        customer.setId(customerPutRequestBody.getId());
        customer.setCpf(customerPutRequestBody.getCpf());
        customer.setName(customerPutRequestBody.getName());
        customer.setEmail(customerPutRequestBody.getEmail());
        customer.setPhone(customerPutRequestBody.getPhone());
        customer.setBirthDate(customerPutRequestBody.getBirthDate());

        Address address = new Address();
        address.setCountry(customerPutRequestBody.getCountry());
        address.setState(customerPutRequestBody.getState());
        address.setCity(customerPutRequestBody.getCity());
        address.setDistrict(customerPutRequestBody.getDistrict());
        address.setPostalCode(customerPutRequestBody.getPostalCode());
        address.setStreet(customerPutRequestBody.getStreet());
        address.setNumber(customerPutRequestBody.getNumber());
        address.setComplement(customerPutRequestBody.getComplement());

        address.setCustomer(customer);
        customer.setAddress(address);

        return customer;
    }

    public static void toCustomer(Customer customer, CustomerPutRequestBody customerPutRequestBody){
        customer.setId(customerPutRequestBody.getId());
        customer.setCpf(customerPutRequestBody.getCpf());
        customer.setName(customerPutRequestBody.getName());
        customer.setEmail(customerPutRequestBody.getEmail());
        customer.setPhone(customerPutRequestBody.getPhone());
        customer.setBirthDate(customerPutRequestBody.getBirthDate());

        customer.getAddress().setCountry(customerPutRequestBody.getCountry());
        customer.getAddress().setState(customerPutRequestBody.getState());
        customer.getAddress().setCity(customerPutRequestBody.getCity());
        customer.getAddress().setDistrict(customerPutRequestBody.getDistrict());
        customer.getAddress().setPostalCode(customerPutRequestBody.getPostalCode());
        customer.getAddress().setStreet(customerPutRequestBody.getStreet());
        customer.getAddress().setNumber(customerPutRequestBody.getNumber());
        customer.getAddress().setComplement(customerPutRequestBody.getComplement());
    }


}
