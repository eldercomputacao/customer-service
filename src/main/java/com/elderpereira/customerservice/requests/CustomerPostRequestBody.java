package com.elderpereira.customerservice.requests;

import com.elderpereira.customerservice.domain.Address;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class CustomerPostRequestBody {

    @NotBlank(message = "The name cannot be empty")
    @Size(min = 2, max = 100, message = "The name must be from 2 to 100 characters")
    private String name;

    @NotBlank(message = "The cpf cannot be empty")
    @Size(min = 14, max = 14, message = "The cpf [ 222.222.222-22 ] must have 14 characters")
    private String cpf;

    //@NotBlank(message = "The birthDate cannot be empty")
    //@Size(min = 10, max = 10, message = "The birthDate [ yyyy-MM-dd ] must have 10 characters")
    private LocalDate birthDate;

//    @NotBlank(message = "The phone cannot be empty")
//    @Size(min = 14, max = 14, message = "The phone [ (22)22222-2222 ] must have 11 numbers")
    private String phone;

   // @Email(message = "The email must be valid")
    private String email;

    //private Address address;

    private String country;
    private String street;
    private String district;
    private Integer number;
    private String complement;
    private String postalCode;
    private String city;
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
