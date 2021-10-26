package com.elderpereira.customerservice.service;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.exceptions.CustomerNotFoundException;
import com.elderpereira.customerservice.repository.CustomerRepository;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import com.elderpereira.customerservice.requests.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findByIdOrThrowCustomerNotFoundException(long id){
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Page<Customer> findAllPageable(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Transactional
    public Customer save(CustomerPostRequestBody customerPostRequestBody) {
        return customerRepository.save(CustomerMapper.toCustomer(customerPostRequestBody));
    }

    @Transactional
    public Customer replace(CustomerPutRequestBody customerPutRequestBody) {
        customerRepository.delete(findByIdOrThrowCustomerNotFoundException(customerPutRequestBody.getId()));
        return customerRepository.save(CustomerMapper.toCustomer(customerPutRequestBody));
    }

    @Transactional
    public Customer replace2(CustomerPutRequestBody customerPutRequestBody) {
        Customer customerSaved = findByIdOrThrowCustomerNotFoundException(customerPutRequestBody.getId());
        Customer customer = CustomerMapper.toCustomer(customerPutRequestBody);
        customer.setId(customerSaved.getId());
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(long id) {
        customerRepository.delete(findByIdOrThrowCustomerNotFoundException(id));
    }

}
