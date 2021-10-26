package com.elderpereira.customerservice.validation;

import com.elderpereira.customerservice.requests.CustomerPostRequestBody;
import com.elderpereira.customerservice.util.CustomerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

@ExtendWith(SpringExtension.class)
class CustomerPostRequestBodyValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    // https://farenda.com/java/bean-validation-unit-testing/
    @Test
    @DisplayName("should not have violations")
    void shouldNotHaveViolations() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("should detect invalid name, when name empty")
    void shouldDetectInvalidName_whenNameEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setName("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid name, when name less than two characters")
    void shouldDetectInvalidName_whenNameLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setName("E");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The name must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("name").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("E").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid name, when name with more than one hundred characters")
    void shouldDetectInvalidName_whenNameWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setName("E");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The name must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("name").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("E").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid cpf, when cpf empty")
    void shouldDetectInvalidCpf_whenCpfEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCpf("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("should detect invalid cpf, when format is invalid")
    void shouldDetectInvalidCpf_whenFormatIsInvalid() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCpf("111.111.111");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The cpf must have the format [ xxx.xxx.xxx-xx ]").isEqualTo(violation.getMessage());
        Assertions.assertThat("cpf").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("111.111.111").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid postalCode, when postalCode empty")
    void shouldDetectInvalidPostalCode_whenPostalCodeEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCpf("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("should detect invalid postalCode, when format is invalid")
    void shouldDetectInvalidPostalCode_whenFormatIsInvalid() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setPostalCode("11111-11");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The postalCode must have the format [ #####-### ]").isEqualTo(violation.getMessage());
        Assertions.assertThat("postalCode").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("11111-11").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid city, when city empty")
    void shouldDetectInvalidCity_whenCityEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCity("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid city, when city less than two characters")
    void shouldDetectInvalidCity_whenCityLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCity("C");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The city must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("city").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("C").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid city, when city with more than one hundred characters")
    void shouldDetectInvalidCity_whenCityWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        String city = ("João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa");
        customer.setCity(city);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The city must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("city").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat(city).isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid state, when state empty")
    void shouldDetectInvalidState_whenStateEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setState("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid state, when state less than two characters")
    void shouldDetectInvalidState_whenStateLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setState("C");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The state must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("state").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("C").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid state, when state with more than one hundred characters")
    void shouldDetectInvalidState_whenStateWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        String state = ("João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa");
        customer.setState(state);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The state must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("state").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat(state).isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid complement, when complement empty")
    void shouldDetectInvalidComplement_whenComplementEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setComplement("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid complement, when complement less than two characters")
    void shouldDetectInvalidComplement_whenComplementLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setComplement("C");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The complement must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("complement").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("C").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid complement, when complement with more than one hundred characters")
    void shouldDetectInvalidComplement_whenComplementWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        String complement = ("João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa");
        customer.setComplement(complement);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The complement must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("complement").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat(complement).isEqualTo(violation.getInvalidValue());
    }


    @Test
    @DisplayName("should detect invalid district, when district empty")
    void shouldDetectInvalidDistrict_whenDistrictEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setDistrict("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid district, when district less than two characters")
    void shouldDetectInvalidDistrict_whenDistrictLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setDistrict("C");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The district must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("district").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("C").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid district, when district with more than one hundred characters")
    void shouldDetectInvalidDistrict_whenDistrictWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        String district = ("João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa");
        customer.setDistrict(district);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The district must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("district").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat(district).isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid street, when street empty")
    void shouldDetectInvalidStreet_whenStreetEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setStreet("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid street, when street less than two characters")
    void shouldDetectInvalidStreet_whenStreetLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setStreet("C");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The street must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("street").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("C").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid street, when street with more than one hundred characters")
    void shouldDetectInvalidStreet_whenStreetWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        String street = ("João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa");
        customer.setStreet(street);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The street must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("street").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat(street).isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid country, when country empty")
    void shouldDetectInvalidCountry_whenCountryEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCountry("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should detect invalid country, when country less than two characters")
    void shouldDetectInvalidCountry_whenCountryLessThanTwoCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setCountry("C");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The country must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("country").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("C").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid country, when country with more than one hundred characters")
    void shouldDetectInvalidCountry_whenCountryWithMoreThanOneHundredCharacters() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        String country = ("João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa " +
                "João Pessoa");
        customer.setCountry(country);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The country must be from 2 to 100 characters").isEqualTo(violation.getMessage());
        Assertions.assertThat("country").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat(country).isEqualTo(violation.getInvalidValue());
    }


    @Test
    @DisplayName("should detect invalid email, when email empty")
    void shouldDetectInvalidEmail_whenEmailEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setEmail("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("should detect invalid email, when format is invalid")
    void shouldDetectInvalidEmail_whenFormatIsInvalid() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setEmail("e@");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The email must be valid").isEqualTo(violation.getMessage());
        Assertions.assertThat("email").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("e@").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid phone, when phone empty")
    void shouldDetectInvalidPhone_whenPhoneEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setPhone("");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("should detect invalid phone, when format is invalid")
    void shouldDetectInvalidPhone_whenFormatIsInvalid() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setPhone("(44) 43333-33");

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The phone must have the format [ (xx) xxxxx-xxxx ]").isEqualTo(violation.getMessage());
        Assertions.assertThat("phone").isEqualTo(violation.getPropertyPath().toString());
        Assertions.assertThat("(44) 43333-33").isEqualTo(violation.getInvalidValue());
    }

    @Test
    @DisplayName("should detect invalid birthDate, when birthDate empty")
    void shouldDetectInvalidBirthDate_whenBirthDateEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setBirthDate(null);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("should detect invalid birthDate, when is future")
    void shouldDetectInvalidBirthDate_whenIsFuture() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setBirthDate(LocalDate.now().plusDays(1));

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The birthDate is not valid").isEqualTo(violation.getMessage());
        Assertions.assertThat("birthDate").isEqualTo(violation.getPropertyPath().toString());
    }

    @Test
    @DisplayName("should detect invalid number, when number empty")
    void shouldDetectInvalidNumber_whenNumberEmpty() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setNumber(null);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);

        //then
        Assertions.assertThat(violations.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("should detect invalid number, when is negative")
    void shouldDetectInvalidNumber_whenIsNegative() {
        //given
        CustomerPostRequestBody customer = CustomerCreator.createCustomerPostRequestBodyValid();
        customer.setNumber(-1);

        //when
        Set<ConstraintViolation<CustomerPostRequestBody>> violations = validator.validate(customer);
        ConstraintViolation<CustomerPostRequestBody> violation = violations.iterator().next();

        //then
        Assertions.assertThat("The number must be greater than zero").isEqualTo(violation.getMessage());
        Assertions.assertThat("number").isEqualTo(violation.getPropertyPath().toString());
    }



    @AfterAll
    static void close() {
        validatorFactory.close();
    }
}
