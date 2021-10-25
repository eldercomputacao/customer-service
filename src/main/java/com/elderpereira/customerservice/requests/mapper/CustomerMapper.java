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

//    public static CustomerPostRequestBody toCustomerPostRequestBody(Customer customer){
//        CustomerPostRequestBody customerPostRequestBody = new CustomerPostRequestBody();
//        customerPostRequestBody.setCpf(customer.getCpf());
//        customerPostRequestBody.setName(customer.getName());
//        customerPostRequestBody.setEmail(customer.getEmail());
//        customerPostRequestBody.setPhone(customer.getPhone());
//        customerPostRequestBody.setBirthDate(customer.getBirthDate());
//
//        //customerPostRequestBody.setAddress(customer.getAddress());
//
//        return customerPostRequestBody;
//    }


}
