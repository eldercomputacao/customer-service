package com.elderpereira.customerservice.repository;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.util.CustomerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Client Repository")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository clientRepository;

    @Test
    @DisplayName("Persist client when Successful")
    void save_PersistClient_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        Customer customerSaved = clientRepository.save(customerToBeSaved);

        Assertions.assertThat(customerSaved).isNotNull();

        Assertions.assertThat(customerSaved.getId()).isNotNull();

        Assertions.assertThat(customerSaved.getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Returns client when sucessful")
    void findById_ReturnsClient_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        clientRepository.save(customerToBeSaved);

        Customer customerFinded = clientRepository.getById(customerToBeSaved.getId());

        Assertions.assertThat(customerFinded).isNotNull();

        Assertions.assertThat(customerFinded.getId()).isNotNull();

        Assertions.assertThat(customerFinded.getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Update client when Successful")
    void save_UpdatesClient_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        Customer customerSaved = clientRepository.save(customerToBeSaved);

        customerSaved.setName("Carlos");

        Customer customerUpdated = clientRepository.save(customerSaved);

        Assertions.assertThat(customerUpdated).isNotNull();

        Assertions.assertThat(customerUpdated.getId()).isNotNull();

        Assertions.assertThat(customerUpdated.getName()).isEqualTo(customerSaved.getName());
    }

    @Test
    @DisplayName("Remove client when Successful")
    void delete_RemovesClient_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        Customer customerSaved = clientRepository.save(customerToBeSaved);

        clientRepository.delete(customerSaved);

        Optional<Customer> animeOptional = clientRepository.findById(customerSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Returns empty list")
    void findAll_ReturnsEmptyList(){
        List<Customer> customers = clientRepository.findAll();

        Assertions.assertThat(customers).isEmpty();
    }

    @Test
    @DisplayName("Returns not empty list")
    void findAll_ReturnsNotEmptyList(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        clientRepository.save(customerToBeSaved);

        List<Customer> customers = clientRepository.findAll();

        Assertions.assertThat(customers).isNotEmpty();
    }

}
