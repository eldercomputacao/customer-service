package com.elderpereira.customerservice.integration;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.repository.CustomerRepository;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.util.CustomerCreator;
import com.elderpereira.customerservice.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Returns list of customer inside page object when successful")
    void findAllPageable_ReturnsListOfCustomersInsidePageObject_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedName = savedCustomer.getName();

        PageableResponse<Customer> customerPage = testRestTemplate.exchange(
                "/customers/pageable", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<Customer>>() {
                }).getBody();

        Assertions.assertThat(customerPage)
                .isNotNull();

        Assertions.assertThat(customerPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customerPage.toList().get(0).getName())
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns customer by id, when successful")
    void findById_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        Long expectedId = savedCustomer.getId();

        Customer customer = testRestTemplate.getForObject("/customers/id/{id}", Customer.class, expectedId);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Returns customer by email, when successful")
    void findByEmail_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedEmail = savedCustomer.getEmail();

        Customer customer = testRestTemplate.getForObject("/customers/email/{email}", Customer.class, expectedEmail);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getEmail()).isNotNull().isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("Returns customer by cpf, when successful")
    void findByCpf_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedCpf = savedCustomer.getCpf();

        Customer customer = testRestTemplate.getForObject("/customers/cpf/{cpf}", Customer.class, expectedCpf);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getCpf()).isNotNull().isEqualTo(expectedCpf);
    }

    @Test
    @DisplayName("Returns customer by phone, when successful")
    void findByPhone_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedPhone = savedCustomer.getPhone();

        Customer customer = testRestTemplate.getForObject("/customers/phone/{phone}", Customer.class, expectedPhone);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getPhone()).isNotNull().isEqualTo(expectedPhone);
    }

    @Test
    @DisplayName("Returns customer when successful")
    void save_ReturnsCustomer_WhenSuccessful(){
        CustomerPostRequestBody customerPostRequestBody = CustomerCreator.createCustomerPostRequestBodyValid();

        ResponseEntity<Customer> customerResponseEntity = testRestTemplate.postForEntity(
                "/customers",
                customerPostRequestBody,
                Customer.class);

        Assertions.assertThat(customerResponseEntity).isNotNull();
        Assertions.assertThat(customerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(customerResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(customerResponseEntity.getBody().getId()).isNotNull();

    }

    @Test
    @DisplayName("Removes customer when successful")
    void delete_RemovesCustomer_WhenSuccessful(){
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        ResponseEntity<Void> customerResponseEntity = testRestTemplate.exchange(
                "/customers/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedCustomer.getId());

        Assertions.assertThat(customerResponseEntity).isNotNull();

        Assertions.assertThat(customerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}
