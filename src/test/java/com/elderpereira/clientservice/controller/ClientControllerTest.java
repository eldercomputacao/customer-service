package com.elderpereira.clientservice.controller;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.request.ClientPostRequestBody;
import com.elderpereira.clientservice.request.ClientPutRequestBody;
import com.elderpereira.clientservice.service.ClientService;
import com.elderpereira.clientservice.util.ClientCreator;
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
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Client> clientPage = new PageImpl<>(List.of(ClientCreator.createClientValid()));
        BDDMockito.when(clientServiceMock.findAllPageable(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(clientPage);

        BDDMockito.when(clientServiceMock.findAll())
                .thenReturn(List.of(ClientCreator.createClientValid()));

        BDDMockito.when(clientServiceMock.findByIdOrThrowClientNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(ClientCreator.createClientValid());

        BDDMockito.when(clientServiceMock.save(ArgumentMatchers.any(ClientPostRequestBody.class)))
                .thenReturn(ClientCreator.createClientValid());

        BDDMockito.when(clientServiceMock.replace(ArgumentMatchers.any(ClientPutRequestBody.class)))
                .thenReturn(ClientCreator.createClientValid());

        BDDMockito.doNothing().when(clientServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Returns list of clients inside page object when successful")
    void list_ReturnsListOfClientsInsidePageObject_WhenSuccessful(){
        String expectedName = ClientCreator.createClientValid().getName();

        Page<Client> clientPage = clientController.findAllPageable(PageRequest.of(0, 1)).getBody();

        Assertions.assertThat(clientPage).isNotNull();

        Assertions.assertThat(clientPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns list of client when successful")
    void findAll_ReturnsListOfClient_WhenSuccessful() {
        String expectedName = ClientCreator.createClientValid().getName();

        List<Client> clients = clientController.findAll().getBody();

        Assertions.assertThat(clients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clients.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns client when successful")
    void findById_ReturnsClient_WhenSuccessful(){
        Long expectedId = ClientCreator.createClientValid().getId();

        Client client = clientController.findById(1).getBody();

        Assertions.assertThat(client).isNotNull();

        Assertions.assertThat(client.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Returns client when successful")
    void save_ReturnsClient_WhenSuccessful(){

        Client client = clientController.save(ClientCreator.createClientPostRequestBodyValid()).getBody();

        Assertions.assertThat(client)
                .isNotNull();

        Assertions.assertThat(client.getName())
                .isEqualTo(ClientCreator.createClientValid().getName());

    }


    @Test
    @DisplayName("Updates client when successful")
    void replace_UpdatesClient_WhenSuccessful(){

        Client client = clientController.replace(ClientCreator.createClientPutRequestBodyValid()).getBody();

        Assertions.assertThat(client)
                .isNotNull();

        Assertions.assertThat(client.getName())
                .isEqualTo(ClientCreator.createClientValid().getName());
    }

    @Test
    @DisplayName("Removes client when successful")
    void delete_RemovesClient_WhenSuccessful(){

        Assertions.assertThatCode(() -> clientController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = clientController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
