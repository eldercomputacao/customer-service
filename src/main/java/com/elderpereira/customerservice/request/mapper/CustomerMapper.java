package com.elderpereira.customerservice.request.mapper;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.request.CustomerPostRequestBody;
import com.elderpereira.customerservice.request.CustomerPutRequestBody;


public class CustomerMapper {

    public static Customer toCustomer(CustomerPostRequestBody customerPostRequestBody){
        Customer customer = new Customer();
        customer.setCpf(customerPostRequestBody.getCpf());
        customer.setName(customerPostRequestBody.getName());
        customer.setEmail(customerPostRequestBody.getEmail());
        customer.setPhone(customerPostRequestBody.getPhone());
        customer.setAddress(customerPostRequestBody.getAddress());
        customer.setBirthDate(customerPostRequestBody.getBirthDate());

        customer.getAddress().setCustomer(customer);

        return customer;
    }

    public static Customer toCustomer(CustomerPutRequestBody customerPutRequestBody){
        Customer customer = new Customer();
        customer.setId(customerPutRequestBody.getId());
        customer.setCpf(customerPutRequestBody.getCpf());
        customer.setName(customerPutRequestBody.getName());
        customer.setEmail(customerPutRequestBody.getEmail());
        customer.setPhone(customerPutRequestBody.getPhone());
        customer.setAddress(customerPutRequestBody.getAddress());
        customer.setBirthDate(customerPutRequestBody.getBirthDate());

        customer.getAddress().setCustomer(customer);

        return customer;
    }

    public static CustomerPostRequestBody toCustomerPostRequestBody(Customer customer){
        CustomerPostRequestBody customerPostRequestBody = new CustomerPostRequestBody();
        customerPostRequestBody.setCpf(customer.getCpf());
        customerPostRequestBody.setName(customer.getName());
        customerPostRequestBody.setEmail(customer.getEmail());
        customerPostRequestBody.setPhone(customer.getPhone());
        customerPostRequestBody.setBirthDate(customer.getBirthDate());

        customerPostRequestBody.setAddress(customer.getAddress());

        return customerPostRequestBody;
    }


}
