package com.elderpereira.customerservice.controller;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import com.elderpereira.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable long id){
        return new ResponseEntity<>(customerService.findByIdOrThrowCustomerNotFoundException(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<Page<Customer>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(customerService.findAllPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody CustomerPostRequestBody customerPostRequestBody) {
        return new ResponseEntity<>(customerService.save(customerPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> replace(@RequestBody CustomerPutRequestBody customerPutRequestBody) {
        return new ResponseEntity<>(customerService.replace(customerPutRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
