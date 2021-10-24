package com.elderpereira.clientservice.request;

import com.elderpereira.clientservice.domain.Address;

public class ClientPutRequestBody {

    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private Address address;

    public ClientPutRequestBody(){

    }

    public ClientPutRequestBody(Long id, String name, String cpf, String phone, String email, Address address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
