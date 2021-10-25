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
    private CustomerController customerServiceMock;

    @Mock
    private CustomerService clientServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Customer> clientPage = new PageImpl<>(List.of(CustomerCreator.createCustomerValid()));
        BDDMockito.when(clientServiceMock.findAllPageable(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(clientPage);

        BDDMockito.when(clientServiceMock.findAll())
                .thenReturn(List.of(CustomerCreator.createCustomerValid()));

        BDDMockito.when(clientServiceMock.findByIdOrThrowCustomerNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(clientServiceMock.save(ArgumentMatchers.any(CustomerPostRequestBody.class)))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.when(clientServiceMock.replace(ArgumentMatchers.any(CustomerPutRequestBody.class)))
                .thenReturn(CustomerCreator.createCustomerValid());

        BDDMockito.doNothing().when(clientServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Returns list of clients inside page object when successful")
    void list_ReturnsListOfClientsInsidePageObject_WhenSuccessful(){
        String expectedName = CustomerCreator.createCustomerValid().getName();

        Page<Customer> clientPage = customerServiceMock.findAllPageable(PageRequest.of(0, 1)).getBody();

        Assertions.assertThat(clientPage).isNotNull();

        Assertions.assertThat(clientPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns list of client when successful")
    void findAll_ReturnsListOfClient_WhenSuccessful() {
        String expectedName = CustomerCreator.createCustomerValid().getName();

        List<Customer> customers = customerServiceMock.findAll().getBody();

        Assertions.assertThat(customers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customers.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns client when successful")
    void findById_ReturnsClient_WhenSuccessful(){
        Long expectedId = CustomerCreator.createCustomerValid().getId();

        Customer customer = customerServiceMock.findById(1).getBody();

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Returns client when successful")
    void save_ReturnsClient_WhenSuccessful(){

        Customer customer = customerServiceMock.save(CustomerCreator.createCustomerPostRequestBodyValid()).getBody();

        Assertions.assertThat(customer)
                .isNotNull();

        Assertions.assertThat(customer.getName())
                .isEqualTo(CustomerCreator.createCustomerValid().getName());

    }


    @Test
    @DisplayName("Updates client when successful")
    void replace_UpdatesClient_WhenSuccessful(){

        Customer customer = customerServiceMock.replace(CustomerCreator.createCustomerPutRequestBodyValid()).getBody();

        Assertions.assertThat(customer)
                .isNotNull();

        Assertions.assertThat(customer.getName())
                .isEqualTo(CustomerCreator.createCustomerValid().getName());
    }

    @Test
    @DisplayName("Removes client when successful")
    void delete_RemovesClient_WhenSuccessful(){

        Assertions.assertThatCode(() -> customerServiceMock.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = customerServiceMock.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
