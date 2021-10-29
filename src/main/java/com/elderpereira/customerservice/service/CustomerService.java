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


public interface CustomerService {

    Customer findById(long id);

    Customer findByEmail(String email);

    Customer findByCpf(String cpf);

    Customer findByPhone(String phone);

    List<Customer> findAll();

    Page<Customer> findAllPageable(Pageable pageable);

    Customer save(CustomerPostRequestBody customerPostRequestBody);

    Customer update(CustomerPutRequestBody customerPutRequestBody);

    Customer updateEmail(long id, String email);

    Customer updatePhone(long id, String phone);

    Customer updateCpf(long id, String cpf);

    void delete(long id);

}
