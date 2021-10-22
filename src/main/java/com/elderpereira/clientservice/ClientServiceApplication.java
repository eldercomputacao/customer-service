package com.elderpereira.clientservice;

import com.elderpereira.clientservice.domain.Address;
import com.elderpereira.clientservice.domain.Client;
import com.elderpereira.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientServiceApplication  {// implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		Client client = new Client();
//		client.setName("elder");
//		client.setEmail("elder@gmail.com");
//		client.setPhone("(33) 3333-3333");
//		client.setCpf("77777777");
//
//		Address address = new Address();
//		address.setCity("jampa");
//		address.setState("paraiba");
//		address.setCountry("brasil");
//		address.setComplement("ap");
//		address.setNumber(1111);
//		address.setPostalCode("11111-111");
//		address.setDistrict("bessa");
//		address.setStreet("av campos sales");
//
//		client.setAddress(address);
//
//		address.setClient(client);
//
//		clientRepository.save(client);
//		clientRepository.save(client);
//		clientRepository.save(client);
//
//	}

}
