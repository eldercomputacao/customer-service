package com.elderpereira.customerservice.requests.mapper;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.util.CustomerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CustomerMapperTest {

    @Test
    void toCustomerTest() {

        CustomerPostRequestBody clientPostRequestBody = CustomerCreator.createCustomerPostRequestBodyValid();

        Customer customer = CustomerMapper.toCustomer(clientPostRequestBody);

        Assertions.assertThat(customer.getName()).isEqualTo(clientPostRequestBody.getName());

        Assertions.assertThat(customer.getAddress()).isNotNull();

        Assertions.assertThat(customer.getAddress().getCustomer()).isNotNull();

    }


}
