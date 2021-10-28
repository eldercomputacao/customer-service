package com.elderpereira.customerservice.requests.mapper;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.requests.CustomerPutRequestBody;
import com.elderpereira.customerservice.util.CustomerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CustomerStructMapperTest {

    Logger logger = LoggerFactory.getLogger(CustomerStructMapperTest.class);

    @Test
    void toCustomerOfCustomerPostRequestBodyTest() {

        CustomerPostRequestBody clientPostRequestBody = CustomerCreator.createCustomerPostRequestBodyValid();

        Customer customer = CustomerStructMapper.MAPPER.toCustomer(clientPostRequestBody);

        logger.info("Customer: {}", customer);

        Assertions.assertThat(customer.getName()).isEqualTo(clientPostRequestBody.getName());
        Assertions.assertThat(customer.getAddress().getCity()).isEqualTo(clientPostRequestBody.getCity());
        Assertions.assertThat(customer.getAddress()).isNotNull();
        Assertions.assertThat(customer.getAddress().getCountry()).isEqualTo(clientPostRequestBody.getCountry());
        Assertions.assertThat(customer.getAddress().getPostalCode()).isEqualTo(clientPostRequestBody.getPostalCode());

    }

    @Test
    void toCustomerTest() {

        CustomerPutRequestBody clientPutRequestBody = CustomerCreator.createCustomerPutRequestBodyValid();

        Customer customer = CustomerStructMapper.MAPPER.toCustomer(clientPutRequestBody);

        logger.info("Customer: {}", customer);

        Assertions.assertThat(customer.getName()).isEqualTo(clientPutRequestBody.getName());
        Assertions.assertThat(customer.getAddress().getCity()).isEqualTo(clientPutRequestBody.getCity());
        Assertions.assertThat(customer.getAddress().getCountry()).isEqualTo(clientPutRequestBody.getCountry());
        Assertions.assertThat(customer.getAddress().getPostalCode()).isEqualTo(clientPutRequestBody.getPostalCode());

    }
}
