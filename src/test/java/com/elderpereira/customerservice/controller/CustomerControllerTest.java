package com.elderpereira.customerservice.controller;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import com.elderpereira.customerservice.service.CustomerService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Customer> customerPage = new PageImpl<>(List.of(CustomerCreator.createCustomerValid()));
        BDDMockito.when(customerServiceMock.findAllPageable(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(customerPage);

        BDDMockito.when(customerServiceMock.findAll())
                .thenReturn(List.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(customerServiceMock.findByIdOrThrowCustomerNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(customerServiceMock.findByEmailOrThrowCustomerNotFoundException(ArgumentMatchers.anyString()))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(customerServiceMock.findByCpfOrThrowCustomerNotFoundException(ArgumentMatchers.anyString()))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(customerServiceMock.findByPhoneOrThrowCustomerNotFoundException(ArgumentMatchers.anyString()))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(customerServiceMock.save(ArgumentMatchers.any(CustomerPostRequestBody.class)))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(customerServiceMock.replace(ArgumentMatchers.any(CustomerPutRequestBody.class)))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.doNothing().when(customerServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Returns list of customers inside page object when successful")
    void list_ReturnsListOfCustomersInsidePageObject_WhenSuccessful() {
        String expectedName = CustomerCreator.createCustomerValid().getName();

        Page<Customer> customerPage = customerController.findAllPageable(PageRequest.of(0, 1)).getBody();

        Assertions.assertThat(customerPage).isNotNull();

        Assertions.assertThat(customerPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customerPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns customer by id, when successful")
    void findById_ReturnsCustomer_WhenSuccessful() {
        Long expectedId = CustomerCreator.createCustomerValid().getId();

        Customer customer = customerController.findById(1).getBody();

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Returns customer by cpf, when successful")
    void findByCpf_ReturnsCustomer_WhenSuccessful() {
        String expectedCpf = CustomerCreator.createCustomerValid().getCpf();

        Customer customer = customerController.findByCpf(expectedCpf).getBody();

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getCpf())
                .isNotNull()
                .isEqualTo(expectedCpf);
    }

    @Test
    @DisplayName("Returns customer by email, when successful")
    void findByEmail_ReturnsCustomer_WhenSuccessful() {
        String expectedEmail = CustomerCreator.createCustomerValid().getEmail();

        Customer customer = customerController.findByEmail(expectedEmail).getBody();

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getEmail())
                .isNotNull()
                .isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("Returns customer by phone, when successful")
    void findByPhone_ReturnsCustomer_WhenSuccessful() {
        String expectedPhone = CustomerCreator.createCustomerValid().getPhone();

        Customer customer = customerController.findByPhone(expectedPhone).getBody();

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getPhone())
                .isNotNull()
                .isEqualTo(expectedPhone);
    }

    @Test
    @DisplayName("Save customer when successful")
    void save_ReturnsCustomer_WhenSuccessful() {

        Customer customer = customerController.save(CustomerCreator.createCustomerPostRequestBodyValid()).getBody();

        Assertions.assertThat(customer)
                .isNotNull();

        Assertions.assertThat(customer.getName())
                .isEqualTo(CustomerCreator.createCustomerValid().getName());

    }


    @Test
    @DisplayName("Delete customer when successful")
    void delete_RemovesCustomer_WhenSuccessful() {

        Assertions.assertThatCode(() -> customerController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = customerController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
