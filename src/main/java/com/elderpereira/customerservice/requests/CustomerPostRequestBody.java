package com.elderpereira.customerservice.requests;

import com.elderpereira.customerservice.domain.Address;

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

    private Address address;

    public CustomerPostRequestBody(){

    }

    public CustomerPostRequestBody(String name, String cpf, String phone, LocalDate birthDate, String email, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ClientPostRequestBody{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
