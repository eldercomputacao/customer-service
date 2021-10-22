package com.elderpereira.clientservice.repository;

import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.util.ClientCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Client Repository")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Persist client when Successful")
    void save_PersistClient_WhenSuccessful(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();

        Client clientSaved = clientRepository.save(clientToBeSaved);

        Assertions.assertThat(clientSaved).isNotNull();

        Assertions.assertThat(clientSaved.getId()).isNotNull();

        Assertions.assertThat(clientSaved.getName()).isEqualTo(clientToBeSaved.getName());
    }

    @Test
    @DisplayName("Returns client when sucessful")
    void findById_ReturnsClient_WhenSuccessful(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();

        clientRepository.save(clientToBeSaved);

        Client clientFinded = clientRepository.getById(clientToBeSaved.getId());

        Assertions.assertThat(clientFinded).isNotNull();

        Assertions.assertThat(clientFinded.getId()).isNotNull();

        Assertions.assertThat(clientFinded.getName()).isEqualTo(clientToBeSaved.getName());
    }

    @Test
    @DisplayName("Update client when Successful")
    void save_UpdatesClient_WhenSuccessful(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();

        Client clientSaved = this.clientRepository.save(clientToBeSaved);

        clientSaved.setName("Carlos");

        Client clientUpdated = this.clientRepository.save(clientSaved);

        Assertions.assertThat(clientUpdated).isNotNull();

        Assertions.assertThat(clientUpdated.getId()).isNotNull();

        Assertions.assertThat(clientUpdated.getName()).isEqualTo(clientSaved.getName());
    }

    @Test
    @DisplayName("Remove client when Successful")
    void delete_RemovesClient_WhenSuccessful(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();

        Client clientSaved = this.clientRepository.save(clientToBeSaved);

        this.clientRepository.delete(clientSaved);

        Optional<Client> animeOptional = this.clientRepository.findById(clientSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }



    @Test
    @DisplayName("Returns empty list")
    void findAll_ReturnsEmptyList(){
        List<Client> clients = clientRepository.findAll();

        Assertions.assertThat(clients).isEmpty();
    }

    @Test
    @DisplayName("Returns not empty list")
    void findAll_ReturnsNotEmptyList(){
        Client clientToBeSaved = ClientCreator.createClientToBeSaved();

       this.clientRepository.save(clientToBeSaved);

        List<Client> clients = clientRepository.findAll();

        Assertions.assertThat(clients).isNotEmpty();
    }

}
