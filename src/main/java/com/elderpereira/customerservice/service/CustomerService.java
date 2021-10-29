package com.elderpereira.customerservice.service;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CustomerService {

    Customer findById(long id);

    Customer findByEmail(String email);

    Customer findByCpf(String cpf);

    Customer findByPhone(String phone);

    List<Customer> findAll();

    Page<Customer> findAllPageable(Pageable pageable);

    Customer save(CustomerPostRequestBody customerPostRequestBody);

    Customer update(long id, CustomerPostRequestBody customerPostRequestBody);

    Customer updateEmail(long id, String email);

    Customer updatePhone(long id, String phone);

    Customer updateCpf(long id, String cpf);

    void delete(long id);

}
