package com.elderpereira.customerservice;

import com.elderpereira.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {// implements CommandLineRunner {

	@Autowired
	private CustomerRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
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
