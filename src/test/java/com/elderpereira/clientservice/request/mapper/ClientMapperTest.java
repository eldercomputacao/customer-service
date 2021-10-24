package com.elderpereira.clientservice.request.mapper;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.request.ClientPostRequestBody;
import com.elderpereira.clientservice.util.ClientCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ClientMapperTest {

    @Test
    void toClientPostRequestBodyTest() {

        Client client = ClientCreator.createClientValid();

        ClientPostRequestBody clientPostRequestBody = ClientMapper.toClientPostRequestBody(client);

        Assertions.assertThat(clientPostRequestBody.getName()).isEqualTo(client.getName());

        Assertions.assertThat(clientPostRequestBody.getAddress().getCountry()).isEqualTo(client.getAddress().getCountry());

    }

    @Test
    void toClientTest() {

        ClientPostRequestBody clientPostRequestBody = ClientCreator.createClientPostRequestBodyValid();

        Client client = ClientMapper.toClient(clientPostRequestBody);

        Assertions.assertThat(client.getName()).isEqualTo(clientPostRequestBody.getName());

        Assertions.assertThat(client.getAddress()).isNotNull();

        Assertions.assertThat(client.getAddress().getClient()).isNotNull();

    }


}
