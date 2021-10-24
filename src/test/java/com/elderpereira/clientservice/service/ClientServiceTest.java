package com.elderpereira.clientservice.service;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.exceptions.ClientNotFoundException;
import com.elderpereira.clientservice.repository.ClientRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Client> clientPage = new PageImpl<>(List.of(ClientCreator.createClientValid()));
        BDDMockito.when(clientRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(clientPage);

        BDDMockito.when(clientRepositoryMock.findAll())
                .thenReturn(List.of(ClientCreator.createClientValid()));

        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClientCreator.createClientValid()));


        BDDMockito.when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
                .thenReturn(ClientCreator.createClientValid());

        BDDMockito.doNothing().when(clientRepositoryMock).delete(ArgumentMatchers.any(Client.class));
    }

    @Test
    @DisplayName("Returns list of clients inside page object when successful")
    void findAllPageable_ReturnsListOfClientsInsidePageObject_WhenSuccessful() {
        String expectedName = ClientCreator.createClientValid().getName();

        Page<Client> clientPage = clientService.findAllPageable(PageRequest.of(0, 1));

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
        String expectedName = ClientCreator.createClientValid().getName();

        List<Client> clients = clientService.findAll();

        Assertions.assertThat(clients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clients.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns anime when successful")
    void findByIdOrThrowClientNotFoundException_ReturnsClient_WhenSuccessful() {
        Long expectedId = ClientCreator.createClientValid().getId();

        Client client = clientService.findByIdOrThrowClientNotFoundException(1);

        Assertions.assertThat(client).isNotNull();

        Assertions.assertThat(client.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Throws ClientNotFoundException when client is not found")
    void findByIdOrThrowClientNotFoundException_ThrowsClientNotFoundException_WhenClientIsNotFound() {
        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ClientNotFoundException.class)
                .isThrownBy(() -> clientService.findByIdOrThrowClientNotFoundException(1));
    }

    @Test
    @DisplayName("Returns client when successful")
    void save_ReturnsClient_WhenSuccessful() {

        Client client = clientService.save(ClientCreator.createClientPostRequestBodyValid());

        Assertions.assertThat(client).isNotNull().isEqualTo(ClientCreator.createClientValid());

    }

    @Test
    @DisplayName("Updates client when successful")
    void replace_UpdatesClient_WhenSuccessful() {

        Assertions.assertThatCode(() -> clientService.replace(ClientCreator.createClientPutRequestBodyValid()))
                .doesNotThrowAnyException();

    }


    @Test
    @DisplayName("Removes client when successful")
    void delete_RemovesClient_WhenSuccessful() {

        Assertions.assertThatCode(() -> clientService.delete(1))
                .doesNotThrowAnyException();

    }

}
