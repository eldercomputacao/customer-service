package com.elderpereira.customerservice.service;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.exceptions.CustomerNotFoundException;
import com.elderpereira.customerservice.repository.AddressRepository;
import com.elderpereira.customerservice.repository.CustomerRepository;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import com.elderpereira.customerservice.requests.mapper.CustomerStructMapper;
import com.elderpereira.customerservice.util.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(long id){
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer findByEmail(String email){
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer findByCpf(String cpf){
        return customerRepository
                .findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer findByPhone(String phone){
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


    public Customer save(CustomerPostRequestBody customerPostRequestBody) {
        Customer customerToSaved = CustomerStructMapper.MAPPER.toCustomer(customerPostRequestBody);
        customerToSaved.getAddress().setCustomer(customerToSaved);
        return customerRepository.save(customerToSaved);
    }


    public Customer update(CustomerPutRequestBody customerPutRequestBody) {
        Customer customerToBeUpdated = findById(customerPutRequestBody.getId());
        updatingFields(customerToBeUpdated, CustomerStructMapper.MAPPER.toCustomer(customerPutRequestBody));
        return customerRepository.save(customerToBeUpdated);
    }


    public Customer updateEmail(long id, String email){
        FieldValidator.validateEmail(email);
        customerRepository.updateEmail(id, email);
        return findById(id);
    }

    public Customer updatePhone(long id, String phone){
        FieldValidator.validatePhone(phone);
        customerRepository.updatePhone(id, phone);
        return findById(id);
    }

    public Customer updateCpf(long id, String cpf){
        FieldValidator.validateCpf(cpf);
        customerRepository.updateCpf(id, cpf);
        return findById(id);
    }

    public void delete(long id) {
        customerRepository.delete(findById(id));
    }

    private void updatingFields(Customer sourceCustomer, Customer targetCustomer){
        targetCustomer.setId(sourceCustomer.getId());
        targetCustomer.setName(sourceCustomer.getName());
        targetCustomer.setCpf(sourceCustomer.getCpf());
        targetCustomer.setEmail(sourceCustomer.getEmail());
        targetCustomer.setPhone(sourceCustomer.getPhone());
        targetCustomer.setBirthDate(sourceCustomer.getBirthDate());
        targetCustomer.getAddress().setCountry(sourceCustomer.getAddress().getCountry());
        targetCustomer.getAddress().setState(sourceCustomer.getAddress().getState());
        targetCustomer.getAddress().setStreet(sourceCustomer.getAddress().getStreet());
        targetCustomer.getAddress().setNumber(sourceCustomer.getAddress().getNumber());
        targetCustomer.getAddress().setPostalCode(sourceCustomer.getAddress().getPostalCode());
        targetCustomer.getAddress().setDistrict(sourceCustomer.getAddress().getDistrict());
        targetCustomer.getAddress().setComplement(sourceCustomer.getAddress().getComplement());
        targetCustomer.getAddress().setCity(sourceCustomer.getAddress().getCity());
    }

}
