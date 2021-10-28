package com.elderpereira.customerservice.service;

import com.elderpereira.customerservice.domain.Address;
import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.exceptions.CustomerNotFoundException;
import com.elderpereira.customerservice.repository.AddressRepository;
import com.elderpereira.customerservice.repository.CustomerRepository;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import com.elderpereira.customerservice.requests.mapper.CustomerMapper;
import com.elderpereira.customerservice.util.FieldValidator;
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

    @Autowired
    private AddressRepository addressRepository;

    public Customer findByIdOrThrowCustomerNotFoundException(long id){
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer findByEmailOrThrowCustomerNotFoundException(String email){
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer findByCpfOrThrowCustomerNotFoundException(String cpf){
        return customerRepository
                .findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer findByPhoneOrThrowCustomerNotFoundException(String phone){
        return customerRepository
                .findByPhone(phone)
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

    //@Transactional
    public Customer replace(CustomerPutRequestBody customerPutRequestBody) {
        customerRepository.delete(findByIdOrThrowCustomerNotFoundException(customerPutRequestBody.getId()));
        return customerRepository.save(CustomerMapper.toCustomer(customerPutRequestBody));
    }

    @Transactional
    public Customer update(CustomerPutRequestBody customerPutRequestBody) {
        Customer customerToBeUpdated = findByIdOrThrowCustomerNotFoundException(customerPutRequestBody.getId());
        CustomerMapper.toCustomer(customerToBeUpdated, customerPutRequestBody);
        return customerRepository.save(customerToBeUpdated);
    }

    @Transactional
    public Customer updateEmail(long id, String email){
        FieldValidator.validateEmail(email);
        customerRepository.updateEmail(id, email);
        return findByIdOrThrowCustomerNotFoundException(id);
    }

    @Transactional
    public Customer updatePhone(long id, String phone){
        FieldValidator.validatePhone(phone);
        customerRepository.updatePhone(id, phone);
        return findByIdOrThrowCustomerNotFoundException(id);
    }

    @Transactional
    public Customer updateCpf(long id, String cpf){
        FieldValidator.validateCpf(cpf);
        customerRepository.updateCpf(id, cpf);
        return findByIdOrThrowCustomerNotFoundException(id);
    }

    @Transactional
    public void delete(long id) {
        customerRepository.delete(findByIdOrThrowCustomerNotFoundException(id));
    }

}
