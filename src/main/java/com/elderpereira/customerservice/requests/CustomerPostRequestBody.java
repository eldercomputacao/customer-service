package com.elderpereira.customerservice.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "The scheme (CustomerPostRequestBody) used in POST REQUESTS")
public class CustomerPostRequestBody {

    // ok
    @Schema(example = "Maria Lima")
    @NotBlank(message = "The name cannot be empty")
    @Size(min = 2, max = 100, message = "The name must be from 2 to 100 characters")
    private String name;

    // ok
    @Schema(example = "888.888.888-88")
    @NotBlank(message = "The cpf cannot be empty")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "The cpf must have the format [ xxx.xxx.xxx-xx ]")
    private String cpf;

    // ok
    @Schema(example = "2010-10-10")
    @NotNull(message = "The birthDate cannot be empty")
    @PastOrPresent(message = "The birthDate is not valid")
    private LocalDate birthDate;

    // ok
    @Schema(example = "(83) 98888-8888")
    @NotBlank(message = "The phone cannot be empty")
    @Pattern(regexp = "\\(\\d\\d\\)\\s\\d\\d\\d\\d\\d-\\d\\d\\d\\d", message = "The phone must have the format [ (xx) xxxxx-xxxx ]")
    private String phone;

    // ok
    @Schema(example = "maria@mail.com")
    @NotBlank(message = "The email cannot be empty")
    @Email(message = "The email must be valid")
    private String email;

    // ok
    @Schema(example = "Brasil")
    @NotBlank(message = "The country cannot be empty")
    @Size(min = 2, max = 100, message = "The country must be from 2 to 100 characters")
    private String country;

    // ok
    @Schema(example = "R Solon Medeiros")
    @NotBlank(message = "The street cannot be empty")
    @Size(min = 2, max = 100, message = "The street must be from 2 to 100 characters")
    private String street;

    // ok
    @Schema(example = "Bessa")
    @NotBlank(message = "The district cannot be empty")
    @Size(min = 2, max = 100, message = "The district must be from 2 to 100 characters")
    private String district;

    @Schema(example = "1145")
    @NotNull(message = "The number cannot be empty")
    @Positive(message = "The number must be greater than zero")
    private Integer number;

    // ok
    @Schema(example = "Apartamento")
    @NotBlank(message = "The complement cannot be empty")
    @Size(min = 2, max = 100, message = "The complement must be from 2 to 100 characters")
    private String complement;

    //ok
    @Schema(example = "58104-130")
    @NotBlank(message = "The postalCode cannot be empty")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "The postalCode must have the format [ #####-### ]")
    private String postalCode;

    // ok
    @Schema(example = "João Pessoa")
    @NotBlank(message = "The city cannot be empty")
    @Size(min = 2, max = 100, message = "The city must be from 2 to 100 characters")
    private String city;

    // ok
    @Schema(example = "Paraíba")
    @NotBlank(message = "The state cannot be empty")
    @Size(min = 2, max = 100, message = "The state must be from 2 to 100 characters")
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
