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
        PageImpl<Customer> customerPage = new PageImpl<>(List.of(CustomerCreator.createCustomerValid()));
        BDDMockito.when(customerRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(customerPage);

        BDDMockito.when(customerRepositoryMock.findAll())
                .thenReturn(List.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerRepositoryMock.findByPhone(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerRepositoryMock.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.doNothing().when(customerRepositoryMock).delete(ArgumentMatchers.any(Customer.class));
    }

    @Test
    @DisplayName("Returns list of customers inside page object when successful")
    void findAllPageable_ReturnsListOfCustomersInsidePageObject_WhenSuccessful() {
        String expectedName = CustomerCreator.createCustomerValid().getName();

        Page<Customer> customerPage = customerService.findAllPageable(PageRequest.of(0, 1));

        Assertions.assertThat(customerPage).isNotNull();

        Assertions.assertThat(customerPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customerPage.toList().get(0).getName())
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns list of customers when successful")
    void findAll_ReturnsListOfCustomers_WhenSuccessful() {
        String expectedName = CustomerCreator.createCustomerValid().getName();

        List<Customer> customers = customerService.findAll();

        Assertions.assertThat(customers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customers.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns customer by id, when successful")
    void findByIdOrThrowCustomerNotFoundException_ReturnsCustomer_WhenSuccessful() {
        Long expectedId = CustomerCreator.createCustomerValid().getId();

        Customer customer = customerService.findByIdOrThrowCustomerNotFoundException(1);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Throws CustomerNotFoundException when customer is not found by id")
    void findByIdOrThrowCustomerNotFoundException_ThrowsCustomerNotFoundException_WhencustomerIsNotFound() {
        BDDMockito.when(customerRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerService.findByIdOrThrowCustomerNotFoundException(1));
    }

    @Test
    @DisplayName("Returns customer by cpf, when successful")
    void findByCpfOrThrowCustomerNotFoundException_ReturnsCustomer_WhenSuccessful() {
        String expectedCpf = CustomerCreator.createCustomerValid().getCpf();

        Customer customer = customerService.findByCpfOrThrowCustomerNotFoundException(expectedCpf);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getCpf())
                .isNotNull()
                .isEqualTo(expectedCpf);
    }

    @Test
    @DisplayName("Throws CustomerNotFoundException when customer is not found by cpf")
    void findByCpfOrThrowCustomerNotFoundException_ThrowsCustomerNotFoundException_WhencustomerIsNotFound() {
        BDDMockito.when(customerRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerService.findByCpfOrThrowCustomerNotFoundException("111.111.111-11"));
    }

    @Test
    @DisplayName("Returns customer by email, when successful")
    void findByEmailOrThrowCustomerNotFoundException_ReturnsCustomer_WhenSuccessful() {
        String expectedEmail = CustomerCreator.createCustomerValid().getEmail();

        Customer customer = customerService.findByCpfOrThrowCustomerNotFoundException(expectedEmail);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getEmail())
                .isNotNull()
                .isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("Throws CustomerNotFoundException when customer is not found by email")
    void findByEmailOrThrowCustomerNotFoundException_ThrowsCustomerNotFoundException_WhencustomerIsNotFound() {
        BDDMockito.when(customerRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerService.findByEmailOrThrowCustomerNotFoundException("111.111.111-11"));
    }

    @Test
    @DisplayName("Returns customer by phone, when successful")
    void findByPhoneOrThrowCustomerNotFoundException_ReturnsCustomer_WhenSuccessful() {
        String expectedPhone = CustomerCreator.createCustomerValid().getPhone();

        Customer customer = customerService.findByPhoneOrThrowCustomerNotFoundException(expectedPhone);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getPhone())
                .isNotNull()
                .isEqualTo(expectedPhone);
    }

    @Test
    @DisplayName("Throws CustomerNotFoundException when customer is not found by phone")
    void findByPhoneOrThrowCustomerNotFoundException_ThrowsCustomerNotFoundException_WhencustomerIsNotFound() {
        BDDMockito.when(customerRepositoryMock.findByPhone(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerService.findByPhoneOrThrowCustomerNotFoundException("(11) 111111-1111"));
    }

    @Test
    @DisplayName("Returns customer when successful")
    void save_ReturnsCustomer_WhenSuccessful() {

        Customer customer = customerService.save(CustomerCreator.createCustomerPostRequestBodyValid());

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getName()).isEqualTo(CustomerCreator.createCustomerValid().getName());

    }

    @Test
    @DisplayName("Updates customer when successful")
    void replace_UpdatesCustomer_WhenSuccessful() {

        Assertions.assertThatCode(() -> customerService.replace(CustomerCreator.createCustomerPutRequestBodyValid()))
                .doesNotThrowAnyException();

    }


    @Test
    @DisplayName("Removes customer when successful")
    void delete_RemovesCustomer_WhenSuccessful() {

        Assertions.assertThatCode(() -> customerService.delete(1))
                .doesNotThrowAnyException();

    }

}
