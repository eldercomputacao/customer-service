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
@DisplayName("Tests for customer Repository")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Persist customer when Successful")
    void save_Persistcustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        Customer customerSaved = customerRepository.save(customerToBeSaved);

        Assertions.assertThat(customerSaved).isNotNull();

        Assertions.assertThat(customerSaved.getId()).isNotNull();

        Assertions.assertThat(customerSaved.getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Returns customer by registered id, when sucessful")
    void findById_ReturnsCustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        customerRepository.save(customerToBeSaved);

        Customer customerFinded = customerRepository.getById(customerToBeSaved.getId());

        Assertions.assertThat(customerFinded).isNotNull();

        Assertions.assertThat(customerFinded.getId()).isNotNull();

        Assertions.assertThat(customerFinded.getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Returns customer by registered phone, when sucessful")
    void findByCpf_ReturnsCustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        customerRepository.save(customerToBeSaved);

        Optional<Customer> customerFinded = customerRepository.findByCpf(customerToBeSaved.getCpf());


        Assertions.assertThat(customerFinded).isNotNull();

        Assertions.assertThat(customerFinded.get().getId()).isNotNull();

        Assertions.assertThat(customerFinded.get().getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Return any customer by unregistered cpf")
    void findByCpf_ReturnsNotCustomer(){

        Optional<Customer> customerFinded = customerRepository.findByCpf("111.111.111-11");

        Assertions.assertThat(customerFinded.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Returns customer by registered email, when sucessful")
    void findByEmail_ReturnsCustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        customerRepository.save(customerToBeSaved);

        Optional<Customer> customerFinded = customerRepository.findByEmail(customerToBeSaved.getEmail());


        Assertions.assertThat(customerFinded).isNotNull();

        Assertions.assertThat(customerFinded.get().getId()).isNotNull();

        Assertions.assertThat(customerFinded.get().getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Return any customer by unregistered email")
    void findByEmail_ReturnsNotCustomer(){

        Optional<Customer> customerFinded = customerRepository.findByEmail("jose@mail.com");

        Assertions.assertThat(customerFinded.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Returns customer by registered phone, when sucessful")
    void findByPhone_ReturnsCustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        customerRepository.save(customerToBeSaved);

        Optional<Customer> customerFinded = customerRepository.findByPhone(customerToBeSaved.getPhone());


        Assertions.assertThat(customerFinded).isNotNull();

        Assertions.assertThat(customerFinded.get().getId()).isNotNull();

        Assertions.assertThat(customerFinded.get().getName()).isEqualTo(customerToBeSaved.getName());
    }

    @Test
    @DisplayName("Return any customer by unregistered phone")
    void findByPhone_ReturnsNotCustomer(){

        Optional<Customer> customerFinded = customerRepository.findByEmail("(33) 33333-3333");

        Assertions.assertThat(customerFinded.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Update customer when Successful")
    void save_UpdatesCustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        Customer customerSaved = customerRepository.save(customerToBeSaved);

        customerSaved.setName("Carlos");

        Customer customerUpdated = customerRepository.save(customerSaved);

        Assertions.assertThat(customerUpdated).isNotNull();

        Assertions.assertThat(customerUpdated.getId()).isNotNull();

        Assertions.assertThat(customerUpdated.getName()).isEqualTo(customerSaved.getName());
    }

    @Test
    @DisplayName("Remove customer when Successful")
    void delete_RemovesCustomer_WhenSuccessful(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        Customer customerSaved = customerRepository.save(customerToBeSaved);

        customerRepository.delete(customerSaved);

        Optional<Customer> animeOptional = customerRepository.findById(customerSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Returns empty list")
    void findAll_ReturnsEmptyList(){
        List<Customer> customers = customerRepository.findAll();

        Assertions.assertThat(customers).isEmpty();
    }

    @Test
    @DisplayName("Returns not empty list")
    void findAll_ReturnsNotEmptyList(){
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();

        customerRepository.save(customerToBeSaved);

        List<Customer> customers = customerRepository.findAll();

        Assertions.assertThat(customers).isNotEmpty();
    }

}
