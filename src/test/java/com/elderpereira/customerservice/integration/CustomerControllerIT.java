package com.elderpereira.customerservice.integration;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.domain.User;
import com.elderpereira.customerservice.repository.CustomerRepository;
import com.elderpereira.customerservice.repository.UserRepository;
import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.util.CustomerCreator;
import com.elderpereira.customerservice.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerIT {

//    @Autowired
//    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    @Qualifier(value = "testRestTemplateRoleUser")
    private TestRestTemplate testRestTemplateRoleUser;

    @Autowired
    @Qualifier(value = "testRestTemplateRoleRoot")
    private TestRestTemplate testRestTemplateRoleRoot;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;


    private static final User USER = new User(
            "User",
            "user",
            "{bcrypt}$2a$10$Izp2HEOra4Z0iCX3.V5qHeeW.UvgOHSPfn.wLfrLtvTuZyRBYVn1G",
            "ROLE_USER");

    private static final User ROOT = new User(
            "Root",
            "root",
            "{bcrypt}$2a$10$Izp2HEOra4Z0iCX3.V5qHeeW.UvgOHSPfn.wLfrLtvTuZyRBYVn1G",
            "ROLE_USER,ROLE_ADMIN");


    @TestConfiguration
    @Lazy
    static class Config {
        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("user", "pass123");
            return new TestRestTemplate(restTemplateBuilder);
        }
        @Bean(name = "testRestTemplateRoleRoot")
        public TestRestTemplate testRestTemplateRoleAdminCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+port)
                    .basicAuthentication("root", "pass123");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @Test
    @DisplayName("Returns list of customer inside page object when successful")
    void findAllPageable_ReturnsListOfCustomersInsidePageObject_WhenSuccessful() {

        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedName = savedCustomer.getName();


        userRepository.save(USER);

        PageableResponse<Customer> customerPage = testRestTemplateRoleUser.exchange(
                "/customers/pageable", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<Customer>>() {
                }).getBody();

        Assertions.assertThat(customerPage)
                .isNotNull();

        Assertions.assertThat(customerPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customerPage.toList().get(0).getName())
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Returns customer by id, when successful")
    void findById_ReturnsCustomer_WhenSuccessful() {


        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        Long expectedId = savedCustomer.getId();

        userRepository.save(USER);

        Customer customer = testRestTemplateRoleUser.getForObject(
                "/customers/id/{id}",
                Customer.class,
                expectedId);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Returns customer by email, when successful")
    void findByEmail_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedEmail = savedCustomer.getEmail();

        userRepository.save(ROOT);

        Customer customer = testRestTemplateRoleRoot.getForObject("/customers/email/{email}", Customer.class, expectedEmail);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getEmail()).isNotNull().isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("Returns customer by cpf, when successful")
    void findByCpf_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedCpf = savedCustomer.getCpf();

        userRepository.save(ROOT);

        Customer customer = testRestTemplateRoleRoot.getForObject("/customers/cpf/{cpf}", Customer.class, expectedCpf);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getCpf()).isNotNull().isEqualTo(expectedCpf);
    }

    @Test
    @DisplayName("Returns customer by phone, when successful")
    void findByPhone_ReturnsCustomer_WhenSuccessful() {
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        String expectedPhone = savedCustomer.getPhone();

        userRepository.save(ROOT);

        Customer customer = testRestTemplateRoleRoot.getForObject("/customers/phone/{phone}", Customer.class, expectedPhone);

        Assertions.assertThat(customer).isNotNull();

        Assertions.assertThat(customer.getPhone()).isNotNull().isEqualTo(expectedPhone);
    }

    @Test
    @DisplayName("Save customer, returns customer when successful")
    void save_ReturnsCustomer_WhenSuccessful(){
        CustomerPostRequestBody customerPostRequestBody = CustomerCreator.createCustomerPostRequestBodyValid();

        userRepository.save(ROOT);

        ResponseEntity<Customer> customerResponseEntity = testRestTemplateRoleRoot.postForEntity(
                "/customers",
                customerPostRequestBody,
                Customer.class);

        Assertions.assertThat(customerResponseEntity).isNotNull();
        Assertions.assertThat(customerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(customerResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(customerResponseEntity.getBody().getId()).isNotNull();

    }

    @Test
    @DisplayName("Save customer, returns customer when successful")
    void save_Returns403_WhenUserIsNotRoot(){
        CustomerPostRequestBody customerPostRequestBody = CustomerCreator.createCustomerPostRequestBodyValid();

        userRepository.save(USER);

        ResponseEntity<Customer> customerResponseEntity = testRestTemplateRoleUser.postForEntity(
                "/customers",
                customerPostRequestBody,
                Customer.class);

        Assertions.assertThat(customerResponseEntity).isNotNull();
        Assertions.assertThat(customerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }

    @Test
    @DisplayName("Removes customer when successful")
    void delete_RemovesCustomer_WhenSuccessful(){
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        userRepository.save(ROOT);

        ResponseEntity<Void> customerResponseEntity = testRestTemplateRoleRoot.exchange(
                "/customers/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedCustomer.getId());

        Assertions.assertThat(customerResponseEntity).isNotNull();

        Assertions.assertThat(customerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Returns 403 when user is not root")
    void delete_Returns403_WhenUserIsNotRoot(){
        Customer savedCustomer = customerRepository.save(CustomerCreator.createCustomerToBeSaved());

        userRepository.save(USER);

        ResponseEntity<Void> customerResponseEntity = testRestTemplateRoleUser.exchange(
                "/customers/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedCustomer.getId());

        Assertions.assertThat(customerResponseEntity).isNotNull();

        Assertions.assertThat(customerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


}
