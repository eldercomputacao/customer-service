package com.elderpereira.customerservice.service;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.exceptions.CustomerNotFoundException;
import com.elderpereira.customerservice.repository.CustomerRepository;
import com.elderpereira.customerservice.util.CustomerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Customer> clientPage = new PageImpl<>(List.of(CustomerCreator.createCustomerValid()));
        BDDMockito.when(customerRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(clientPage);

        BDDMockito.when(customerRepositoryMock.findAll())
                .thenReturn(List.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CustomerCreator.createCustomerValid()));


        BDDMockito.when(customerRepositoryMock.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.doNothing().when(customerRepositoryMock).delete(ArgumentMatchers.any(Customer.class));
    }

    @Test
    @DisplayName("Returns list of clients inside page object when successful")
    void findAllPageable_ReturnsListOfClientsInsidePageObject_WhenSuccessful() {
        String expectedName = CustomerCreator.createCustomerValid().getName();

        Page<Customer> clientPage = customerService.findAllPageable(PageRequest.of(0, 1));

        Assertions.assertThat(clientPage).isNotNull();

        Assertions.assertThat(clientPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clientPage.toList().get(0).getName())
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns list of clients when successful")
    void findAll_ReturnsListOfClients_WhenSuccessful() {
        String expectedName = CustomerCreator.createCustomerValid().getName();

        List<Customer> customers = customerService.findAll();

        Assertions.assertThat(customers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customers.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns anime when successful")
    void findByIdOrThrowClientNotFoundException_ReturnsClient_WhenSuccessful() {
        Long expectedId = CustomerCreator.createCustomerValid().getId();

        Customer customer = customerService.findByIdOrThrowCustomerNotFoundException(1);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Throws ClientNotFoundException when client is not found")
    void findByIdOrThrowClientNotFoundException_ThrowsClientNotFoundException_WhenClientIsNotFound() {
        BDDMockito.when(customerRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerService.findByIdOrThrowCustomerNotFoundException(1));
    }

    @Test
    @DisplayName("Returns client when successful")
    void save_ReturnsClient_WhenSuccessful() {

        Customer customer = customerService.save(CustomerCreator.createCustomerPostRequestBodyValid());

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getName()).isEqualTo(CustomerCreator.createCustomerValid().getName());

    }

    @Test
    @DisplayName("Updates client when successful")
    void replace_UpdatesClient_WhenSuccessful() {

        Assertions.assertThatCode(() -> customerService.replace(CustomerCreator.createCustomerPutRequestBodyValid()))
                .doesNotThrowAnyException();

    }


    @Test
    @DisplayName("Removes client when successful")
    void delete_RemovesClient_WhenSuccessful() {

        Assertions.assertThatCode(() -> customerService.delete(1))
                .doesNotThrowAnyException();

    }

}
