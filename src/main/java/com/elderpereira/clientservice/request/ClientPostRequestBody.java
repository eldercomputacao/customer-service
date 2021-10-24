package com.elderpereira.clientservice.request;

import com.elderpereira.clientservice.domain.Address;

import java.time.LocalDate;

public class ClientPostRequestBody {

    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private Address address;

    public ClientPostRequestBody(){

    }

    public ClientPostRequestBody(String name, String cpf, String phone, LocalDate birthDate, String email, Address address) {
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
